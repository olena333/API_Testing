package com.GoRest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GoRestPosts {

    String apiName = "https://gorest.co.in/public/v1/posts/";
    Response apiResponse;
    String headerName = "Authorization";
    String token = "Bearer deef60c87fe579169569b06a980e49f3144acfb08c4889747f6599f05d18e661";
    int id;

    @Given("Posts api is working and returning {int} status code")
    public void posts_api_is_working_and_returning_status_code(int statusCode) {
        int responseCode = given().when().get(apiName).thenReturn().statusCode();
        assertEquals(responseCode, statusCode);
    }

    @When("User wants to add new Posts with {int},{string} and {string}")
    public void user_wants_to_add_new_posts_with_and(Integer user_id, String title, String body) {
        JSONObject posts = new JSONObject();
        posts.put("user_id",user_id);
        posts.put("title",title);
        posts.put("body",body);

        apiResponse = given().header(headerName,token)
                .contentType(ContentType.JSON)
                .and().body(posts)
                .when().post(apiName);
        assertEquals( apiResponse.statusCode(),201);
        id = apiResponse.jsonPath().getInt("data.id");
    }

    @When("System can verify the new posts is add successfully")
    public void system_can_verify_the_new_posts_is_add_successfully() {
        assertEquals(200,given().when().get(apiName + id).statusCode());

    }

    @Then("System should cleanUp new posts data")
    public void system_should_clean_up_new_posts_data() {
        int deleteStat = given().header(headerName,token)
                .when().delete(apiName + id).thenReturn().statusCode();
        assertEquals(204,deleteStat);
    }

    @Then("System should verify that test data posts is removed successfully")
    public void system_should_verify_that_test_data_posts_is_removed_successfully() {
        assertEquals(404, given().when().get(apiName + id).statusCode());
    }

    @Then("User should wants to update the {string} in the user details")
    public void user_should_wants_to_update_the_in_the_user_details(String UpdatedTitle) {
        JSONObject updatedPosts = new JSONObject();
        updatedPosts.put("title",UpdatedTitle);
        apiResponse = given().header(headerName,token)
                .contentType(ContentType.JSON)
                .and().body(updatedPosts)
                .when().put(apiName + id);
        assertEquals(200,apiResponse.statusCode());
    }
}
