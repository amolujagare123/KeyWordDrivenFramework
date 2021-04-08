package com.regression.login;

import com.invoiceplane.keyword.Engine.KyWordEngineExpaning;
import org.junit.Test;

import java.io.IOException;

public class LoginTest1 {

    KyWordEngineExpaning keywordEngine;

    @Test
    public void allLoginPageTest() throws IOException {
        keywordEngine = new KyWordEngineExpaning();

        keywordEngine.startExecution("login");
    }

}
