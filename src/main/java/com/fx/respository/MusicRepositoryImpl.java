package com.fx.respository;

import java.net.URI;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.fx.exception.MusicDataException;
import com.fx.vo.MusicDataVO;

import reactor.core.publisher.Mono;


public class MusicRepositoryImpl implements MusicRepository{

	private final String rapidAPIKey = "cb9d056906msh202db15fb23cdf9p1e99c4jsn7dd51c460112";
	
	@Override
	public Mono<MusicDataVO> getSongs(String trackName, String offset, String limit) throws Exception {
		Mono<MusicDataVO> monoData = getTrackInfo(trackName, offset, limit);
		return monoData;
	}

	private Mono<MusicDataVO> getTrackInfo(String searchTxt, String offset, String limit) throws Exception{
		StringBuffer urlStr = new StringBuffer();
		urlStr.append("https://%s/search")
				.append("?term=").append(searchTxt)
				.append("&locale=").append("en-US")
				.append("&offset=").append(offset)
				.append("&limit=").append(limit);
		
		URI uri =  URI.create(String.format(urlStr.toString(), "shazam.p.rapidapi.com"));
		WebClient webclient = WebClient.create();
		
		Mono<MusicDataVO> mono = 	webclient.get()
											.uri(uri)
											.header("X-RapidAPI-Key", this.rapidAPIKey)
											.header("X-RapidAPI-Host", "shazam.p.rapidapi.com")
											.retrieve()
											.bodyToMono(MusicDataVO.class)
											.onErrorMap(MusicDataException::new);
		return mono; 
	}
}
