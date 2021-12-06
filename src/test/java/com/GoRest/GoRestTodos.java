package com.GoRest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GoRestTodos {

    String apiName = "https://gorest.co.in/public/v1/todos/";
    Response apiResponse;
    String headerName = "Authorization";
    String token = "Bearer 5b5cbaa487640c6849d12f5d8fff85cfd03d5b59286484fc6bc7328d27df84a8";
    int userId;

    @Given("Todos api is working and returning {int} status code")
    public void user_api_is_working_and_returning_status_code(int statusCode) {
        apiResponse = given().when().get(apiName);
        int responseCode = apiResponse.thenReturn().statusCode();
        assertEquals(responseCode,statusCode);
    }

    @When("User adds new todo with {int}, {string}, {string} and {string}")
    public void user_adds_new_todo_with_and(int userID, String title, String dueOn, String status) {
        JSONObject todo = new JSONObject();
        todo.put("user_id",userID);
        todo.put("title",title);
        todo.put("due_on",dueOn);
        todo.put("status", status);

        apiResponse = given().header(headerName,token).contentType(ContentType.JSON).and().body(todo).when().post(apiName);
        apiResponse.then().log().body();
        //Store the id for user that created
        //get and delete call
        assertEquals(201, apiResponse.statusCode());
        userId = apiResponse.jsonPath().getInt("data.id");
        System.out.println(userId);
    }
    @When("System creates new todo")
    public void system_creates_new_todo() {
        int statusCode = given().when().get(apiName+userId).statusCode();
        System.out.println("UserId: "+userId);
        assertEquals(200,statusCode);
    }

    @Then("System should clean up new todo data")
    public void system_should_clean_up_new_todo_data() {

        int deleteStat = given().header(headerName,token)
                .when().delete(apiName + userId).thenReturn().statusCode();
        assertEquals(204,deleteStat);
    }

    @Then("System should verify that test data todos is created successfully")
    public void system_should_verify_that_test_data_todos_is_created_successfully() {
        assertEquals(404, given().when().get(apiName + userId).statusCode());
    }
}
