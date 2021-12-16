# microservices-limits-service
This source code contains the limit-service
which is a part of the microservices project

### Total Project Dependencies on this github account
* Checkout - https://github.com/zikozee/microservices-limits-service
* Checkout - https://github.com/zikozee/microservices-cloud-config-server
* Checkout - https://github.com/zikozee/microservices-currency-exchange-service


## Setup
### Requirements
* Should use Java 8 or higher. Previous versions of Java are un-tested.

### Support
For questions and help:
* contact me at ezekiel.eromosei@gmail.com

### NOTE
- curl http://localhost:8080/limits
- if we specify this spring.cloud.config.profile=dev and spring.cloud.config.name=limits-service
- it will override the spring.application.name and profile used 
- to see properties that will be called and from here browse: http://localhost:8888/{application_name}/profile


application_name ::: is same as spring application name    or spring.cloud.config.name if used