package com.regression.login;

import com.invoiceplane.keyword.Engine.KeyWordEngine830;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest1 {



   /* KyWordEngineExpaning engine ;
    @Test
    public void allLoginPageTest() throws IOException {

        engine = new KyWordEngineExpaning();
        engine.startExecution("login");
    }
*/



    @Test
    public void LoginpageTest() throws IOException {
        KeyWordEngine830 engine = new KeyWordEngine830();
        engine.startExecution("login");
    }
}
