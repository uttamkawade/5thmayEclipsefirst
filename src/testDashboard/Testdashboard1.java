package testDashboard;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Testdashboard1 
{
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
	
	
	
	//click on dashboard
	@Test(priority=1)
	
	public void clickDashboard(){
		WebElement dash=driver.findElement(By.xpath("//span[text()='Dashboard']"));
		dash.click();
		
		String id = driver.getWindowHandle();
		System.out.println("currnt window id>>"+id);
		System.out.println("current url>>>>>"+driver.getCurrentUrl());
		
		
		
	}
	
	//click on users
	
@Test(priority=2)
	
	public void clickuser() throws Exception{
		WebElement user=driver.findElement(By.xpath("//a[@href='users.html']"));
		user.click();
		
		String id = driver.getWindowHandle();
		System.out.println("currnt window id>>"+id);
		System.out.println("current url>>>>>"+driver.getCurrentUrl());
		Thread.sleep(1000);
		driver.navigate().back();
		System.out.println("title>>>"+driver.getTitle());
	
	}
//click on operators

@Test(priority=3)

public void clickoperators() throws Exception{
	WebElement operator=driver.findElement(By.xpath("//a[@href='operators.html']"));
	operator.click();
	
	String id = driver.getWindowHandle();
	System.out.println("currnt window id>>"+id);
	System.out.println("current url>>>>>"+driver.getCurrentUrl());
	Thread.sleep(1000);
	driver.navigate().back();
	System.out.println("title>>>"+driver.getTitle());

}

//click on downloads
@Test(priority=4)

public void clickdownloads() throws Exception{
	WebElement downloads=driver.findElement(By.xpath("//a[@href='downloads.html']"));
	downloads.click();
	
	String id = driver.getWindowHandle();
	System.out.println("currnt window id>>"+id);
	System.out.println("current url>>>>>"+driver.getCurrentUrl());
	Thread.sleep(1000);
	driver.navigate().back();
	System.out.println("title>>>"+driver.getTitle());

}


	
//click on course offered more info all simultaneously and get window ids
	@Test(priority=5)
	public void testCourcesoffer() throws InterruptedException{
		String mainWindowhandle=driver.getWindowHandle();
		System.out.println("ID of the main window of dashboard>>>"+mainWindowhandle);
		
		List<WebElement>links= driver.findElements(By.xpath("//a[text()='More info ']"));
		
		for (WebElement link : links) {
			link.click(); // 5 win open
		
				Thread.sleep(2000);
		}
				Set<String> WindowsId =driver.getWindowHandles();   //5 values in the set
				for (String id : WindowsId) {
					System.out.println("id of the multiple windows >>>"+id);
					
					driver.switchTo().window(id);     // comment becouse of its switch to syllabus window this is logout if dashboard page
					System.out.println("URL of the current page---"+driver.getCurrentUrl());
					
					
				}
				
	}
	


	//click out logout
	@Test(priority=6)

	public void clicklogout() throws Exception{
		WebElement logout=driver.findElement(By.xpath("//span[text()='Logout']"));
		logout.click();
		Thread.sleep(1000);
		if(driver.getTitle().equals("JavaByKiran | Log in")){
			System.out.println("logout succesfully");
			
		}

	}
	// test click on only one more info and get handle window.
		@Test(priority=6)
		public void winTest() {
			
			String expUrl ="file:///C:/Program%20Files/Selelium%20Software/Offline%20Website/Offline%20Website/pages/pdf/selenium-testing-syllabus-jbk.pdf";
			System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.get("file:///C:/Program%20Files/Selelium%20Software/Offline%20Website/Offline%20Website/index.html");
			driver.findElement(By.id("email")).sendKeys("kiran@gmail.com");
			driver.findElement(By.id("password")).sendKeys("123456");
			driver.findElement(By.xpath("//button")).click();// 1window
			
			String parentId = driver.getWindowHandle();
			System.out.println("parent window id is:  "+parentId);
			driver.findElement(By.partialLinkText("More")).click();// 2 window
			
			Set<String> allWins= driver.getWindowHandles();// 2 vlaues
			
			for(String str: allWins) {
				if(!str.equals(parentId)) {    // cheack parent and child window
					System.out.println("child window id: "+str);
					driver.switchTo().window(str);
					Assert.assertEquals(driver.getCurrentUrl(), expUrl);
				}
			}
				}
}