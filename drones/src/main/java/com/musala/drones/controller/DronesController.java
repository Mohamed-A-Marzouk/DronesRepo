package com.musala.drones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.musala.drones.defines.ApiEndPoints;
import com.musala.drones.defines.ResponseMessages;
import com.musala.drones.exception.DroneException;
import com.musala.drones.logger.DronesLogger;
import com.musala.drones.model.Drone;
import com.musala.drones.model.GeneralResponse;
import com.musala.drones.model.LoadingRequest;
import com.musala.drones.service.DronesService;
import com.musala.drones.validator.RequestsValidator;

@RestController
@RequestMapping(ApiEndPoints.DRONE)
public class DronesController {

    @Autowired
    DronesService dronesService;

    @PostMapping(ApiEndPoints.REGISTER_DRONE)
    public GeneralResponse addDrone(@RequestBody Drone drone) throws DroneException {
        DronesLogger.INFO_LOGGER.info("Start registerDrone");
        RequestsValidator.validateDrone(drone);

        return dronesService.addDrone(drone);

    }

    @GetMapping(ApiEndPoints.AVILABLE_DRONE)
    public GeneralResponse getAvilableDrones() {
        DronesLogger.INFO_LOGGER.info("Start getAvilableDrones");
        return dronesService.getAvilableDrones();
    }

    @GetMapping(ApiEndPoints.DRONE_BATTERY_LEVEL)
    public GeneralResponse getDronebatteryLevel(@RequestParam String serial) throws DroneException {
        DronesLogger.INFO_LOGGER.info("Start getDronebatteryLevel for drone :" + serial);
        return dronesService.getDronebatteryLevel(serial);
    }

    @PostMapping(ApiEndPoints.LOAD_DRONE)
    public GeneralResponse loadDrone(@RequestBody LoadingRequest request) throws DroneException {
        DronesLogger.INFO_LOGGER.info("Start loadDrone");
        RequestsValidator.validateLoadingRequest(request);
        return dronesService.loadDrone(request);

    }

    @GetMapping(ApiEndPoints.GET_DRONE_LOAD)
    public GeneralResponse getDroneLoad(@RequestParam String serial) throws DroneException {
        DronesLogger.INFO_LOGGER.info("Start getDroneLoad");
        return dronesService.getDroneLoad(serial);
    }

}
