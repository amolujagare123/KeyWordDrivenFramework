package com.invoiceplane.keyword.Engine;

import com.invoiceplane.keyword.base.Base;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class KeyWordEngine {

    public WebDriver driver;
    public Properties prop;
    public static Workbook book;
    public static Sheet sheet;
    public Base base;
    public WebElement element;


    public final String SENARIO_SHEET_PATH = "KeywordExcel/Keywords.xlsx";

    public void startExecution(String sheetName) throws IOException, InterruptedException {

        FileInputStream file = new FileInputStream(SENARIO_SHEET_PATH);

        /*String locatorName="";
        String locatorValue ="";
*/
        book= WorkbookFactory.create(file);
        sheet = book.getSheet(sheetName);

        int k=0;
        for (int i=0;i<sheet.getLastRowNum();i++)
        {
            String locatorName="";
            String locatorValue ="";
           String locatorColValue =  sheet.getRow(i+1).getCell(k+1).toString().trim();
            if(!locatorColValue.equalsIgnoreCase("NA")){
                locatorName = locatorColValue.split("=")[0].trim();
                locatorValue = locatorColValue.split("=")[1].trim();
            }

            String action  = sheet.getRow(i+1).getCell(k+2).toString().trim();
            String value  = sheet.getRow(i+1).getCell(k+3).toString().trim();

            switch (action)
            {
                case "open browser" :
                                    base = new Base();
                                    prop = base.init_properties();
                                    if(value.isEmpty() || value.equalsIgnoreCase("NA"))
                                        driver = base.init_driver(prop.getProperty("browser"));
                                    else
                                        driver = base.init_driver(value);
                                    break;
                case "enter url" :
                                    prop = base.init_properties();
                                    if(value.isEmpty() || value.equalsIgnoreCase("NA"))
                                       driver.get(prop.getProperty("browser"));
                                    else
                                        driver.get(value);
                                    break;
                case "quit" :
                                    driver.close();
                                    break;

                default:break;
            }

            switch(locatorName)
            {
                case "id" :
                    element = driver.findElement(By.id(locatorValue));
                    if(action.equalsIgnoreCase("sendkeys"))
                        element.sendKeys(value);
                    else if(action.equalsIgnoreCase("click"))
                    {element.click();
                    Thread.sleep(4000);}
                    //locatorName=null;
                    break;

                case "name" :
                    element = driver.findElement(By.name(locatorValue));
                    if(action.equalsIgnoreCase("sendkeys"))
                        element.sendKeys(value);
                    else if(action.equalsIgnoreCase("click"))
                    {element.click();
                        Thread.sleep(4000);}
                    //locatorName=null;
                    break;

                case "linkText" :
                    element = driver.findElement(By.linkText(locatorValue));
                    element.click();
                    Thread.sleep(4000);
                   // locatorName=null;
                    break;
                default:break;
            }
        }
    }
}
