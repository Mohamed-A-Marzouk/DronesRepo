package com.musala.drones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.musala.drones.dao.AuditDao;
import com.musala.drones.model.AuditModel;

@Service
public class AuditService {
	@Autowired
	AuditDao auditDao; 
	public  void logAudit(List<AuditModel> auditModels) {
		
		
		auditDao.logAudit(auditModels);
	

}
}
