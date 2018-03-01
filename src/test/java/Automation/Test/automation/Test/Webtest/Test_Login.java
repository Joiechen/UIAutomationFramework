package Automation.Test.automation.Test.Webtest;

import Automation.PageActions.automation.PageActions.Webtest.PageActions_Login;
import org.testng.Assert;
import org.testng.annotations.*;
import Automation.Business.Layer.ExceptionUtil;
import Automation.Framework.automation.Framework.*;

public class Test_Login {

    BrowserEmulator driver;
    PageActions_Login actionLogin;
    String baseUrl;

    @BeforeTest
    public void setup() throws ExceptionUtil {
        baseUrl = "https://github.com/login";
        driver = new BrowserEmulator();
    }

    @Test(priority = 0)// Try to sign with Invalid Password
    public void SignIntoGMailInvalidPassword() throws InterruptedException {
        actionLogin = new PageActions_Login();
        driver.open(baseUrl);
        actionLogin.loginWebandWait("joie_chen9@hotmail.com", "123123");
        String loginPageMsg = actionLogin.getWrongPasswordTextMessage();
        Assert.assertTrue(loginPageMsg.contains("Incorrect username or password."));
    }
}
