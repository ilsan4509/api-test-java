import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import pojo.Api;
import pojo.GetCourse;
import pojo.WebAutomation;

public class OAuthTest {

	public static void main(String[] args) {
		
		// Titles of courses we want to validate
		String[] courseTitles = {"Selenium Webdriver Java", "Cypress", "Protractor"};

		// Step 1: Get Access Token using OAuth 2.0 Client Credentials Flow
		String response = given()
				.formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com") // Provide client ID
				.formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W") // Provide client secret
				.formParams("grant_type", "client_credentials").formParams("scope", "trust").when().log().all()
				.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString(); // Send POST request to get token
																				
		System.out.println(response);

		// Step 2: Parse the JSON response to extract access token
		JsonPath jsonPath = new JsonPath(response);
		String accessToken = jsonPath.getString("access_token");

		// Step 3: Use the access token to call the protected resource API
		GetCourse gc = given().queryParams("access_token", accessToken).when().log().all()
				.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails") // Call protected API
				.as(GetCourse.class);
		
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getInstructor());
		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
		
		List<Api> apiCourses = gc.getCourses().getApi();
		for (int i = 0; i < apiCourses.size(); i++) {
			if (apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
				System.out.println(apiCourses.get(i).getPrice()); 
			}
		}
		
		// Step 4: Extract all course titles from WebAutomation section
		// WebAutomation
		ArrayList<String> a = new ArrayList<String>();  // Get the course names 
		
		List<WebAutomation> w = gc.getCourses().getWebAutomation();
		
		for (int j = 0; j < w.size(); j++) {
			a.add(w.get(j).getCourseTitle());
		}
		
		// Step 5: Validate the course titles match expected titles
		List<String> expectedList = Arrays.asList(courseTitles);
		
		Assert.assertTrue(a.equals(expectedList));
		
	}
}
