read me for drones task

prerequisite
-java 8 installed 
-clean and build project to download maven dependencies for spring 

run project

you can run it from ide as spring boot project 
 
or

run project as stand alone app on windows using generated jar in target path after bulding the project
1-you need to open cmd and cd to project path
2-run mvnw package
3-run cd target
4-run java -jar demo-0.0.1-SNAPSHOT.jar

you can find project logs in folder logs in project path
for audit and application logges
also you can find audit logges in database table battery_history 

test project 
you can use swagger using following link after runnin app

http://localhost:8080/swagger-ui/index.html#/

or using post man with requests samples that provided in this file

you have preloaded date to use

drone state

STATE_ID  	STATE_DESC  
1			IDLE
2			LOADING
3			LOADED
4			DELIVERING
5			DELIVERED
6			RETURNING

drone model

MODEL_ID  	MODEL_DESC  
1			Lightweight
2			Middleweight
3			Cruiserweight
4			Heavyweight

drones

SERIAL  	MODELIDFK  	WEIGHT  	BATTERY_CAPACITY  	STATEIDFK  
Drone1		1			100			80					3
Drone2		2			200			80					1
Drone3		1			100			20					1
Drone4		3			300			50					1

medications

NAME  			WEIGHT  	CODE  
medication1		50			medication1
medication2		80			medication2
medication3		100			medication3
medication4		20			medication4
medication5		200			medication5

drones_loads

DRONE_SERIAL  	MEDICATION_CODE  
Drone1			medication1

finally Battery history table for logging history in database



requests samples 

registerDrone request
 url
http://localhost:8080/drone/registerDrone

body
{
  "serialNumber": "Drone Name",
  "model": 1,
  "weight": 50,
  "batteryCapacity": 30,
  "state": 1
}

model,state must be one of the apove values that in drone model and drone state tables
batteryCapacity should be int number between 0 & 100

loadDrone request

url
http://localhost:8080/drone/loadDrone

body
{
  "droneSerial": "Drone4",
  "medicationCodes": [
    "medication4","medication4"
  ]
}

droneSerial must be one of registered drones in idel state
medicationCodes must be list of registered medications



getDroneLoad request

url
http://localhost:8080/drone/getDroneLoad?serial=Drone1

serial parameter should be one of registerd drones


getDronebatteryLevel request

url
http://localhost:8080/drone/getDronebatteryLevel?serial=Drone1

serial parameter should be one of registerd drones



avilableDrones request 

url
http://localhost:8080/drone/avilableDrones

no parameters needed
















