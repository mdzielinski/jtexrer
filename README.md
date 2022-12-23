# Exchange Rates buffer by Mateusz Dzieliński

## how to start using calculator within test-app?

### prerequisites:

- `Java 17` (as development kit sdkman artifact was used: Java.net 17-open)
- `Maven 3.6.3`

### running steps

- clone application
- build with `mvn clean install`
    - running application as requested (
      prod): `mvn spring-boot:run -Dspring-boot.run.profiles=online,5pastmidnight,standardspread,h2InMemory`
    - testing application in offline test
      mode: `mvn spring-boot:run -Dspring-boot.run.profiles=offline,every1min,standardspread,h2`
- additional profiles:
    - `zerospread` - all currencies exchange has 0% spread strategy (to be used instead of `standardspread`).
    - `postgresInDocker` - connects to database in container, defined in [postgres_image.yaml](./src/main/docker/postgres_image.yaml)


### additional useful links

- access H2 console `http://localhost:8085/h2-console/` user:`sa`, password:`admin`
- access Swagger UI `http://localhost:8085/swagger-ui/index.html`

Have fun!
