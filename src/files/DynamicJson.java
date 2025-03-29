package files;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {
	
	@Test
	public void addBook() {
		
		// Set base URI for the API
		RestAssured.baseURI = "http://216.10.245.166";
		
		// Send a POST request to the Add Book API
		String response = given().header("Content-Type", "application/json")
		.body(payload.Addbook())
		.post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		// Parse the response string to JSON 
		JsonPath js = ReUsableMethods.rawToJason(response);
		// Extract the generated book ID from the response
		String id = js.get("ID");
		System.out.println(id);
		
	}
}
