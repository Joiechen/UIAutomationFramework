package Automation.PageActions.automation.PageActions.Webtest;

import Automation.Business.Layer.ExceptionUtil;
import Automation.Framework.automation.Framework.WebElements;
import Automation.PageFactory.automation.PageFactory.Webtest.PageObjects_Login;


public class PageActions_Login {
    WebElements elements;
    PageObjects_Login po;

    public void loginWebandWait(String username,String password) throws ExceptionUtil {
        elements.type(po.username,username);
        elements.type(po.password,password);
        elements.click(po.loginbtn);
    }

    public String getWrongPasswordTextMessage() throws ExceptionUtil{
        return elements.getText(po.errormsg);
    }
}
