package phase2lmsapihackathon.steps;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
public class TestContext {
	private RequestSpecification request;
	private Response response;
	private String token;
}
