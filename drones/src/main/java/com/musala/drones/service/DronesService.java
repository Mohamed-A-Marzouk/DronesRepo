package com.musala.drones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musala.drones.dao.DronesDao;
import com.musala.drones.defines.DroneState;
import com.musala.drones.defines.ResponseMessages;
import com.musala.drones.exception.DroneException;
import com.musala.drones.logger.DronesLogger;
import com.musala.drones.model.Drone;
import com.musala.drones.model.GeneralResponse;
import com.musala.drones.model.LoadingRequest;
import com.musala.drones.model.Medication;
import com.musala.drones.utils.ResponseUtil;

@Service
public class DronesService {

    @Autowired
    DronesDao dronesDao;

    public GeneralResponse addDrone(Drone drone) throws DroneException {
        DronesLogger.INFO_LOGGER.info("start addDrone in service");
        Drone existDrone = dronesDao.getDrone(drone.getSerialNumber());
        if (existDrone != null) {
            DronesLogger.INFO_LOGGER.info(String.format(ResponseMessages.DRONE_SERIAL_EXISTS, drone.getSerialNumber()));
            throw new DroneException(String.format(ResponseMessages.DRONE_SERIAL_EXISTS, drone.getSerialNumber()));
        }

        Boolean added = dronesDao.addDrone(drone);

        String message;
        if (added) {
            message = ResponseMessages.DRONE_ADDED;
        } else {
            message = ResponseMessages.DRONE_NOT_ADDED;
        }

        return ResponseUtil.buildResponse(message, null);

    }

    public GeneralResponse getAvilableDrones() {
        DronesLogger.INFO_LOGGER.info("start getAvilableDrones in service");

        List<Drone> avilableDrones = dronesDao.getAvailableDronesForLoading();

        return ResponseUtil.buildResponse(ResponseMessages.FETCH_AVILABLE_DRONE_SUCCESS, avilableDrones);
    }

    public GeneralResponse getDronebatteryLevel(String serial) throws DroneException {
        DronesLogger.INFO_LOGGER.info("start getDronebatteryLevel in service for dronr : " + serial);
        Drone existDrone = dronesDao.getDrone(serial);
        if (existDrone == null) {
            DronesLogger.INFO_LOGGER.info(String.format(ResponseMessages.DRONE_SERIAL_NOT_EXISTS, serial));
            throw new DroneException(String.format(ResponseMessages.DRONE_SERIAL_NOT_EXISTS, serial));
        }
        String batteryLevel = dronesDao.getBatteryLevelForDrone(serial);

        return ResponseUtil.buildResponse(ResponseMessages.FETCH_DRONE_BATTERY_LEVEL_SUCCESS, batteryLevel);
    }

    public List<Drone> getAllDrones() {
        DronesLogger.INFO_LOGGER.info("start getAllDrones in service ");
        return dronesDao.getAllDrones();
    }

    public GeneralResponse loadDrone(LoadingRequest request) throws DroneException {
        DronesLogger.INFO_LOGGER.info("Get Drone for loading");
        Drone drone = dronesDao.getDrone(request.getDroneSerial());
        if (drone == null) {
            DronesLogger.INFO_LOGGER.info(String.format(ResponseMessages.DRONE_SERIAL_NOT_EXISTS, request.getDroneSerial()));
            throw new DroneException(String.format(ResponseMessages.DRONE_SERIAL_NOT_EXISTS, request.getDroneSerial()));
        }
        if (drone.getState() != DroneState.IDLE.getValue()) {
            throw new DroneException("You can not load drone that in "+DroneState.getById(drone.getState())+" State");
        }
        if (drone.getBatteryCapacity() < 25) {
            throw new DroneException("You can not load drone when battery level is lower than 25");
        }
        DronesLogger.INFO_LOGGER.info("Get Medications for loading");
        List<Medication> medications = dronesDao.getMedicationsByCode(request.getMedicationCodes());
        int medicationsWeight = 0;
        for (Medication medication : medications) {
            medicationsWeight += medication.getWeight();
        }

        if (drone.getWeight() < medicationsWeight) {
            throw new DroneException("You can not load drone with over weight ");
        }
        DronesLogger.INFO_LOGGER.info("update Drone status to loading state");
        dronesDao.updateState(drone.getSerialNumber(), DroneState.LOADING.getValue());

        if (dronesDao.loadDrone(request)) {
            DronesLogger.INFO_LOGGER.info("update Drone status to loaded state");
            dronesDao.updateState(drone.getSerialNumber(), DroneState.LOADED.getValue());
            return ResponseUtil.buildResponse("Drone Loaded", null);
        } else {
            return ResponseUtil.buildResponse("Drone Not Loaded", null);
        }
    }

    public GeneralResponse getDroneLoad(String serial) throws DroneException {
        DronesLogger.INFO_LOGGER.info("start getDroneLoad in service for dronr : " + serial);
        Drone existDrone = dronesDao.getDrone(serial);
        if (existDrone == null) {
            DronesLogger.INFO_LOGGER.info(String.format(ResponseMessages.DRONE_SERIAL_NOT_EXISTS, serial));
            throw new DroneException(String.format(ResponseMessages.DRONE_SERIAL_NOT_EXISTS, serial));
        }
        List<String> loads = dronesDao.getDroneLoad(serial);
        return ResponseUtil.buildResponse(ResponseMessages.FETCH_LOADS_SUCCESS, loads);

    }

}
