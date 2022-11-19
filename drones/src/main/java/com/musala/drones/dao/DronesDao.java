package com.musala.drones.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.musala.drones.logger.DronesLogger;
import com.musala.drones.model.AuditModel;
import com.musala.drones.model.Drone;
import com.musala.drones.model.LoadingRequest;
import com.musala.drones.model.Medication;
import com.musala.drones.utils.ConvertJavaObjectToJsonUtil;

@Repository
public class DronesDao {

	@Autowired
	JdbcTemplate jdbcTemplate; 
	
	private RowMapper<Drone> mapper = (ResultSet rs, int rowNum) -> {
		Drone drone = new Drone();
		
		drone.setSerialNumber(rs.getString(1));
		drone.setModel(rs.getInt(2));
		drone.setWeight(rs.getInt(3));
		drone.setBatteryCapacity(rs.getInt(4));
		drone.setState(rs.getInt(5));
		
		return drone;
		
	};
	private RowMapper<Medication> medicationsMapper = (ResultSet rs, int rowNum) -> {
		Medication medication = new Medication();
		
		medication.setName(rs.getString(1));
		medication.setWeight(rs.getInt(2));
		medication.setCode(rs.getString(3));
				
		return medication;
		
	};
	
	public boolean addDrone(Drone drone) {
		DronesLogger.INFO_LOGGER.info("start addDrone in Dao");
		if(jdbcTemplate.update("insert into drones (serial,modelIDFK,weight,battery_capacity,stateIDFK) values (?,?,?,?,?)",
				drone.getSerialNumber(),drone.getModel(),drone.getWeight(),drone.getBatteryCapacity(),drone.getState())>0) {
			return true;
		}else {
			return false ;
		}
	}
	public Drone getDrone(String serial){
		DronesLogger.INFO_LOGGER.info("start getDrone in Dao for drone : "+serial);
		 String query = "select * from drones " 
	                + " where serial  = '"+serial+"'";

		 List<Drone> drons= jdbcTemplate.query(query, mapper);
		 if(drons != null &&  drons.size()>0) {
			 return drons.get(0);
		 }
		return null;
	}

	public List<Drone> getAvailableDronesForLoading(){
		DronesLogger.INFO_LOGGER.info("start getAvailableDronesForLoading in Dao");
		String query = "SELECT * FROM drones " 
                + " WHERE  stateIDFK = 1 AND battery_capacity >= 25";
		 return  jdbcTemplate.query(query, mapper);
		
	}
	
	public String getBatteryLevelForDrone(String serial){
		DronesLogger.INFO_LOGGER.info("start getBatteryLevelForDrone in Dao for drone : "+serial);
		 String query = "select * from drones " 
	                + " where serial  = '"+serial+"'";

		 return jdbcTemplate.query(query, mapper).get(0).getBatteryCapacity()+"%";
		
	}
	
	public List<Drone> getAllDrones(){
		DronesLogger.INFO_LOGGER.info("start getAllDrones in Dao ");
		String query = "SELECT * FROM drones " ;
		 return  jdbcTemplate.query(query, mapper);
	}

	public boolean loadDrone(LoadingRequest request) {		
		DronesLogger.INFO_LOGGER.info("start loadDrone in Dao");
		int[] result = null;
		String query = "INSERT INTO drones_loads "
				+ "(drone_serial,medication_code) VALUES (?,?) ";
		try (PreparedStatement ps = DataSourceUtils.getConnection(jdbcTemplate.getDataSource()).prepareStatement(query)) {
            for (int i = 0; i < request.getMedicationCodes().size(); i++) {

                ps.setString(1, request.getDroneSerial());
                ps.setString(2, request.getMedicationCodes().get(i));
                ps.addBatch();
            }
           result =  ps.executeBatch();

        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
		if(result == null || result.length == 0) {
			return false;
		}else {
			return true;
		}
	}

	public List<String> getDroneLoad(String serial) {
		DronesLogger.INFO_LOGGER.info("start getDroneLoad in Dao for drone : "+serial);
		String query = "select * from drones_loads " 
                + " where drone_serial  = '"+serial+"'";
		
		return jdbcTemplate.query(query, (rs) -> {
			List<String> medications = new ArrayList<>();
            while (rs.next()) {
            	medications.add(rs.getString(2));
            }
            return medications;
        });
	}
	
	public List<Medication> getMedicationsByCode(List<String> medicationCodes){
		DronesLogger.INFO_LOGGER.info("start getMedicationsByCode in Dao for codes : "+ConvertJavaObjectToJsonUtil.convertObjectToJson(medicationCodes));
		String query = "SELECT * FROM medications " 
                + " WHERE  code IN (%s)";
		String inSql = String.join(",", Collections.nCopies(medicationCodes.size(), "?"));
		 return  jdbcTemplate.query(String.format(query, inSql), medicationsMapper,medicationCodes.toArray());
		
	}
	
	 public int updateState(String serial, int state) {
		 DronesLogger.INFO_LOGGER.info("start updateState in Dao for drone : "+serial +" to be in state :"+state);
		 String query = "UPDATE drones SET stateIDFK = ? "
	                + " WHERE serial  = ?";
		 return jdbcTemplate.update(query,state,serial);
	 }


}
