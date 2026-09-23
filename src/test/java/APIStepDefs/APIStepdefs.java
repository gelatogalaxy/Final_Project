package APIStepDefs;

import io.cucumber.java.Before;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class APIStepdefs {

    private static RequestSpecification requestSpec;
    private String endpointPath;
    private Response response;
    private static String newUserID;


    @Before
    public void setup(){
        // Set the base URL/headers globally
        RestAssured.baseURI = "https://dummyapi.io/data/v1/";
        requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON) // Sets "Content-Type: application/json"
                .addHeader("app-id", "63a804408eb0cb069b57e43a")
                .addHeader("Content-Type", "application/json")
                .build();
        }

    @Given("the endpoint path is {string}")
    public void theEndpointPathIs(String path){
        this.endpointPath = path;
    }

    @When("I submit POST request with this body:")
    public void ISubmitPOSTRequestWithThisBody(String docString){

        response = given()
                .spec(requestSpec)
                .body(docString)
                .post(endpointPath);

    }
    @Then("status code should be {int}")
    public void statusCodeShouldBe(Integer statusCode) {
        response.then().statusCode(statusCode);
    }
    @And("response field {string} should be {string}")
    public void responseFieldShouldBe(String field, String expectedValue) {
        response.then().body(field, equalTo(expectedValue));
    }
    @And("extract response field id into newUserID")
    public void extractIdIntoNewUserId() {
        newUserID = response.jsonPath().getString("id");
    }

    @Given("the endpoint path is {string} + newUserID")
    public void theEndpointPathIsNewUserID(String path1) {
        this.endpointPath = path1+newUserID;
    }

    @When("I submit GET request")
    public void iSubmitGETRequest() {
        response = given()
                .spec(requestSpec)
                .get(endpointPath);
    }

    @When("I submit PUT request with this body:")
    public void iSubmitPUTRequestWithThisBody(String docString) {

        response = given()
                .spec(requestSpec)
                .body(docString)
                .put(endpointPath);
    }


    @When("I submit DELETE request")
    public void iSubmitDELETERequest() {

        response = given()
                .spec(requestSpec)
                .delete(endpointPath);
    }

    @And("response field {string} should be newUserID")
    public void responseFieldShouldBeNewUserID(String field) {
        response.then().body(field, equalTo(newUserID));
    }

    @And("the response field {string} contains {string}")
    public void theResponseFieldContains(String field, String expectedValue) {
        response.then().body(field, hasItem(expectedValue));
    }
}

