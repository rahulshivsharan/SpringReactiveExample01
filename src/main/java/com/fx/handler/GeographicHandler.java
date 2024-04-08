package com.fx.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.fx.respository.GeographicRepositoryImpl;
import com.fx.respository.GeographicRespository;
import com.fx.vo.CountryDataVO;

import reactor.core.publisher.Mono;

public class GeographicHandler {
	
	public Mono<ServerResponse> doGetCountries(ServerRequest request){
		GeographicRespository repo = null;		
		Mono<CountryDataVO> monoData = null;
		Mono<ServerResponse> responseMono = null;
		try {
			repo = new GeographicRepositoryImpl();
			monoData = repo.loadCountries();
			
			responseMono =   ServerResponse.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(monoData, CountryDataVO.class);
			
			return responseMono;
		}catch(Exception e) {
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("error", e.getMessage());
			Mono<Map<String, String>> monoErrorData = Mono.justOrEmpty(dataMap);
			
			Mono<ServerResponse> responseErrorMono = ServerResponse.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(monoErrorData, Map.class); 
			
			return responseErrorMono;
		}
	}
	
	public Mono<ServerResponse> doGetSomeCountries(ServerRequest request){
		GeographicRespository repo = null;
		CountryDataVO data = null;
		Mono<CountryDataVO> monoData = null;
		Mono<ServerResponse> responseMono = null;
		try {
			repo = new GeographicRepositoryImpl();
			String startIndex = request.pathVariable("startIndex");
			String pageSize = request.pathVariable("pageIndex");
			int startIdx = Integer.parseInt(startIndex);
			int pageLimit = Integer.parseInt(pageSize);
			
			monoData = repo.loadCountries(startIdx, pageLimit);
			
			responseMono =   ServerResponse.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(monoData, CountryDataVO.class);
			
			return responseMono;
		}catch(Exception e) {
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("error", e.getMessage());
			Mono<Map<String, String>> monoErrorData = Mono.justOrEmpty(dataMap);
			
			Mono<ServerResponse> responseErrorMono = ServerResponse.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(monoErrorData, Map.class); 
			
			return responseErrorMono;
		}
	}
}
