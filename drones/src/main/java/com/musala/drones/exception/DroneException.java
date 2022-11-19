package com.musala.drones.exception;

public class DroneException extends Exception{
	private String errorMsg;

	public DroneException(String errorMsg) {
		super();
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
