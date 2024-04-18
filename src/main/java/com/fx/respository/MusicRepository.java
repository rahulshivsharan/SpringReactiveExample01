package com.fx.respository;

import com.fx.vo.MusicDataVO;

import reactor.core.publisher.Mono;

public interface MusicRepository {
	
	public Mono<MusicDataVO> getSongs(String trackName, String offset, String limit) throws Exception;
}
