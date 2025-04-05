import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.AddPlace;
import pojo.Location;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

public class serializeTest {
	
	public static void main(String[] args) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setName("Frontline house");
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		
		p.setTypes(myList);
		Location l = new Location();
		l.setLat(38.383494);
		l.setLng(33.427362);
		p.setLocation(l); // Set Location object in AddPlace object
		
		// Send a POST request and handle the response
		Response res = given().queryParam("key", "qaclick123")
		.body(p) // Set the request body with AddPlace object
		.when().post("/maps/api/place/add/json") // Send a POST request to the API
		.then().assertThat().statusCode(200).extract().response();
		
		String responseString = res. asString(); // Convert the response to a string
		System.out.println(responseString);
		
		
	}

}
