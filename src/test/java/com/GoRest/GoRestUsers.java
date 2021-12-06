package com.GoRest;

import io.cucumber.createmeta.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.internal.com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.json.simple.JSONObject;


import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.junit.Assert.assertEquals;

public class GoRestUsers {


    String apiName = "https://gorest.co.in/public/v1/users/";
    Response apiResponse;
    String headerName = "Authorization";
    String token = "Bearer 5b5cbaa487640c6849d12f5d8fff85cfd03d5b59286484fc6bc7328d27df84a8";
    int id;

    @Given("User api is working and returning {int} status code")
    public void user_api_is_working_and_returning_status_code(int statusCode) {
        apiResponse = given().when().get(apiName);
        int responseCode = apiResponse.thenReturn().statusCode();
        assertEquals(responseCode,statusCode);
    }

    @When("User adds new user with {string}, {string},{string} and {string}")
    public void user_adds_new_user_with_and(String name, String email, String gender, String status) {
        JSONObject user = new JSONObject();
        user.put("name",name);
        user.put("email",email);
        user.put("gender",gender);
        user.put("status",status);

        apiResponse = given().header(headerName,token).contentType(ContentType.JSON).and().body(user).when().post(apiName);
        apiResponse.then().log().body();
        //Store the id for user that created
        //get and delete call
        assertEquals(201,apiResponse.statusCode());
        id = apiResponse.jsonPath().getInt("data.id");
        System.out.println(id);
    }

    @When("System creates new user")
    public void system_creates_new_user() {
         assertEquals(200,given().when().get(apiName+id).statusCode());
    }

    @Then("System should clean up new user data")
    public void system_should_clean_up_new_user_data() {
        int deleteStat = given().header(headerName,token)
                .when().delete(apiName + id).thenReturn().statusCode();
                assertEquals(204,deleteStat);
    }

    @Then("System should verify that test data user is removed successfully")
    public void system_should_verify_that_test_data_user_is_removed_successfully() {
        assertEquals(404, given().when().get(apiName + id).statusCode());

    }


    @When("User wants to add new user with {string}, {string}, {string} and {string}")
    public void user_wants_to_add_new_user_with_and(String string, String string2, String string3, String string4) {

    }

    @Then("User wants to update the {string} in user details")
    public void user_wants_to_update_the_in_user_details(String updatedEmail) {
        JSONObject updatedUser = new JSONObject();
        updatedUser.put("email", updatedEmail);
        apiResponse = given().header(headerName, token)
                .contentType(ContentType.JSON)
                .and().body(updatedUser)
                .when().put(apiName + id);

        assertEquals(200, apiResponse.statusCode());
    }

    @Then("System can verify the new user is added succesfully")
    public void system_can_verify_the_new_user_is_added_succesfully() {

    }
}
