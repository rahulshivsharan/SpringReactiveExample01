package com.fx.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

public class FunAppHandler {
	
	public Mono<ServerResponse> doGetFunction(ServerRequest request){
		String responseText = "Hello World, My first Route";
		Mono<ServerResponse> responseMono = ServerResponse.ok().syncBody(responseText); 
		return responseMono;
	}
	
	public Mono<ServerResponse> doPostFunction(ServerRequest request){
		String messageString = request.pathVariable("msg");
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("message", messageString);
		
		Mono<Map<String, String>> monoData = Mono.justOrEmpty(dataMap);
		
		Mono<ServerResponse> responseMono = ServerResponse.ok()
														.contentType(MediaType.APPLICATION_JSON)
														.body(monoData, Map.class); 
		return responseMono;
	}
}
