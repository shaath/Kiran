package com.PrimusBank.Tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class BranchCreationTC 
{

	public static void main(String[] args) 
	{
		WebDriver driver=new FirefoxDriver();
		driver.get("http://primusbank.qedgetech.com/");
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		
		driver.findElement(By.id("txtuId")).sendKeys("Admin");
		driver.findElement(By.id("txtPword")).sendKeys("Admin");
		driver.findElement(By.id("login")).click();
		//Branch Creation
		driver.findElement(By.xpath("//*[@id='Table_01']/tbody/tr[2]/td/table/tbody/tr[2]/td/a/img")).click();
		driver.findElement(By.id("BtnNewBR")).click();
		driver.findElement(By.id("txtbName")).sendKeys("Miryalaguda123");
		driver.findElement(By.id("txtAdd1")).sendKeys("Miryalaguda123");
		driver.findElement(By.id("txtZip")).sendKeys("12345");
		
		WebElement cntry=driver.findElement(By.id("lst_counrtyU"));
		Select cntrydrop=new Select(cntry);
		cntrydrop.selectByVisibleText("INDIA");
		
		WebElement state=driver.findElement(By.id("lst_stateI"));
		Select statedrop=new Select(state);
		statedrop.selectByVisibleText("Andhra Pradesh");
		
		WebElement city=driver.findElement(By.id("lst_cityI"));
		Select citydrop=new Select(city);
		citydrop.selectByVisibleText("Hyderabad");
		
		driver.findElement(By.id("btn_insert")).click();
		
		Alert alt=driver.switchTo().alert();
		
		alt.accept();
		
		driver.findElement(By.xpath("//*[@id='Table_01']/tbody/tr/td[1]/a/img")).click();
		
		driver.findElement(By.xpath("//*[@id='Table_02']/tbody/tr/td[3]/a/img")).click();
		
		driver.close();

	}

}
