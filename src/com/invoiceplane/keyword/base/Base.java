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
    public static Properties prop;

    public static WebDriver init_driver(String browserName)
    {
        if (browserName.equalsIgnoreCase("chrome")) {
           WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        return driver;
    }

    /*public static Properties init_properties() throws IOException {
        prop=new Properties();
        FileInputStream ip = new FileInputStream("C:\\Users\\PC\\IdeaProjects\\KeyWordDrivenFramework\\Resources\\config.properties");
        prop.load(ip);
        return prop;
      //  ResourceBundle rb = ResourceBundle.getBundle("config.properties");
    }

    public static String getUrl() throws IOException {

        return  init_properties().getProperty("url");
    }*/
 }
