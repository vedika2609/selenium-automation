package core.driver;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.service.DriverService;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/*
 * Created by : vedikagupta
 * Date : 05/10/20
 */
public interface Browser<W extends WebDriver, D extends DriverService, C extends Capabilities> {

    D startService() throws IOException;

    C getOptions();

    W getLocalDriver(D service, C capabilities);

    default void setWindowSize(WebDriver driver) {
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension defaultBrowserDimension = new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
        driver.manage().window().setSize(defaultBrowserDimension);
    }

    default void setTimeouts(WebDriver driver) {
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
    }

    void stopService();
}