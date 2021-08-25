package com.invoiceplane.keyword.Engine;

import com.invoiceplane.keyword.base.Base;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;

public class KeyWordEngine8AM extends Base {
    public final String SENARIO_SHEET_PATH = "KeywordExcel\\Keywords1.xlsx";
    //static WebDriver driver;

    public void startExecution(String sheetName) throws IOException {

        FileInputStream fis = new FileInputStream(SENARIO_SHEET_PATH);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getPhysicalNumberOfRows();

        for(int i=0;i<rowCount;i++) {

            String locatorColVal = sheet.getRow(i+1).getCell(1).toString().trim();
            String locatorName="";
            String locatorValue="";

            if(!locatorColVal.equalsIgnoreCase("NA")) // locatorColVal = "id=email" {"id","email"}
            {
                locatorName = locatorColVal.split("=")[0];
                locatorValue = locatorColVal.split("=")[1];
            }

            String action = sheet.getRow(i+1).getCell(2).toString().trim();
            String value = sheet.getRow(i+1).getCell(3).toString().trim();

            // --> for the actions where no locator is involved

            switch (action)
            {
                case "open browser" : init_driver(value);break;
                case "enter url" : driver.get(value);break;
                case "quit" : driver.close();break;
                case "assertWithUrl" :
                            Assert.assertEquals(driver.getCurrentUrl(),value,
                                    "this is not a correct page");
                            break;
                case "assertWithPageTitle" :
                            Assert.assertEquals(driver.getTitle(),value,
                                    "this is not a correct page");
                            break;
            }

            // --> for the actions where  locator is involved

            switch (locatorName)
            {
                case "id" :
                    if(action.equalsIgnoreCase("click"))
                        driver.findElement(By.id(locatorValue)).click();
                    if(action.equalsIgnoreCase("sendkeys"))
                        driver.findElement(By.id(locatorValue)).sendKeys(value);
                    break;
                case "name" :
                    if(action.equalsIgnoreCase("click"))
                        driver.findElement(By.name(locatorValue)).click();
                    if(action.equalsIgnoreCase("sendkeys"))
                        driver.findElement(By.name(locatorValue)).sendKeys(value);
                    break;

                case "linkText" :
                       driver.findElement(By.linkText(locatorValue)).click();
                             break;
            }

        }

    }
}