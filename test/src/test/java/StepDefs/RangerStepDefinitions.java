package StepDefs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;

import java.util.Map;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java8.En;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.StringUtils;
import cucumber.api.java8.En.*;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.text.StringContainsInOrder;

/**
 * Created by frunn on 08/04/2017.
 */
public class RangerStepDefinitions implements En {

    private Response response;
    private RequestSpecification request;
    private ValidatableResponse json;
    private String BASEURL = "http://10.47.54.248:8080/ranger";

    @Given("ranger service is up and running")
    public void ranger_service_up() {
        request = given().auth().none();
    }

    @When("a user retrieves List of location")
    public void a_user_retrieves_list_of_locations() {
        response = request.when().get(BASEURL + "/locations");
        System.out.println("response: " + response.prettyPrint());
    }


    @When("a user creates a location called \"(.*)\"")
    public void a_user_creates_location_namned(String locationName) {
        response = request.when().queryParam("name", locationName).put(BASEURL + "/location");
        System.out.println("response: " + response.prettyPrint());
    }

    @Then("the status code is (\\d+)")
    public void verify_status_code(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @And("location \"(.*)\" is created")
    public void response_include_location(String locationName) {
        response = request.get(BASEURL + "/locations");
        response.then().body("name",hasItems(locationName));
        String responseBody = response.getBody().asString();
        System.out.println(responseBody + " " + responseBody.length());
    }

    @After("@CleanUpCreateLocation")
    public void do_this_after_scenarioX() {
        request.queryParam("location", "orvar").delete(BASEURL + "/location");

    }
}

