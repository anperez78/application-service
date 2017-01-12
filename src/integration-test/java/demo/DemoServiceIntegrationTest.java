package demo;

import org.junit.Test;
import static com.jayway.restassured.RestAssured.*;

public class DemoServiceIntegrationTest {

    @Test
    public void createApplication() throws Exception {

        CreateApplicationDTO newApp = new CreateApplicationDTO();
        newApp.setFullName("Antonio Perez");

        given().
            contentType("application/json").
            body(newApp).
        when().
            post("http://service-api.dev:8080/application").
        then().
            statusCode(200);
    }

    @Test
    public void getAllApplications() throws Exception {

        given().
            contentType("application/json").
        when().
            get("http://service-api.dev:8080/application").
        then().
            statusCode(200);
    }
}
