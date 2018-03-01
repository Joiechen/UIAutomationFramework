package Automation.Framework.automation.Framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import Automation.Business.Layer.ExceptionUtil;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import Automation.Business.Layer.GlobalSettings;

import java.util.Set;

public class WebElements {

    static WebDriver browser;

    int timeout = Integer.parseInt(GlobalSettings.timeout);

    public WebElement getElement(String xpath) throws ExceptionUtil{
        if (xpath.contains("=>") == false){
            throw new ExceptionUtil("Positioning syntax errors, lack of '=>'.");
        }

        String by = xpath.split("=>")[0];
        String value = xpath.split("=>")[1];

        if (by.equals("id")) {
            WebElement element = browser.findElement(By.id(value));
            return element;
        } else if (by.equals("name")) {
            WebElement element = browser.findElement(By.name(value));
            return element;
        } else if (by.equals("class")) {
            WebElement element = browser.findElement(By.className(value));
            return element;
        } else if (by.equals("linkText")) {
            WebElement element = browser.findElement(By.linkText(value));
            return element;
        } else if (by.equals("xpath")) {
            WebElement element = browser.findElement(By.xpath(value));
            return element;
        } else if (by.equals("css")) {
            WebElement element = browser.findElement(By.cssSelector(value));
            return element;
        } else {
            throw new ExceptionUtil("Please enter the correct targeting elements,'id','name','class','xpaht','css'.");
        }
    }

    /**
     * Wait for an element within a certain amount of time
     *
     * @param xpath
     *            the element's xpath the second
     */
    public void waitElement(String xpath, int second) throws ExceptionUtil{

        if (xpath.contains("=>") == false) {
            throw new ExceptionUtil("Positioning syntax errors, lack of '=>'.");
        }

        String by = xpath.split("=>")[0];
        String value = xpath.split("=>")[1];
        By findelement = null;

        if (by.equals("id")) {
            findelement = By.id(value);
        } else if (by.equals("name")) {
            findelement = By.name(value);
        } else if (by.equals("class")) {
            findelement = By.className(value);
        } else if (by.equals("linkText")) {
            findelement = By.linkText(value);
        } else if (by.equals("xpath")) {
            findelement = By.xpath(value);
        } else if (by.equals("css")) {
            findelement = By.cssSelector(value);
        } else {
            throw new ExceptionUtil("Please enter the correct targeting elements,'id','name','class','xpaht','css'.");
        }
        new WebDriverWait(browser, second).until(ExpectedConditions
                .presenceOfElementLocated(findelement));

    }


    /**
     * Click the page element
     *
     * @param xpath
     *            the element's xpath
     */
    public void click(String xpath) throws ExceptionUtil{

        waitElement(xpath, timeout);
        WebElement element = getElement(xpath);
        try {
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Type text at the page element<br>
     * Before typing, try to clear existed text
     *
     * @param xpath
     *            , the element's xpath
     * @param text
     *            , the input text
     */
    public void type(String xpath, String text) throws ExceptionUtil{

        waitElement(xpath, timeout);
        WebElement element = getElement(xpath);

        try {
            element.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            element.sendKeys(text);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Right click element.
     *
     * @param xpath
     *            the element's xpath
     */
    public void rightClick(String xpath) throws ExceptionUtil{
        waitElement(xpath, timeout);

        Actions action = new Actions(browser);
        WebElement element = getElement(xpath);

        action.contextClick(element).perform();
    }

    /**
     * click and hold element.
     *
     * @param xpath
     *            the element's xpath
     */
    public void clickAndHold(String xpath) throws ExceptionUtil{
        waitElement(xpath, timeout);

        Actions action = new Actions(browser);
        WebElement element = getElement(xpath);

        action.clickAndHold(element).perform();
    }

    /**
     * Drags an element a certain distance and then drops it.
     *
     * @param el_xpath
     *            , the element's xpath
     * @param ta_xpath
     *            , the element's xpath
     */
    public void dragAndDrop(String el_xpath, String ta_xpath) throws ExceptionUtil{
        waitElement(el_xpath, timeout);
        waitElement(ta_xpath, timeout);

        Actions action = new Actions(browser);
        WebElement el = getElement(el_xpath);
        WebElement ta = getElement(ta_xpath);

        action.dragAndDrop(el, ta).perform();
    }

    /**
     * Click the element by the link text.
     *
     * @param text
     *            , the element's link text
     */
    public void clickText(String text) {

        WebElement element = browser.findElement(By.partialLinkText(text));
        try {
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Select the select tag value
     *
     * @param xpath
     * @param value
     */
    public void selectValue(String xpath, String value) throws ExceptionUtil{

        waitElement(xpath, timeout);

        WebElement element = getElement(xpath);
        Select sel = new Select(element);
        sel.selectByValue(value);
    }


    /**
     * Enter the iframe
     *
     * @param xpath
     *            , the iframe's xpath
     */
    public void enterFrame(String xpath) throws ExceptionUtil{
        waitElement(xpath, timeout);
        WebElement element = getElement(xpath);
        browser.switchTo().frame(element);
    }

    /**
     * Leave the iframe
     */
    public void leaveFrame() {
        browser.switchTo().defaultContent();
    }

    /**
     * Open the new window and switch the handle to the newly opened window.
     *
     * @param xpath
     *            , the open windows element xpath
     */
    public void openOneWindow(String xpath) throws ExceptionUtil{

        waitElement(xpath, timeout);

        String sreach_handle = browser.getWindowHandle();
        WebElement element = getElement(xpath);
        try {
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Set<String> handles = browser.getWindowHandles();
        for (String handle : handles) {
            if (handle.equals(sreach_handle) == false) {
                browser.switchTo().window(handle);
            }
        }

    }

    /**
     * Return text from specified web element.
     *
     * @param xpath
     * @return
     */
    public String getText(String xpath) throws ExceptionUtil{
        WebElement element = getElement(xpath);
        return element.getText();
    }

    /**
     * Gets the value of an element attribute.
     *
     * @return
     */
    public String getAttribute(String xpath, String attribute) throws ExceptionUtil{
        WebElement element = getElement(xpath);
        String value = element.getAttribute(attribute);
        return value;
    }


}
