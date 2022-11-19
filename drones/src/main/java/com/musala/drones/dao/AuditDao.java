package com.musala.drones.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.musala.drones.model.AuditModel;
@Repository
public class AuditDao {
	@Autowired
	JdbcTemplate jdbcTemplate; 
	public void logAudit(List<AuditModel> auditModels) {
		String query = "INSERT INTO battery_history "
				+ "(drone_serial,battery_level) VALUES (?,?) ";
		
		try (PreparedStatement ps = DataSourceUtils.getConnection(jdbcTemplate.getDataSource()).prepareStatement(query)) {
            for (int i = 0; i < auditModels.size(); i++) {

                ps.setString(1, auditModels.get(i).getDroneSerial());
                ps.setInt(2, auditModels.get(i).getBatteryLevel());
                ps.addBatch();
            }
            ps.executeBatch();

        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
			
				
		
	}

}
