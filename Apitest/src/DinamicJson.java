


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReusableMethod;
import files.payload;

public class DinamicJson {
	@Test (dataProvider = "Books")
	public void Addbook(String isbn, String aisle) {
		
//		String addBookResp = given().log().all().header("Content-Type", "application/json").body(payload.addBook()).
//		when().post("/Library/Addbook.php").
//		then().log().all().assertThat().statusCode(200).extract().response().asString();
//		
//		JsonPath js = ReusableMethod.rawToJson(addBookResp);
//		String respMsg= js.get("ID");
//		System.out.println("Response message" + respMsg);
		
			RestAssured.baseURI = "http://216.10.245.166";
		String addBookResp = given().log().all().header("Content-Type", "application/json").body(payload.addBook(isbn, aisle)).
				when().post("/Library/Addbook.php").
				then().log().all().extract().response().asString();
		
				JsonPath js = new JsonPath(addBookResp);
				
				String respMsg= js.get("ID");
				System.out.println("Response message " + respMsg);
		}
	
	@DataProvider (name = "Books")
	public Object[][] BookProvider() {
		Object[][] books = new Object[][] {{"cdfsc", "34234"}, {"qwrcs", "9842"}, {"vdvlmdv", "932"}};
//		return new Object[][] {{"cdfsc","34234"}, {"qwrcs", "9842"}, {"vdvlmdv", "932"}};
		return books;
	}
}

	

