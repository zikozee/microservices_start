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

###DELETE ALL RELATED TO LABEL WITH APP NAME
- kubectl delete all -l app=<app_name>

### COPY K8s yaml to file
kubectl get deployment currency-exchange -o yaml
kubectl get svc currency-exchange -o yaml

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
- ROLLOUTS
- kubectl rollout history deployment <deployment_name>     :::::::::::: gives number of revision
- kubectl rollout undo deployment currency-exchange --to-revision=<revision_number_from_above>
- **by defaults, when switching to a new release, there is a bit of downtime**
- 
- ### LIVENESS AND READINESS PROBE
- readiness probe: health check if unsuccessful, don't send traffic
- liveness probe: health check if unsuccessful, restart pod
- 
- **spring-boot >=2.3** provides /actuator/health/readiness and /actuator/health/liveness from actuator
- explore /actuator,  /actuator/health, to see other options
- 
- also enabling 
> management:endpoint:health:probes:enabled: true
> management.health:livenessstate:enabled: true 
> management.health.readinessstate:enabled: true

- ### AUTOSCALING
- manual :: kubectl scale deployment currency-exchange --replicas=3
- manual :: change from deployment file
- 
- automatic :: kubectl autoscale deployment currency-exchange --min=1 --max=3 --cpu-percent=70 
                                           autoscale from    min_pod to  max_pod   based on load%  
- **kubectl get hpa**  :: check config
- production: use say 70%
- test: use 5% for testing
## SERVICES (svc)
- This ensures consumers get a never changing url
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
- kubectl rollout history deployment currency-conversion


## K8s deployment with yml
=====================
show tree: Ctrl + F12


## K8s Configuration
### setting env variable
-**CONTAINER ENV**
- we can do this directly on a container by using the env
>   name: base-currency-conversion-service
    env:
      - name: CURRENCY_EXCHANGE_URI
        value: http://currency-exchange

- **CONFIG MAP**
- kubectl create configmap currency-conversion --from-literal=CURRENCY_EXCHANGE_URI=http://currency-exchange


### Logs:
object:value in string collection
e.g
textPayload:83d1f4fe24c6e171







## CLEAN-UP
-disable clusters
-disable services : service & ingress
-disable all enabled stackdriver apis
-disable Cloud SQL Admin API