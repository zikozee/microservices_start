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

DELETE ALL RELATED TO LABEL WITH APP NAME
- kubectl delete all -l app=<app_name>

## PODS
=======
- kubectl explain pods
- kubectl get pods  [--watch]

## REPLICASET (rs)
- kubectl get rs  [--watch]
- ReplicaSet ensures that a specified number of pod replicas are running at any given time
- This guy maintains/monitors the number of pods
- try scaling with this: **kubectl scale deployment hello-world-rest-api --replicas=3**
- rs is always tied to a release version

## DEPLOYMENT
- kubectl get deployment [--watch]
- changing deployment image
kubectl set image deployment hello-world-rest-api hello-world-rest-api=DUMMY_IMAGE:TEST
                                                    container_name
## SERVICES (svc)
- This ensures cusumers get a never changing url
- search **Load Balancing** to see Load Balancers created under networks


## K8s deployment without yml
=====================


### Currency exchange
-kubectl create deployment currency-exchange  --image=zikozee/base-currency-conversion-service:0.0.11-SNAPSHOT
-kubectl expose deployment currency-exchange --type=LoadBalancer --port=8100

- **Service created by k8s (Not Recommended)**
- This is based on the application name we used
- CURRENCY_EXCHANGE_SERVICE_HOST
- **Create Custom Env (Recommended)**
- HOSTNAME  : podname, just like docker=== container name
- **delete all**
- kubectl delete all -l app=<currency-exchange>



### Currency conversion 
kubectl create deployment currency-conversion  --image=zikozee/base-currency-conversion-service:0.0.11-SNAPSHOT
kubectl expose deployment currency-conversion --type=LoadBalancer --port=8100

- **Service created by k8s (Not Recommended)**
- This is based on the application name we used
- CURRENCY_CONVERSION_SERVICE_HOST
- **Create Custom Env (Recommended)**
- TEST LOADBALANCING and seee pod name change
- ./watch curl http://35.232.209.247:8100/currency-conversion-feign/from/USD/to/INR/quantity/15
- **delete all**
- kubectl delete all -l app=<currency-conversion>


## K8s deployment with yml
=====================
show tree: Ctrl + F12








## CLEAN-UP
-disable clusters
-disable services : service & ingress
-disable all enabled stackdriver apis
-disable Cloud SQL Admin API