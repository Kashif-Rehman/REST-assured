package tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JsonSchemaValidator {

	@BeforeMethod
	void first() {
		baseURI = "https://reqres.in";
	}

	@Test
	void testGet() {

		given()

				.when().get("/api/users?page=2")

				.then()

				.assertThat().body(matchesJsonSchemaInClasspath("schema.json"))

				.statusCode(200)

				.body("data[4].first_name", equalTo("George"))

				.body("data.first_name", hasItems("George", "Rachel"))

				.log().all();
	}

}
