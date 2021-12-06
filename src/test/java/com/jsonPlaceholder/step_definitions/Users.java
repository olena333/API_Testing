package com.jsonPlaceholder.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Users {

    String urlName = "https://jsonplaceholder.typicode.com/users";
    Response apiResponse;

    @Given("System calls user api with valid url")
    public void system_calls_user_api_with_valid_url() {
        apiResponse = given().when().get(urlName);
    }

    @When("User api return response with status code {int}")
    public void user_api_return_response_with_status_code(Integer int1) {
        int apiResponseCode = apiResponse.thenReturn().statusCode();
    }

    @Then("System should validate api response with list of users")
    public void system_should_validate_api_response_with_list_of_users() {
        apiResponse.then().body("id[0]", is(1));
    }
}
