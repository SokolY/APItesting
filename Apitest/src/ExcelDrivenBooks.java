


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.DataDriven;
import files.ReusableMethod;
import files.payload;

public class ExcelDrivenBooks {
	String respMsg; 
	DataDriven data = new DataDriven();
//	@Test (dataProvider = "Books")
//	@Test 
//	public void Addbook(String isbn, String aisle) {
	@Test
		public void Addbook() throws IOException {	
			RestAssured.baseURI = "http://216.10.245.166";
			HashMap<String, String> bookMap = new HashMap<>();
			System.out.println("Data from Excel: " + data.getData("Addbook", "Books").get(1));
			bookMap.put("name", data.getData("Addbook", "Books").get(1));
			bookMap.put("isbn", data.getData("Addbook", "Books").get(2));
			bookMap.put("aisle", data.getData("Addbook", "Books").get(3));
			bookMap.put("author", data.getData("Addbook", "Books").get(4));
//			bookMap.put("name", "Learn Rest Assured Automation with Java mapr");
//			bookMap.put("isbn", "kdvlk");
//			bookMap.put("aisle", "878");
//			bookMap.put("author", "John foo");
		String addBookResp = given().log().all().header("Content-Type", "application/json").body(bookMap).
				when().post("/Library/Addbook.php").
				then().log().all().extract().response().asString();
		
				JsonPath js = new JsonPath(addBookResp);
				
				
				System.out.println("Add book response is " + addBookResp);
				respMsg= js.get("ID");
				System.out.println("Parsed response message " + respMsg);
		}
	
//	@DataProvider (name = "Books")
//	public Object[][] BookProvider() {
//		Object[][] books = new Object[][] {{"cafslwq", "34236"}, {"qarclwq", "9846"}, {"vavlmdlwq", "9326"}};
//		return books;
//	}
//	
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
		
//		System.out.println("Delete response msg " + deleteResp);
		JsonPath js1 = ReusableMethod.rawToJson(deleteResp);
		String messageFromResp = js1.get("msg");
		System.out.println("Deleted  message is " + messageFromResp);
		Assert.assertEquals(messageFromResp, "book is successfully deleted");
	}
}

	

