package demo;

import demo.dao.ApplicationDAO;
import demo.resource.DemoServiceResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

public class DemoServiceApp extends Application<DemoServiceConfiguration>
{
    @Override
    public String getName() {
        return "demo-service";
    }

    @Override
    public void initialize(Bootstrap<DemoServiceConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<DemoServiceConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(DemoServiceConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
    }

    @Override
    public void run(DemoServiceConfiguration configuration, Environment environment) throws ClassNotFoundException {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");

        final ApplicationDAO applicationDAO = jdbi.onDemand(ApplicationDAO.class);
        final DemoServiceResource applicationServiceResource = new DemoServiceResource(applicationDAO);

        environment.jersey().register(applicationServiceResource);
    }

    public static void main( String[] args ) throws Exception
    {
        new DemoServiceApp().run(args);
    }
}
