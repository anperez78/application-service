package application.service;

import application.service.resource.ApplicationServiceResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;
import application.service.dao.ApplicationDAO;

public class App extends Application<AppConfiguration>
{
    @Override
    public String getName() {
        return "application-service";
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
    }

    @Override
    public void run(AppConfiguration configuration, Environment environment) throws ClassNotFoundException {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");

        final ApplicationDAO applicationDAO = jdbi.onDemand(ApplicationDAO.class);
        final ApplicationServiceResource applicationServiceResource = new ApplicationServiceResource(applicationDAO);

        environment.jersey().register(applicationServiceResource);
    }

    public static void main( String[] args ) throws Exception
    {
        new App().run(args);
    }
}
