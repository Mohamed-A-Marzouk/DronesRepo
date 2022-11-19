package com.musala.drones.model;

public class AuditModel {
	private String droneSerial;
	private int batteryLevel;
	public AuditModel(String droneSerial, int batteryLevel) {
		this.droneSerial = droneSerial;
		this.batteryLevel = batteryLevel;
	}
	public String getDroneSerial() {
		return droneSerial;
	}
	public void setDroneSerial(String droneSerial) {
		this.droneSerial = droneSerial;
	}
	public int getBatteryLevel() {
		return batteryLevel;
	}
	public void setBatteryLevel(int batteryLevel) {
		this.batteryLevel = batteryLevel;
	}
	

}
