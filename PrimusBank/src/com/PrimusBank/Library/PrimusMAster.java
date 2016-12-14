package com.PrimusBank.Library;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.openqa.selenium.support.ui.Select;

public class PrimusMAster 
{
	//Global Variable section
	public static WebDriver driver;
	public static String expval,actval;
	public static FileInputStream fi;
	public static Properties pr;
	public static String prPath="F:\\Kiran\\PrimusBank\\src\\com\\PrimusBank\\Properties\\primus.properties";
	public static Alert alt;
	public static String altMsg=null;
	/*ProjectName  : Primus Bank
	ModuleName	 : Admin
	Function Name: browser_LAunch
	Arguments	 : br
	Return Type	 : NA
	Description  : It Will Launch Browser
	Author  	 : Sharath
	Created Date :2/12/2016	*/
	public void browser_LAunch(String br)
	{
		if (br.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if (br.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "F:\\Kiran\\PrimusBank\\src\\com\\PrimusBank\\Jars\\chromedriver.exe");
			driver=new ChromeDriver();
		}
		else if (br.equalsIgnoreCase("ie"))
		{
			System.setProperty("webdriver.ie.driver", "F:\\Kiran\\PrimusBank\\src\\com\\PrimusBank\\Jars\\IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		}
	}
	
	
	
	/*ProjectName  : Primus Bank
	ModuleName	 : Admin
	Function Name: primus_Launch
	Arguments	 : url
	Return Type	 : String
	Description  : It Will Launch Application
	Author  	 : Sharath
	Created Date :2/12/2016	*/
	
	public String primus_Launch(String Url) throws IOException
	{
		fi=new FileInputStream(prPath);
		pr=new Properties();
		pr.load(fi);
		expval="login";
		driver.get(Url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		actval=driver.findElement(By.id(pr.getProperty("login"))).getAttribute("id");
		if (expval.equalsIgnoreCase(actval))
		{
			return "Pass";
		}
		else
		{
			return "Fail";
		}
		
	}
	
	/*ProjectName  : Primus Bank
	ModuleName	 : Admin
	Function Name: primus_Login
	Arguments	 : u, p
	Return Type	 : String
	Description  : It Will Login Application
	Author  	 : Sharath
	Created Date :2/12/2016	*/
	public String primus_Login(String u,String p)
	{
		expval="Welcome to Admin";
		driver.findElement(By.id(pr.getProperty("username"))).sendKeys(u);
		driver.findElement(By.id(pr.getProperty("password"))).sendKeys(p);
		driver.findElement(By.id(pr.getProperty("login"))).click();
		Sleeper.sleepTightInSeconds(10);
		try 
		{
			actval=driver.findElement(By.xpath(pr.getProperty("welcome"))).getText();
		}
		catch (Exception e) 
		{
			
			alt=driver.switchTo().alert();
			actval=alt.getText();
			alt.accept();
		}
		
		if (expval.equalsIgnoreCase(actval))
		{
			return "Pass";
		}
		else
		{
			return actval+" Fail";
		}
	}
	
	/*ProjectName  : Primus Bank
	ModuleName	 : Admin
	Function Name: primus_Logout
	Arguments	 : NA
	Return Type	 : String
	Description  : It Will Logout Application
	Author  	 : Sharath
	Created Date :2/12/2016	*/
	public String primus_Logout()
	{
		expval="Login";
		driver.findElement(By.xpath(pr.getProperty("logout"))).click();
		actval=driver.findElement(By.id(pr.getProperty("login"))).getAttribute("id");
		if (expval.equalsIgnoreCase(actval))
		{
			return "Pass";
		}
		else
		{
			return "Fail";
		}
	}
	/*ProjectName  : Primus Bank
	ModuleName	 : Admin
	Function Name: primus_Close
	Arguments	 : NA
	Return Type	 : NA
	Description  : It Will close Browser
	Author  	 : Sharath
	Created Date :2/12/2016	*/
	
	public void primus_Close()
	{
		driver.close();
	}
	
	
	//Branch Creation
	
	public String primus_BranchCreation(String brName, String Add1,String zip, String Country,String State,String City)
	{
		expval="created Sucessfully";
		driver.findElement(By.xpath("//*[@id='Table_01']/tbody/tr[2]/td/table/tbody/tr[2]/td/a/img")).click();
		driver.findElement(By.id("BtnNewBR")).click();
		driver.findElement(By.id("txtbName")).sendKeys(brName);
		driver.findElement(By.id("txtAdd1")).sendKeys(Add1);
		driver.findElement(By.id("txtZip")).sendKeys(zip);
		
		WebElement cntry=driver.findElement(By.id("lst_counrtyU"));
		Select cntrydrop=new Select(cntry);
		cntrydrop.selectByVisibleText(Country);
		
		WebElement state=driver.findElement(By.id("lst_stateI"));
		Select statedrop=new Select(state);
		statedrop.selectByVisibleText(State);
		
		WebElement city=driver.findElement(By.id("lst_cityI"));
		Select citydrop=new Select(city);
		citydrop.selectByVisibleText(City);
		
		driver.findElement(By.id("btn_insert")).click();
		
		Alert alt=driver.switchTo().alert();
		actval=alt.getText();
		alt.accept();
		driver.findElement(By.xpath("//*[@id='Table_01']/tbody/tr/td[1]/a/img")).click();
		if (actval.contains(expval))
		{
			return "Pass";
		}
		else
		{
			return "Fail";
		}
		
		
	}
	
	//branch Updation
	
	public String primus_BranchUpdation(String EmpId,String uzip)
	{
		expval="updated Sucessfully";
		driver.findElement(By.xpath("//*[@id='Table_01']/tbody/tr[2]/td/table/tbody/tr[2]/td/a/img")).click();
		List<WebElement> rows=driver.findElements(By.xpath("//*[@id='DG_bankdetails']/tbody/tr"));
		for (int i = 1; i < rows.size()-1; i++)
		{
			List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
			String eId=cols.get(0).getText();
//			System.out.println(eId);
			
			if (eId.equals(EmpId))
			{
				cols.get(6).click();
				driver.findElement(By.id("txtzipu")).sendKeys(uzip);
				driver.findElement(By.id("btnupdate")).click();
				
				Alert alt=driver.switchTo().alert();
				actval=alt.getText();
				alt.accept();
				driver.findElement(By.xpath(".//*[@id='Table_01']/tbody/tr/td[1]/a/img")).click();
				break;
			}
			
		}
		
		if (actval.contains(expval)) 
		{
			return "Pass";
		}
		else
		{
			return "Fail";
		}
	}

}