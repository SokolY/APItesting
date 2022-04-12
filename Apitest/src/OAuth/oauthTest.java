package OAuth;
import static io.restassured.RestAssured.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverInfo;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojo.CoursesResp;
import pojo.RahulShettyResponse;
import pojo.api;
import pojo.webAutomation;
public class oauthTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
//		System.setProperty("webdriver.chrome.driver", "D:\\Yura\\Trainings\\APITestting\\ChromeDriver\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
		
//		System.setProperty("webdriver.gecko.driver", "D:\\Yura\\Trainings\\APITestting\\ChromeDriver\\geckodriver-v0.30.0-win64\\geckodriver.exe");
//		WebDriver driver = new FirefoxDriver();
//		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
//		Thread.sleep(2000);
//		driver.findElement(By.id("identifierId")).sendKeys("");	
//		Thread.sleep(2000);
//		driver.findElement(By.id("identifierNext")).click();
//		Thread.sleep(3000);
//		driver.quit();
		
		
		String codeUrl = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWhyblBXydeRbG-TH4uWu1mV2efmpEGbXqTND72jOt9yuqpNKiALJwacyvOjDRumKA&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";
		
		String splitedStr = codeUrl.split("code=")[1];
		System.out.println(splitedStr);
		String parsedCode = splitedStr.split("&scope")[0];
		System.out.println("Parse code is: " + parsedCode);
		

		//getAccessToken
				String accessTokenResponse = given().urlEncodingEnabled(false).
						queryParams("code", parsedCode).				 
				queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
				queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W").
				queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php").
				queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W").
				queryParams("grant_type", "authorization_code").
				when().log().all().
				post("https://www.googleapis.com/oauth2/v4/token").asString();
				
				JsonPath js = new JsonPath(accessTokenResponse);
				String accessToken = js.getString("access_token");
				System.out.println("Access token is " + accessToken);
				
////				getCources
//				String st1 = given().queryParam("access_token", accessToken).
//					when().get("https://rahulshettyacademy.com/getCourse.php").asString();
//				
//				
//				System.out.println(st1);
//				
				
				RahulShettyResponse rr = given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
						.when().get("https://rahulshettyacademy.com/getCourse.php").as(RahulShettyResponse.class);
				
				
				System.out.println("LinkedIn is: " + rr.getLinkedIn());
				
				
				System.out.println("Expertise is: " + rr.getExpertise());
				
				
//				System.out.println("Courses are: " + );
				
				for(webAutomation wa : rr.getCourses().getWebAutomation()) {
					System.out.println("webAutomation course title is: " + wa.getCourseTitle());
					if(wa.getCourseTitle().equals("Cypress")) {
						System.out.println("Price for course Cypress is: " + wa.getPrice()); 
					}
				}
//				
//				
				
				List<api> apiCourses = rr.getCourses().getApi();
				for(int i = 0; i<apiCourses.size(); i++) {
					System.out.println("Course title is: " + apiCourses.get(i).getCourseTitle());
					System.out.println("Course price is: " + apiCourses.get(i).getPrice());
				}
				
		
		
		
		
	}

}
