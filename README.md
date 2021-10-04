# Conversion API

## Overview
This repository contains APIs for basic conversion. 

## Guidelines
### API is supporting following Conversions

* <b>Mass:</b>
  * from/to - kilogram or pound
  * {{X}}   - value to be convert
  * e.g. /convert/v1/mass/kilogram/pound/{{X}}
* <b>Temperature:</b>
  * from/to - fahrenheit or celsius
  * {{X}}   - value to be convert
  * /convert/v1/temperature/celsius/fahrenheit/{{X}}
* <b>Discount:</b>
  * {{X}}   - Original value
  * {{X}}   - Discount Percentage
  * /convert/v1/discount/{{X}}/{{Y}}
* <b>Digital Storage:</b>
  * from/to - gb or mb
  * {{X}}   - value to be convert
  * /convert/v1/digitalstorage/gb/mb/{{X}}
* <b>Time:</b>
  * from/to - sec or min
  * {{X}}   - value to be convert
  * /convert/v1/time/sec/min/{{X}}

<i><u>Note: API returns Json on success. Refer json property "result" for outcome.</i></u>  
### Prerequisite
* Maven: 3.6.3
* Java : 8 or above
* PostMan: 9.0.5

Use following steps for running the application
1. Clone Repository
   ```
   git clone git@github.com:sandeepm1987/Conversion.git
   ```
2. Build application
   ````
   goto root directory (/Conversion/)
   mvn clean install
   ````
3. Run application
   ````
   java -jar .\target\conversion-0.0.1-SNAPSHOT.jar
   ````
4. Use Postman/Browser for verification
   ````
   http://localhost:8080/actuator/health (This should return status "UP")
   ````
### Use Postman
1. Download following json
   1. Conversion.postman_collection.json
   2. DEV.postman_environment.json
2. Open Postman
3. Goto collections
4. Click import button
5. Select Folder where both this file is situated
6. click on import
7. Select Conversion from left collection panel
8. Click on Run button

### Code Review
* Rest controllers have been used for crating endpoints
* Controller calls factory for calling respective services
* Service calls helper class for evaluating conversion
* For time being I have used EnumMap for storing all static conversions (This can be replaced by DB or REST endpoints)


## Features
### Designed API with following features 
1. Spring Boot
2. <i>Junit5</i> for unit and integration testing
3. Spring Loggers
4. Sleuth (This is very useful for tracking complete request in logs)
5. Swagger (Basic implementation)
6. Actuators (For health check and Metrics information)
7. Postman (For verifying end points. It also verifies test cases written in postman)

## Future Scope
### Following can be implemented in future
1. Swagger (should be improvised)
2. Security (Secure API with OAUTH2)
3. Use React/Angular for testing APIs
4. BDD
5. Database (Populate static data (Conversion) from DB)

## Support
<b>Please enter an issue in the repo for any questions or problems.</b>
