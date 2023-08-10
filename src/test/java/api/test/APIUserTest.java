package api.test;

import api.Endpoint.APIHelper;
import api.Payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;



public class PostUserTest {

    private Logger logger;
    Faker faker;
    User userPayload;
    String username;

    @BeforeClass
    public void setup() {
        userPayload = new User();
        faker = new Faker();
        username = faker.name().username();
        logger= LoggerFactory.getLogger(PostUserTest.class);

    }

    @Test(priority = 1)
    public void testPostUser() {
        logger.info("Execute the POst request");

        userPayload.setUsername(username);
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhoneNumber(faker.phoneNumber().cellPhone());

        Response response = APIHelper.SendPostRequest(userPayload);
        Assert.assertEquals(response.getStatusCode(), 200);
        Object typevalue = response.then().extract().response().getBody().path("type");
        System.out.println(userPayload.getUsername());

    }

    @Test(priority = 2)
    public void testGetUser() {

        Response response = APIHelper.getRequest(userPayload.getUsername());
        Assert.assertEquals(response.prettyPeek().statusCode(), 200);

        Map<String, Object> userresponse = response.path("");
        Assert.assertEquals(userresponse.get("username"), userPayload.getUsername());
        System.out.println(userresponse.get("email"));
    }


    @Test(priority = 3)
    public void testUpdateUser() {
        userPayload.setFirstname(faker.name().firstName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        System.out.println(userPayload.getEmail());
        Response putresponse = APIHelper.SendPutRequest(this.userPayload.getUsername(), this.userPayload);


        Assert.assertEquals(putresponse.prettyPeek().statusCode(), 200);


        Response getResponse = APIHelper.getRequest(this.userPayload.getUsername());
        System.out.println(getResponse.prettyPeek());
        Map<String, Object> userresponse = getResponse.path("");
        Assert.assertEquals(userresponse.get("email"), userPayload.getEmail());
    }


}
