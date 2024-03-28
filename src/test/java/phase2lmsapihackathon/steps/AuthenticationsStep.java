package phase2lmsapihackathon.steps;

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

public class AuthenticationsStep {
	RequestSpecification request;
	Response response;
	//TestContext testContext;
	/*AuthenticationsStep(TestContext testContext){
		this.testContext = testContext;
	}*/

	//static RequestSpecification request;
	public String bearerTokenAuthentication() throws IOException {
	/*Endpoints[] endpointArr = Endpoints.values();
	  for(Endpoints endpoint: endpointArr) 
	     System.out.println(endpoint.getUrl()); */
	
	Map<String,Object> endPoints = 	EndpointsUtils.getJsonDataAsMap();
		System.out.println("Authenticatio is in progress.....");
	
	
	byte[] login = Files.readAllBytes(Paths.get(System.getProperty("user.dir")+endPoints.get("LoginCredential").toString()));
    String loginCred = new String(login);
	//request = RestAssured.given().body(loginCred);
    //String loginCred1 = new String(login);

    RestAssured.baseURI = endPoints.get("BaseUrl").toString();   
    		/*request =  RestAssured.given().contentType(ContentType.JSON).body(loginCred);		 
    				.filter((req, res, ctx) -> {
      	        // Mask the "password" field in the request body
    	        if (req.getBody() != null) {
    	            String body = req.getBody().toString();
    	           // body = body.replaceAll("\"password\":\"[^\"]*\"", "\"password\":\"*****\"");
    	            body = body.replaceAll("\"password\":\"[a-zA-Z@0-9]*\"", "\"password\":\"*****\"");
    	            req.body(body);
    	        }
    	        // Mask the "Authorization" header
    	        if (req.getHeaders().hasHeaderWithName("Authorization")) {
    	            req.removeHeader("Authorization");
    	            req.header("Authorization", "*****");
    	        }
     	        return ctx.next(req, res);
    	    }).log().body();
    		       request
    				 .when()
				     .post(endPoints.get("LoginEndPoint").toString());*/

    				//.when().post(endPoints.get("LoginEndPoint").toString());
    		/* response = request
		             .when().contentType(ContentType.JSON).body(loginCred1)
				     .post(endPoints.get("LoginEndPoint").toString());*/
				   //  .then().assertThat().statusCode(200); 
	        request = RestAssured.given();
    		 response = request.header("Content-Type","application/json").body(loginCred)
    		             .when()
    				     .post(endPoints.get("LoginEndPoint").toString()); 
    String vResponse = response.then().extract().response().asString();
    ValidatableResponse validResponse = response
    		                            .then().assertThat().statusCode(200);
    String token = validResponse.extract().path("token");
    System.out.println("Token: "+token);
   // Map<String,String> cookieMap;
    //ExtractableResponse<ResponseOptions<cookieMap>>  eResponse = validResponse.extract().cookies();
   // for(Map.Entry<String, String> map : cookieMap.entrySet()) 
    //	System.out.println("Cookie: "+map.getKey()+"value: "+map.getValue());
    
    System.out.println("Responsein string: "+vResponse);

    return token;
	}
}
