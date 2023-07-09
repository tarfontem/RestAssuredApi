package myDemo;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.ReusableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	
	
	@Test
	public void sumOfCourses() {
		JsonPath js = ReusableMethods.parseResponse(payload.CoursePrice());
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		
		int sum  = 0;
		
		int count = js.getInt("courses.size()");
		
	
		
		for(int i =0; i < count; i++) {
			
			int price = js.getInt("courses["+i+"].price");
			int copies = js.getInt("courses["+i+"].copies");
			
			sum += price * copies;
		}
		System.out.println(sum);
		Assert.assertEquals(sum, purchaseAmount);
		
	}
	
	

}
