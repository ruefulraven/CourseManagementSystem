package com.cms.security;

import java.io.IOException;
import java.util.Map;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CMSFailureHandler implements AuthenticationFailureHandler {

	private ObjectMapper objectMapper = new ObjectMapper(); 
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		Map<String, Object> data =new HashMap<>();
		data.put("TimeStamp: ", Calendar.getInstance().getTime());
		data.put("Error: ", exception.getMessage());
		
		response.getOutputStream().println(objectMapper.writeValueAsString(data));
		
	}

}
