package myDemo;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.Assert;

import files.ReusableMethods;
import files.payload;
public class JiraTestOne {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "http://localhost:8080/";
		SessionFilter session = new SessionFilter();
		
		//Login to Jira
		given().relaxedHTTPSValidation().header("Content-Type","application/json")
		.body(payload.getLoginData()).log().all().filter(session)
		.when().post("rest/auth/1/session")
		.then().log().all().assertThat().statusCode(200);
		
		//create and issue
		String createdIssueResponse = given().log().all().header("Content-Type","application/json")
		.body(payload.createIssue("RES", "Debit card issue", "Create an issue with this debit card", "Bug")).log().all()
		.filter(session)
		.when().post("rest/api/2/issue")
		.then().assertThat().statusCode(201).extract().response().asString();
		
		//Parse the createdIssueResponse with JsonPath Method
		String issueId = ReusableMethods.parseResponse(createdIssueResponse).get("id");
		System.out.println(issueId);
		
		//Create another issue because we have to get one issue deleted with the delete issue
		
		String createdIssueResponse1 = given().log().all().header("Content-Type","application/json")
		.body(payload.createIssue("RES", "Debit card issue1", "Second issue created", "Bug")).log().all()
		.filter(session)
		.when().post("rest/api/2/issue")
		.then().assertThat().statusCode(201).extract().response().asString();
		
		String issueId1 = ReusableMethods.parseResponse(createdIssueResponse1).get("id");
		System.out.println("The issue id to be deleted is: "+issueId1);
		
		//Delete an issue : issueId1
		
		given().filter(session)
		.when().delete("rest/api/2/issue/"+issueId1+"")
		.then().log().all().assertThat().statusCode(204);
		
		//add a comment to an issue NB we have to use a path parameter here
		String commentResponse = given().log().all().header("Content-Type","application/json").pathParam("key", issueId)
		.body(payload.addCommentToIssue("This is a comment being added to "+issueId+" from the restassured"))
		.filter(session)
		.when().post("rest/api/2/issue/{key}/comment")
		.then().log().all().assertThat().statusCode(201).extract().response().asString();
		
		//Parse the commentResponse above to get the commentID to be used in the update of the comment 
		String commentID = ReusableMethods.parseResponse(commentResponse).get("id");
		
		//update the existing comment above
		String expectedMessage = "This is an update on comment "+commentID+" related to issue "+issueId+"";
		String expectedIssue = given().log().all().header("Content-Type","application/json").pathParam("key1", commentID).pathParam("id", issueId)
		.body(payload.updateComment(expectedMessage)).filter(session)
		.when().put("rest/api/2/issue/{id}/comment/{key1}")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		String myUpdatedId = ReusableMethods.parseResponse(expectedIssue).get("id").toString();
		
		//Add other comments
		 given().log().all().header("Content-Type","application/json").pathParam("key", issueId)
				.body(payload.addCommentToIssue("This is another comment being added to "+issueId+" from the restassured as second"))
				.filter(session)
				.when().post("rest/api/2/issue/{key}/comment")
				.then().log().all().assertThat().statusCode(201);
		 
		 given().log().all().header("Content-Type","application/json").pathParam("key", issueId)
			.body(payload.addCommentToIssue("this comment"))
			.filter(session)
			.when().post("rest/api/2/issue/{key}/comment")
			.then().log().all().assertThat().statusCode(201);
		
		 given().log().all().header("Content-Type","application/json").pathParam("key", issueId)
			.body(payload.addCommentToIssue("another comment"))
			.filter(session)
			.when().post("rest/api/2/issue/{key}/comment")
			.then().log().all().assertThat().statusCode(201);
		 
		 
		 given().log().all().header("Content-Type","application/json").pathParam("key", issueId)
			.body(payload.addCommentToIssue("this comment again"))
			.filter(session)
			.when().post("rest/api/2/issue/{key}/comment")
			.then().log().all().assertThat().statusCode(201); 
		 
		//add an attachment to an issue
		
		given().log().all().header("X-Atlassian-Token","no-check").header("Content-Type","multipart/form-data")
		.pathParam("key", issueId)
		.filter(session).multiPart("file",new File("jira.txt"))
		.when().post("rest/api/2/issue/{key}/attachments")
		.then().log().all().assertThat().statusCode(200);
		
		//get issue
		
		String issueDetails = given()
		.filter(session).pathParam("key", issueId).queryParam("fields", "comment")
		.when().get("rest/api/2/issue/{key}")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		
		
		System.out.println(issueDetails);
		JsonPath js = ReusableMethods.parseResponse(issueDetails);
		int commentCount = js.get("fields.comment.comments.size()");
		
		for (int i=0; i<commentCount;i++)
		{
			String commentIssueId = js.get("fields.comment.comments["+i+"].id").toString();
			if (commentIssueId.equalsIgnoreCase(myUpdatedId))
			{
				String message = js.get("fields.comment.comments["+i+"].body").toString();
				System.out.println(message);
				Assert.assertEquals(message, expectedMessage);
			}
		}
		
		
		
		
		
		
		
		
		
		
		

	}

}
