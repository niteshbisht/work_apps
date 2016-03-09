package org.gradle;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.app.challenge.domain.UserAccount;
import com.app.challenge.domain.UserToken;

public class Person {
    public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
    	UserAccount uac = new UserAccount();
    	UserToken utc = new UserToken();
    	uac.setUserToken(utc);
    	ObjectMapper om = new ObjectMapper();
    	System.out.println(om.writeValueAsString(uac));
	}
}
