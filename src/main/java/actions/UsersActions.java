package actions;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.Reporter;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class UsersActions {

	public static String BASE_URL = "https://jsonplaceholder.typicode.com/";

	public static String USER_ENDPOINT = BASE_URL + "";
	private RequestSpecification requestSpecification;

	public UsersActions() {
		requestSpecification = new RequestSpecBuilder().build();
		requestSpecification.baseUri(BASE_URL);

	}

	public String getAllUsers() {
		return given().spec(requestSpecification).get("/users").jsonPath().getString("username");
	}

	public String getUserId(String username) {
		return given().spec(requestSpecification).get("/users?username=" + username).jsonPath().getString("id[0]");
	}

	public void isUserpresent(String username) {
		if (getUserId(username) == null) {
			Assert.fail("Username with " + username + " does not exists");
		}
	}
}
