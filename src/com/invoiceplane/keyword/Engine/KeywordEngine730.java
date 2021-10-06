package com.invoiceplane.keyword.Engine;

import com.invoiceplane.keyword.base.Base;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static com.invoiceplane.keyword.base.Base.init_driver;

public class KeywordEngine730 extends Base {

    String sheetPath = "KeywordExcel/Keywords1.xlsx";


    public void startEngine(String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(sheetPath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getPhysicalNumberOfRows();

        for(int i=1;i<rowCount;i++)
        {
            XSSFRow row = sheet.getRow(i);

            String locatorColVal = row.getCell(1).toString().trim();
            String locatorName = "";
            String locatorValue = "";

            if(!locatorColVal.equalsIgnoreCase("NA"))
            {
                 // id=email
                locatorName = locatorColVal.split("=")[0];
                locatorValue = locatorColVal.split("=")[1];
            }

            String action = row.getCell(2).toString().trim();
            String value = row.getCell(3).toString().trim();

            // for the keywords / actions where no locator is involved

            switch (action)
            {
                case "open browser" :
                    driver = init_driver(value);
                    break;

                case "enter url" :
                    driver.get(value);
                    break;

                case "quit" :
                    driver.close();
                    break;

                case "assertWithPageTitle" :
                    Assert.assertEquals(driver.getTitle()
                            ,value,"wrong page");
                    break;

                case "assertWithUrl" :
                    Assert.assertEquals(driver.getCurrentUrl()
                            ,value,"wrong page");
                    break;

            }

            // for actions where locator is involved

            switch (locatorName)
            {
                case "id" :
                    if(action.equalsIgnoreCase("sendkeys"))
                        driver.findElement(By.id(locatorValue)).sendKeys(value);
                    else if(action.equalsIgnoreCase("click"))
                        driver.findElement(By.id(locatorValue)).click();

                    break;

                case "name" :
                    if(action.equalsIgnoreCase("sendkeys"))
                        driver.findElement(By.name(locatorValue)).sendKeys(value);
                    else if(action.equalsIgnoreCase("click"))
                        driver.findElement(By.name(locatorValue)).click();
                    break;

                case "linkText" :
                    driver.findElement(By.linkText(locatorValue)).click();
                    break;

            }

        }

    }
}
