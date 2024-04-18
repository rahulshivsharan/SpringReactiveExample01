package com.fx.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.fx.respository.MusicRepository;
import com.fx.respository.MusicRepositoryImpl;
import com.fx.vo.MusicDataVO;

import reactor.core.publisher.Mono;

public class MusicHandler {
	
	public Mono<ServerResponse> getSongs(ServerRequest request){
		Mono<ServerResponse> responseMono = null;
		Mono<MusicDataVO> musicData = null;
		MusicRepository repository = null;
		try {
			repository = new MusicRepositoryImpl();
			String trackName = request.pathVariable("trackName");
			String offset = request.pathVariable("offset");
			String limit = request.pathVariable("limit");
			musicData = 	repository.getSongs(trackName, offset, limit);
			responseMono = ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(musicData,MusicDataVO.class); 
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}	
		
		return responseMono; 
	}
}
