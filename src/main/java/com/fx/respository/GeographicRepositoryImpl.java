package com.fx.respository;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.ExchangeFunctions;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fx.exception.CountryDataException;
import com.fx.vo.CountryDataVO;
import com.fx.vo.CountryVO;

import reactor.core.publisher.Mono;

public class GeographicRepositoryImpl implements GeographicRespository{

	private ExchangeFunction exchange = ExchangeFunctions.create(new ReactorClientHttpConnector());
	private final String rapidAPIKey = "cb9d056906msh202db15fb23cdf9p1e99c4jsn7dd51c460112";
	
	@Override
	public Mono<CountryDataVO> loadCountries() throws Exception {
		Mono<CountryDataVO> monoResponse = null;
		int startIndex = 0;
		int pageSize = 10;
		try {
			double n = Math.random() * 50;
			startIndex = (int) n;
			System.out.println(startIndex);
			monoResponse  = this.getCountryDataReactive(startIndex, pageSize);
		}catch(Exception e) {
			throw e;
		}
		
		return monoResponse;
	}

	@Override
	public Mono<CountryDataVO> loadCountries(int startIndex, int pageSize) throws Exception {
		Mono<CountryDataVO> monoData = null;
		try {
			monoData = getCountryDataReactive(startIndex, pageSize);
		}catch(Exception e) {
			throw e;
		}
		return monoData;
	}
	
	private Mono<CountryDataVO> getCountryDataReactive(int startIndex, int pageSize){
		StringBuffer url = new StringBuffer();		
		url.append("https://%s/countries?offset=")
				.append(startIndex)
				.append("&limit=")
				.append(pageSize);
		
		URI uri = URI.create(String.format(url.toString(), "wft-geo-db.p.rapidapi.com/v1/geo"));
		WebClient webClient = WebClient.create();
		
		
		return webClient.get()
				.uri(uri)
				.header("X-RapidAPI-Key", rapidAPIKey)
				.retrieve()
				.bodyToMono(CountryDataVO.class)
				.onErrorMap(CountryDataException::new);
	}

}
