package core.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.service.DriverService;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/*
 * Created by : vedikagupta
 * Date : 05/10/20
 */
public class Chrome implements Browser<WebDriver, DriverService, Capabilities> {
    private ChromeDriverService chromeDriverService;

    @Override
    public ChromeDriverService startService() throws IOException {
        if (Objects.isNull(chromeDriverService)) {
            WebDriverManager.chromedriver().setup();
            chromeDriverService = new ChromeDriverService.Builder()
                    .usingDriverExecutable(new File(WebDriverManager.chromedriver().getDownloadedDriverPath()))
                    .usingAnyFreePort()
                    .build();
            chromeDriverService.start();
        }
        return chromeDriverService;
    }

    @Override
    public WebDriver getLocalDriver(DriverService service, Capabilities capabilities) {
        return new ChromeDriver((ChromeDriverService) service, (ChromeOptions) capabilities);
    }

    @Override
    public ChromeOptions getOptions() {
        String printOptions = "{\"recentDestinations\": " +
                "[{\"id\": \"Save as PDF\", \"origin\": \"local\", \"account\": \"\"}]" +
                ", \"selectedDestinationId\": \"Save as PDF\", \"version\": 2}";

        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();

        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("profile.default_content_setting_values.notifications", 1);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("credentials_enable_service", false);
        prefs.put("printing.print_preview_sticky_settings.appState", printOptions);

        options.setHeadless(false);
        options.addArguments("--kiosk-printing");
        options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
        options.setExperimentalOption("prefs", prefs);
        return options;
    }

    @Override
    public void stopService() {
        if (Objects.nonNull(chromeDriverService))
            chromeDriverService.stop();
    }
}