package com.regression.login;

import com.invoiceplane.keyword.Engine.KeywordEngine730;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginPageTest {


    @Test
    public void loginPageTest() throws IOException {

       KeywordEngine730 keywordEngine730 = new KeywordEngine730();
        keywordEngine730.startEngine("login");
    }
}
