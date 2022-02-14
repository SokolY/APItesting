import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReusableMethod;
import files.payload;

public class test {

	public static void main(String[] args) {
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").
		body(payload.AddAddress()).when().post("maps/api/place/add/json").then().assertThat().statusCode(200).body("scope", equalTo("APP")).
		extract().response().asString();
		
		System.out.println(response);
//		JsonPath jsresp = new JsonPath(response);
//		String placeId = jsresp.getString("place_id");
		String placeId = ReusableMethod.rawToJson(response).getString("place_id");
		System.out.println(placeId);
		
		System.out.println("Update plece function");
		System.out.println("-----------------------------------------------------");
	
		
		String address = "70 happy street, USA";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").
		body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+address+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}").
		when().put("maps/api/place/update/json").
		then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		

		System.out.println("Get place function");
		System.out.println("-----------------------------------------------------");
		String getPlaceResponceStr = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId).
		when().get("maps/api/place/get/json").
		then().log().all().assertThat().statusCode(200).extract().response().asString();
		
//		JsonPath jsRespGetPlace = new JsonPath(getPlaceResponceStr);
		
		String addressFromResp = ReusableMethod.rawToJson(getPlaceResponceStr).getString("address");
		
		System.out.println(getPlaceResponceStr);
		System.out.println(addressFromResp);
		Assert.assertEquals(addressFromResp, "71 happy street, USA");
	}	

}
