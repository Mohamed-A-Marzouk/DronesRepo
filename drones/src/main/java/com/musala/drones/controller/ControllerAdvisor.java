package com.musala.drones.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.musala.drones.exception.DroneException;
import com.musala.drones.model.GeneralResponse;

@RestControllerAdvice
public class ControllerAdvisor   {

    @ExceptionHandler(DroneException.class)
    public GeneralResponse catchExceptions(DroneException exception){
    	
        return new GeneralResponse(exception.getErrorMsg(), null);
    }
    
    @ExceptionHandler(Exception.class)
    public GeneralResponse catchExceptions(Exception exception){
        return new GeneralResponse(exception.getMessage(), null);
    }
    
}

