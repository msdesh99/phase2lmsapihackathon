package phase2lmsapihackathon.steps;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import phase2lmsapihackathon.endpoints.EndpointsUtils;

public class GetAllUsersStep {
     RequestSpecification request;
     Response response;
     HashMap<String,String> headerMap = new HashMap<String,String>(); 
	
	@Given("User creates GET Request for UserAPI module {string}")
	public void user_creates_get_request_for_user_api_module(String authStatus) throws IOException {
		
			AuthenticationsStep auth  = new AuthenticationsStep();
		     String token = auth.bearerTokenAuthentication();
				System.out.println("REsponse: "+ token);
				
				Map<String,Object> endPoints = 	EndpointsUtils.getJsonDataAsMap();
				RestAssured.baseURI = endPoints.get("BaseUrl").toString();  
				headerMap.put("Authentication", "Bearer "+ token);
		 	    headerMap.put("Content-Type", "application/json");
		 	    headerMap.put("Accept", "application/json");


                // this.request = RestAssured.given();
                		 	  // .headers(headerMap);
		
	   
	}

	@When("User sends HTTPS Request")
	public void user_sends_https_request() {
	    
	}

	@Then("User receives {int} OK Status with response body.")
	public void user_receives_ok_status_with_response_body(int expectedStatus) {
	    
	}


}
