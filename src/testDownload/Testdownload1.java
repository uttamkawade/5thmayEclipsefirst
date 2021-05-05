package testDownload;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Testdownload1
{
	WebDriver driver =null;
	@BeforeMethod
	public void openbrowser(){
		System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
		driver= new ChromeDriver();
		driver.get("file:///C:/Program%20Files/Selelium%20Software/Offline%20Website/Offline%20Website/index.html");
		driver.findElement(By.id("email")).sendKeys("kiran@gmail.com");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.xpath("//button")).click();
		driver.findElement(By.xpath("//span[text()='Downloads']")).click();
		
		}
	@AfterMethod
	public void closebrowser(){
		driver.quit();;
	}
	//32bit click
	@Test(priority=1)
	public void verifyTable32bit()
	{
		
		for(int i=2;i<9;i++)
		{
			//to click on 32bit source
				
				WebElement colm5= driver.findElement(By.xpath("//tr["+i+"]/td[5]"));  
				if(colm5.getText().contains("32bit")){
					colm5.click();
					System.out.println(driver.getCurrentUrl());
					driver.navigate().back();	
		}
			
	}
		}
	
	
	@Test(priority=2)
	public void verifyTable32bit1()
	{
		
		for(int i=2;i<9;i++)
		{
			//to print on 32bit source
				
				WebElement colm5= driver.findElement(By.xpath("//tr["+i+"]/td[5]"));  
				if(colm5.getText().contains("32bit")){
				String actual =colm5.getText();
				String expected ="32bit";
				System.out.println(actual);
				Assert.assertEquals(actual, expected);
				}
		}
	}
	
	
	//64bit click
		@Test(priority=3)
		public void verifyTable64bit()
		{
			
			for(int i=2;i<9;i++)
			{
				//to click on 64bit source
					
					WebElement colm5= driver.findElement(By.xpath("//tr["+i+"]/td[6]"));  
					if(colm5.getText().contains("64bit")){
						colm5.click();
						System.out.println(driver.getCurrentUrl());
						driver.navigate().back();	
			}
				
		}
			}
				//Official website click and print ids
	@Test(priority=4)
	public void verifyTableOfficial()
	{	
		for(int i=2;i<9;i++)
		{
		//to click on official source
				WebElement link= driver.findElement(By.xpath("//tr["+i+"]/td[8]"));  
					link.click();
					//System.out.println("current url>>"+driver.getCurrentUrl());
					System.out.println(link.getText());
					}
		 Set<String> windowIds =driver.getWindowHandles();  //7 values in a set
	       for(String id: windowIds){
	    	  
	    	   driver.switchTo().window(id);
	    	   System.out.println("ids of a multiple windows--"+id);
	    	   System.out.println("Url of current page --"+driver.getCurrentUrl());
	    	   
	    		   
	    	   
	       }
		
	}
	
	@Test(priority=5)
	public void verifyTableOfficial1()
	{	
		for(int i=2;i<9;i++)
		{
		//to print on official source
				WebElement link= driver.findElement(By.xpath("//tr["+i+"]/td[8]"));  
					String Text1= link.getText();
					String Text2 ="Official Website";
					Assert.assertEquals(Text1, Text2);
				}
		
	}
	@Test(priority=6)
	public void verifyTablecommon()
	{	
		for(int i=2;i<9;i++)
		{
	
		WebElement link1= driver.findElement(By.xpath("//tr["+i+"]/td[7]"));  
		if(link1.getText().contains("2.52.0")||link1.getText().contains("3.9.0")||link1.getText().contains("Common")){
			link1.click();
			
			System.out.println(driver.getCurrentUrl());
			driver.navigate().back();
		}
		}
		}
	//click on last four coloumn
	
	@Test(priority=7)
	public void TestTable(){
		for(int i= 2;i<8;i++){    //here only change for loop then operation change
			for(int j =1;j<9;j++){   //here only change row and coluomn count =7 then test run
				//WebElement link =driver.findElement(By.xpath("//tr["+i+"]/td["+j+"]"));
				if(j==5){
					WebElement colm5= driver.findElement(By.xpath("//tr["+i+"]/td[5]"));  
					if(colm5.getText().contains("32bit")){
						colm5.click();
						System.out.println(driver.getCurrentUrl());
						driver.navigate().back();
					}else{
						System.out.println("in 5th colomn" + (j-4) +" the row no data"); 
						
					}
					}else if(j==6){
						System.out.println("started 6th col.");
						WebElement colm6= driver.findElement(By.xpath("//tr["+i+"]/td[6]"));   
						if(colm6.getText().contains("64bit")){
							colm6.click();
							System.out.println(driver.getCurrentUrl());
							driver.navigate().back();
						}else{
							System.out.println("in 6th colomn" + (j-4) +" the row no data");
						}
						
					}else if(j==7){
						WebElement link1= driver.findElement(By.xpath("//tr["+i+"]/td[7]"));  
						if(link1.getText().contains("2.52.0")||link1.getText().contains("3.9.0")||link1.getText().contains("Common")){
							link1.click();
							System.out.println(driver.getCurrentUrl());
							driver.navigate().back();
						}else{
								System.out.println("in 7th colomn" + (j-4) +" the row no data");
							}
					}else if(j==8){
						WebElement link2= driver.findElement(By.xpath("//tr["+i+"]/td[8]"));  
						link2.click();
						System.out.println(driver.getCurrentUrl());
						
					}
			}
		}
	}
	//
	//Verify footer and click on Javabykiran
	@Test(priority=8)
	public void verifyFooter() throws Exception{
		String t1=driver.findElement(By.xpath("//footer")).getText();
		String t2="Design for Selenium Automation Test V 2.3.0\n"+"Copyright © 2005-2019 JavaByKiran. All rights reserved.";
		Assert.assertEquals(t1, t2);
		Thread.sleep(1000);
		driver.findElement(By.partialLinkText("Java")).click();
		driver.navigate().back();
	}
	
	//assert whole LHS navigator
		//@Test(priority=9)
		//public void verifyLeftNavigate(){
			//String s1=null;
			//ArrayList<String> al = new ArrayList<>();
			//al.add("Kiran");
			//al.add("Online");
			//al.add("MAIN NAVIGATION");
			//al.add("Dashboard");
			//al.add("Users");
			//al.add("Operators");
		//	al.add("Useful Links");
		//	al.add("Downloads");
			//al.add("Logout");
			
			//for (String string : al) {
				// s1=string;
				 //System.out.println("expected>>>"+s1);
			//}
			
			
		//	String t1=driver.findElement(By.xpath("//section[@class='sidebar']")).getText();
			//String t2 ="Kiran\n" + "Online\n" + "MAIN NAVIGATION\n" + "Dashboard\n" + "Users\n" + "Operators\n" + "Useful Links\n" + "Downloads\n" + "Logout\n";
	       // System.out.println(t1);
	       
	   //Assert.assertEquals(t1, s1);


		//}
  //home link click		
		@Test(priority=10)
		public void verifyHomelink() {
		System.out.println("Before title>>>"+driver.getTitle());
		System.out.println("Before title>>>"+driver.getCurrentUrl());
		
		driver.findElement(By.partialLinkText("Home")).click();
		System.out.println("After title>>>"+driver.getTitle());
		System.out.println("Before title>>>"+driver.getCurrentUrl());

		}
		
		//no run properly 
		@Test(priority=11)
		public void verifyDownloadlistHead(){
			String head1 =null;
			String s1=null;
			ArrayList<String> al = new ArrayList<>();
			al.add("Sr");
			al.add("Name");
			al.add("vendor");
			al.add("Version");
			al.add("32bit");
			al.add("64bit");
			al.add("Common");
			//al.add("Official Source*For prevoius and latest releases");
			
			for (String string : al) {
				 s1=string;
			}
			
			for(int i=1;i<8;i++){
				 
				WebElement head=driver.findElement(By.xpath("//tbody/tr/th["+i+"]"));
				head1=head.getText();
				System.out.println(head1);
				
			}
			Assert.assertEquals(head1, s1);
			
		
			
		}
		
	}