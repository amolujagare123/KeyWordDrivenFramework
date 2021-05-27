package com.regression.login;

import com.invoiceplane.keyword.Engine.KeyWordEngine730;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest1 {


    @Test
    public void LoginpageTest() throws IOException {
        KeyWordEngine730 engine = new KeyWordEngine730();
        engine.startExecution("login");
    }
}
