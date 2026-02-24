package tests;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AlchemyTests extends BaseTest{

    String alchemyApp = "com.ilyin.alchemy";

    @Test
    void testGetHintsForAdvertisement(){
        AndroidDriver driver = (AndroidDriver) getWebDriver();

        driver.activateApp(alchemyApp);

        alchemyMainPage.clickOnPlayButton();

        alchemyGamePage.clickOnAddTipsButton();

        hintMenu.getHintViaAdvertisement(driver, alchemyApp, advertisement);

        driver.terminateApp(alchemyApp);
    }
}



