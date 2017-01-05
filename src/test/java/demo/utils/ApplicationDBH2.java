package demo.utils;

import demo.dao.ApplicationDAO;
import com.codahale.metrics.MetricRegistry;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.junit.rules.ExternalResource;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

public class ApplicationDBH2 extends ExternalResource {

    private DBI dbi;
    private Handle handle;

    public ApplicationDAO getApplicationDAO() {
        return dbi.open(ApplicationDAO.class);
    }

    public void resetApplications() {
        Handle resetHandle = dbi.open();
        resetHandle.execute("delete from applications");
        resetHandle.close();
    }

    @Override
    protected void before() throws Throwable {
        Environment environment = new Environment("test-env", Jackson.newObjectMapper(), null, new MetricRegistry(), null);
        dbi = new DBIFactory().build(environment, getDataSourceFactory(), "test");
        handle = dbi.open();
        handle.execute("create table applications(applicationid serial primary key, fullname varchar(100) not null);");
    }

    @Override
    protected void after() {
        handle.execute("drop all objects");
        handle.close();
    }

    private DataSourceFactory getDataSourceFactory() {
        DataSourceFactory dataSourceFactory = new DataSourceFactory();
        dataSourceFactory.setDriverClass("org.h2.Driver");
        dataSourceFactory.setUrl("jdbc:h2:./build/h2db");
        dataSourceFactory.setUser("sa");
        dataSourceFactory.setPassword("sa");
        return dataSourceFactory;
    }
}
