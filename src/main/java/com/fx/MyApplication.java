package com.fx;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.fx.handler.FunAppHandler;
import com.fx.handler.GeographicHandler;
import com.fx.handler.MusicHandler;
import com.fx.respository.MusicRepositoryImpl;
import com.fx.vo.StudentDataVO;
import com.fx.vo.StudentVO;

import reactor.core.publisher.Mono;

@SpringBootApplication
public class MyApplication {
    
	public static void main( String[] args ){
		try {
			SpringApplication myApplication = new SpringApplication(MyApplication.class);
			myApplication.setDefaultProperties(Collections.singletonMap("server.port", "8089"));
			myApplication.run(args);			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
    }
	
	@Bean
	public RouterFunction<ServerResponse> routes() {
		return RouterFunctions.route()
								.GET("/sayHi",(req) ->{
										return ServerResponse.ok().syncBody("Hello Hi, How Are you");
								})
								.POST("/repeatMe",(req) ->{
									return ServerResponse.ok().body(req.bodyToMono(String.class), String.class);
								})
								.POST("/message",(req) ->{
									return ServerResponse.ok().body(req.bodyToMono(Map.class), Map.class);
								}).POST("/filter/list",(req) ->{
									
									Mono<StudentDataVO> studentDataMono = req.bodyToMono(StudentDataVO.class);
									
									Mono<StudentDataVO> filteredDataMono = studentDataMono.map((data) -> {										
										List<StudentVO> studentList = data.getList();
										List<StudentVO> filteredList = studentList.stream().filter(studentVO -> {
											return studentVO.getStudentId() > 10;
										}).collect(Collectors.toList()); 
										return new StudentDataVO(filteredList);
									});
									
									return ServerResponse.ok().body(filteredDataMono, StudentDataVO.class);
								}).build();
	}
	
	@Bean
	public RouterFunction<ServerResponse> funAppRoutes() {
		FunAppHandler applicationRoutes = new FunAppHandler(); 
		return RouterFunctions.route()
							.GET("/funApp", applicationRoutes::doGetFunction)
							.POST("/funApp/echo/{msg}", applicationRoutes::doPostFunction)
							.build();
	}
	
	
	@Bean
	public RouterFunction<ServerResponse> georaphicApplicationRoutes() {
		GeographicHandler applicationRoutes = new GeographicHandler(); 
		return RouterFunctions.route()
							.GET("/geo/countries", applicationRoutes::doGetCountries)
							.GET("/geo/countries/{startIndex}/{pageIndex}", applicationRoutes::doGetSomeCountries)							
							.build();
	}
	
	@Bean
	public RouterFunction<ServerResponse> MusicApplicationRoutes() {
		MusicHandler applicationRoutes = new MusicHandler(); 
		return RouterFunctions.route()							
							.GET("/music/songs/{trackName}/{offset}/{limit}", applicationRoutes::getSongs)							
							.build();
	}
	
	
}
