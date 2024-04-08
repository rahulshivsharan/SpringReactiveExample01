package com.fx.respository;

import com.fx.vo.CountryDataVO;

import reactor.core.publisher.Mono;

public interface GeographicRespository {
	public Mono<CountryDataVO> loadCountries() throws Exception;
	public Mono<CountryDataVO> loadCountries(int startIndex, int pageSize) throws Exception;
}
