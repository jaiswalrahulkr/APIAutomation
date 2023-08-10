package api.Endpoint;



//GET https://petstore.swagger.io/v2//user/{username}
///  Post user

//PUT /user/{username}
// Delete /user/{username}
public class Routes {

    //User Module
    public static String base_url="https://petstore.swagger.io/v2";
    public static String get_url=base_url+"/user/{username}";
    public static String put_url=base_url+"/user/{username}";
    public static String delete_url=base_url+"/user/{username}";
    public static String post_url=base_url+"/user";
}
