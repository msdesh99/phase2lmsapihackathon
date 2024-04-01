package phase2lmsapihackathon.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.restassured.response.Response;
import phase2lmsapihackathon.endpoints.EndpointsUtils;

public class TestBase {

     public static Map<String, Object> endPoints;
     public static HashMap<String,String> headerMap = new HashMap<String,String>(); 
     public static Response previousResponse;
     public static String previousUserId;
     public static HashMap<String,Object> dataMap = new HashMap<String, Object>();
     
	public TestBase() throws IOException {
   	 endPoints = EndpointsUtils.getJsonDataAsMap();

     }
   
}
