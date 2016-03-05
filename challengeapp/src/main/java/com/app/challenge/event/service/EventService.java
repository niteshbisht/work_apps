package com.app.challenge.event.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.app.challenge.event.vo.AppResponseVO;
import com.app.challenge.event.vo.ChallengeAppResponseVO;
import com.app.challenge.event.vo.UserAccountVO;

@Path("/rivalService")
public interface EventService {

	
	@GET
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Path("/registerNewUser")
	public ChallengeAppResponseVO<AppResponseVO> registerNewUser(String token, String email); 
	
	
	@POST
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Path("/registerNewDevice")
	ChallengeAppResponseVO<AppResponseVO> registerNewDevice(
			UserAccountVO userAccountVO);
}
