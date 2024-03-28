package phase2lmsapihackathon.steps;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import phase2lmsapihackathon.utils.AuthenticationsStep;
import phase2lmsapihackathon.utils.TestBase;

public class GetAllProgramsStep extends TestBase{
	TestContext testContext;
	String baseUrl;
     public GetAllProgramsStep(TestContext testContext) throws IOException {
		super();
		this.testContext = testContext;
		RestAssured.baseURI = TestBase.endPoints.get("BaseUrl").toString(); 
		this.baseUrl =  TestBase.endPoints.get("BaseUrl").toString();
	}

	@When("User sends HTTPS Request to get all Programs")
	public void user_sends_https_request_to_get_all_programs() {
		testContext.response =  testContext.request
					.when().log().all()
					.headers(TestBase.headerMap)
					.get(baseUrl+TestBase.endPoints.get("GetAllPrograms"));		
	}
	@Then("User receives {int} OK Status with response body for get Programs.")
	public void user_receives_ok_status_with_response_body_for_get_programs(Integer int1) throws IOException {
		testContext.validResponse = testContext.response
        .then();
        //.assertThat().statusCode(200);
		int actualStatus = testContext.response.getStatusCode();
		if(actualStatus == 401) {
			TestBase.headerMap.put("Authentication", null);
		     AuthenticationsStep.bearerTokenAuthentication();						 
		}
		Assert.assertEquals(actualStatus, 200);
	}

}
