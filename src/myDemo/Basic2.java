package myDemo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import files.payload;

public class Basic2 {

	public static void main(String[] args) throws IOException {
		// we want to add a place with the AddPlace api, then get the place that was added, update the place 
		//and finally delete the place.
		
		RestAssured.baseURI = "https://rahulshettyacademy.com"; // our base url is understood directly by the restassured
		//If we are given a json file rather than using a payload file as we used in the other methods, we can do the following
		//convert file data to string -> convert to byte data -> convert byte data to string and the body method accepts
		
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(new String(Files.readAllBytes(Paths.get("D:\\personal growth\\addplace.json")))).when().post("maps/api/place/add/json")
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
