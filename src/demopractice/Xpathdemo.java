package demopractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Xpathdemo {
	WebDriver driver =null;
	@BeforeTest
	public void openbrowser(){
		System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
		driver= new ChromeDriver();
		driver.get("file:///C:/Program%20Files/Selelium%20Software/Offline%20Website/Offline%20Website/index.html");
		driver.findElement(By.id("email")).sendKeys("kiran@gmail.com");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.xpath("//button")).click();
		
		
		}
	@AfterTest
	public void closebrowser(){
		driver.quit();
	}
	
	
	@Test
	public void DemoXpath(){
		driver.findElement(By.xpath("//span[text()='Users']")).click();
		
		
		
	}
}
