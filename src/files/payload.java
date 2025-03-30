package files;

//Class that returns request payloads as String
public class payload {
	
	// Method to return JSON for Add Place API
	public static String AddPlace() {
		
		
		return "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Frontline house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}\r\n"
				+ "";
	}
	
	public static String CoursePrice() {
		return "{\r\n"
				+ "  \"dashboard\": {\r\n"
				+ "    \"purchaseAmount\": 1330,\r\n"
				+ "    \"website\": \"rahulshettyacademy.com\"\r\n"
				+ "  }, \r\n"
				+ "  \"courses\": [\r\n"
				+ "    {\r\n"
				+ "      \"title\": \"Selenium\",\r\n"
				+ "      \"price\": 60,\r\n"
				+ "      \"copies\": 6\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"title\": \"Cypress\",\r\n"
				+ "      \"price\": 40,\r\n"
				+ "      \"copies\": 4\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"title\": \"RPA\",\r\n"
				+ "      \"price\": 50,\r\n"
				+ "      \"copies\": 8\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"title\": \"Rest API\",\r\n"
				+ "      \"price\": 30,\r\n"
				+ "      \"copies\": 9\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "      \"title\": \"Appiun\",\r\n"
				+ "      \"price\": 20,\r\n"
				+ "      \"copies\": 7\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "}";
	}
	
	public static String Addbook(String isbn, String aisle) {
		String payload = "{\r\n"
		+ "\r\n"
		+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
		+ "\"isbn\":\""+isbn+"\",\r\n"
		+ "\"aisle\":\""+aisle+"\",\r\n"
		+ "\"author\":\"Johsdf\"\r\n"
		+ "}\r\n"
		+ "";
		
		return payload;
	}
	
}
