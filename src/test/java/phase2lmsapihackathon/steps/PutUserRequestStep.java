package phase2lmsapihackathon.steps;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import phase2lmsapihackathon.payload.PutUserLogin;
import phase2lmsapihackathon.payload.ResponseModel;
import phase2lmsapihackathon.payload.User;
import phase2lmsapihackathon.payload.UserProgramBatch;
import phase2lmsapihackathon.payload.UserRoleMaps;
import phase2lmsapihackathon.requestbody.UserRequestBody;
import phase2lmsapihackathon.utils.TestBase;
import phase2lmsapihackathon.utils.XLUtility;

public class PutUserRequestStep extends TestBase{
	TestContext testContext;
	String scenarioType, fileName,userId,endpt;
	Object[] obj;
	User user;
	UserRoleMaps userRoleMaps;
	ResponseModel responseModel;
	PutUserLogin putUserLogin;
	String jsonBody;
	String reqRoleId;
    
	public PutUserRequestStep(TestContext testContext) throws IOException{
	   try {	
		this.testContext = testContext;
		RestAssured.baseURI = TestBase.endPoints.get("BaseUrl").toString();
		this.fileName = System.getProperty("user.dir")
				+TestBase.endPoints.get("DataFile").toString();
		this.responseModel = new ResponseModel();		

	   } catch (Exception e) {
		   e.printStackTrace();
	   }			
	}
	@When("User sends {string} scenarios {string} for user reads data from excel {string}")
	public void user_sends_scenarios_for_user_reads_data_from_excel(String scenarioType, String endpointUrl, String sheetName) {
		this.scenarioType = scenarioType;
	    this.endpt = endpointUrl;
		try {
	    	int totTestCases = XLUtility.getTotalScenarioCount(sheetName,fileName);	    	
	    	for(int i=1;i<=totTestCases;i++) {
			obj = UserRequestBody.getRequestBody(sheetName, this.fileName,i);
			this.responseModel.setReqStatus("200");
			this.responseModel.setStatusCode("OK");
			this.responseModel.setMessage("Suceess");

			user = (User) obj[0];

			this.userId = user.getUserId();

			ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
					.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
			this.jsonBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
			//System.out.println("jsonBody: "+jsonBody);
			testContext.response = testContext.request
								   .when().log().body().body(this.jsonBody)
								   .put(RestAssured.baseURI
									+TestBase.endPoints.get(this.endpt).toString()+this.userId);								   
			validate_response();
	    	}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@When("User sends {string} scenarios {string} for RoleStatus reads data from excel {string}")
	public void user_sends_scenarios_for_role_status_reads_data_from_excel(String scenarioType, String endpointUrl, String sheetName) {
			//System.out.println("retrive stored value: "+TestBase.dataMap.get("userId"));
			//System.out.println("retrive stored value: "+TestBase.dataMap.get("roleId"));
			//System.out.println("retrive stored value: "+TestBase.dataMap.get("userRoleStatus"));
			this.scenarioType = scenarioType;
		try {
	    	 int totTestCases = XLUtility.getTotalScenarioCount(sheetName,fileName);
	    	 this.endpt = endpointUrl;	    	
	    	for(int i=2;i<=totTestCases;i++) {
			obj = UserRequestBody.getUpdateRoleStatusRequestBody(sheetName, this.fileName,i);
			userRoleMaps = (UserRoleMaps)obj[0];          

			this.reqRoleId = userRoleMaps.getRoleId();
			this.userId = (String)TestBase.dataMap.get("userId");
			  // if(this.reqRoleId.contains(TestBase.dataMap.get("roleId").toString())) {
			   if(this.reqRoleId.contains("R03") ){

				   this.responseModel.setMessage("UserStatus Updated for User: "+this.userId);
				   responseModel.setReqStatus("200");				   
			   } else {
				   this.responseModel.setMessage("RoleID: "+this.reqRoleId +" not found for the UserID: "
			               +this.userId);
				   this.responseModel.setStatusCode("false");
				   this.responseModel.setReqStatus("404");					 
			   }
		
			ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
					.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
			this.jsonBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(userRoleMaps);
			System.out.println("jsonBody: "+jsonBody);            
			testContext.response = testContext.request
								   .when().body(this.jsonBody).log().body()
								   .put(RestAssured.baseURI
									+TestBase.endPoints.get(this.endpt)+this.userId);	
			validate_response();
	    	}
		}catch (Exception e) {
			e.printStackTrace();
		}	
}	
	@When("User sends {string} scenarios {string} for RoleId reads data from json {string}")
	public void user_sends_scenarios_for_role_id_reads_data_from_json(String scenarioType, String endpointUrl, String sheetName) {
		  //requestWriter = new StringWriter();  
		  //requestCapture = new PrintStream(new WriterOutputStream(requestWriter));
		  
		  this.scenarioType = scenarioType;
	      this.endpt = endpointUrl;
	        try {
            String[] roleArr = {"R01","R02","R03","RRR"};
  			this.reqRoleId = (String)TestBase.dataMap.get("roleId");
  			System.out.println("roleId from get: "+this.reqRoleId);
  			this.userId = (String)TestBase.dataMap.get("userId");
	        	for(int i=0; i<4;i++) {
	        	 Map<String, String[]> dataMap = new HashMap<String, String[]>();	        	 
	 		    dataMap.put("userRoleList",new String[]{roleArr[i]});
                if(this.reqRoleId.contains(roleArr[i])) {
                   this.responseModel.setMessage("Role "+this.reqRoleId +"already exists for user "+this.userId);
 				   this.responseModel.setStatusCode("false");
 				   //this.responseModel.setReqStatus("404");
 				   this.responseModel.setReqStatus("200");
                }
                else {
                  this.responseModel.setMessage("Role Id Updated for User: "+this.userId);
				   this.responseModel.setReqStatus("200");
 				   //responseModel.setStatusCode("false");
                } if(roleArr[i].contains("RRR")) {
                    this.responseModel.setMessage("Invalid role id");
  				   this.responseModel.setReqStatus("400");
                }
		  		ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
						.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
				this.jsonBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dataMap);		
		  		System.out.println("jsonBody: "+this.jsonBody );               
	        		//this.jsonBody = role;
	    	    
	        	testContext.response = testContext.request.log().all()
	        						   .when().log().body().body(this.jsonBody)
	        						   .put(RestAssured.baseURI
	        						  +TestBase.endPoints.get(this.endpt)+userId);
	        validate_response();	
	        	} 	
	        }catch(Exception e) {
	        	e.printStackTrace();	        	
	        }	
}
	@When("User sends {string} scenarios {string} for loginStatus reads data from excel {string}")
	public void user_sends_scenarios_for_login_status_reads_data_from_excel(String scenarioType, String endpointUrl, String sheetName) {
       this.scenarioType = scenarioType;
       this.endpt = endpointUrl;
       this.userId =(String) TestBase.dataMap.get("userId");
       try {
  	      int totTestCases = XLUtility.getTotalScenarioCount(sheetName,this.fileName);
  	      for(int i=1;i<=totTestCases;i++) {
  			this.obj = UserRequestBody.getUserLoginRequestBody(sheetName, this.fileName,i);
  			this.putUserLogin = (PutUserLogin)this.obj[0];
  			this.responseModel = (ResponseModel)this.obj[1];
  			ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
					.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
			this.jsonBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.putUserLogin);
			System.out.println("jsonBody: "+jsonBody);
		   // System.out.println("url: "+RestAssured.baseURI
			//						+TestBase.endPoints.get(this.endpt).toString()+this.userId);
			testContext.response = testContext.request
								   .when().log().body().body(this.jsonBody)
								   .put(RestAssured.baseURI
									+TestBase.endPoints.get(this.endpt).toString()+this.userId);								   
			validate_response();
  	      }
  	   
       }catch(Exception e) {
    	   e.printStackTrace();
       }
	}      
       @When("User sends {string} scenarios {string} for programBatchStatus reads data from excel {string}")
       public void user_sends_scenarios_for_program_batch_status_reads_data_from_excel(String scenarioType, String endpointUrl, String sheetName) {
                this.scenarioType = scenarioType;
                this.endpt = endpointUrl;
                this.userId =(String) TestBase.dataMap.get("userId");
                try {
                      int totTestCases = XLUtility.getTotalScenarioCount(sheetName,fileName);	
                      for(int i=1;i<=totTestCases;i++) {
                    	 obj = UserRequestBody.getUserRoleProgramBatch(sheetName,this.fileName, i); 
                    	 UserProgramBatch userProgramBatch = new UserProgramBatch();
                    	 userProgramBatch = (UserProgramBatch) obj[0];
                    	 this.responseModel = (ResponseModel) obj[1];
                    	 ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
             					.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
             			this.jsonBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(userProgramBatch);
             			System.out.println("jsonBody: "+jsonBody);
             			testContext.response = testContext.request
								   .when().log().body().body(this.jsonBody)
								   .put(RestAssured.baseURI
									+TestBase.endPoints.get(this.endpt).toString()+this.userId);								   
			validate_response();
                      }

                	
                } catch(Exception e) {
                	e.printStackTrace();
                }
       }
	@Then("validate response")
	public void validate_response() {
		//System.out.println("in validation");
		//System.out.println("sce: "+this.scenarioType);
		//System.out.println("respo: "+testContext.response.body().path("message"));
		try {
		testContext.validResponse = testContext.response				
									.then().log().body()
									.assertThat().onFailMessage("Actual message: "+ 
									testContext.response.body())
									.assertThat().statusCode(Integer.valueOf(responseModel.getReqStatus()));
									//.body("RoleID",equalTo("RoleID for the"));
		int actualResponseCode = testContext.response.then().extract().statusCode();
		System.out.println("In Assertion: Actual " + actualResponseCode + " Expected:"
				+ Integer.valueOf(this.responseModel.getReqStatus()));
		//System.out.println("actual:   "+testContext.response.body().path("message"));
        //System.out.println("expected: "+this.responseModel.getMessage());  
        //if(!(this.scenarioType.contains("Positive")))
		//Assert.assertEquals(testContext.response.body().path("message"),responseModel.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}             
}	
	/*
	private void readrolefromjson() {
    	byte[] roleId = Files.readAllBytes(Paths.get(System.getProperty("user.dir")
	      + TestBase.endPoints.get("UpdateUserRoleDataFile").toString()        			
			));        	
    	jsonBody1 = new String(roleId);                
    	System.out.println("userId: "+ userId);
    	String[] roles = jsonBody1.substring(0,jsonBody1.length()-1).split(",");
    	for(int i=0;i<roles.length;i++) {
    		if(i==0)	 
    			this.jsonBody = roles[i].substring(1);
    		else if(i==roles.length-1) {
    			int leng = roles[i].length();
    			this.jsonBody = roles[i].substring(0,leng-2);} 
    		//  Map<String, String[]> dataMap = UserRequestBody.getUpdateRoleRequestBody(sheetName, this.fileName);
	}*/

}
