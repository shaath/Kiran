package com.PrimusBank.Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.exec.ExecuteException;

import com.PrimusBank.Library.PrimusMAster;
import com.PrimusBank.Utilities.GenericMaster;

public class xlops {

	public static void main(String[] args) throws IOException
	{
		GenericMaster gm=new GenericMaster();
		PrimusMAster pm=new PrimusMAster();
		String xlpath="F:\\Kiran\\PrimusBank\\src\\com\\PrimusBank\\TestData\\BranchData.xlsx";
		String xlout="F:\\Kiran\\PrimusBank\\src\\com\\PrimusBank\\Results\\Branchrescol.xlsx";
 
		int rc=gm.row_Cnt(xlpath, "BrData");
		System.out.println(rc);
		pm.browser_LAunch("firefox");
		pm.primus_Launch("http://primusbank.qedgetech.com");
		pm.primus_Login("Admin", "Admin");
		for (int i = 1; i <= rc; i++)
		{
			String br=gm.read_Excel( xlpath, "BrData", i, 0);
			String add1=gm.read_Excel( xlpath, "BrData", i, 1);
			String zip=gm.read_Excel( xlpath, "BrData", i, 2);
			String cntry=gm.read_Excel( xlpath, "BrData", i, 3);
			String state=gm.read_Excel( xlpath, "BrData", i, 4);
			String city=gm.read_Excel( xlpath, "BrData", i, 5);
			
			System.out.println(br+"---"+add1+"---"+zip+"---"+cntry+"---"+state+"---"+city);
			
			String res=pm.primus_BranchCreation(br, add1, zip, cntry, state, city);
			
			if (res.equalsIgnoreCase("Pass")) 
			{
				gm.write_Green_Excel(xlpath, "BrData", i, 6, res,xlout);
				
			}
			else
			{
				gm.write_RED_Excel(xlpath, "BrData", i, 6, res,xlout);
			}
			
			
			xlpath=xlout;
		}
		

		pm.primus_Logout();
		pm.primus_Close();
	}

}
