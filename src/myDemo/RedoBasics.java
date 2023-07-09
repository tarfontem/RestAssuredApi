package myDemo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.payload;


public class RedoBasics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI ="https://rahulshettyacademy.com/";
		
		//Add a place
		
	   String response = given().log().all().queryParam("key", "qaclick123").headers("Content-Type","application/json")
	    .body(payload.AddPlace())
	    .when().post("maps/api/place/add/json")
	    .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("server","Apache/2.4.41 (Ubuntu)")
	    .extract().response().asString();
	    
	    JsonPath js = new JsonPath(response);
	    String placeId = js.getString("place_id");
	    System.out.println(placeId);
	    
	    
	    //Update place using the post method
	    String newAddress = "70 Summer walk, Canada";
	    
	    given().log().all().queryParam("key", "qaclick123").headers("Content-Type","application/json")
	    .body(payload.UpdatePlace(placeId, newAddress))
	    .when().put("maps/api/place/update/json")
	    .then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
	    
	    
	    //Get place using the Get method
	    
	   String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
	    .when().get("maps/api/place/get/json")
	    .then().log().all().assertThat().statusCode(200).extract().response().asString();
	   System.out.println(getPlaceResponse);
	   
	   JsonPath js1 = new JsonPath(getPlaceResponse);
	   String actualAddress = js1.getString("address");
	   System.out.println("The actual location is: "+actualAddress);
	   Assert.assertEquals(actualAddress, newAddress);
	   
	    
	    

	}

}
