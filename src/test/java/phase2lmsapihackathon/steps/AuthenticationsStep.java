package phase2lmsapihackathon.steps;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import phase2lmsapihackathon.utils.TestBase;

public class AuthenticationsStep extends TestBase{
	static RequestSpecification request;
	static Response response;
	static String token;
	static PrintStream logFile;
	public AuthenticationsStep() throws IOException {
		super();
		
	}
	public static void bearerTokenAuthentication() throws IOException {
		//token =null;
		//logFile = TestBase.logFile;
	   if(headerMap.get("Authorization")==null) {
		   	 logFile = new PrintStream(new FileOutputStream(System.getProperty("user.dir")+"/logs/restassuredlog/logFile.txt"));
            RestAssured.config = RestAssured.config().logConfig(new LogConfig().defaultStream(logFile));
		System.out.println("Authenticatio is in progress.....");
		byte[] login = Files.readAllBytes(Paths.get(System.getProperty("user.dir")
			               +TestBase.endPoints.get("LoginCredentialDataFile").toString()));
		String loginCred = new String(login);
	
		RestAssured.baseURI = TestBase.endPoints.get("BaseUrl").toString();    
		
	        request = RestAssured.given()
	        		.log().headers()
	        		
	        		 .filter(RequestLoggingFilter.logRequestTo(logFile))
 				     .filter(ResponseLoggingFilter.logResponseTo(logFile));
	        
    		response = request.header("Content-Type","application/json").body(loginCred)
    		             .when()
    				     .post(endPoints.get("LoginEndPoint").toString());
        	System.out.println("password: "+response.body().path("password"));

    	String vResponse = response.then().extract().response().asString();
    	ValidatableResponse validResponse = response
    		                            .then().assertThat().statusCode(200);
    	String token = validResponse.extract().path("token");
    	headerMap.put("Authorization", "Bearer "+ token);
 	    headerMap.put("Content-Type", "application/json");
 	    headerMap.put("Accept", "application/json");
 	  
    	//System.out.println("Responsein string: "+vResponse);

	}
	}	   
}
