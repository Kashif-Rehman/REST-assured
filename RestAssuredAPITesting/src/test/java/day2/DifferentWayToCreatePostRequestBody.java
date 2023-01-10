package day2;
/*
 * Using HashMap
 * Using Org.JSON
 * POJO(plain old java object)
 * Using JSON file
 * */

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class DifferentWayToCreatePostRequestBody {
	//@Test(priority = 1)
	void testPostHashMap() {
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("firstName", "Albart");
		data.put("lastName", "Eintine");
		data.put("subjectId", 1);
		
		given()
		.contentType("application/json")
		.body(data)
		.when()
		.post("http://localhost:3000/users")
		.then()
		.statusCode(201)
		.body("firstName", equalTo("Albart"))
		.body("lastName", equalTo("Eintine"))
		.body("subjectId", equalTo(1))
		.header("Content-type", "application/json; charset=utf-8")
		.log().all();
	}
	
	@Test(priority = 2)
	public void deleteUser() {
		baseURI = "http://localhost:3000";   		
		
		given()
		
		.when()
			.delete("/users/6")
		
		 .then()
		 	.statusCode(200);
		

	}
	
//	@Test(priority = 1)
	void testPostUsingJsonLibrary() {

				
		JSONObject data = new JSONObject();
		data.put("firstName", "Albart");
		data.put("lastName", "Eintine");
		data.put("subjectId", 1);
		
		given()
		.contentType("application/json")
		.body(data.toString())
		.when()
		.post("http://localhost:3000/users")
		.then()
		.statusCode(201)
		.body("firstName", equalTo("Albart"))
		.body("lastName", equalTo("Eintine"))
		.body("subjectId", equalTo(1))
		.header("Content-type", "application/json; charset=utf-8")
		.log().all();
	}

	
//	@Test(priority = 1)
	void testPostUsingPojoClass() {

	
		Pojo_Post_Request data = new Pojo_Post_Request();
		
		data.setFirstName("Albart");
		data.setLastName("Eintine");
		data.setSubjectId(2);		
		
		given()
		.contentType("application/json")
//		.contentType(ContentType.JSON).accept(ContentType.JSON)
		.body(data)
		.when()
		.post("http://localhost:3000/users")
		.then()
		.statusCode(201)
		.body("firstName", equalTo("Albart"))
		.body("lastName", equalTo("Eintine"))
		.body("subjectId", equalTo(2))
		.header("Content-type", "application/json; charset=utf-8")
		.log().all();
	}
	
	@Test(priority = 1)
	void testPostUsingExternalJsonFile() throws FileNotFoundException {
		
		File f  = new File(".\\body.json");		
		FileReader fr = new FileReader(f);
		
		JSONTokener jt = new JSONTokener(fr);
		JSONObject data = new JSONObject(jt);
			
		
		given()
		.contentType("application/json")
//		.contentType(ContentType.JSON).accept(ContentType.JSON)
		.body(data.toString())
		.when()
		.post("http://localhost:3000/users")
		.then()
		.statusCode(201)
		.body("firstName", equalTo("Albart"))
		.body("lastName", equalTo("Eintine"))
		.body("subjectId", equalTo(1))
		.header("Content-type", "application/json; charset=utf-8")
		.log().all();
	}

}
