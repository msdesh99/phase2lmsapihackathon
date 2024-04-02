package phase2lmsapihackathon.steps;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import phase2lmsapihackathon.utils.LoggerLoad;
import phase2lmsapihackathon.utils.TestBase;
import phase2lmsapihackathon.utils.XLUtility;

public class GetAllUsersStep extends TestBase{
	TestContext testContext;
	String endpt;
	String file;
	String sheetName;
	PrintStream logFile;
     public GetAllUsersStep(TestContext testContext) throws IOException {
		super();
		try {
			this.testContext = testContext;
			RestAssured.baseURI = TestBase.endPoints.get("BaseUrl").toString(); 
			this.file = System.getProperty("user.dir")+
				    TestBase.endPoints.get("DataFile").toString();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
     @When("User sends HTTPS Request {string} for allUsers")
     public void user_sends_https_request_for_all_users(String endpointUrl) {
    	 this.endpt = endpointUrl;  	 
    	 try {	
    			testContext.response =  testContext.request
    						.when().log().all()
    						.headers(TestBase.headerMap)
    						.get(RestAssured.baseURI+TestBase.endPoints.get(endpointUrl));	
    		 }catch (Exception ex) {
    		      LoggerLoad.logInfo(ex.getMessage());
    		      ex.printStackTrace();  
    		}      	
     }

     @When("User sends HTTPS Request {string} with userId")
     public void user_sends_https_request_with_user_id(String endpointUrl) {
    	 this.endpt = endpointUrl;
    	 sheetName = TestBase.endPoints.get("UserPositivePutScenario").toString();
    	 try {
			String userId = XLUtility.getUserIdFromExcel(sheetName,this.file);
			System.out.println("userID from excel: "+userId);
			testContext.response = testContext.request
								.when().log().all()
								.get(RestAssured.baseURI+TestBase.endPoints.get(this.endpt)+userId);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
   
     }
     @When("User sends HTTP GET Request roleId {string} {string}")
     public void user_sends_http_get_request_role_id(String endpointUrl, String id) {
    	 this.endpt = TestBase.endPoints.get(endpointUrl).toString()+id;
    	 try {
    	 testContext.response = testContext.request
    			  				 .when().log().body()
    			  				 .get(RestAssured.baseURI+this.endpt);
    	 } catch (Exception e) {
    		 e.printStackTrace();
    	 }
     }

       @Then("Users receives {string} with response Body")
     public void users_receives_with_response_body(String expectedStatus) {
    	 testContext.validResponse = testContext.response
    			 					.then().log().body()
    			 					.assertThat().statusCode(Integer.valueOf(expectedStatus));
     }


     @Then("User receives {string} OK Status with response body")
 	public void user_receives_ok_status_with_response_body(String expectedStatus) {
 		try {
 			String userIdRes="";
 			testContext.validResponse = testContext.response
 	        .then().log().body();
 			int actualStatus = testContext.response.getStatusCode();
 	        Assert.assertEquals(actualStatus,Integer.valueOf(expectedStatus));
           
 			if(actualStatus==401) {
 				TestBase.headerMap.put("Authorization", null);
 			     AuthenticationsStep.bearerTokenAuthentication();						 
 			}
 			if(this.endpt.contains("GetUserByUserId")) {
 				
 			InputStream getUserbyUserIdJsonSchema =getClass().getClassLoader()
					    .getResourceAsStream("GetUserByUserIdJsonSchema.json");
 			   userIdRes =
 			    		     testContext.response.then()
 		    		        .statusCode(200)
 			    		        .and()
 			    		        //.assertThat()     
 			    		        //.body("user.userFirstName",hasItem("TwoFirst"))
 			    		       // .assertThat()
 			    		        //.body(JsonSchemaValidator.matchesJsonSchema(getUserbyUserIdJsonSchema))
 			    		        .and()
 			    		        .extract()
 			    		        .path("user.userId");
 
 			}					              
 		 //  if(TestBase.dataMap.get("userId").toString()==null)	
  			TestBase.dataMap.put("userId", testContext.response.body().path("user.userId"));
  			TestBase.dataMap.put("roleId", testContext.response.body().path("roles[0].roleId"));
  			TestBase.dataMap.put("userRoleStatus", testContext.response.body().path("roles[0].userRoleStatus"));
  			int actualStatusCode = testContext.response.then().extract().statusCode();
  	    	System.out.println("In Assertions: actualCode: "+ actualStatusCode +
  	    			 			" ExpectedCode: "+ expectedStatus);
 			System.out.println("Saved UserId: "+TestBase.dataMap.get("userId"));
 			if(actualStatus==Integer.valueOf(expectedStatus))	
 				LoggerLoad.logInfo("<===>"
 						+ "Testcase for Get request with Endpoints: " 
 						+ this.endpt + " is Successfully <===>");

 		  }catch (Exception ex) {
 		      LoggerLoad.logInfo(ex.getMessage());
 		      ex.printStackTrace();  
 		}      	
 		}

}
