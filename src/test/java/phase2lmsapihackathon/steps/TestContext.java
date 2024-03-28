package phase2lmsapihackathon.steps;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.NoArgsConstructor;

public class TestContext {
	public RequestSpecification request;
	public  Response response;
    public String baseUrl;
    public ValidatableResponse validResponse;
}
