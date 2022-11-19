
DROP TABLE IF EXISTS drone_model;
CREATE  TABLE drone_model (
  model_id INT NOT NULL AUTO_INCREMENT ,
  model_desc VARCHAR(45) NOT NULL ,
  PRIMARY KEY (model_id) );
  
 DROP TABLE IF EXISTS drone_state; 
  CREATE  TABLE drone_state (
  state_id INT NOT NULL AUTO_INCREMENT ,
  state_desc VARCHAR(45) NOT NULL ,
  PRIMARY KEY (state_id) );
  
  DROP TABLE IF EXISTS drones;
  CREATE  TABLE drones (
  serial VARCHAR(100) NOT NULL ,
  modelIDFK INT NOT NULL ,
  weight INT NOT NULL ,
  battery_capacity INT NOT NULL ,
  stateIDFK INT NOT NULL ,
  PRIMARY KEY (serial) ,
  CONSTRAINT droneStateIDFK
    FOREIGN KEY (stateIDFK )
    REFERENCES drone_state (state_id )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT droneModelIDFK
    FOREIGN KEY (modelIDFK )
    REFERENCES drone_model (model_id )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
    DROP TABLE IF EXISTS medications;
    CREATE  TABLE medications (
  name VARCHAR(100) NOT NULL ,
  weight INT NOT NULL ,
  code VARCHAR(100) NOT NULL ,
  PRIMARY KEY (code) );
  
  DROP TABLE IF EXISTS battery_history;
  CREATE  TABLE battery_history (
  drone_serial VARCHAR(100) NOT NULL ,
  battery_level INT NOT NULL,
  time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP );



CREATE TABLE drones_loads (
  drone_serial varchar(100) NOT NULL,
  medication_code varchar(100) NOT NULL,
  CONSTRAINT droneIDKF FOREIGN KEY (drone_serial) REFERENCES drones (serial) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT medicationIDFK FOREIGN KEY (medication_code) REFERENCES medications (code) ON DELETE NO ACTION ON UPDATE NO ACTION
) 


  

  
  
    