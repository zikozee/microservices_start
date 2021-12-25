## ensures apis registered wth eureka can be called from gateway
- spring.cloud.gateway.discovery.locator.enabled=true
- e.g http://localhost:8765/CURRENCY-EXCHANGE/currency-exchange/from/USD/to/INR
- to make it lowercased we use : spring.cloud.gateway.discovery.locator.lower-case-service-id=true
- hence - e.g http://localhost:8765/CURRENCY-EXCHANGE/currency-exchange/from/USD/to/INR
  http://localhost:8765/currency-conversion/currency-conversion-feign/from/USD/to/INR/quantity/10


## EXPLORING ROUTES
- we define a bean of RouteLocator, with a RouteLocatorBuilder as param
- we return a route which is of type Function<PredicateSpec, Buildable<Route>  
- we use a lambda for simplicity
- we define path the route is going using .path("/PUT_PATH_HERE")  // This is called path-matching
- we can match **header p.header, order, ZoneDateTime(request after this time in a zone), remoteAddr**
- we can add some headers using filters(f -> f.addRequestHeader("KEY", "VALUE")


## Alternative for reaching Apis
- DISABLE THIS **spring.cloud.gateway.discovery.locator.enabled=true** and **spring.cloud.gateway.discovery.locator.lower-case-service-id=true**
- based on the config route, we can do this
- http://localhost:8765/currency-conversion-feign/from/USD/to/INR/quantity/10, http://localhost:8765/currency-exchange/from/USD/to/INR
- in the route config, u can change the uri to be accessed
- see sample in redirection
- we use filters(f -> f.rewritePath(oldPath, newPath))
- we are expecting **fetch-money** and rerouting to currency-conversion-feign on eureka
- sample : **http://localhost:8765/fetch-money/from/USD/to/INR/quantity/10**
- NOTE: path must contain the valid endpoint uri we wanna access except we use rewrite such as the fetch-money above

## Adding Global Filters
- that is anything we do here is inherited by every filter
- see samplein logging -> LoggingFilter