package com.invoiceplane.keyword.Engine;

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

import static com.invoiceplane.keyword.base.Base.init_driver;

public class KeyWordEngine730 {

    public final String SENARIO_SHEET_PATH="KeywordExcel\\Keywords1.xlsx";
    static WebDriver driver;

    public void startExecution(String sheetName) throws IOException {

        FileInputStream fis = new FileInputStream(SENARIO_SHEET_PATH);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getPhysicalNumberOfRows();

        for(int i=1;i<rowCount;i++)
        {
            XSSFRow row = sheet.getRow(i);

            String locatorColValue = row.getCell(1).toString().trim();
            String locatorName ="";
            String locatorValue ="";

            // locatorColValue = "id=email"

            if(!locatorColValue.equalsIgnoreCase("NA"))
            {
                locatorName = locatorColValue.split("=")[0]; // id
                locatorValue = locatorColValue.split("=")[1]; // email
            }

            String action = row.getCell(2).toString().trim();
            String value = row.getCell(3).toString().trim();

            // operation for the actions where locator is not involved

            switch (action)
            {
                case "open browser" : driver = init_driver(value); break;
                case "enter url" : driver.get(value); break;
                case "quit" : driver.close(); break;
                // assertion cases

                case "assertWithUrl" : String expected = value;
                                        String actual = driver.getCurrentUrl();
                    System.out.println("actual="+actual);
                    System.out.println("expected="+expected);
                    Assert.assertEquals(actual,expected);
                    break;

                case "assertWithPageTitle" : String expected1 = value;
                    String actual1 = driver.getTitle();
                    System.out.println("actual="+actual1);
                    System.out.println("expected="+expected1);
                    Assert.assertEquals(actual1,expected1);
                    break;

            }

            // operation for the actions where locator is involved

            switch (locatorName)
            {

                case  "id" :
                   // WebElement element = driver.findElement(By.id(locatorValue));
                    if(action.equalsIgnoreCase("click"))
                    driver.findElement(By.id(locatorValue)).click();
                    if(action.equalsIgnoreCase("sendkeys"))
                    driver.findElement(By.id(locatorValue)).sendKeys(value);
                    break;

                case  "name" :
                    //WebElement element1 = driver.findElement(By.name(locatorValue));
                    if(action.equalsIgnoreCase("click"))
                    driver.findElement(By.name(locatorValue)).click();
                    if(action.equalsIgnoreCase("sendkeys"))
                    driver.findElement(By.name(locatorValue)).sendKeys(value);
                    break;

                case  "linkText" :
                   // WebElement element2 = driver.findElement(By.linkText(locatorValue));
                    if(action.equalsIgnoreCase("click"))
                    driver.findElement(By.linkText(locatorValue)).click();
            }

        }

    }
}
