package phase2lmsapihackathon.steps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.lms.hackathon.Batch;

import com.fasterxml.jackson.databind.ObjectMapper; 
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.lms.util.ExcelUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import phase2lmsapihackathon.utils.AuthenticationsStep;
import phase2lmsapihackathon.utils.LoggerLoad;
import phase2lmsapihackathon.utils.TestBase;

public class BatchStep extends TestBase{
	TestContext testContext;
	String baseUrl;

	ExcelUtil excelUtil = new ExcelUtil();
	static RequestSpecification request;

	Random rand = new Random();
	static Response response;
     public BatchStep(TestContext testContext) throws IOException {
		super();
		this.testContext = testContext;
		RestAssured.baseURI = TestBase.endPoints.get("BaseUrl").toString(); 
		this.baseUrl =  TestBase.endPoints.get("BaseUrl").toString();
	}

	/*
	This method for getting all the batch items
	 */
	@When("User sends HTTPS Request to get batches with {string} and {int}")
	public void user_sends_https_request_to_get_batches_with_and(String sheetName, Integer rowNum) {
		try {
			List<Map<String, String>> data = excelUtil.getData(TestBase.endPoints.get("dataInput").toString(), sheetName);
			testContext.response =  testContext.request
					.when().log().all()
					.headers(TestBase.headerMap)
					.get(data.get(rowNum).get("baseUrl")+data.get(rowNum).get("endPoint"));
			//testContext.response.then().log().all();
			testContext.response
					.then()
					.log().all();
		}catch (Exception ex) {
			LoggerLoad.logInfo(ex.getMessage());
			ex.printStackTrace();
		}
	}
	/*
	get batch by ID
	 */
	@When("User sends GET Request to get batch info with {string} and {int}")
	public void user_sends_get_request_to_get_batch_info_with_and(String sheetName, Integer rowNum) {
		try {
			List<Map<String, String>> data = excelUtil.getData(TestBase.endPoints.get("dataInput").toString(), sheetName);
			testContext.response =  testContext.request
					.when().log().all()
					.headers(TestBase.headerMap)
					.get(data.get(rowNum).get("baseUrl")+data.get(rowNum).get("endPoint")+Integer.parseInt(data.get(rowNum).get("BatchId").substring(0,4)));
			testContext.response.then().log().all();
		}catch (Exception ex) {
			LoggerLoad.logInfo(ex.getMessage());
			ex.printStackTrace();
		}
	}

	/*
	get batch by name
	 */
	@When("User sends GET Request to get batch info by Name with {string} and {int}")
	public void user_sends_get_request_to_get_batch_info_by_name_with_and(String sheetName, Integer rowNum) {
		try {
			List<Map<String, String>> data = excelUtil.getData(TestBase.endPoints.get("dataInput").toString(), sheetName);
			testContext.response =  testContext.request
					.when().log().all()
					.headers(TestBase.headerMap)
					.get(data.get(rowNum).get("baseUrl")+data.get(rowNum).get("endPoint")+data.get(rowNum).get("BatchName"));
			testContext.response.then().log().all();
		}catch (Exception ex) {
			LoggerLoad.logInfo(ex.getMessage());
			ex.printStackTrace();
		}
	}

	/*

	 */
	@Given("User creates POST Request {string} and {int} for the LMS API batch endpoint with mandatory field.")
	public void user_creates_post_request_for_the_lms_api_batch_endpoint(String Sheetname, int rownumber) throws Exception {
		AuthenticationsStep.bearerTokenAuthentication();
		Response[] responses= null;
		List<Map<String, String>> data = excelUtil.getData(TestBase.endPoints.get("dataInput").toString(), Sheetname);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("batchName", data.get(rownumber).get("batchName") + "-" + rand.nextInt(1000));
		map.put("batchDescription", data.get(rownumber).get("batchDescription"));
		map.put("batchNoOfClasses",10);
		map.put("programId", 16240);
		map.put("batchStatus", data.get(rownumber).get("status"));
		response = RestAssured.given().headers(TestBase.headerMap)
				.spec(createBatch())
				.body(map)
				.when().post();
		response.then().log().all();
		if(responses==null) {
			responses=new Response[data.size()];
		}
		responses[rownumber]=response;
		response.then().log().all();
		if(rownumber==0) {
			Response storeresponse1 = responses[rownumber];
			String Batchname1= storeresponse1.jsonPath().getString("batchName");
			int BatchID1 = storeresponse1.jsonPath().getInt("batchId");
		}else if(rownumber==1) {
			Response storeresponse2 = responses[rownumber];
			String Batchname2= storeresponse2.jsonPath().getString("batchName");
			int BatchID2 = storeresponse2.jsonPath().getInt("batchId");
		}
	}

	@When("User sends HTTPS Request and  request Body with mandatory , additional  fields")
	public void user_sends_https_request_and_request_body_with_mandatory_additional_fields() {
		System.out.println("batch nane Posted ");
	}
	@Then("User receives {int} Created Status with response body.")
	public void user_receives_created_status_with_response_body(Integer int1) {
		System.out.println();
	}
   
