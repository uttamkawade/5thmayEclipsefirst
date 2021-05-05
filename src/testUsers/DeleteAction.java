package testUsers;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class DeleteAction 
{
	WebDriver driver;
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
		driver.close();
	}
	
	//click action to delete all cancel if not default use dismiss. 
	@Test
	public void actinDelete() throws Exception{
		WebElement user=driver.findElement(By.xpath("//span[text()='Users']"));
		user.click();
		
		
			List<WebElement> Delete=driver.findElements(By.xpath("//span[text()='Delete']"));
			for (WebElement Deletebtn : Delete) {
				Deletebtn.click();
				Alert alert = driver.switchTo().alert();
				System.out.println(alert.getText());
				if(alert.getText().equals("Are you sure you want to delete this user")){
					alert.dismiss();
				}else{
				alert.accept();
				Thread.sleep(2000);
				}
			}
			
	}
	
	//click to delete all button
	@Test
	public void actinDelete1() throws Exception{
		WebElement user=driver.findElement(By.xpath("//span[text()='Users']"));
		user.click();
		
		
			List<WebElement> Delete=driver.findElements(By.xpath("//span[text()='Delete']"));
			for (WebElement Deletebtn : Delete) {
				Deletebtn.click();
				Alert alert = driver.switchTo().alert();
				System.out.println(alert.getText());
				
				if(alert.getText().equals("Are you sure you want to delete this user")){
					alert.accept();
					alert.accept();
				}else{
				alert.accept();
				Thread.sleep(2000);
				}
			}
	}
	
	//click on Add user and add user
   @Test
   public void singleAddUser() throws Exception{
	   WebElement user=driver.findElement(By.xpath("//span[text()='Users']"));
		user.click();
	   WebElement adduser = driver.findElement(By.xpath("//button[text()='Add User']"));
	   adduser.click();
	   
	   driver.findElement(By.id("username")).sendKeys("Uttam Kawade");
	   driver.findElement(By.id("mobile")).sendKeys("7218991947");
	   driver.findElement(By.id("email")).sendKeys("uttamkawde@gmail.com");
	   driver.findElement(By.id("course")).sendKeys("java");
	   
	   //click gender
	   WebElement male = driver.findElement(By.id("Male"));
	   male.click();
	   
	   //select state
	   WebElement select = driver.findElement(By.xpath("//select"));
	   Select sel = new Select(select);
	   
	   sel.selectByVisibleText("Maharashtra");
	 
	   driver.findElement(By.id("password")).sendKeys("1234567");
	   Thread.sleep(2000);
	   
	   //submit 
	   
	   driver.findElement(By.xpath("//button")).click();
	  
	  Alert alert =driver.switchTo().alert();
	  System.out.println(alert.getText());
		
	   alert.accept();
	   
	  
   }
   
 //multiple add users using dataprovider
   
   @Test(priority=4,dataProvider="users")
   public void multipleAdduser(String uname, String mob,String email,String course,String gender,String state,String pass ) throws Exception{
	   WebElement user=driver.findElement(By.xpath("//span[text()='Users']"));
		user.click();
	   WebElement adduser = driver.findElement(By.xpath("//button[text()='Add User']"));
	   adduser.click();
	   
	   driver.findElement(By.id("username")).sendKeys(uname);
	   driver.findElement(By.id("mobile")).sendKeys(mob);
	   driver.findElement(By.id("email")).sendKeys(email);
	   driver.findElement(By.id("course")).sendKeys(course);
	   //click gender
	   WebElement male = driver.findElement(By.id(gender));
	   male.click();
	   
	   //select state
	   WebElement select = driver.findElement(By.xpath("//select"));
	   Select sel = new Select(select);
	   
	   sel.selectByVisibleText(state);
	 
	   driver.findElement(By.id("password")).sendKeys(pass);
	   Thread.sleep(2000);
	   
	   //submit 
	   
	   driver.findElement(By.xpath("//button")).click();
	   
	   Alert alert =driver.switchTo().alert();
	   System.out.println(alert.getText());
	   alert.accept();
	  
	  
		driver.navigate().back();
		driver.navigate().back();
   }
		@DataProvider
		public Object[][] users() throws Exception{
			FileInputStream fis = new FileInputStream("Users.xls");
			Workbook workbook = Workbook.getWorkbook(fis);
			Sheet sh =workbook.getSheet("users");
			
			String dataArr[][]= new String[sh.getRows()-1][sh.getColumns()];
			for(int i=1;i<sh.getRows();i++) {
				for(int j=0;j<sh.getColumns();j++) {
					String data=sh.getCell(j,i).getContents();	
					dataArr[i-1][j]=data;
					
				}
			}
			return dataArr;
		}
		
		
		
		//Assart for add Users label
		@Test(priority=6)
		public void addUserLabel() throws Exception{
			
			//Add Expected data in arraylist
			 ArrayList<String> expected = new ArrayList<>();
			   expected.add("Username");
			   expected.add("Mobile");
			   expected.add("Email");
			   expected.add("Courses");
			   expected.add("Gender");
			   expected.add("State");
			   expected.add("Password");
			   System.out.println(expected);
			   Thread.sleep(2000);
			 WebElement user=driver.findElement(By.xpath("//span[text()='Users']"));
				user.click();
			   WebElement adduser = driver.findElement(By.xpath("//button[text()='Add User']"));
			   adduser.click();
			 
			   //actual data from 
			   List<WebElement> labels = driver.findElements(By.xpath("//button//preceding::label"));
			   
			  
			Assert.assertEquals(labels.get(0).getText(), "Username");
			 ArrayList<String> Actual = new ArrayList<>();
			for (WebElement label : labels) {
				String actualText= label.getText();
				
				Actual.add(actualText);
				//System.out.println(Actual);
			}
			System.out.println(Actual);
			Assert.assertEquals(Actual, expected);
		}
		
		
	//doubt for read data
		
		
