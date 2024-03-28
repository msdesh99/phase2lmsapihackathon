package phase2lmsapihackathon.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import phase2lmsapihackathon.endpoints.Endpoints;
import phase2lmsapihackathon.endpoints.EndpointsUtils;

public class AuthenticationsStep extends TestBase{
	static RequestSpecification request;
	static Response response;
	static String token;
	public AuthenticationsStep() throws IOException {
		super();
		
	}
	public static void bearerTokenAuthentication() throws IOException {
		//token =null;
	   if(headerMap.get("Authentication")==null) {

		System.out.println("Authenticatio is in progress.....");
		byte[] login = Files.readAllBytes(Paths.get(System.getProperty("user.dir")
			               +TestBase.endPoints.get("LoginCredential").toString()));
		String loginCred = new String(login);
	
		RestAssured.baseURI = TestBase.endPoints.get("BaseUrl").toString();    
		
	        request = RestAssured.given().log().headers();
    		response = request.header("Content-Type","application/json").body(loginCred)
    		             .when()
    				     .post(endPoints.get("LoginEndPoint").toString()); 
    		
    	String vResponse = response.then().extract().response().asString();
    	ValidatableResponse validResponse = response
    		                            .then().assertThat().statusCode(200);
    	String token = validResponse.extract().path("token");
    	headerMap.put("Authorization", "Bearer "+ token);
 	    headerMap.put("Content-Type", "application/json");
 	    headerMap.put("Accept", "application/json");
 	  
    	System.out.println("Responsein string: "+vResponse);

	}
	}	   
}
