package myDemo;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import static io.restassured.RestAssured.*;

import java.io.File;


import files.payload;
public class JiraTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "http://localhost:8080/";
		SessionFilter session = new SessionFilter();
		
		//login cookies to keep session
		String response = given().log().all().headers("Content-Type","application/json")
				.body(payload.getLoginData()).log().all()
		.filter(session).when().post("rest/auth/1/session")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		
		//add a comment
		given().log().all().pathParam("key", "10102").headers("Content-Type","application/json")
		.body(payload.addComment("This a new comment added using the API to add comment to issue id 10102"))
		.filter(session).when().post("rest/api/2/issue/{key}/comment")
		.then().log().all().assertThat().statusCode(201);
		
		//add an attachment to an existing issue
		
		given().log().all().header("X-Atlassian-Token","no-check").filter(session).pathParam("key","10102")
		.header("Content-Type","multipart/form-data").multiPart("file", new File("jira.txt"))
		.when().post("rest/api/2/issue/{key}/attachments")
		.then().log().all().assertThat().statusCode(200);
	

	}

}
