import org.testng.Assert;
import org.testng.asserts.Assertion;

import files.payload;
import io.restassured.path.json.JsonPath;

public class parseJson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsonPath jsonResp = new JsonPath(payload.ComplexJsonResponse());
		int coursesCount = jsonResp.getInt("courses.size()");
		System.out.println(coursesCount);
		int purchaseAmount = jsonResp.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		String courseTitle = jsonResp.getString("courses[0].title");
		int coursePrice;
		System.out.println(courseTitle);
		int RPAcopies = 0;
		int coursesPrice = 0;
		int courseCopies = 0;
		for(int i =0; i<coursesCount; i++) {
			int total = 0;
			courseTitle = jsonResp.getString("courses["+i+"].title");
			coursePrice = jsonResp.getInt("courses["+i+"].price");
			courseCopies = jsonResp.getInt("courses["+i+"].copies");
			total = coursePrice * courseCopies;
			coursesPrice += total;
			System.out.println(courseTitle);
			System.out.println(coursePrice);
			if(courseTitle.equals("RPA")) {
				RPAcopies = jsonResp.getInt("courses["+i+"].copies");
			}
			
		}
		System.out.println(RPAcopies);
		System.out.println(coursesPrice);
		Assert.assertEquals(purchaseAmount, coursesPrice);
		
	}

}
