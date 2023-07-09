package myDemo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReusableMethods;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DynamicJson {
	
	
	@Test(dataProvider = "booksData")
	public void addBook(String isbn,String aisle) {
		
		RestAssured.baseURI = "http://216.10.245.166";
		String addBookResponse =given().header("Content-Type","application/json").body(payload.addBook(isbn,aisle))
		.when().post("/Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = ReusableMethods.parseResponse(addBookResponse);
		//JsonPath js = new JsonPath(addBookResponse);
		//before running the test go back to the payload on addBook() method and change the value of the "isbn" 
		//key and "aisle" , to random values to avoid using values that have been used because if the values already exist
		//the test will fail

		String id = js.get("ID");
		System.out.println(id);
		
		
		//Delete Book
		
		
		
		
		}
		
	
	@Test(dataProvider = "booksData")
	public void deleteBook(String isbn, String aisle)
	{
		RestAssured.baseURI = "http://216.10.245.166";
		given().log().all().header("Content-Type","application/json").body(payload.getDeleteBook(isbn,aisle))
		.when().post("/Library/DeleteBook.php")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("book is successfully deleted"));
		
	}
	
	@DataProvider(name="booksData")
	public Object[][] getBooks()
	{
		return new Object[][] {{"rdd","643"},{"wfa","731"},{"pwt","459"}};
	}
	
	/*
	@DataProvider(name="deleteBooksData")
	public Object[][] delBooks()
	{
		return new Object[][] {{"ttc","643"},{"gbh","731"},{"csp","459"}};
	}*/
	
	//https://www.toolsqa.com/testng/testng-dataproviders/
}
