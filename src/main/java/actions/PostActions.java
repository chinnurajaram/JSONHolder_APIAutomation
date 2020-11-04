package actions;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.Assert;

import java.util.ArrayList;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class PostActions {
	public static String BASE_URL = "https://jsonplaceholder.typicode.com/";
	public static String USER_ENDPOINT = BASE_URL;
	public JsonPath jsonPathEvaluator = null;
	private RequestSpecification requestSpecification;

	public PostActions() {
		requestSpecification = new RequestSpecBuilder().build();
		requestSpecification.baseUri(BASE_URL);
	}

	public List<Integer> getPostId(String userId) {
		return given().spec(requestSpecification).get("/posts?userId=" + userId).jsonPath().getList("id");
	}
	
	public List<Integer> getPostsWithPostId(String postId) {
		return given().spec(requestSpecification).get("/posts?id="+postId).jsonPath().getList("id[0]");
	}

	public void isPostPresent(List<Integer> postList) {
		if (postList.isEmpty()) {
			Assert.fail("No Post written by this user");
		}
	}
}
