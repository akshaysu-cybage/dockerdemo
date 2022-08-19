package com.akshay.dockerdemo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import com.akshay.dockerdemo.models.AuthenticationRequest;

class HelloControllerTest {
	
	public HelloController helloController;
	
	@BeforeEach
	public void beforeAll() {
		helloController = spy(HelloController.class);
		
	}

	@Test
	void testGetHello() throws ClientProtocolException, IOException {
		//fail("Not yet implemented");
		String expected = "hello from spring boot..will build an image";
		String helloStrActual = helloController.getHello();
		//System.out.println("aaaaaaaaaaaaaaaa" + helloStr);
		assertNotNull(helloStrActual);
		
	}
	
	@Test
	void testGetHelloCompare() throws ClientProtocolException, IOException {
		//fail("Not yet implemented");
		String expected = "hello from spring boot..will build an image";
		String helloStrActual = helloController.getHello();
		//System.out.println("aaaaaaaaaaaaaaaa" + helloStr);
		assertEquals(expected, helloStrActual);
	}
	
//	@Test
//	public void testAuthenticate() {
//		HelloController helloMock = mock(HelloController.class);
//		AuthenticationRequest req = mock(AuthenticationRequest.c)
//		AuthenticationManager auth = mock(AuthenticationManager.class);
//		
//	}

}
