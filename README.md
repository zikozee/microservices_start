# microservices_start




- netstat -ano | findstr "8761"
- taskkill /f /pid 27144

## Docker config for spring-boot
> <configuration>
    <!--DOCKER CONFIG-->
    <image>
    <name>zikozee/${project.artifactId}:${project.version}</name>
    </image>
    <pullPolicy>IF_NOT_PRESENT</pullPolicy>
</configuration>

- IF_NOT_PRESENT: if the image is not present locally, then pull
- mvn clean spring-boot:build-image -DskipTests


## Overriding a property in application.yml
### since we localhost is not same in docker

- we can override the **eureka.client.serviceUrl.defaultZone** property in docker-compose
- pointing to: **http://naming-server:8761/eureka**
- this way we ensure 