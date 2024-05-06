package com.fx;

import  org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyApplicationTest{
    
	@LocalServerPort
    int port;
	
	@Autowired
	WebTestClient webTestClient;
	
	@Before
	public void before() {
		webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:" + port).build();
	}
	
	@Test
	public void sayHi() {
		webTestClient.get()
					.uri("/sayHi")
					.exchange()
					.expectStatus()
					.isOk()
					.expectBody(String.class)
					.consumeWith(result -> {
						String body = result.getResponseBody();
						Assert.assertEquals(body,"Hello Hi, How Are you");
					});
	}
}
