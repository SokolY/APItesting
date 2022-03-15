	import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
public class JiraAPI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "http://localhost:8080";
		SessionFilter session = new SessionFilter();
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
		
		given().pathParam("id", "10006").log().all().header("Content-Type", "application/json").body("{\r\n"
				+ "    \"body\": \"Commet to the issue from Eclipse.\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").filter(session).
		when().post("/rest/api/2/issue/{id}/comment").
		then().log().all().assertThat().statusCode(201);
	}

}
