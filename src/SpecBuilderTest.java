import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;


public class SpecBuilderTest {
	
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
		
		// Build a reusable request specification (재사용 가능한 요청 사양 생성)
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		
		// Build a reusable response specification (재사용 가능한 응답 사양 생성)
		ResponseSpecification resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		// Prepare and send the request using spec builder (사양 객체를 사용하여 요청 준비 및 전송)
		RequestSpecification res = given().spec(req)
		.body(p); // Attach POJO as request body
		
		// Send POST request and apply response spec for validation (POST 요청 전송 후 응답 사양 적용하여 검증)
		Response response = res.when().post("/maps/api/place/add/json") // Send a POST request to the API
		.then().spec(resspec) // Validate response using response specification
		.extract().response();
		
		String responseString = response.asString(); // Convert the response to a string
		System.out.println(responseString);
		
	}

}
