package tests;


import static io.restassured.RestAssured.*;

import org.json.JSONObject;
import org.testng.annotations.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PatchAndDelete {

	int id;

	@BeforeMethod
	void first() {
		baseURI = "https://reqres.in";
	}

	@Test(priority = 1)
	void testPost() {

		JSONObject jsondata = new JSONObject();
		jsondata.put("name", "Kashif");
		jsondata.put("job", "Softwate Engineer");

		id = given()

				.header("Content-Type", "application/json")

				.contentType(ContentType.JSON).accept(ContentType.JSON)

				.body(jsondata.toString()).log().all()

				.when().post("/api/users")// .then().statusCode(201).log().all()

				.jsonPath().getInt("id"); //getting id of new created user
		
		System.out.println("this is id : " + id);
	}

	@Test(priority = 2, dependsOnMethods = "testPost")
	void putUser() {

		JSONObject jsondata = new JSONObject();
		jsondata.put("name", "K Rehman");
		jsondata.put("job", "QA Engineer");

		given()

				.contentType("application/json").body(jsondata.toString())

				.when()

				.put("/api/users/" + id)

				.then().statusCode(200).log().all();
	}

	
	@Test(priority = 3)
	void patchUser() {

		JSONObject jsondata = new JSONObject();
		jsondata.put("name", "K ur Rehman");
		//jsondata.put("job", "QA Engineer");

		given()

				.contentType("application/json").body(jsondata.toString())

				.when()

				.put("/api/users/" + id)

				.then().statusCode(200).log().all();
		
		
	}
	
	
	@Test(priority = 4,dependsOnMethods = "putUser")
	void getUser() {
		given()
		.when().get("/api/users/"+2)
		.then().statusCode(200).log().all();
		
	}
	

}
