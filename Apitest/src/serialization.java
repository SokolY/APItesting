import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class serialization {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String[] tp = {"shoe park", "shop"};
		
		AddPlaceRequest addPlace = new AddPlaceRequest();
		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		
		addPlace.setLocation(location);
		addPlace.setAccuracy(30);
		addPlace.setName("Frontline house");
		addPlace.setPhone_number("(+91) 983 893 3937");
		addPlace.setAddress("29, side layout, cohen 09");
		addPlace.setTypes(tp);
		addPlace.setWebsite("http://google.com");
		addPlace.setLanguage("French-IN");
		
		
		Response rs = given().queryParam("key", "qaclick123").body(addPlace).
		when().post("/maps/api/place/add/json").
		then().assertThat().statusCode(200).extract().response();
		
		String parsedResp = rs.asString();
		System.out.println(parsedResp);

	}

}
