package phase2lmsapihackathon.steps;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import phase2lmsapihackathon.utils.AuthenticationsStep;
import phase2lmsapihackathon.utils.TestBase;

public class CommonSteps extends TestBase{
	
	TestContext testContext;
	String baseUrl;
     public CommonSteps(TestContext testContext) throws IOException {
		super();
		this.testContext = testContext;
		RestAssured.baseURI = TestBase.endPoints.get("BaseUrl").toString(); 
		this.baseUrl =  TestBase.endPoints.get("BaseUrl").toString();
	}

	//RequestSpecification request;
    //Response response;
	
	@Given("User creates GET Request for UserAPI module {string}")
	public void user_creates_get_request_for_user_api_module(String authStatus) throws IOException {
		     AuthenticationsStep.bearerTokenAuthentication();						 
		     testContext.request = RestAssured
		    		 				.given()
		    		 				.headers(TestBase.headerMap)
		    		 				.log().all();
	}

}
