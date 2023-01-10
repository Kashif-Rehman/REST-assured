package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class TestExamples {
	@Test
	public void test_1() {

		Response response = given().get("https://reqres.in/api/users?page=2");

		System.out.println("Status Code: " + response.statusCode());
		System.out.println("Response Time: " + response.getTime());
		System.out.println("Response Body: \n" + response.body().asPrettyString());
		System.out.println("Status Line:" + response.statusLine());
		System.out.println("Header: " + response.getHeader("content-type"));

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);

	}

	@Test
	public void test_2() {

		baseURI = "https://reqres.in/api";

		given()

				.get("/user?page=2")

		.then().statusCode(200)

				.body("data[1].name", equalTo("chili pepper")).log().all();

	}

}
