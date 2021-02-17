package stepDefinations;



import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;
import testDataBuilder.ReqSpecBuilder;
import testDataBuilder.Resources;
import testDataBuilder.TestDataBuilder;

@RunWith(Cucumber.class)
public class stepDefination extends ReqSpecBuilder {
	
	RequestSpecification req1;
	Response res1;
	String res2;
	TestDataBuilder td = new TestDataBuilder();
	static String placeID;
	@Given("AddPlace payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		//res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		req1 = given().spec(getRequestSpecObject()).body(td.getddPlaceBuilderObject(name,language,address));
    }

	@When("^user calls \"([^\"]*)\" with \"([^\"]*)\" http request$")
    public void user_calls_something_with_something_http_request(String resource, String method) throws Throwable {
		Resources resourceValue = Resources.valueOf(resource);
		//resourceValue.getResource();
		if(method.equalsIgnoreCase("POST"))
			res1 = req1.when().post(resourceValue.getResource());
		else if(method.equalsIgnoreCase("GET"))
			res1 = req1.when().get(resourceValue.getResource());
		else if(method.equalsIgnoreCase("DELETE"))
			res1 = req1.when().post(resourceValue.getResource());
    }

    @Then("^the api call got success result with status code 200$")
    public void the_api_call_got_success_result_with_status_code_200() throws Throwable {
        res2 = res1.then().extract().response().asString();
        System.out.println(res2);
    }

    @And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
    public void something_in_response_body_is_something(String key, String value) throws Throwable {
    	if(getJsonPath(res2,key).equalsIgnoreCase(value)) {
    		System.out.println("PASS");
    	}
      
    }
    
    @And("Validate the {string} in the {string} response")
    public void validate_the_in_the_response(String name, String resource) throws Throwable {
    	placeID = getJsonPath(res2,"place_id");
    	//Resources resourceValue = Resources.valueOf(resource);
    	req1 =given().spec(getRequestSpecObject()).queryParam("place_id", placeID);
    	user_calls_something_with_something_http_request(resource,"GET");
    	String name1 = getJsonPath(res1.asString(),"name");
    	System.out.println(name1);
    	Assert.assertEquals(name, name1);
    }
    
    @Given("Delete Place Payload")
    public void delete_place_payload() throws IOException {
    	System.out.println(placeID);
    	req1 = given().spec(getRequestSpecObject()).body(td.getDeletePayload(placeID));
    }
    
}
