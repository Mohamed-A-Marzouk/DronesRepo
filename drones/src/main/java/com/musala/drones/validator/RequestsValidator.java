package com.musala.drones.validator;

import com.musala.drones.defines.ResponseMessages;
import com.musala.drones.exception.DroneException;
import com.musala.drones.logger.DronesLogger;
import com.musala.drones.model.Drone;
import com.musala.drones.model.LoadingRequest;

public class RequestsValidator {

    public static void validateDrone(Drone drone) throws DroneException {
        DronesLogger.INFO_LOGGER.info("Start validateDrone");
        if (drone.getSerialNumber().length() > 100) {
            DronesLogger.error("serial length greater than 100 char");
            throw new DroneException(ResponseMessages.SERIAL_LENGTH_VALIDATOR);
        }
        if (drone.getWeight() > 500) {
            DronesLogger.error("drone weight is greater than 500gr");
            throw new DroneException(ResponseMessages.DRONE_WEIGHT_VALIDATOR);
        }
        if (drone.getBatteryCapacity() > 100 || drone.getBatteryCapacity() <0 ) {
            DronesLogger.error("Invalid battery cabacity");
            throw new DroneException(ResponseMessages.INVALIED_BATTERY_CAPACITY);
        }
        DronesLogger.INFO_LOGGER.info("End validateDrone");

    }

    public static void validateLoadingRequest(LoadingRequest request) throws DroneException {
        DronesLogger.INFO_LOGGER.info("Start validateLoadingRequest");
        if(request.getDroneSerial() == null){
            DronesLogger.error("null serial number");
            throw new DroneException(ResponseMessages.INVALIED_INPUT);
        }
        if(request.getMedicationCodes() == null || request.getMedicationCodes().isEmpty()){
            DronesLogger.error("empty medication codes");
            throw new DroneException(ResponseMessages.INVALIED_INPUT);
        }
        
        DronesLogger.INFO_LOGGER.info("End validateLoadingRequest");
       
    }
}
