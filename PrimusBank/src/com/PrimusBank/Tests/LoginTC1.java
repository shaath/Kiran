package com.PrimusBank.Tests;

import java.io.IOException;

import com.PrimusBank.Library.PrimusMAster;

public class LoginTC1 {

	public static void main(String[] args) throws IOException
	{
		PrimusMAster pm=new PrimusMAster();
		
		pm.browser_LAunch("firefox");
		
		String res=pm.primus_Launch("http://primusbank.qedgetech.com");
		System.out.println("PrimusBank LAunch "+res);
		
		res=pm.primus_Login("Admin", "Admin");
		System.out.println("Primus BAnk Login "+res);
		
		res=pm.primus_BranchUpdation("32","45612");
		System.out.println("Primus Bank Branch updation "+res);
		
		res=pm.primus_Logout();
		System.out.println("Primus Bank Logout "+res);
		
		pm.primus_Close();
		System.out.println("Browser close successfull");
	}

}
