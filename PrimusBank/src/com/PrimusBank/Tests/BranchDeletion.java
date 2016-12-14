package com.PrimusBank.Tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.server.browserlaunchers.Sleeper;

public class BranchDeletion {

	public static void main(String[] args) 
	{
		WebDriver driver=new FirefoxDriver();
		driver.get("http://primusbank.qedgetech.com/");
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		
		driver.findElement(By.id("txtuId")).sendKeys("Admin");
		driver.findElement(By.id("txtPword")).sendKeys("Admin");
		driver.findElement(By.id("login")).click();
		Sleeper.sleepTight(3);
		driver.findElement(By.xpath("//table[@id='Table_01']/tbody/tr[2]/td/table/tbody/tr[2]/td/a/img")).click();
		
		List<WebElement> rows=driver.findElements(By.xpath("//*[@id='DG_bankdetails']/tbody/tr"));
		for (int i = 1; i < rows.size()-1; i++)
		{
			List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
			String eId=cols.get(0).getText();
			System.out.println(eId);
			if (eId.equals("37"))
			{
				
				cols.get(7).findElement(By.tagName("img")).click();
				Sleeper.sleepTightInSeconds(5);

				Alert alt1=driver.switchTo().alert();
				String altText=alt1.getText();
				System.out.println(altText);
				alt1.accept();
				Sleeper.sleepTightInSeconds(5);
				String res=alt1.getText();
				System.out.println(res);
				alt1.accept();
				
				driver.findElement(By.xpath("//*[@id='Table_01']/tbody/tr/td[1]/a/img")).click();
				break;
			}
		}
		
		driver.findElement(By.xpath("//*[@id='Table_02']/tbody/tr/td[3]/a/img")).click();
		
		driver.close();

	}

}
