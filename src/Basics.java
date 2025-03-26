import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import files.payload;

//REST Assured Basic API Test
public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// validate if Add Place API is working as expected
		// given - all input details
		// when - Submit the API there - resource, http method
		// Then - validate the response
		
		// Set the base URI for all requests (모든 요청에서 공통으로 사용될 기본 URI 설정)
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123") // API key as a query parameter (API 인증 키
				.header("Content-Type", "application/json") // Tells server we're sending JSON (JSON 형식으로 보낸다고 서버에 알림)
		.body(payload.AddPlace()) // Request body from AddPlace method (AddPlace 메서드로부터 요청 본문 받기)
		.when().post("maps/api/place/add/json") // Send POST request to the endpoint (엔드포인트에 POST 요청 보내기)
		.then().assertThat().statusCode(200) // Verify status code is 200 (응답 코드가 200인지 확인)
		.body("scope", equalTo("APP")) // Check if response body contains scope=APP (응답 본문에 scope 값이 APP인지 확인)
		.header("server", "Apache/2.4.52 (Ubuntu)") // Check server info in response header (응답 헤더의 서버 정보 확인)
		.extract().response().asString(); // Save the entire response as String (전체 응답을 문자열로 저장)
		
		System.out.println(response);
		
		// Parse the response string to JSON (문자열 형태의 응답을 JSON으로 파싱)
		JsonPath js = new JsonPath(response); // Create JsonPath object (JsonPath 객체 생성)
		String placeId = js.getString("place_id"); // Extract place_id value from JSON (응답 JSON에서 place_id 추출)
		
		System.out.println(placeId);
		
		//Add place -> Update Place with New Address -> Get Place to validate if New address is present in response
		
	}
}
