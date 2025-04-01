import static io.restassured.RestAssured.given;

import io.restassured.path.json.JsonPath;

public class OAuthTest {
	
	public static void main(String[] args) {
		
		// Step 1: Get Access Token using OAuth 2.0 Client Credentials Flow
		String response = given()
		.formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com") // Provide client ID
		.formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W") // Provide client secret
		.formParams("grant_type", "client_credentials")
		.formParams("scope", "trust")
		.when().log().all()
		.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString(); // Send POST request to get token
		
		System.out.println(response);
		
		// Step 2: Parse the JSON response to extract access token
		JsonPath jsonPath = new JsonPath(response);
		String accessToken = jsonPath.getString("access_token");
		
		// Step 3: Use the access token to call the protected resource API
		String response2 = given()
		.queryParams("access_token", accessToken)
		.when().log().all()
		.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails") // Call protected API
		.asString();
		
		System.out.println(response2);
		
	}

}
