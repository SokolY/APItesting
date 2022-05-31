import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;

public class SpecBuilderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
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
		
		
		RequestSpecification reqbuild = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123").
				setContentType(ContentType.JSON).build();
		ResponseSpecification resp = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		RequestSpecification req = given().spec(reqbuild).
				body(addPlace);
		
		
		Response rs = req.
		when().post("/maps/api/place/add/json").
		then().spec(resp).extract().response();
		
		String parsedResp = rs.asString();
		System.out.println(parsedResp);

	}

}
