package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import utils.ApiConfig;

public class ApiUserSteps {

    // Share the API state across different Cucumber steps
    private RequestSpecification request;
    private Response response;
    private String endpoint;

    @Given("the API endpoint for user profile {string} is ready")
    public void the_api_endpoint_for_user_profile_is_ready(String userId) {
        // Pull in the base API config (like the base URL and headers)
        request = RestAssured.given().spec(ApiConfig.getBaseSpec());

        endpoint = "/users/" + userId;
    }

    @When("I send a GET request to the endpoint")
    public void i_send_a_get_request_to_the_endpoint() {
        // Send the GET request and save the output to check later
        response = request.when().get(endpoint);

        // Print the payload to the console for easy debugging
        System.out.println("API Response: " + response.getBody().asPrettyString());
    }

    @Then("the API response status code should be {int}")
    public void the_api_response_status_code_should_be(Integer expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(actualStatusCode, expectedStatusCode.intValue(), "Status code mismatch!");
    }

    @And("the response should contain the user's name {string}")
    public void the_response_should_contain_the_user_s_name(String expectedName) {
        // Parse the JSON body to extract the specific name field
        String actualName = response.jsonPath().getString("name");
        Assert.assertEquals(actualName, expectedName, "Name mismatch in database!");
    }
}