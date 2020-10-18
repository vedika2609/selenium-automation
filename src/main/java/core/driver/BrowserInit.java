package core.driver;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.xml.XmlTest;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
 * Created by : vedikagupta
 * Date : 05/10/20
 */

// TODO: 05/10/20 : read browser from config file & add support for remote execution  
public class BrowserInit {
    private static ThreadLocal<WebDriverThread> threadLocalDriver;
    private static List<WebDriverThread> threadLocalDriverPool = Collections.synchronizedList(new LinkedList<>());

    @BeforeSuite(alwaysRun = true)
    public static void initDriver(XmlTest test) {
        threadLocalDriver = ThreadLocal.withInitial(() -> {
            WebDriverThread driverThread = new WebDriverThread();
            threadLocalDriverPool.add(driverThread);
            return driverThread;
        });
    }

    /**
     * Use this method to get the instance of the Webdriver in the
     * current thread's context
     *
     * @return an instance of the Webdriver
     */
    public static WebDriver getDriver() {
        return threadLocalDriver.get().setDriver();
    }

    @AfterMethod(alwaysRun = true)
    public static void clearCookies() {
        threadLocalDriver.get().clearCookies();
    }

    @AfterSuite(alwaysRun = true)
    public static void quitDriver() {
        threadLocalDriverPool.forEach((webDriverThread -> {
            webDriverThread.quitDriver();
            threadLocalDriver.remove();
        }));
    }
}
