package com.app.challenge.event.service;

import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;


@Service("rivalService")
public class EventServiceImpl implements EventService {
	
	
	@Override
	public Response registerNewUser(String token, String email) {
		
		return Response.ok("registered").build();
	}

	

}
