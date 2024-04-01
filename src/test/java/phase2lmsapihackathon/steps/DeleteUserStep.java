package phase2lmsapihackathon.steps;

import java.io.IOException;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import phase2lmsapihackathon.payload.ResponseModel;
import phase2lmsapihackathon.utils.TestBase;

public class DeleteUserStep extends TestBase{
	TestContext testContext;
	String userId;
	ResponseModel responseModel;
	
	public DeleteUserStep(TestContext testContext) throws IOException{
	try{ //super();
		this.testContext = testContext;
		RestAssured.baseURI = TestBase.endPoints.get("BaseUrl").toString();
		this.responseModel = new ResponseModel();
	}catch(Exception e) {
		e.printStackTrace();
	}
	}
	
	@When("User sends HTTP Request {string} with userId")
	public void user_sends_http_request_with_user_id(String endpointUrl) {
	    	//this.endpt = TestBase.endPoints.get(endpointUrl).toString();
		this.userId = TestBase.dataMap.get("userId").toString();
System.out.println("userId: "+this.userId);
	    	testContext.response = testContext.request
	    						   .when().log().all()
	    						   .delete(RestAssured.baseURI+
	    								   TestBase.endPoints.get(endpointUrl).toString()+this.userId);
	
	}

	@Then("User receives {string} OK Status with message")
	public void user_receives_ok_status_with_message(String statusCode) {
		testContext.validResponse = testContext.response
									.then().log().body()
									.assertThat().statusCode(Integer.valueOf(statusCode));
				
	}


	

}
