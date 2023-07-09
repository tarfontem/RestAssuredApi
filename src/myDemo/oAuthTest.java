package myDemo;

import static io.restassured.RestAssured.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import files.ReusableMethods;
import io.restassured.path.json.JsonPath;


public class oAuthTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Get the code via login 
		
		/*
		System.setProperty("webdriver.chrome.driver",
				"D:\\Downloads\\chromedriver_win32\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
		
		driver.findElement(By.cssSelector("input[type = 'email']")).sendKeys("tarfontem@gmail.com");
		
		driver.findElement(By.cssSelector("input[type = 'email']")).sendKeys(Keys.ENTER);
		Thread thread = new Thread();
		thread.wait(3000);
		
		driver.findElement(By.cssSelector("input[type = 'password']")).sendKeys("fti221987");
	
		driver.findElement(By.cssSelector("input[type = 'password']")).sendKeys(Keys.ENTER);
		thread.wait(3000);
		
		String link = driver.getCurrentUrl();
		
		*/
		
		//https://rahulshettyacademy.com/getCourse.php?code=4%2F0AZEOvhWUwSBFS0MPq71LDhVHOZvj85Nl3R5qMJGcaTg3xhLXwlmxTYbNkMzeSLEf7JVmuQ&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none
		//The code above had to be commented out because google does not allow web automation on its pages as of now
		String link = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AZEOvhVY0r6PJtCyOoBjX_aVf3CNkW-An79tgB79_rywDNIS5ofS18O0IE6Nyz1KRbQFaw&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=2&prompt=none";
		
		String firstSplit = link.split("code=")[1];
		String code = firstSplit.split("&scope")[0];
		
		
		
		
	
		//Use the code to exchange for token
		String exchangeResponse = given().urlEncodingEnabled(false)
		.queryParams("code",code)
		.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type","authorization_code")
		.when().log().all()
		.post("https://www.googleapis.com/oauth2/v4/token")
		.asString();
		
		//String accessToken = ReusableMethods.parseResponse(exchangeResponse).getString("access_token");
		
		JsonPath js = new JsonPath(exchangeResponse);
		String accessToken = js.getString("access_token");
		System.out.println(accessToken);
		
		
		//Add the actual request to get the course from https://rahulshettyacademy.com/getCourse.php
		String coursesResponse = given().queryParam("access_token", accessToken)
		.when()
		.get("https://rahulshettyacademy.com/getCourse.php").asString();
		System.out.println(coursesResponse);

	}

}
