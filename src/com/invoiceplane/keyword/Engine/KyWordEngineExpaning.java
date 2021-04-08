package com.invoiceplane.keyword.Engine;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;

import static com.invoiceplane.keyword.base.Base.init_driver;

// for 9 pm april batch - code continue

public class KyWordEngineExpaning {

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

    }



    }
}
