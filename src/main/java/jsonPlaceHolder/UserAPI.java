package jsonPlaceHolder;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class UserAPI {

    //TODO
    //1.Call api
    //2.Send request
    //3.Get response with status code
    //4.Validate status code

    static String urlName = "https://jsonplaceholder.typicode.com";
    static String todosUrlName = "https://jsonplaceholder.typicode.com/todos";
    static String photosUrlName = "https://jsonplaceholder.typicode.com/photos";
    static Response response = given().when().get(photosUrlName);

    public static void main(String[] args) {
        //getAllUsers();
        //System.out.println(getStatusCode());
        //int[] ids = new int[]{2,4,6,8,9};
        //getUserByID(ids);
        //assertStatusCode();
        //getAlbums();
        //getPhotoOne();
        //getAllToDos();
    }

    public static void getAllUsers(){
        given()
                .when().get(urlName)
                .thenReturn()
                .body()
                .prettyPrint();
    }

    public static int getStatusCode(){
        int statusCode = given()
                .when().get(urlName)
                .thenReturn()
                .getStatusCode();
        return statusCode;
    }

    public static void getUserByID(int[] ids) {
        for (int each : ids) {
            given().when().get(urlName + "/" + each).thenReturn().prettyPeek();
        }
    }

    public static void assertStatusCode(){
        response.then().assertThat().statusCode(200);
    }

    public static void getAlbums(){
        response.thenReturn().prettyPeek();
    }

    public static void getPhotoOne(){
        given().when().get(photosUrlName+"/"+1).thenReturn().prettyPeek();
    }

    public static void getAllToDos(){
        given().when().get(todosUrlName).thenReturn().prettyPeek();
    }




}
