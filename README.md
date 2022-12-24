```text
       _ __           
      (_) /____  _____
     / / __/ _ \/ ___/
    / / /_/  __/ /    
 __/ /\__/\___/_/     
/___/ JellyTech Exchange Rate Buffer

```

### Prerequisites:
- `Java 17` (as development kit sdkman artifact was used: Java.net 17-open).
- `Maven 3.6.3`.
- `Docker` and `Docker-compose` (to run Postgres database in container).

### Running steps
1. Clone application.
2. **Build** with `mvn clean install`.
3. Run with one of following profile groups:
    - **Run production profile group**: `mvn spring-boot:run -Dspring-boot.run.profiles=production`.
      - `online`
      - `cron5pastmidnight`
      - `standardspread`
      - `h2InFile`
    - **Run development profile group**: `mvn spring-boot:run -Dspring-boot.run.profiles=development`
      - `offline`
      - `cronevery1minute`
      - `standardspread`
      - `h2InFile`
#### Profiles description:
   - Database connection:
       - `h2InFile` - defines connection to H2 database (database will be created automatically, need no additional action)
       - `postgresInDocker` - defines connection to Postgres database in Docker container. Container must be lifted prior to application.
   - Spread strategy:
     - `standardspread` - standard spread strategy.
     - `zerospread` - 0% spread strategy (to be used instead of `standardspread`).
   - Http client definition:
     - `online` - enables http client fetching data form `fixer.io`.
     - `online` - enables http client fetching data form `fixer.io`.
   - Scheduling strategy:  
     - `cron5pastmidnight` - defines scheduling of data fetch once a day, 5 past midnight.
     - `standardspread` - spreads as defined by requirements.

### Useful links
- access H2 console `http://localhost:8085/h2-console/` user:`sa`, password:`admin`
- access Swagger UI `http://localhost:8085/swagger-ui/index.html`

Have fun!