package com.musala.drones.utils;

import com.musala.drones.model.GeneralResponse;

public class ResponseUtil {
	
	public static GeneralResponse buildResponse(Object... params) {
		GeneralResponse generalResponse = new GeneralResponse();
		generalResponse.setMessage(params[0].toString());
		generalResponse.setPayload(params[1]);				
		return generalResponse;
		
	}

}
