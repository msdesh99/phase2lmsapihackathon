package phase2lmsapihackathon.steps;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import phase2lmsapihackathon.utils.LoggerLoad;
import phase2lmsapihackathon.utils.TestBase;

public class CommonSteps extends TestBase{	
	TestContext testContext;
     public CommonSteps(TestContext testContext) throws IOException {
		super();
		try {
			this.testContext = testContext;
			RestAssured.baseURI = TestBase.endPoints.get("BaseUrl").toString(); 
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}

     @Given("User creates {string} Request for UserAPI module {string}")
     public void user_creates_request_for_user_api_module(String requestType, String authStatus) {
    	try {
   		     AuthenticationsStep.bearerTokenAuthentication();						 
   		     testContext.request = RestAssured
   		    		 				.given()
   		    		 				.headers(TestBase.headerMap)
   		    		 				.log().headers();
   		}catch (Exception ex) {
   		      LoggerLoad.logInfo(ex.getMessage());
   		      ex.printStackTrace();  
   		}      
   	}
}   
   	/*
	@Given("User creates GET Request for UserAPI module {string}")
	public void user_creates_get_request_for_user_api_module(String authStatus) throws IOException {
		try {
		     AuthenticationsStep.bearerTokenAuthentication();						 
		     testContext.request = RestAssured
		    		 				.given()
		    		 				.headers(TestBase.headerMap)
		    		 				.log().headers();
		}catch (Exception ex) {
		      LoggerLoad.logInfo(ex.getMessage());
		      ex.printStackTrace();  
		}      
	}	
	

	}*/
/*	@Then("User receives {int} OK Status with response body for {string} {string}.")
	public void user_receives_ok_status_with_response_body_for(int expectedStatus, String requestType, String module) {		   
	try {
		testContext.validResponse = testContext.response
        .then().log().headers();
        //.assertThat().statusCode(200);
		int actualStatus = testContext.response.getStatusCode();
		if(actualStatus==401) {
			TestBase.headerMap.put("Authorization", null);
		     AuthenticationsStep.bearerTokenAuthentication();						 
		}
		
		Assert.assertEquals(actualStatus, expectedStatus);
		
	  }catch (Exception ex) {
	      LoggerLoad.logInfo(ex.getMessage());
	      ex.printStackTrace();  
	}      	
	}

}*/