 	/*@When("User sends HTTPS Request to get batches")
 	public void user_sends_https_request_to_get_batches() {
 		try {	
 			testContext.response =  testContext.request
 						.when().log().all()
 						.headers(TestBase.headerMap)
 						.get(baseUrl+TestBase.endPoints.get("batches"));
 			//testContext.response.then().log().all();
			testContext.response
					.then()
					.log().all();
		}catch (Exception ex) {
 		      LoggerLoad.logInfo(ex.getMessage());
 		      ex.printStackTrace();  
 		}    
 	}




 	@Then("User receives OK Status with response body for batch module.")
 	public void user_receives_ok_status_with_response_body_for_batch_module() {
 		try {	
 			testContext.response
 	          .then()
 	          .log().all();	
 		 }catch (Exception ex) {
 		      LoggerLoad.logInfo(ex.getMessage());
 		      ex.printStackTrace();  
 		}    
 	}

	@When("User sends GET Request with {string} to get batch info")
	public void user_sends_get_request_with_to_get_batch_info(String string) {
		try {
			testContext.response =  testContext.request
					.when().log().all()
					.headers(TestBase.headerMap)
					.get(baseUrl+TestBase.endPoints.get("getBatchByBatchId")+string);
			testContext.response.then().log().all();
		}catch (Exception ex) {
			LoggerLoad.logInfo(ex.getMessage());
			ex.printStackTrace();
		}
	}

	*//*@When("User sends GET Request with {string} to get details batch")
	public void user_sends_get_request_with_batch_name(String batchName) {
		try {
			testContext.response =  testContext.request
					.when().log().all()
					.headers(TestBase.headerMap)
					.get(baseUrl+TestBase.endPoints.get("getBatchByBatchName")+batchName);
			testContext.response.then().log().all();
		}catch (Exception ex) {
			LoggerLoad.logInfo(ex.getMessage());
			ex.printStackTrace();
		}
	}*//*
	@When("User posts HTTPS Request to batches")
 	public void user_post_https_request_to_batches() {
 		try {
 			System.out.println("Authentication is in progress.....");
 			Batch batch2 = new Batch("AWS-2022", "active", 2, 16306 );

 			testContext.request = RestAssured.given().log().headers();
 			testContext.response = testContext.request.header("Content-Type","application/json").body(batch2)
    		             .when()
    				     .post(endPoints.get("batches").toString()); 
 			testContext.response.then().log().all();
 		 }catch (Exception ex) {
 		      LoggerLoad.logInfo(ex.getMessage());
 		      ex.printStackTrace();  
 		}    
 	}

	@Given("User creates GET Request for Batch module {string}")
	public void user_creates_get_request_for_batch_module(String authStatus) {
		try {
			if (authStatus.equals("NoAuth")) {
				headerMap.remove("Authorization");
				testContext.request = RestAssured.given().log().all();
			} else {
				AuthenticationsStep.bearerTokenAuthentication();
				testContext.request = RestAssured
						.given()
						.headers(TestBase.headerMap)
						.log().headers();

			}

		} catch (Exception ex) {
			LoggerLoad.logInfo(ex.getMessage());
			ex.printStackTrace();
		}
	}


	@When("User sends GET Request with {string} to get details of batch")
	public void user_sends_get_request_with_to_get_details_of_batch(String strBatchName) {
		try {
			testContext.response =  testContext.request
					.when().log().all()
					.headers(TestBase.headerMap)
					.get(baseUrl+TestBase.endPoints.get("getBatchByBatchName")+strBatchName);
			testContext.response.then().log().all();
		}catch (Exception ex) {
			LoggerLoad.logInfo(ex.getMessage());
			ex.printStackTrace();
		}
	}

	@Then("User receives {string} Status with response body for {string} {string}.")
	public void user_receives_status_with_response_body_for(String expectedStatus, String string2, String string3) {
		try {
			testContext.validResponse = testContext.response
					.then().log().headers();
			//.assertThat().statusCode(200);
			int actualStatus = testContext.response.getStatusCode();
			if (actualStatus == 401) {
				TestBase.headerMap.put("Authentication", null);
				AuthenticationsStep.bearerTokenAuthentication();
			}

			Assert.assertEquals(actualStatus, expectedStatus);

		} catch (Exception ex) {
			LoggerLoad.logInfo(ex.getMessage());
			ex.printStackTrace();
		}
	}

	@Then("User receives {string} Status with response body for {string}.")
	public void user_receives_status_with_response_body_for(String expectedStatus, String string2) {
		try {
			testContext.validResponse = testContext.response
					.then().log().headers();
			//.assertThat().statusCode(200);
			int actualStatus = testContext.response.getStatusCode();
			if (actualStatus == 401) {
				TestBase.headerMap.put("Authentication", null);
				AuthenticationsStep.bearerTokenAuthentication();
			}

			Assert.assertEquals(actualStatus, Integer.parseInt(expectedStatus));

		} catch (Exception ex) {
			LoggerLoad.logInfo(ex.getMessage());
			ex.printStackTrace();
		}
	}



	@When("User sends HTTPS Request and  request Body with mandatory , additional  fields")
	public void user_sends_https_request_and_request_body_with_mandatory_additional_fields() {
		System.out.println();
	}
	@Then("User receives {int} Created Status with response body.")
	public void user_receives_created_status_with_response_body(Integer int1) {
		System.out.println();
	}*/

}
