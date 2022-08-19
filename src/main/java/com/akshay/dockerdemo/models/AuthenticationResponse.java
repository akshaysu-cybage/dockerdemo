package com.akshay.dockerdemo.models;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable{

	private String jwt;

	public AuthenticationResponse(String jwt) {
		super();
		this.jwt = jwt;
	}
	
	public AuthenticationResponse() {
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	
	
}
