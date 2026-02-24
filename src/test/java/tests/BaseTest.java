package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import config.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import pages.alchemy.AlchemyGamePage;
import pages.alchemy.AlchemyMainPage;
import pages.alchemy.components.Advertisement;
import pages.alchemy.components.HintMenu;
import pages.vk.VkMainPage;
import pages.vk.VkPlayerPage;

import static com.codeborne.selenide.Selenide.open;


public abstract class BaseTest {

    protected VkMainPage vkMainPage;
    protected VkPlayerPage vkPlayerPage;

    protected AlchemyMainPage alchemyMainPage;
    protected AlchemyGamePage alchemyGamePage;
    protected HintMenu hintMenu;
    protected Advertisement advertisement;


    @BeforeEach
    void setUp(){
        Configuration.browser = DriverFactory.class.getName();
        Configuration.browserSize = null;
        Configuration.timeout = 40_000;
        open();

        vkMainPage = new VkMainPage();
        vkPlayerPage = new VkPlayerPage();

        alchemyMainPage = new AlchemyMainPage();
        alchemyGamePage = new AlchemyGamePage();
        hintMenu = new HintMenu();
        advertisement = new Advertisement();
    }

    @AfterEach
    void tearDown(){
        Selenide.closeWebDriver();
    }
}
