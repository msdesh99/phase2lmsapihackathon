package phase2lmsapihackathon.steps;

import com.lms.util.ExcelUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import phase2lmsapihackathon.utils.AuthenticationsStep;
import phase2lmsapihackathon.utils.LoggerLoad;
import phase2lmsapihackathon.utils.TestBase;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
            List<Map<String, String>> data = excelUtil.getData(TestBase.EXCEL_FILE, sheetName);
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
            List<Map<String, String>> data = excelUtil.getData(TestBase.EXCEL_FILE, sheetName);
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
            List<Map<String, String>> data = excelUtil.getData(TestBase.EXCEL_FILE, sheetName);
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
        List<Map<String, String>> data = excelUtil.getData(TestBase.EXCEL_FILE, Sheetname);
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


}
