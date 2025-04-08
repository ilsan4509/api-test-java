import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pojo.LoginRequest;
import pojo.LoginResponse;

import static io.restassured.RestAssured.given;

public class ECommerceAPITest {
	
	public static void main (String[] args) {
		// TODO Auto-generated method stub 
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();
		
		// Create a LoginRequest object and set the email and password
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUserEmail("rahulshetty@gmail.com");
		loginRequest.setUserPassword("Iamking@000");
		
		// Set the request specification, log the request, and send the login request
		RequestSpecification reqLogin = given().log().all().spec(req).body(loginRequest);
		
		// Send the POST request and map the response to a LoginResponse object
		LoginResponse loginResponse = reqLogin.when().post("/api/ecom/auth/login").then().log().all().extract().response()
				.as(LoginResponse.class);
		
		System.out.println(loginResponse.getToken());
		System.out.println(loginResponse.getUserId());
		
	}
	
}
