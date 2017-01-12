package demo;

import org.junit.BeforeClass;
import org.junit.Test;
import static com.jayway.restassured.RestAssured.*;

public class DemoServiceIntegrationTest {


    private static String underTestBaseURL = "http://service-api.dev:8080";

    @BeforeClass
    public static void initializeVariables() {
        String intTestsBaseUrl = System.getenv("INT_TESTS_BASE_URL");
        if (intTestsBaseUrl != null) {
            underTestBaseURL = intTestsBaseUrl;
        }
    }


    @Test
    public void createApplication() throws Exception {

        CreateApplicationDTO newApp = new CreateApplicationDTO();
        newApp.setFullName("Antonio Perez");

        given().
            contentType("application/json").
            body(newApp).
        when().
            post(underTestBaseURL + "/application").
        then().
            statusCode(200);
    }

    @Test
    public void getAllApplications() throws Exception {

        given().
            contentType("application/json").
        when().
            get(underTestBaseURL + "/application").
        then().
            statusCode(200);
    }
}
