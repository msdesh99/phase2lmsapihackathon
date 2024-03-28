package phase2lmsapihackathon.steps;

import java.io.IOException;

import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import phase2lmsapihackathon.utils.LoggerLoad;
import phase2lmsapihackathon.utils.TestBase;

public class GetAllUsersStep extends TestBase{
	TestContext testContext;
	String baseUrl;
     public GetAllUsersStep(TestContext testContext) throws IOException {
		super();
		this.testContext = testContext;
		RestAssured.baseURI = TestBase.endPoints.get("BaseUrl").toString(); 
		this.baseUrl =  TestBase.endPoints.get("BaseUrl").toString();
	}
	@When("User sends HTTPS Request")
	public void user_sends_https_request() {
	 try {	
		testContext.response =  testContext.request
					.when().log().all()
					.headers(TestBase.headerMap)
					.get(baseUrl+TestBase.endPoints.get("GetAllUsers"));	
	 }catch (Exception ex) {
	      LoggerLoad.logInfo(ex.getMessage());
	      ex.printStackTrace();  
	}      	
	}



}
