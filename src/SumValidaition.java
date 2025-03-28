import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class SumValidaition {
	
	@Test  // Marks this method as a TestNG test method
	public void sumOfCourses() {
		
		int sum = 0;
		
		// Convert raw JSON to JsonPath object
		JsonPath js = new JsonPath(payload.CoursePrice()); 
		
		int count = js.getInt("courses.size()");
		
		// Loop through all courses and calculate amount = price * copies
		for (int i = 0; i < count; i++) {
			int price = js.get("courses["+i+"].price");
			int copies = js.get("courses["+i+"].copies");
			int amount = price * copies;
			System.out.println(amount);
			sum = sum + amount;
		}
		System.out.println(sum);
		
		// Get expected purchase amount from dashboard
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		
		// Assert that calculated sum matches purchase amount
		Assert.assertEquals(sum, purchaseAmount);
	}
}
