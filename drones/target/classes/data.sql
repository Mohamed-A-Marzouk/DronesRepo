

INSERT INTO drone_model (model_desc) VALUES ('Lightweight'),
 ('Middleweight'), ('Cruiserweight'),
 ('Heavyweight');


INSERT INTO drone_state (state_desc) VALUES ('IDLE'),
 ('LOADING'),
 ('LOADED'),
 ('DELIVERING'),
 ('DELIVERED'),
 ('RETURNING');
 
 INSERT INTO drones (serial,modelIDFK,weight,battery_capacity, stateIDFK) VALUES ('Drone1', '1', '100', '80', '3'),
('Drone2', '2', '200', '80', '1'),
('Drone3', '1', '100', '20', '1'),
('Drone4', '3', '300', '50', '1');

 
 INSERT INTO medications (name, weight, code) VALUES ('medication1', '50', 'medication1'),
 ('medication2', '80', 'medication2'),
 ('medication3', '100', 'medication3'),
 ('medication4', '20', 'medication4'),
 ('medication5', '200', 'medication5');

 INSERT INTO drones_loads(drone_serial,medication_code)
VALUES
('Drone1','medication1');

 