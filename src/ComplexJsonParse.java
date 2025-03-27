import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	public static void main(String[] args) {
		// Convert JSON string to JsonPath object
		JsonPath js = new JsonPath(payload.CoursePrice());
		
		// Print No of courses returned by API
		int count = js.getInt("courses.size()");
		System.out.println(count);
		
		// Print Purchase Amount 
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);
		
		// Print Title of the first course
		String titleFirstCouse = js.get("courses[0].title");
		System.out.println(titleFirstCouse);
		
		// Print All course titles and their respective Prices 
		for (int i = 0; i < count; i++) {
			String courseTitle = js.get("courses["+i+"].title");
			String coursePrice = js.get("courses["+i+"].price").toString();
			System.out.println(coursePrice);
			System.out.println(courseTitle);
		}
		
		// Print no of copies sold by RPA Course
		System.out.println("Print no of copies sold by RPA Course");
		for (int i = 0; i < count; i++) {
			String courseTitles = js.get("courses["+i+"].title");
			// If course title is RPA, print its sold copies
			if(courseTitles.equalsIgnoreCase("RPA")) {
				// Copies sold
				int copies = js.get("courses["+i+"].copies");
				System.out.println(copies);
				break;
			}
		}
	}
}
