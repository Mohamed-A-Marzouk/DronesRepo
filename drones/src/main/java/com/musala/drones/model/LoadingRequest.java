package com.musala.drones.model;

import java.util.List;

public class LoadingRequest {

	private String droneSerial;
	private List<String> medicationCodes;
	public String getDroneSerial() {
		return droneSerial;
	}
	public void setDroneSerial(String droneSerial) {
		this.droneSerial = droneSerial;
	}
	public List<String> getMedicationCodes() {
		return medicationCodes;
	}
	public void setMedicationCodes(List<String> medicationCodes) {
		this.medicationCodes = medicationCodes;
	}

	
}
