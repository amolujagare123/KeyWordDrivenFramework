package com.invoiceplane.keyword.Engine;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.invoiceplane.keyword.base.Base.init_driver;

// for 9 pm april batch - code continue

public class KyWordEngineExpaning {

     static WebDriver driver ;
    public final String SENARIO_SHEET_PATH="KeywordExcel\\Keywords1.xlsx";

    public void startExecution(String sheetName) throws IOException {

    FileInputStream fis = new FileInputStream(SENARIO_SHEET_PATH);
    XSSFWorkbook workbook = new XSSFWorkbook(fis);
    XSSFSheet sheet = workbook.getSheet(sheetName);

    int rowCount = sheet.getPhysicalNumberOfRows();



    for(int i=0;i<rowCount-1;i++)
    {
        XSSFRow row = sheet.getRow(i+1);

        String locatorColValue = row.getCell(1).toString().trim();
        String locatorName ="";
        String locatorValue="";

        if(!locatorColValue.equalsIgnoreCase("NA")) //
            // check whether the cell value is not equal to NA
        {
          //  locatorColValue = "id=email"

         String[]  arr   = locatorColValue.split("="); // {"id","email"}

            // arr[0] --> id
            // arr[1] --> email

            locatorName = locatorColValue.split("=")[0]; // id
            locatorValue = locatorColValue.split("=")[1]; // username
        }

        String action = row.getCell(2).toString().trim();
        String value = row.getCell(3).toString().trim();

        // ---- for actions where in no locator is needed ----

        String expected ="";
        String actual="";

        switch (action)
        {
            case "open browser" : driver = init_driver(value) ; break;

            case "enter url" : driver.get(value);  break;

            case "quit" : driver.close(); break;

            case "assertWithUrl" : expected = value;
                                  actual = driver.getCurrentUrl();
                                 try {
                                     Assert.assertEquals(actual, expected);
                                 }
                                 catch(AssertionError e)
                                 {
                                     e.printStackTrace();
                                 }
                                 break;

            case "assertWithPageTitle" : expected = value;
                                         actual = driver.getTitle();
                                        try {
                                            Assert.assertEquals(actual, expected);
                                        }
                                        catch(AssertionError e)
                                        {
                                            e.printStackTrace();
                                        }
                                        break;
        }

        // ---- for actions where in locator is needed ----

        switch (locatorName) {
            case "id":
                if (action.equalsIgnoreCase("click")) {
                    driver.findElement(By.id(locatorValue)).click();
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                } else if (action.equalsIgnoreCase("sendkeys"))
                    driver.findElement(By.id(locatorValue)).sendKeys(value);
                break;

            case "name":
                if (action.equalsIgnoreCase("click")){
                    driver.findElement(By.name(locatorValue)).click();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                 }

                            else if(action.equalsIgnoreCase("sendkeys"))
                                driver.findElement(By.name(locatorValue)).sendKeys(value);
                            break;

            case "linkText" : driver.findElement(By.linkText(locatorValue)).click();
                                 break;
        }

    }



    }
}
