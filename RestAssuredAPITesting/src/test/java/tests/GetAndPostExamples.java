package tests;

import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class GetAndPostExamples {

	@BeforeClass
	void first() {
		baseURI = "https://reqres.in";
	}

	@Test
	void testGet() {

		given()

				.when().get("/api/users?page=2")

				.then().statusCode(200)

				.body("data[4].first_name", equalTo("George"))

				.log().all();
	}

	@Test
	void testPost() {

		
		JSONObject jsondata =  new JSONObject();
		jsondata.put("name", "Kashif");
		jsondata.put("job", "Softwate Engineer");
		
		given()
		.header("Content-Type", "application/json")
		.contentType(ContentType.JSON).accept(ContentType.JSON)
		.body(jsondata)
		.when().post("/api/users").then().statusCode(201).log().all();
		
		
		

	}

}
