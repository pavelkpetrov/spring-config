#Spring Demo to show configuration service usage with profiles and properties refresh

Eureka server url : http://localhost:8761/
Config server status check url for  config-client service :  http://localhost:8888/config-client/info
Config server status check url for  config-client service (TEST profile) :  http://localhost:8888/config-client-TEST/info

Config-client service http://localhost:8080/message to check the current properties values.
Config-client service (TEST profile) http://localhost:8081/message to check the current properties values.
Config-client service refresh URL to refrech changeable properies: http://localhost:8080/actuator/refresh
Config-client service refresh URL to refrech changeable properies (TEST profile): http://localhost:8081/actuator/refresh

How to test:
Change path to config/config-client.yml in the configuration-service\src\main\resources\bootstrap.yml 
run Eureka service         
   - mvn spring-boot:run -pl eureka-service
run Config service            
   - mvn spring-boot:run -pl configuration-service
run Config client service     
   -  mvn spring-boot:run -pl configuration-client
run Config client service with the TEST profile: 

   - mvn spring-boot:run -pl configuration-client -Dspring.profiles.active=TEST -Dserver.port=8081

In the browser request url: http://localhost:8888/config-client/info

Result:{"name":"config-client","profiles":["info"],"label":null,"version":null,"state":null,"propertySources":[{"name":"file:///c:/Users/pavelp/Documents/config/config-client.yml","source":{"test.message":"Hello config initial","changeable.property":"Changeable property initial"}}]}

In the browser request url: http://localhost:8080/message

Result : Application :config-client | current unchangeable property test.message=Hello config initial | current changeable value of propertyConfiguration.property=Changeable property initial

Change in the config/config-client.yml prolerty:
 "Hello config initial" to "Hello config changed"
 "Changeable property initial" to "Changeable property changed"

In the browser request url: http://localhost:8888/config-client/info

Result:{"name":"config-client","profiles":["info"],"label":null,"version":null,"state":null,"propertySources":[{"name":"file:///c:/Users/pavelp/Documents/config/config-client.yml","source":{"test.message":"Hello config changed","changeable.property":"Changeable property changed"}}]}

In the postman execute URL POST: http://localhost:8080/actuator/refresh

In the browser request url: http://localhost:8080/message

Result : Application :config-client | current unchangeable property test.message=Hello config initial | current changeable value of propertyConfiguration.property=Changeable property changed

 