package core.driver;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.service.DriverService;

import java.io.IOException;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/*
 * Created by : vedikagupta
 * Date : 05/10/20
 */

class WebDriverThread {
    private WebDriver webDriver;
    private Browser<WebDriver, DriverService, Capabilities> browsers;

    private static BrowserTypes determineEffectiveBrowser(String... browser) {
        if (nonNull(browser) && browser.length != 0)
            return BrowserTypes.valueOf(browser[0].toUpperCase());
        if (nonNull(System.getProperty("browser"))) {
            return BrowserTypes.valueOf(System.getProperty("browser").toUpperCase());
        } else {
            return BrowserTypes.CHROME;
        }
    }

    WebDriver setDriver() {
        if (isNull(webDriver)) {
            Capabilities options;
            DriverService service;

            BrowserTypes browserType = determineEffectiveBrowser();

            switch (browserType) {
                case CHROME:
                    browsers = new Chrome();
                    break;
                default:
                    throw new InvalidArgumentException("Selected Browser not available!");
            }

            options = browsers.getOptions();
            try {
                service = browsers.startService();
                webDriver = browsers.getLocalDriver(service, options);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        browsers.setWindowSize(webDriver);
        return new Augmenter().augment(webDriver);
    }

    void clearCookies() {
        webDriver.manage().deleteAllCookies();
        webDriver.navigate().refresh();
    }

    void quitDriver() {
        if (nonNull(webDriver)) {
            webDriver.quit();
            webDriver = null;
        }
        browsers.stopService();
    }
}