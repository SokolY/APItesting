	import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
public class JiraAPI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "http://localhost:8080";
		SessionFilter session = new SessionFilter();
		
		//Log in
		String response = given().log().all().header("Content-Type", "application/json").body("{\r\n"
				+ "    \"username\": \"yura.sokol\",\r\n"
				+ "    \"password\": \"Jira1234\"\r\n"
				+ "}").filter(session).
				when().post("/rest/auth/1/session").
				then().log().all().extract().response().asString();
		
		System.out.println("The response is: " + response);
		JsonPath js = new JsonPath(response);
		String cookesId = js.getString("session.value");
		System.out.println("Session cookes is " + cookesId);
		
		//Add comment
		String addCommentResponse = given().pathParam("id", "10006").header("Content-Type", "application/json").body("{\r\n"
				+ "    \"body\": \"Commet to the issue from Eclipse for verefication.\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").filter(session).
		when().post("/rest/api/2/issue/{id}/comment").
		then().assertThat().statusCode(201).extract().response().asString();
		
		System.out.println("Add comment responce is: " + addCommentResponse);
		JsonPath addComentRaps = new JsonPath(addCommentResponse);
		int neededCommentId = addComentRaps.getInt("id");
		System.out.println("Comment id is: " + neededCommentId);
//		
////		
//		//Attach file 
//		given().filter(session).header("X-Atlassian-Token", "no-check").pathParam("key", "10006").header("Content-Type", "multipart/form-data").
//		multiPart("file", new File("D:\\FromPC\\DiscD\\Java\\APITesting\\Apitest\\src\\AddPlace.txt")).
//		when().post("/rest/api/2/issue/{key}/attachments").
//		then().log().all().assertThat().statusCode(200);
		
		//get Issue
		String issueText = given().filter(session).pathParam("key", "10006").queryParam("fields", "comment").
		when().get("/rest/api/2/issue/{key}").
		then().extract().response().asString();
		
		JsonPath issueComments = new JsonPath(issueText);	
		
		int commentCountSize = issueComments.getInt("fields.comment.comments.size()");
		
		System.out.println("Count of comments is the issue is: " +  commentCountSize);
		String commentText = null;
		for(int i = 0; i <commentCountSize; i++) {
			int commentId = issueComments.getInt("fields.comment.comments["+i+"].id");
			System.out.println(commentId);
			if(commentId == neededCommentId) {
				commentText = issueComments.getString("fields.comment.comments["+i+"].body");
//				System.out.println(commentText);
				
			}
		}
		Assert.assertEquals(commentText, "Commet to the issue from Eclipse for verefication.");
	}

}
