# Jelly Tech Exchange Rates buffer by Mateusz Dzieliński

## how to start using calculator within test-app?

### prerequisits:
 - `Java 17` (as developemnt kit sdkman artifact was used: Java.net 17-open)           
 - `Maven 3.6.3`

### steps
 - clone application
 - build with `mvn clean install`
   - running application: `mvn spring-boot:run -Dspring-boot.run.profiles=default` 
   - testing application: `mvn spring-boot:run -Dspring-boot.run.profiles=dummy`
 - access H2 console `http://localhost:8085/h2-console/` user:`sa`, password:`admin`
 - access 

 