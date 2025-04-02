import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class BugTest {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub 
		
		RestAssured.baseURI = "https://ilsan4509.atlassian.net/";
		
		// Step 1: Create a Jira Issue
		String createIssueResponse = given()
				.header("Content-Type", "application/json") // Specify request body type
				.header("Authorization", "Basic (secret)") // Basic Auth token for Jira API (secret)
				.body("{\r\n"
					+ "    \"fields\": {\r\n"
					+ "       \"project\":\r\n"
					+ "       {\r\n"
					+ "          \"key\": \"SCRUM\"\r\n"
					+ "       },\r\n"
					+ "       \"summary\": \"menu items are not working - automation\",\r\n"
					+ "       \"issuetype\": {\r\n"
					+ "          \"name\": \"Bug\"\r\n"
					+ "       }\r\n"
					+ "   }\r\n"
					+ "}") // Request payload containing project, summary, and issue type
				.log().all()
				.post("rest/api/2/issue").then().log().all().assertThat().statusCode(201) // Expect 201 Created response
				.extract().response().asString();
		
		// Extract issue ID from response
		JsonPath js = new JsonPath(createIssueResponse);
		String issued = js.getString("id");
		System.out.println(issued);
		
		// Step 2: Upload attachment to the created issue
		given().pathParam("key", issued) // Set dynamic issue key
				.header("X-Atlassian-Token", "no-check") // Bypass CSRF check
				.header("Authorization", "Basic (secret)")
				.multiPart("file", new File("C:\\Users\\ilsan\\Downloads\\image (1).png")) // Multipart file for upload
				.post("rest/api/2/issue/{key}/attachments").then().log().all().assertThat().statusCode(200); // Expect 200 OK after upload

	}

}
