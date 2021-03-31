package com.example.demo;


import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.cloud.sleuth.CurrentTraceContext;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.SpanCustomizer;
import org.springframework.cloud.sleuth.TraceContext;
import org.springframework.cloud.sleuth.http.HttpServerHandler;
import org.springframework.cloud.sleuth.http.HttpServerResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestFilter implements Filter {
	final ServletRuntime servlet = ServletRuntime.get();
	
	final CurrentTraceContext currentTraceContext;

	final HttpServerHandler handler;
	
	TestFilter(CurrentTraceContext currentTraceContext, HttpServerHandler httpServerHandler) {
		this.currentTraceContext = currentTraceContext;
		this.handler = httpServerHandler;
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("init CORSFilter");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		log.info(req.getHeader("X-B3-TRACEID"));
		
		Span span = handler.handleReceive(new HttpServletRequestWrapper(req));

		// Add attributes for explicit access to customization or span context
		request.setAttribute(SpanCustomizer.class.getName(), span);
		request.setAttribute(TraceContext.class.getName(), req.getHeader("X-B3-TRACEID"));
		SendHandled sendHandled = new SendHandled();
		request.setAttribute(SendHandled.class.getName(), sendHandled);

		Throwable error = null;
		CurrentTraceContext.Scope scope = currentTraceContext.newScope(span.context());
		try {
			// any downstream code can see Tracer.currentSpan() or use
			// Tracer.currentSpanCustomizer()
			chain.doFilter(req, res);
		}
		catch (Throwable e) {
			error = e;
			throw e;
		}
		finally {
			// When async, even if we caught an exception, we don't have the final
			// response: defer
			if (servlet.isAsync(req)) {
				servlet.handleAsync(handler, req, res, span);
			}
			else if (sendHandled.compareAndSet(false, true)) {
				// we have a synchronous response or error: finish the span
				HttpServerResponse responseWrapper = HttpServletResponseWrapper.create(req, res, error);
				handler.handleSend(responseWrapper, span);
			}
			scope.close();
		}
	}
	
	@Override
	public void destroy() {
		log.info("destroy CORSFilter");
	}
	
	static final class SendHandled extends AtomicBoolean {

	}
	
}