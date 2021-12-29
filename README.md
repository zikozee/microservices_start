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


## KUBERNETES
==============
- deploying directly: kubectl create deployment hello-world-rest-api --image=in28min/hello-world-rest-api:0.0.1.RELEASE

- exposing the deployment as a LoadBalancer: kubectl expose deployment/hello-world-rest-api --type=LoadBalancer --port=8080
- instead of / we can use whitespace

- to see services deployed: services & ingress
- check events in a sorted order: **kubectl get events --sort-by=.metadata.creationTimestamp**

## PODS
=======
- kubectl explain pods

## REPLICASET (rs)
- ReplicaSet ensures that a specified number of pod replicas are running at any given time
- This guy maintains/monitors the number of pods
- try scaling with this: **kubectl scale deployment hello-world-rest-api --replicas=3**
- rs is always tied to a release version

## DEPLOYMENT
- changing deployment image
kubectl set image deployment hello-world-rest-api hello-world-rest-api=DUMMY_IMAGE:TEST
                                                    container_name
## SERVICES (svc)
- This ensures cusumers get a never changing url
- search **Load Balancing** to see Load Balancers created under networks