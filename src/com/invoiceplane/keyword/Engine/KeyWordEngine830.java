package com.invoiceplane.keyword.Engine;

import com.invoiceplane.keyword.base.Base;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static com.invoiceplane.keyword.base.Base.init_driver;

public class KeyWordEngine830 {


    public final String SENARIO_SHEET_PATH="KeywordExcel\\Keywords1.xlsx";
    static WebDriver driver;

    public void startExecution(String sheetName) throws IOException {

        FileInputStream fis = new FileInputStream(SENARIO_SHEET_PATH);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getPhysicalNumberOfRows();

        for(int i =1;i <rowCount ;i++)
        {
            XSSFRow row = sheet.getRow(i);

            String locatorColValue = row.getCell(1).toString().trim();

            String locatorName ="";
            String locatorValue ="";

            if(!locatorColValue.equalsIgnoreCase("NA"))
            {
                String[] strArr = locatorColValue.split("=");// id=email--> {"id", "email"}

                locatorName = strArr[0]; // id
                locatorValue = strArr[1]; // email

                /*locatorName = locatorColValue.split("=")[0]; // id=email--> {"id", "email"}
                locatorValue = locatorColValue.split("=")[1];*/
            }

            String action = row.getCell(2).toString().trim();
            String value = row.getCell(3).toString().trim();

            // lets write a switch case for the actions that doesnot need locator
            String expected="";
            String actual="";
            switch (action)
            {
                case "open browser" : driver = init_driver(value);break;

                case "enter url" : driver.get(value);break;
                case "quit" : driver.close();break;
                case "assertWithUrl" :expected = value;
                                        actual = driver.getCurrentUrl();
                                        Assert.assertEquals(actual,expected);
                                        break;
                case "assertWithPageTitle" :expected = value;
                                           actual = driver.getTitle();
                                            Assert.assertEquals(actual,expected);
                                            break;
            }

            // lets write a switch case for the actions that need locator

            switch (locatorName)
            {
                case "id" : if(action.equalsIgnoreCase("click"))
                    driver.findElement(By.id(locatorValue)).click();
                    if(action.equalsIgnoreCase("sendkeys"))
                        driver.findElement(By.id(locatorValue)).sendKeys(value);
                    break;
                case "name" : if(action.equalsIgnoreCase("click"))
                    driver.findElement(By.name(locatorValue)).click();
                    if(action.equalsIgnoreCase("sendkeys"))
                        driver.findElement(By.name(locatorValue)).sendKeys(value);
                    break;
                case "linkText" : if(action.equalsIgnoreCase("click"))
                    driver.findElement(By.linkText(locatorValue)).click();
                    break;
            }
        }




    }




}
