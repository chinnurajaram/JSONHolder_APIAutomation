package ExecuteTests;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import actions.*;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.FunctionLibrary;
import org.testng.Assert;
import org.testng.Reporter;

public class CommentsTest {
	SoftAssert softAssert = new SoftAssert();
	UsersActions userObj = new UsersActions();
	PostActions postObj = new PostActions();
	CommentsActions commentObj = new CommentsActions();
	FunctionLibrary funcObj = new FunctionLibrary();
	List<Integer> postList = null;
	int postCount = 0;
	int cmtcount = 0;
	int emailfail = 0;

	@Test
	@Parameters({ "invalidUsername" })
	public void getUserWithInvalidUsername(String invalidUsername) {
		if (userObj.getUserId(invalidUsername) == null) {
			Reporter.log("Username " + invalidUsername + " does not exist");
		} else {
			Assert.fail("Username with " + invalidUsername + " exists");
		}
	}

	@Test
	@Parameters({ "invalidPostId" })
	public void getPostWithInvalidPostId(String invalidPostId) {
		if (postObj.getPostsWithPostId(invalidPostId) == null) {
			Reporter.log("Post Id " + invalidPostId + " does not exist");
		} else {
			Assert.fail("Post Id " + invalidPostId + " exists");
		}
	}

	@Test
	@Parameters({ "validUsername" })
	public void ValidateCommentsEmailFormat(String validUsername) {
		userObj.isUserpresent(validUsername);
		postList = postObj.getPostId(userObj.getUserId(validUsername));
		postObj.isPostPresent(postList);
		postCount = postList.size();
		for (int i = 0; i < postCount; i++) {
			List<String> emailList = commentObj.getCommentsEmail(postList.get(i));
			for (int j = 0; j < emailList.size(); j++) {
				cmtcount++;
				if (funcObj.validateEmailFormat(emailList.get(j))) {
					Reporter.log(emailList.get(j) + "- Validation success");
				} else {
					emailfail++;
					Reporter.log(emailList.get(j) + "- Validation Failed");
				}
			}
		}
		if (cmtcount == 0) {
			Assert.fail("User posts don't have any comments.Please try with some other user");
		}
		if (emailfail > 0) {
			Assert.fail("Email validation failed for email address");
		}
	}
}
