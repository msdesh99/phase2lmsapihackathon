package phase2lmsapihackathon.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import phase2lmsapihackathon.endpoints.EndpointsUtils;

public class TestBase {

     public static Map<String, Object> endPoints;
     public static HashMap<String,String> headerMap = new HashMap<String,String>(); 

	public TestBase() throws IOException {
   	 endPoints = EndpointsUtils.getJsonDataAsMap();

    	   //setup();
     }
     public static void setup() throws IOException {
    	 endPoints = EndpointsUtils.getJsonDataAsMap();

    	 //endPoints = EndpointsUtils.getJsonDataAsMap();

     }
     
}
