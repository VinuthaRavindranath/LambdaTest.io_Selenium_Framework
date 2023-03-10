package org.lambda.ecomm.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.lambda.ecomm.exception.FrameworkException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.aspectj.util.FileUtil;


public class DriverFactory {

    public WebDriver driver;
    public Properties prop;
    public OptionsManager optionsManager;
    public static String highlight;
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

    /**
     * this method is initializing the driver on the basis of given browser name
     *
     * @return this returns the driver
     */
    public WebDriver initDriver(Properties prop) {

        optionsManager = new OptionsManager(prop);

        highlight = prop.getProperty("highlight").trim();
        String browserName = prop.getProperty("browser").toLowerCase().trim();

        System.out.println("browser name is : " + browserName);
        if (browserName.equalsIgnoreCase("chrome")) {
            tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
        } else if (browserName.equalsIgnoreCase("firefox")) {
            tlDriver.set(new FirefoxDriver(optionsManager.getFireFoxOptions()));
        } else if (browserName.equalsIgnoreCase("safari")) {
            tlDriver.set(new SafariDriver());
        } else if (browserName.equalsIgnoreCase("edge")) {
            tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
        } else {
            throw new FrameworkException("NO BROWSER FOUND EXCEPTION....");
        }

        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().get(prop.getProperty("url"));
        return getDriver();

    }

    /*
     * get the local thread copy of the driver
     */
    public synchronized static WebDriver getDriver() {
        return tlDriver.get();
    }

    /**
     * this method is reading the properties from the .properties file
     *
     * @return
     */
    public Properties initProp() {

        // mvn clean install -Denv="qa"
        // mvn clean install
        prop = new Properties();
        FileInputStream ip = null;
        String envName = System.getProperty("env");
        System.out.println("Running test cases on Env: " + envName);

        try {
            if (envName == null) {
                System.out.println("no env is passed....Running tests on Staging env...");
                ip = new FileInputStream("./src/test/resources/config/staging.config.properties");
            } else {
                switch (envName.toLowerCase().trim()) {
                    case "dev":
                        ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
                        break;
                    case "staging":
                        ip = new FileInputStream("./src/test/resources/config/staging.config.properties");
                        break;
                    case "uat":
                        ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
                        break;
                    case "prod":
                        ip = new FileInputStream("./src/test/resources/config/config.properties");
                        break;

                    default:
                        System.out.println("....Wrong env is passed....No need to run the test cases....");
                        throw new FrameworkException("WRONG ENV IS PASSED...");
                }

            }
        } catch (FileNotFoundException e) {

        }

        try {
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;
    }

    /**
     * take screenshot
     */
    public static String getScreenshot() {
        File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
        File destination = new File(path);
        try {
            FileUtil.copyFile(srcFile, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

}