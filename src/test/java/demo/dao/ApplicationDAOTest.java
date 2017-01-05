package demo.dao;

import demo.model.Application;
import demo.utils.ApplicationDBH2;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ApplicationDAOTest {

    @ClassRule
    public static final ApplicationDBH2 applicationDB = new ApplicationDBH2();

    @Before
    public void setup() {
        applicationDB.resetApplications();
    }

    @Test
    public void insertApplication() {
        ApplicationDAO applicationDAO = applicationDB.getApplicationDAO();
        Long newId = applicationDAO.insert("John Smith");
        assertNotNull(newId);
    }

    @Test
    public void getApplications() {
        ApplicationDAO applicationDAO = applicationDB.getApplicationDAO();
        applicationDAO.insert("John Smith 1");
        applicationDAO.insert("John Smith 2");
        applicationDAO.insert("John Smith 3");

        List<Application> applications = applicationDAO.getAll();
        assertEquals(3, applications.size());
    }

}
