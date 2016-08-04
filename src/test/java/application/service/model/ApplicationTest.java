package application.service.model;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.junit.Assert.assertEquals;

public class ApplicationTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    private static final String NULL_ERROR_MESSAGE = "may not be null";
    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void serializesToJson() throws Exception {
        final Application application = createApplication();
        assertEquals(fixture("fixtures/application.json"), MAPPER.writeValueAsString(application));
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        final Application application = createApplication();
        assertEquals(application, MAPPER.readValue(fixture("fixtures/application.json"), Application.class));
    }

    @Test
    public void validateNotNull() throws Exception {
        Application application = new Application();

        Set<ConstraintViolation<Application>> constraintViolations =
                validator.validate(application);

        assertEquals(2, constraintViolations.size());
        assertEquals(NULL_ERROR_MESSAGE, constraintViolations.iterator().next().getMessage());
    }

    public static Application createApplication() {
        return new Application()
                .setApplicationId(new Long(10))
                .setFullName("John M. Smith");
    }
}
