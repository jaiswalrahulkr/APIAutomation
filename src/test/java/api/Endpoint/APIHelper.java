package api.Endpoint;



import api.Payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;


/*
 * Created for User create , Get ,update , delete Operation
 * */
public class APIHelper {
    public static Response SendPostRequest(User payload) {
       Response response =  given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.post_url).then().extract().response();
        return response;
    }

    public static Response SendPutRequest(String username,User payload)
    {
        Response response=  given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username",username)
                .body(payload)
                .when()
                .put(Routes.put_url).then().extract().response();
        return response;
    }

    public static Response getRequest(String username)
    {
        Response response=  given()
                .pathParam("username",username)
                .when()
                .get(Routes.get_url).then().extract().response();
        return response;
    }

    public static Response deleteRequest(String username)
    {
        Response response=  given()
                .pathParam("username",username)
                .when()
                .delete(Routes.delete_url);
        return response;

    }
}
