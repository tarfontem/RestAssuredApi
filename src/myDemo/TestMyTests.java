package myDemo;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.ReusableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class TestMyTests {

	@Test
	public void myApiTests() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body(payload.AddPlace())
				.when().post("maps/api/place/add/json")
				.then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP")).header("Server", "Apache/2.4.41 (Ubuntu)")
				.extract().response().asString();
				
				 JsonPath js = ReusableMethods.parseResponse(response);
				 String placeId = js.getString("place_id");
				 
				 //Update place
				 
				 String newAddress = "70 Summer walk, Canada";
				 
				 given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
				 .body(payload.UpdatePlace(placeId, newAddress))
				 .when().put("maps/api/place/update/json")
				 .then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));
				 
				 
				 //Get Place
				 
				 String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
				 .when().get("maps/api/place/get/json")
				 .then().log().all().assertThat().statusCode(200).extract().response().asString();
				 
				 JsonPath js1 = ReusableMethods.parseResponse(getPlaceResponse);
				 String actualAddress = js1.getString("address");
				 Assert.assertEquals(actualAddress, newAddress);
				 System.out.println("The end");
		
		
		
		
	}
	
}
