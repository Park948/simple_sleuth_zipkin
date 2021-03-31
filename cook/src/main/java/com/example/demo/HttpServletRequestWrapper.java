package com.example.demo;

import java.util.Collection;
import java.util.Collections;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.sleuth.http.HttpServerRequest;
import org.springframework.lang.Nullable;

class HttpServletRequestWrapper implements HttpServerRequest {

	/** @since 5.10 */
	public static HttpServerRequest create(HttpServletRequest request) {
		return new HttpServletRequestWrapper(request);
	}

	HttpServletRequest delegate;

	HttpServletRequestWrapper(HttpServletRequest delegate) {
		if (delegate == null) {
			throw new NullPointerException("delegate == null");
		}
		this.delegate = delegate;
	}

	@Override
	public Collection<String> headerNames() {
		return Collections.list(this.delegate.getHeaderNames());
	}

	@Override
	public Object unwrap() {
		return delegate;
	}

	@Override
	public String method() {
		return delegate.getMethod();
	}

	@Override
	public String route() {
		Object maybeRoute = delegate.getAttribute("http.route");
		return maybeRoute instanceof String ? (String) maybeRoute : null;
	}

	@Override
	public String path() {
		return delegate.getRequestURI();
	}

	// not as some implementations may be able to do this more efficiently
	@Override
	public String url() {
		StringBuffer url = delegate.getRequestURL();
		if (delegate.getQueryString() != null && !delegate.getQueryString().isEmpty()) {
			url.append('?').append(delegate.getQueryString());
		}
		return url.toString();
	}

	@Override
	public String header(String name) {
		return delegate.getHeader(name);
	}

	/** Looks for a valid request attribute "error". */
	@Nullable
	Throwable maybeError() {
		Object maybeError = delegate.getAttribute("error");
		if (maybeError instanceof Throwable) {
			return (Throwable) maybeError;
		}
		maybeError = delegate.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
		if (maybeError instanceof Throwable) {
			return (Throwable) maybeError;
		}
		return null;
	}

}