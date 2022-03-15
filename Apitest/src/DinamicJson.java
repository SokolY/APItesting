


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReusableMethod;
import files.payload;

public class DinamicJson {
	String respMsg; 
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
				
				
				System.out.println("Add book response is " + addBookResp);
				respMsg= js.get("ID");
				System.out.println("Parsed response message " + respMsg);
		}
	
	@DataProvider (name = "Books")
	public Object[][] BookProvider() {
		Object[][] books = new Object[][] {{"cafslwq", "34236"}, {"qarclwq", "9846"}, {"vavlmdlwq", "9326"}};
//		return new Object[][] {{"cdfsc","34234"}, {"qwrcs", "9842"}, {"vdvlmdv", "932"}};
		return books;
	}
	
	@Test
	public void deleteBook() {
		RestAssured.baseURI = "http://216.10.245.166";
		String str = respMsg;	
		System.out.println("Book from added class " + str);
		String deleteResp = given().log().all().header("Content-Type", "application/json").body("{\r\n"
				+ " \r\n"
				+ "\"ID\" : \""+str+"\"\r\n"
				+ " \r\n"
				+ "} \r\n"
				+ "").
		when().post("/Library/DeleteBook.php").
		then().log().all().extract().response().asString();
		
		System.out.println("Delete response msg " + deleteResp);
		JsonPath js1 = ReusableMethod.rawToJson(deleteResp);
		String messageFromResp = js1.get("msg");
		System.out.println("Deleted  message is " + messageFromResp);
		Assert.assertEquals(messageFromResp, "book is successfully deleted");
	}
}

	

