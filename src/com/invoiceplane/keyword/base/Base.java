package com.invoiceplane.keyword.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

//responsible for driver excution
public class Base {

    public static WebDriver driver;


    public static WebDriver init_driver(String browserName)
    {
        if (browserName.equalsIgnoreCase("chrome")) {
           WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        return driver;
    }


 }
