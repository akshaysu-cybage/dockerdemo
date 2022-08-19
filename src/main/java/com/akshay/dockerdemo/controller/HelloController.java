package com.akshay.dockerdemo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akshay.dockerdemo.models.AuthenticationRequest;
import com.akshay.dockerdemo.models.AuthenticationResponse;
import com.akshay.dockerdemo.service.MyUserDetailsService;
import com.akshay.dockerdemo.util.JwtUtil;



@RestController
public class HelloController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@GetMapping("/hello")
	public String getHello() throws ClientProtocolException, IOException {
		
		//getTechnologies("techstack");
		return "hello from spring boot..will build an image";
	}
	
	@PostMapping("/authenticate")
	public AuthenticationResponse authenticateUserDetails(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		}catch(BadCredentialsException e) {
			throw new Exception("incorrect username or password");
		}
		
		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwtToken = jwtTokenUtil.generateToken(userDetails);
		
		return new AuthenticationResponse(jwtToken);
	}
	
	
	@GetMapping("/gettechnologies")
	public String getTechnologies(@RequestParam(name = "techstack") String techstack) throws ClientProtocolException, IOException{
		List<String> ll = new ArrayList<String>();
		
		HttpClient client = new DefaultHttpClient();
		  HttpGet request = new HttpGet("http://techstackdemoC:8084/techstack/" + techstack);
		  HttpResponse response = client.execute(request);
//		  BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
//		  String line = "";
//		  while ((line = rd.readLine()) != null) {
//		    //System.out.println(line);
//			  ll.add(line);
//		  }
		  
		  HttpEntity resp = response.getEntity();
		  String json = EntityUtils.toString(resp);
		return json;
	}
}
