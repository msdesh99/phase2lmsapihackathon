package phase2lmsapihackathon.steps;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.lms.util.ExcelUtil;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import phase2lmsapihackathon.utils.AuthenticationsStep;
import phase2lmsapihackathon.utils.LoggerLoad;
import phase2lmsapihackathon.utils.TestBase;

public class CommonSteps extends TestBase {

    TestContext testContext;
    String baseUrl;

    ExcelUtil excelUtil = new ExcelUtil();

    public CommonSteps(TestContext testContext) throws IOException {
        super();
        this.testContext = testContext;
        RestAssured.baseURI = TestBase.endPoints.get("BaseUrl").toString();
        this.baseUrl = TestBase.endPoints.get("BaseUrl").toString();
    }

    @Given("User creates GET Request for UserAPI module {string}")
    public void user_creates_get_request_for_user_api_module(String authStatus) throws IOException {
        try {
            if (authStatus.equals("NoAuth")) {
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


    @Then("User receives {int} OK Status with response body for {string} {string}.")
    public void user_receives_ok_status_with_response_body_for(int expectedStatus, String requestType, String module) {
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


    @Given("User sends a request with {string} and {int}")
    public void user_sends_a_request_with_and(String sheetName, Integer rowNum) {
        try {
            List<Map<String, String>> data = excelUtil.getData(TestBase.EXCEL_FILE, sheetName);
            if (data.get(rowNum).get("AuthStatus").equals("NoAuth")) {
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


    @Then("User receives response with {string} and {int}")
    public void user_receives_response_with_and(String sheetName, Integer rowNum) {
        try {
            List<Map<String, String>> data = excelUtil.getData(TestBase.EXCEL_FILE, sheetName);
            testContext.validResponse = testContext.response
                    .then().log().headers();
            //.assertThat().statusCode(200);
            int actualStatus = testContext.response.getStatusCode();
            if (actualStatus == 401) {

                AuthenticationsStep.bearerTokenAuthentication();
            }
            Assert.assertEquals(actualStatus, Integer.parseInt(data.get(rowNum).get("httpStatusCode").substring(0, 3)));

        } catch (Exception ex) {
            LoggerLoad.logInfo(ex.getMessage());
            ex.printStackTrace();
        }
    }

}
