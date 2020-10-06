package com.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    public static Properties getPropertiesObject() throws IOException {
        FileInputStream file = new FileInputStream("Resources\\config.properties");
        Properties prop = new Properties();
        prop.load(file);
        return prop;
    }

    public static String getUrl() throws IOException {

        return getPropertiesObject().getProperty("url");
    }

    public static String getUsername() throws IOException {
        return  getPropertiesObject().getProperty("username");
    }

    public static String getPassword() throws IOException {
        return  getPropertiesObject().getProperty("password");
    }


    public static String getBrowser() throws IOException {
        return  getPropertiesObject().getProperty("browser");
    }



}
