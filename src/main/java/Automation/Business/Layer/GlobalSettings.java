package Automation.Business.Layer;

import java.util.Properties;
import java.io.FileInputStream;

public class GlobalSettings {

    public static Properties prop = getProperties();

    public static int Mode = Integer.parseInt(prop.getProperty("Mode", "2"));
    public static int browserType = Integer.parseInt(prop.getProperty("BrowserType", "2"));
    public static String Host = prop.getProperty("Host", "127.0.0.1:4444");
    public static String timeout = prop.getProperty("Timeout", "5");

    public static String getProperty(String property) {
        return prop.getProperty(property);
    }

    public static Properties getProperties() {
        Properties prop = new Properties();
        try {
            FileInputStream file = new FileInputStream("prop.properties");
            prop.load(file);
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop;
    }

}