//		@Test(priority=7,dataProvider= "userData")	
//		public void userlist(String srn,String uname,String uemail,String umobile,String ucourse,String ugender,String ustate,String uaction)
//		{
//			String user=null;
//		   // String user1=null;
//			ArrayList<String> explist = new ArrayList<String>();
//			
//			explist.add(srn);
//			explist.add(uname);
//			explist.add(uemail);
//			explist.add(umobile);
//			explist.add(ucourse);
//			explist.add(ugender);
//			explist.add(ustate);
//			explist.add(uaction);
//		
//			System.out.println("expected excel data"+explist);
//			
//			//List<WebElement> userlist = driver.findElements(By.xpath("//tr//td"));
//			
//			
//			 WebElement userb=driver.findElement(By.xpath("//span[text()='Users']"));
//			userb.click();
//		int row=1;	
//		ArrayList<String> actuallist = new ArrayList<String>();
//		
////		if(row==1)
////		{
////		for(int j=1;j<=8;j++)
////		   {
////		List<WebElement> 	headlist=driver.findElements(By.xpath("//tr["+row+"]/th"));
////		for (WebElement headlist1 : headlist) {
////			
////		
////		user=headlist1.getText();
////		System.out.println("in if++++"+user);
////		//Assert.assertEquals(user,explist.get(j-1));
////		
////		}//end of for
////		 
////		}
////		}//end of if
////		else
////		{
//			for(int i=2;i<=5;i++)
//			{
//			List<WebElement> 	datalist=driver.findElements(By.xpath("//tr["+i+"]/td"));
//			for (WebElement datalist1 : datalist) {
//			user=datalist1.getText();
//			System.out.println(user);
//			Assert.assertEquals(user, explist.get(i-1));
//			
//		   }	
////			row++;
////	    }//end of else
//		}
//			actuallist.add(user);
//			
//		
//		//Assert.assertEquals(actuallist, explist);
//		
//		
//		}
//		@DataProvider
//		  public Object[][] userData() throws Exception {
//			FileInputStream fis =new FileInputStream("LoginData.xls");
//			Workbook workbook = Workbook.getWorkbook(fis);
//			Sheet sh = workbook.getSheet("users");
//			String dataArr[][]= new String[sh.getRows()-1][sh.getColumns()];
//			for(int i=1;i<sh.getRows();i++) {
//				for(int j=0;j<sh.getColumns();j++) {
//					String data=sh.getCell(j,i).getContents();	
//					dataArr[i-1][j]=data;
//					
//				}
//			}
//			return dataArr;
//
//		}
}
   

