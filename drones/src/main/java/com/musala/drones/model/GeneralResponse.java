package com.musala.drones.model;

public class GeneralResponse {
	private String message;
	private Object payload;
	
	public GeneralResponse() {
		super();
	}
	public GeneralResponse(String message, Object payload) {
		super();
		this.message = message;
		this.payload = payload;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getPayload() {
		return payload;
	}
	public void setPayload(Object payload) {
		this.payload = payload;
	}
	

}
