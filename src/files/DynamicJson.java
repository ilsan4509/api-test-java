package files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {
	
	@Test(dataProvider = "BooksData") // Bind test method with data provider
	public void addBook(String isbn, String aisle) {
		// Set base URI for the API
		RestAssured.baseURI = "http://216.10.245.166";
		
		// Send a POST request to the Add Book API
		String response = given().header("Content-Type", "application/json")
		.body(payload.Addbook(isbn, aisle)) // Generate request body with parameters
		.when() // Trigger the POST request to the API
		.post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		// Parse the response string to JSON 
		JsonPath js = ReUsableMethods.rawToJason(response);
		// Extract the generated book ID from the response
		String id = js.get("ID");
		System.out.println(id);
		
	}
	
	@DataProvider(name = "BooksData") // Provide multiple sets of test data to the test method
	public Object[][] getData() {
		// array = collection of elements 
		// multidimensional array = collection of arrays
		return new Object[][] {
			{"afsdf", "1234"}, 
			{"aefsdv", "685464"}, 
			{"asrgyj", "4654"}
		};
	}
	
}
