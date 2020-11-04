package actions;

import static io.restassured.RestAssured.given;

import java.util.List;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class CommentsActions {

	public static String BASE_URL = "https://jsonplaceholder.typicode.com/";
	public static String USER_ENDPOINT = BASE_URL;
	public JsonPath jsonPathEvaluator = null;
	private RequestSpecification requestSpecification;

	public CommentsActions() {
		requestSpecification = new RequestSpecBuilder().build();
		requestSpecification.baseUri(BASE_URL);
	}

	public List<String> getCommentsEmail(int postId) {
		return given().spec(requestSpecification).get("/comments?postId=" + postId).jsonPath().getList("email");
	}

}
