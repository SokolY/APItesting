import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import files.payload;

public class test {

	public static void main(String[] args) {
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").
		body(payload.AddAddress()).when().post("maps/api/place/add/json").then().assertThat().statusCode(200).body("scope", equalTo("APP")).
		extract().response().asString();
		System.out.println(response);
		JsonPath jsresp = new JsonPath(response);
		String placeId = jsresp.getString("place_id");
		System.out.println(placeId);
	}

}
