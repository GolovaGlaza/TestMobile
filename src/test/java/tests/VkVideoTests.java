package tests;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Test;
import utils.LogcatUtils;

import java.time.Duration;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class VkVideoTests extends BaseTest {

    String vkVideoApp = "com.vk.vkvideo";

    @Test
    void videoPlayingTest(){
        AndroidDriver driver = (AndroidDriver) getWebDriver();

        driver.activateApp(vkVideoApp);

        vkMainPage.closeAdvertisementButton()
                .closeLoginIfPresents()
                .clickOnFirstVideoFromList();

        vkPlayerPage.shouldBePlaying()
                .clickOnPauseButton()
                .shouldNotBePlaying(Duration.ofSeconds(4));

        driver.terminateApp(vkVideoApp);
    }

}
