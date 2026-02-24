package config;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.net.URL;
import java.time.Duration;

public class DriverFactory implements WebDriverProvider {
    @Override
    public WebDriver createDriver(Capabilities ignored) {

        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setDeviceName(MobileConfig.deviceName())
                .setPlatformVersion(MobileConfig.platformVersion())
                .setNewCommandTimeout(Duration.ofSeconds(120))
                .amend("appium:uiautomator2ServerLaunchTimeout", 60000)
                .amend("appium:uiautomator2ServerInstallTimeout", 60000)
                .amend("appium:adbExecTimeout", 60000);

        options.setCapability("chromedriverAutodownload", true);
        options.setCapability("ensureWebviewsHavePages", true);

        try {
            return new AndroidDriver(new URL(MobileConfig.appiumUrl()), options);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
