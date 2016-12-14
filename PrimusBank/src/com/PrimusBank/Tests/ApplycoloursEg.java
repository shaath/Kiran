package com.PrimusBank.Tests;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ApplycoloursEg {

	public static void main(String[] args) throws IOException 
	{
		String xlpath="F:\\Kiran\\PrimusBank\\src\\com\\PrimusBank\\TestData\\Test.xlsx";
		String xlout="F:\\Kiran\\PrimusBank\\src\\com\\PrimusBank\\Results\\Testres1232.xlsx";
		FileInputStream fi=new FileInputStream(xlpath);
		
		XSSFWorkbook wb=new XSSFWorkbook(fi);
		XSSFSheet ws=wb.getSheet("Sheet1");
		
		CellStyle style=wb.createCellStyle();
		Font font=wb.createFont();
		
		for(int i = 1; i <= ws.getLastRowNum(); i++) 
		{
			XSSFRow r=ws.getRow(i);
			XSSFCell c=r.getCell(0);
			XSSFCell c1=r.getCell(1);
			XSSFCell c2=r.createCell(2);
			
			
			String f=c.getStringCellValue();
			String l=c1.getStringCellValue();
			
			System.out.println(f+"---"+l);
			
			c2.setCellValue("Pass");
		
//			style.setFillForegroundColor(IndexedColors.RED.getIndex());
//			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			
			font.setColor(IndexedColors.RED.getIndex());
			style.setFont(font);
			
			c2.setCellStyle(style);
			
		}
		
		FileOutputStream fo=new FileOutputStream(xlout);
		wb.write(fo);
		wb.close();
	}

}
