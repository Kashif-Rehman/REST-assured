package tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import static io.restassured.matcher.RestAssuredMatchers.*;

public class XMLSchemaVadidation {
	@Test
	void schemaValidator() throws IOException {

		File file = new File(".\\Add.xml");

		FileInputStream fileInputStream = new FileInputStream(file);

		String requestBody = IOUtils.toString(fileInputStream, "UTF-8");

		baseURI = "https://ecs.syr.edu/faculty/fawcett/Handouts/cse775/code/calcWebService/";

		given()

				.contentType("text/xml")

				.accept(ContentType.XML)

				.body(requestBody)

				.when()

				.post("Calc.asmx")

				.then().statusCode(200).log().all().and()

				.body("//*:AddResult.text()", equalTo("100"))

				.and()

				.assertThat().body(matchesXsdInClasspath("calculator.xsd"));

	}
}
