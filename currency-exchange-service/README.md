- http://localhost:<GENERATED-PORT>/currency-exchange/from/EUR/to/INR


## Resilience4j
- https://resilience4j.readme.io/docs/getting-started-3
- dependency: resilience4j-spring-boot2 (io.github.resilience4j)
- additional dependencies: starter-actuator and starter-aop
- **note** for each api we can configure a specific behavior by giving it a specific name
- then that name is used in the yml file to set its properties

# Retries:
- @Retry(name="default") // 3 times
- for custom retry on a particular method, give the method a unique name,
- and set the number of retries in application.properties  **resilience4j.retry.instances.sample-api.max-attempts=5**
- note the method name defined in retry is **sample-api**
- we can configure interval: resilience4j.retry.instances.sample-api.wait-duration=5s
- we can also set enable-exponential-backoff which starts with our **wait-duration** and exponentially increase

# FallBack Response:
- we can have fallback response with the retry
- we must throw a form of exception that extends Exception or throwable and customize it to return the response we
- want

## Circuit-Breaker
- Used is the service is down for a long time
- it returns a default response preventing the failing api from taking more load
- cd to CLOUD FOLDER: **.\watch curl http://localhost:8765/currency-exchange/sample-api**
- **https://resilience4j.readme.io/docs/circuitbreaker**
- CLOSED: calling the dependent microservice continuously (application startup)
- OPEN: here only the fallback response is returned. hence, dependent microservice is not called (when it notices service is failing)
- HALF_OPEN: a percentage is sent to the microservice, while the other percentages, the fall back response is sent

## RateLimiting
- number of calls to be allowed per second
- it the limit is exceeded, we get an error

## BulkHead
- number of concurrent calls