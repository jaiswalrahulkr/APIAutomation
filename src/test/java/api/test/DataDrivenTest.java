package api.test;

import api.Endpoint.APIHelper;
import api.Payload.User;
import api.utilities.Dataproviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DataDrivenTest {


    @Test(priority = 1,dataProvider = "Data", dataProviderClass = Dataproviders.class)
    public void testPostMethod(String Username, String FirstName,String password,String Email) {

        User userPayload = new User();
        userPayload.setUsername(Username);
        userPayload.setFirstname(FirstName);
        userPayload.setPassword(password);
        userPayload.setEmail(Email);

        Response response = APIHelper.SendPostRequest(userPayload);
        Assert.assertEquals(response.getStatusCode(), 200);


    }
}
