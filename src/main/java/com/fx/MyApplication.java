package com.fx;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.fx.handler.FunAppHandler;
import com.fx.handler.GeographicHandler;
import com.fx.handler.MusicHandler;
import com.fx.respository.MusicRepositoryImpl;

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
