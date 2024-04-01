package phase2lmsapihackathon.endpoints;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EndpointsUtils {
	private static ObjectMapper objectMapper = new ObjectMapper();
	static String endpointsFilePath = System.getProperty("user.dir") + "/src/test/resources/qa/endpoints.json";

	static File file = new File(endpointsFilePath);

	public static Map<String, Object> getJsonDataAsMap() throws IOException {
		Map<String, Object> data = objectMapper.readValue(file, new TypeReference<Map<String, Object>>() {
		});
		return data;
	}



	public static void main(String[] args) throws IOException {
		//EndpointsUtils endpoint = new EndpointsUtils();
		System.out.println(getJsonDataAsMap().get("BaseUrl"));   
		String env = System.getProperty("env")==null?"qa" :System.getProperty("env");
		// System.out.println("env: "+env+"dir: "+System.getProperty("user.dir"));

	}
}