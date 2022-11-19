package com.musala.drones.tasks;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.musala.drones.logger.DronesLogger;
import com.musala.drones.model.AuditModel;
import com.musala.drones.model.Drone;
import com.musala.drones.service.AuditService;
import com.musala.drones.service.DronesService;
import com.musala.drones.utils.ConvertJavaObjectToJsonUtil;

@Component
public class AuditTask {
	@Autowired
	DronesService  dronesService;
	@Autowired
	AuditService auditService;
	
	@Scheduled(fixedRate = 60000)
	public void batteryLevelAudit() {
		List<Drone> drones=dronesService.getAllDrones();
		if(drones.size()>0) {
			List<AuditModel> auditModels = new ArrayList<AuditModel>();
			AuditModel model ;
			for(Drone drone :drones) {
				 model = new AuditModel(drone.getSerialNumber(), drone.getBatteryCapacity());
				 auditModels.add(model);
			}
			DronesLogger.AUDIT_LOGGER.info("logging batch with size : "+auditModels.size());
			DronesLogger.AUDIT_LOGGER.info(ConvertJavaObjectToJsonUtil.convertObjectToJson(auditModels));
			auditService.logAudit(auditModels);
		}
	}
}
