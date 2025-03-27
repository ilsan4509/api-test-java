package files;

import io.restassured.path.json.JsonPath;

public class ReUsableMethods {
	
	public static JsonPath rawToJason(String response) {
		// Method to convert a raw response string into JsonPath object
		JsonPath js1 = new JsonPath(response);
		return js1;
	}

}
