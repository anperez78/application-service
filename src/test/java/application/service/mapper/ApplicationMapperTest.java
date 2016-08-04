package application.service.mapper;

import application.service.model.Application;
import org.junit.Test;

import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ApplicationMapperTest {

    private static ApplicationMapper applicationMapper = new ApplicationMapper();
    private static ResultSet mockedResultSet = mock(ResultSet.class);

    @Test
    public void validMap() throws Exception {

        Long expectedApplicationId = new Long(1);
        String expectedFullName = "John Smith";

        when(mockedResultSet.getLong("applicationid")).thenReturn(expectedApplicationId);
        when(mockedResultSet.getString("fullname")).thenReturn(expectedFullName);

        Application application = applicationMapper.map(0, mockedResultSet, null);

        assertEquals(expectedApplicationId, application.getApplicationId());
        assertEquals(expectedFullName, application.getFullName());
    }
}
