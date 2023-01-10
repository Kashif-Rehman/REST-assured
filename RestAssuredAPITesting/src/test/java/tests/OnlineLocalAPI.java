package tests;

import org.json.JSONObject;
import org.testng.annotations.Test;


import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class OnlineLocalAPI {
//	@Test
	void getUser() {

		baseURI = "http://localhost:3000";

		given().get("/users").then().statusCode(200).log().all();

	}

//	@Test
	public void postUser() {
		baseURI = "http://localhost:3000";
			
		JSONObject jsondata =  new JSONObject();
		jsondata.put("firstName", "ALI");
		jsondata.put("lastName", "Azmat");
		jsondata.put("subjectId", 1);
		
	      		
		
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(jsondata.toString())
		
		.when()
			.post("/users")
		
		 .then()
		 	.statusCode(201).log().all();
		

	}
	
//	@Test
	public void putUser() {
		baseURI = "http://localhost:3000";
			
		JSONObject jsondata =  new JSONObject();
		jsondata.put("firstName", "Albert");
		jsondata.put("lastName", "Eintein");
		jsondata.put("subjectId", 2);
		
	      		
		
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(jsondata.toString())
		
		.when()
			.put("/users/5")
		
		 .then()
		 	.statusCode(200).log().all();
		

	}
	
	
//	@Test
	public void patchUser() {
		baseURI = "http://localhost:3000";
			
		JSONObject jsondata =  new JSONObject();
		jsondata.put("firstName", "Albert");
		jsondata.put("lastName", "Marlo");
		jsondata.put("subjectId", 2);
		
	      		
		
		given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(jsondata.toString())
		
		.when()
			.patch("/users/5")
		
		 .then()
		 	.statusCode(200).log().all();
		

	}
	
	@Test
	public void deleteUser() {
		baseURI = "http://localhost:3000";
			
	    		
		
		given()
		
		.when()
			.delete("/users/5")
		
		 .then()
		 	.statusCode(200).log().all();
		

	}
	
	
}
