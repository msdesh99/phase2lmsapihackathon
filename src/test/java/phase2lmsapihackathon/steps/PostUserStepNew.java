package phase2lmsapihackathon.steps;

import java.io.IOException;

import org.junit.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import phase2lmsapihackathon.payload.ResponseModel;
import phase2lmsapihackathon.payload.User;
import phase2lmsapihackathon.requestbody.UserRequestBody;
import phase2lmsapihackathon.utils.LoggerLoad;
import phase2lmsapihackathon.utils.TestBase;
import phase2lmsapihackathon.utils.XLUtility;

public class PostUserStepNew extends TestBase{
	TestContext testContext;
	String userId;
	int testcaseNo, totalTC;
	User user = new User();
	String authStatus, scenarioType,file;
	ResponseModel responseModel = new ResponseModel();
	Object[] obj,obj1;
	public PostUserStepNew(TestContext testContext) throws IOException {	
		super();
		try {
			this.testContext = testContext;
			RestAssured.baseURI = TestBase.endPoints.get("BaseUrl").toString(); 
			this.authStatus="AuthBearer";
			this.file = System.getProperty("user.dir")+TestBase.endPoints.get("DataFile").toString();
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}

	    @When("user sends request Body with details for {string} scenario from excel for {string}.")
		public void user_sends_request_body_with_details_for_scenario_from_excel_for(String scenarioType, String rowNo) {
			this.scenarioType = scenarioType;
			this.testcaseNo = Integer.valueOf(rowNo);
		    this.testcaseNo=1;
			String sheetName;
			if (this.scenarioType.contentEquals("negative")) {
				sheetName = TestBase.endPoints.get("postandputNegativeScenarioSheet").toString();
			} else
				sheetName =TestBase.endPoints.get("UserPositivePostScenario").toString();
			//System.out.println("Sce: "+this.scenarioType+" file: "+file+" sheet: "+sheetName);
	       
			try {
		    	 int totTestCases = XLUtility.getTotalScenarioCount(sheetName,this.file);
		    	//for(int i=2;i<=totTestCases;i++) {
			    	//for(int i=2;i<=totTestCases;i++) {

				obj = UserRequestBody.GetPostRequestBody(sheetName,this.file, this.testcaseNo);

				user = (User) obj[0];
				responseModel = (ResponseModel) obj[1];
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
				System.out.println("int: "+ "json: "+json);
				 try {	
						testContext.response =  testContext.request
									.when().body(json).log().body()
									.headers(TestBase.headerMap)
									.post(RestAssured.baseURI+TestBase.endPoints.get("NewUserRoleDto"));		
					 }catch (Exception ex) {
					      LoggerLoad.logInfo(ex.getMessage());
					      ex.printStackTrace();  
					}     
				/* if(this.scenarioType.contains("positive"))
					 i = totTestCases+1;
				 user_receives_status_with_response_body();
				
		    	}	*/ 
				/*LoggerLoad.logInfo("<===>TestCase:" + testcaseNo + " " + authStatus + "-Create user POST Request with  "
						+ scenarioType + " Scenario <===>");*/

			} catch (Exception ex) {
				LoggerLoad.logInfo(ex.getMessage());
				ex.printStackTrace();
			}
		}

		@Then("user receives Status with response body.")
		public void user_receives_status_with_response_body() {
			try {
				if (this.authStatus.contentEquals("NoAuth")) {
					testContext.response.then().log().all().assertThat().statusCode(401).header("Content-type", "application/json");
				} else {
					String jsonString = testContext.response.asString();
					Assert.assertNotEquals(jsonString, null);
					int actualResponseCode = testContext.response.then().extract().statusCode();
					System.out.println("UserId: "+testContext.response.body().path("userId"));
					if(actualResponseCode==201) {
					userId = (String) (this.scenarioType.contentEquals("positive")?testContext.response.body().path("userId"):"");
					TestBase.dataMap.put("userId", userId);
					XLUtility.writeData(TestBase.endPoints.get("UserPositivePutScenario").toString(),
							this.file, userId);
					}
					System.out.println("In Assertion: Actual " + actualResponseCode + " Expected:"
							+ Integer.valueOf(responseModel.getReqStatus()));
					System.out.println("User Details--->>>>>" + jsonString);
					Assert.assertTrue((actualResponseCode == Integer.valueOf(responseModel.getReqStatus())));				
				}
			} catch (Exception ex) {
				LoggerLoad.logInfo(ex.getMessage());
				ex.printStackTrace();
			}

		}

	@Then("User receives {int} Create Status with response body.")
	public void user_receives_create_status_with_response_body(int int1) {
		try {
			testContext.validResponse = testContext.response
	        .then().log().all();
	        //.assertThat().statusCode(200);
			int actualStatus = testContext.response.getStatusCode();
			if(actualStatus==401) {
				TestBase.headerMap.put("Authentication", null);
			     AuthenticationsStep.bearerTokenAuthentication();						 
			}
			System.out.println("User Created status: "+actualStatus);
			//Assert.assertEquals(actualStatus, expectedStatus);
			
		  }catch (Exception ex) {
		      LoggerLoad.logInfo(ex.getMessage());
		      ex.printStackTrace();  
		}      	
		}
}
