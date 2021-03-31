package com.example.demo;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

//@Component
//public class PlayerHandler {
//    public Mono<ServerResponse> getName(ServerRequest request) {
//        Mono<String> name = Mono.just(request.pathVariable("name"));
//        return ServerResponse.ok().body(name, String.class);
//    }
//    
////    public Mono<ServerResponse> getBoard(ServerRequest request) {
////        long boardId = Long.valueOf(request.pathVariable("id"));
////        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
////        Mono<Board> boardMono = boardRepository.findOne(boardId);
////        Mono<BoardDto> boardDtoMono = boardMono.map(boardDtoConverter::convert);
////        return boardDtoMono.flatMap(boardDto -> ServerResponse.ok()
////                                                             .contentType(MediaType.APPLICATION_JSON)
////                                                             .body(fromObject(boardDto))
////                                                             .switchIfEmpty(notFound));
////    }
//}