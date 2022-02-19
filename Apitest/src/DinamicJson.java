


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import files.ReusableMethod;
import files.payload;

public class DinamicJson {
	@Test
	public void Addbook() {
		
//		String addBookResp = given().log().all().header("Content-Type", "application/json").body(payload.addBook()).
//		when().post("/Library/Addbook.php").
//		then().log().all().assertThat().statusCode(200).extract().response().asString();
//		
//		JsonPath js = ReusableMethod.rawToJson(addBookResp);
//		String respMsg= js.get("ID");
//		System.out.println("Response message" + respMsg);
		
			RestAssured.baseURI = "http://216.10.245.166";
		String addBookResp = given().log().all().header("Content-Type", "application/json").body(payload.addBook()).
				when().post("/Library/Addbook.php").
				then().log().all().extract().response().asString();
				JsonPath js = new JsonPath(addBookResp);
				String respMsg= js.get("ID");
				System.out.println("Response message " + respMsg);
		}
	}	
	
//}
