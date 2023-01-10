package tests;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


import io.restassured.http.ContentType;

public class SoapXMLRequest {
	
	
	@Test
	void validateSoapXML() throws IOException {
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
				
				.body("//*:AddResult.text()", equalTo("100"));
		
		
		
	}

}