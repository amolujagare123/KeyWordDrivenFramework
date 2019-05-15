package com.regression.login;

import com.invoiceplane.keyword.Engine.KeyWordEngine;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {
    public KeyWordEngine keyWordEngine;

    @Test
    public void logintest() throws IOException, InterruptedException {
        keyWordEngine = new KeyWordEngine();
        keyWordEngine.startExecution("login");

    }

}
