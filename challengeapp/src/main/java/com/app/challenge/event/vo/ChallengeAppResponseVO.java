package com.app.challenge.event.vo;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;
import org.springframework.web.bind.annotation.ResponseBody;

@JacksonStdImpl
public class ChallengeAppResponseVO<T> {
	
	private T object;
	private boolean error;
	private String errorMessage;

	/**
	 * @param object
	 */
	
	public ChallengeAppResponseVO(T object) {
		super();
		this.object = object;
	}
	
	public ChallengeAppResponseVO(boolean error,String errorMessage) {
		super();
		this.error =error;
		this.errorMessage =errorMessage;
	}
	
	
	
	
}
