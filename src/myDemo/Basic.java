package myDemo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import files.payload;

public class Basic {

	public static void main(String[] args) {
		// we want to add a place with the AddPlace api, then get the place that was added, update the place 
		//and finally delete the place.
		
		RestAssured.baseURI = "https://rahulshettyacademy.com"; // our base url is understood directly by the restassured
		//The way to do this in java is to first of all use the given, when and then methods
		/*for the given we have chained to the .log().all() to let the parameters we input to be printed out on the 
		console*/
		//The query parameters go to the .queryParam method
		/*The header apparently uses the Content-Type  as application/Json. we chain it all together on the .header
		 header method*/
		//Since we are using the post method to add the place, this means that we will automatically add a body
		//The body is added via the .body method where we pass the json body.
		// We created a package named files in the in the src where we added a class called payload and within the 
		//class we added a static method called AddPlace() that mainly returned the json body of the api.
		//we can therefore call the payload.AddPlace() method directly into our script here to make our work clean
		//The .given method to contains maily the api method which is the post. .post is chained directly to the 
		//given() method and the .post() takes as parameter the api resource 
		//after the .post we chain to the .then() method which deals with the api out put and assertions
		
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payload.AddPlace()).when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200)
		.body("scope", equalTo("APP")).header("server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		//
		
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);  // used to parse the response above. it has a variety of methods
		String placeId=js.getString("place_id");
		
		System.out.println(placeId);
		
		//We want to update a place now with the update place API
		//We still have to use the given,when, then method
		
		String newAddress = "70 Summer walk, Canada";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payload.UpdatePlace(placeId,newAddress))
		.when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//get place after update
		
		
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		System.out.println(getPlaceResponse);
		
		JsonPath js1 = new JsonPath(getPlaceResponse);
		String actualAddress = js1.getString("address");
		System.out.println(actualAddress);
		
		
		

	}

}
