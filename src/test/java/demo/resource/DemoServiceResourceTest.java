package demo.resource;

import demo.dao.ApplicationDAO;
import demo.dto.CreateApplicationDTO;
import demo.model.Application;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DemoServiceResourceTest {

    private static final ApplicationDAO mockedApplicationDAO = mock(ApplicationDAO.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new DemoServiceResource(mockedApplicationDAO))
            .build();

    @Test
    public void getAll() throws Exception {

        List<Application> applications = new ArrayList<>();
        applications.add(new Application().setApplicationId(new Long(1)).setFullName("person1"));
        applications.add(new Application().setApplicationId(new Long (2)).setFullName("person2"));
        when(mockedApplicationDAO.getAll()).thenReturn(applications);

        List<Application> result = resources.client()
                .target("/application")
                .request()
                .get(new GenericType<List<Application>>() {
                });

        assertEquals(2, result.size());
        assertEquals("person1", result.get(0).getFullName());
    }

    @Test
    public void addValidApplication() throws Exception {

        Long expectedApplicationId = new Long(999);
        CreateApplicationDTO createApplicationDTO = new CreateApplicationDTO();
        createApplicationDTO.setFullName("John Smith");
        when(mockedApplicationDAO.insert(anyString())).thenReturn(expectedApplicationId);

        Integer applicationId = resources.client()
            .target("/application")
            .request(MediaType.APPLICATION_JSON)
            .post(Entity.json(createApplicationDTO), Integer.class);

        assertEquals((long)expectedApplicationId, (long)applicationId);
        verify(mockedApplicationDAO, times(1)).insert(anyString());
    }

    @Test
    public void addInvalidApplication() throws Exception {

        CreateApplicationDTO createApplicationDTO = new CreateApplicationDTO();
        createApplicationDTO.setFullName(null);

        try {
            resources.client()
                .target("/application")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(createApplicationDTO), Integer.class);
            fail("Should have thrown validation error");
        } catch (ClientErrorException ex) {
            assertEquals(422, ex.getResponse().getStatus());
        }
    }
}
