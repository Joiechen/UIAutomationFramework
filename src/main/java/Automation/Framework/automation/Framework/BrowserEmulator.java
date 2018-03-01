package Automation.Framework.automation.Framework;

import Automation.Business.Layer.*;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.opera.OperaDriver;

public class BrowserEmulator {

    static WebDriver browser;

    int timeout = Integer.parseInt(GlobalSettings.timeout);

    public BrowserEmulator() throws ExceptionUtil{
        int browserType = GlobalSettings.browserType;

        if (browserType == 1) {
            browser = new FirefoxDriver();
        } else if (browserType == 2) {
            browser = new ChromeDriver();
        } else if (browserType == 3) {
            browser = new InternetExplorerDriver();
        } else if (browserType == 4) {
            browser = new EdgeDriver();
        } else if (browserType == 5) {
            browser = new OperaDriver();
        } else if (browserType == 6) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--headless");
            browser = new ChromeDriver(chromeOptions);
        } else {

            throw new ExceptionUtil("Positioning syntax errors, lack of '=>'.");
        }
    }

    /**
     * Open the URL
     *
     * @param url
     */
    public void open(String url) {
        // pause(stepInterval);
        try {
            browser.get(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Set browser window wide and high.
     *
     * @param wide
     * @param high
     */
    public void setWindow(int wide, int high) {

        browser.manage().window().setSize(new Dimension(wide, high));
    }

    /**
     * Setting browser window is maximized
     *
     */
    public void maxWindow() {

        browser.manage().window().maximize();
    }

    /**
     * close the browser Simulates the user clicking the "close" button in the
     * title bar of a pop up
     */
    public void close() {
        browser.close();
    }

    /**
     * Quit the browser
     */
    public void quit() {
        browser.quit();
    }

    /**
     * Refresh the browser
     */
    public void refresh() {
        browser.navigate().refresh();
    }

    /**
     * Returns the title of the current page
     *
     * @return
     */
    public String getTitle() {
        return browser.getTitle();
    }

    /**
     * Returns the URL of the current page
     *
     * @return
     */
    public String getUrl() {
        return browser.getCurrentUrl();
    }


    /**
     * Accept warning box.
     */
    public void acceptAlert() {
        browser.switchTo().alert().accept();
    }

    /**
     * Dismisses the alert available.
     */
    public void dismissAlert() {
        browser.switchTo().alert().dismiss();
    }

    /**
     * TakesScreenshot.
     */
    public void TakesScreenshot(String file_path) throws Exception {
        File srcFile = ((TakesScreenshot)browser).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile,new File(file_path));
        try {

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
