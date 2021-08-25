package com.regression.login;

import com.invoiceplane.keyword.Engine.KeyWordEngine8AM;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginPageTest {

    KeyWordEngine8AM keyWordEngine8AM;
    @Test
    public void loginPageTest() throws IOException {

        keyWordEngine8AM = new KeyWordEngine8AM();
        keyWordEngine8AM.startExecution("login");
    }
}
