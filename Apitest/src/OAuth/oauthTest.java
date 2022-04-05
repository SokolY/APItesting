package OAuth;
import static io.restassured.RestAssured.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverInfo;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
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
		
		
		String codeUrl = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWhhpuCaW0PnWQiAgYq9IxF-bcOmUbbYVqQQIt_3NMt-HWQyhPU-dLtm-N1wwE_pYg&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
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
				
				//getCources
				String st1 = given().queryParam("access_token", accessToken).
					when().get("https://rahulshettyacademy.com/getCourse.php").asString();
				
				
				System.out.println(st1);
		
		
		
		
	}

}
