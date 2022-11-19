package com.musala.drones.defines;

public class ResponseMessages {
	public static final String SERIAL_LENGTH_VALIDATOR = "You can not register drone with serial length greater than 100 char";
	public static final String DRONE_WEIGHT_VALIDATOR = "You can not register drone with Weight greater than 500gr";
	public static final String INVALIED_BATTERY_CAPACITY ="Invalid Battery Capacity"; 
        public static final String DRONE_ADDED = "Drone Added";
	public static final String DRONE_NOT_ADDED = "Drone Not Added";
	public static final String DRONE_SERIAL_EXISTS = "Drone serial %s already exist";
        public static final String DRONE_SERIAL_NOT_EXISTS = "Drone serial %s Not exist";
        public static final String FETCH_AVILABLE_DRONE_SUCCESS = "Avilable drones fetched successfuly";
        public static final String FETCH_DRONE_BATTERY_LEVEL_SUCCESS = "Drone Battery Level fetched successfuly";
	public static final String FETCH_LOADS_SUCCESS = "Loads Fetched Successfuly";
        public static final String INVALIED_INPUT ="Invalid input"; 

}
