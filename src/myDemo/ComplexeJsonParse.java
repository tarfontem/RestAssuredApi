package myDemo;

import files.ReusableMethods;
import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexeJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JsonPath js = ReusableMethods.parseResponse(payload.CoursePrice());
		
		String websiteName = js.getString("dashboard.website");
		System.out.println(websiteName);
		
		
		int count = js.getInt("courses.size()");
		System.out.println(count);
		
		
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		
		String firstCourseTitle = js.getString("courses[0].title");
		System.out.println(firstCourseTitle);
		
		for(int i =0; i < count; i++) {
			
			System.out.println(js.getString("courses["+i+"].title"));
			System.out.println(js.getInt("courses["+i+"].price"));
			
		} 
		
		int sum  = 0;
		
		for(int i =0; i < count; i++) {
			
			sum += js.getInt("courses["+i+"].price") * js.getInt("courses["+i+"].copies");
		}
		System.out.println(sum);
		
		
		
		for(int i =0; i < count; i++) {
			
			if(js.getString("courses["+i+"].title").equals("RPA")) {
				
				
				System.out.println(js.getInt("courses["+i+"].copies"));
				break;
			}
			
		}
		
		

	}
	

}
