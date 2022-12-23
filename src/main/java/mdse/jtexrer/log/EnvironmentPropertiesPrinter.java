package mdse.jtexrer.log;

@Component
public class EnvironmentPropertiesPrinter {
    @Autowired
    private Environment env;

    @PostConstruct
    public void logApplicationProperties() {
        LOGGER.info("{}={}", "bael.property", env.getProperty("bael.property"));
        LOGGER.info("{}={}", "app.name", env.getProperty("app.name"));
        LOGGER.info("{}={}", "app.description", env.getProperty("app.description"));
    }
}