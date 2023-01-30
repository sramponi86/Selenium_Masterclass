import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ApiTests {
    static FrameworkProperties properties;
    static String userId;

    @BeforeClass
    public static void prepareObjects() throws IOException {
        properties = new FrameworkProperties();
        testUserSignUp();
    }

    public static void testUserSignUp() throws IOException {
        Response response = null;
        String targetURI = Constants.BASE_URL + Constants.SIGN_UP_URL;

        JsonObject payload = new JsonObject();
        payload.addProperty(Constants.ID, properties.getPropValues(Constants.ID));
        payload.addProperty(Constants.USERNAME, properties.getPropValues(Constants.USERNAME));
        payload.addProperty(Constants.FIRST_NAME, properties.getPropValues(Constants.FIRST_NAME));
        payload.addProperty(Constants.LAST_NAME, properties.getPropValues(Constants.LAST_NAME));
        payload.addProperty(Constants.EMAIL, properties.getPropValues(Constants.EMAIL));
        payload.addProperty(Constants.COUNTRY_OF_RESIDENCE, properties.getPropValues(Constants.COUNTRY_OF_RESIDENCE));
        payload.addProperty(Constants.PHONE_NUMBER, properties.getPropValues(Constants.PHONE_NUMBER));
        payload.addProperty(Constants.ADDRESS, properties.getPropValues(Constants.ADDRESS));
        payload.addProperty(Constants.DESCRIPTION, properties.getPropValues(Constants.DESCRIPTION));
        payload.addProperty(Constants.PASSWORD, properties.getPropValues(Constants.PASSWORD));

        response = RestAssured.given()
                .relaxedHTTPSValidation()
                .header(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_VALUE)
                .body(payload)
                .when()
                .post(targetURI);

        assertEquals(Constants.SUCCESS_CODE, response.statusCode());
        JsonObject jsonResponse = response.as(JsonObject.class);
        userId = jsonResponse.get(Constants.ID).getAsString();
        System.out.println("UserID retrieved from the server: " + userId);
    }

    @Test
    public void testRetrieveAllUsers(){
        String targetURI = Constants.BASE_URL + Constants.RETRIEVE_URL;

        RestAssured.given()
                .relaxedHTTPSValidation()
                .header(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_VALUE)
                .when()
                .get(targetURI)
                .then()
                .statusCode(Constants.SUCCESS_CODE);
    }

    @Test
    public void testRetrieveSpecificUser(){
        String targetURI = Constants.BASE_URL + Constants.RETRIEVE_URL + Constants.SEPARATOR + userId;

        RestAssured.given()
                .relaxedHTTPSValidation()
                .header(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_VALUE)
                .when()
                .get(targetURI)
                .then()
                .statusCode(Constants.SUCCESS_CODE);
    }

    public static void deleteSpecificUser(){
        String targetURI = Constants.BASE_URL + Constants.RETRIEVE_URL + Constants.SEPARATOR + userId;

        RestAssured.given()
                .relaxedHTTPSValidation()
                .header(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_VALUE)
                .when()
                .delete(targetURI)
                .then()
                .statusCode(Constants.SUCCESS_CODE);
    }

    @AfterClass
    public static void cleanup(){
        deleteSpecificUser();
    }
}