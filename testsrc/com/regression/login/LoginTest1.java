package com.regression.login;

import com.invoiceplane.keyword.Engine.KyWordEngineExpaning;
import org.junit.Test;

import java.io.IOException;

public class LoginTest1 {



    KyWordEngineExpaning engine ;
    @Test
    public void allLoginPageTest() throws IOException {

        engine = new KyWordEngineExpaning();
        engine.startExecution("login");
    }

}
