package com.invoiceplane.keyword.base;

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

    public WebDriver driver;
    public Properties prop;

    public WebDriver init_driver(String browserName)
    {
        if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver.exe");
            driver = new FirefoxDriver();
        }
        return driver;
    }

    public Properties init_properties() throws IOException {
        prop=new Properties();
        FileInputStream ip = new FileInputStream("C:\\Users\\amol\\IdeaProjects\\KeyWordDrivenFramework\\Resources\\config.properties");
        prop.load(ip);
        return prop;
      //  ResourceBundle rb = ResourceBundle.getBundle("config.properties");
    }
}
