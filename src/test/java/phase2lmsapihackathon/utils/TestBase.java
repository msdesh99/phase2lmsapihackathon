package phase2lmsapihackathon.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import phase2lmsapihackathon.endpoints.EndpointsUtils;

public class TestBase {

     public static Map<String, Object> endPoints;
     public static HashMap<String,String> headerMap = new HashMap<String,String>();
    static RequestSpecBuilder req = new RequestSpecBuilder();

	public TestBase() throws IOException {
   	 endPoints = EndpointsUtils.getJsonDataAsMap();

    	   //setup();
     }
     public static void setup() throws IOException {
    	 endPoints = EndpointsUtils.getJsonDataAsMap();
     }

    public static RequestSpecification createBatch() {
        req.setBaseUri(endPoints.get("BaseUrl").toString());
        req.setBasePath(endPoints.get("batches").toString());
        req.setAccept(ContentType.JSON);
        req.setContentType(ContentType.JSON);
        RequestSpecification reqspec = req.build();
        return reqspec;

    }
     
}
