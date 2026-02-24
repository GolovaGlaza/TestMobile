package pages.vk;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Assertions;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;


public class VkMainPage {

    private final SelenideElement skipAdvertisementButton =
            $(AppiumBy.id("com.vk.vkvideo:id/close_btn_left"));

    private final SelenideElement skipLoginButton =
            $(AppiumBy.id("com.vk.vkvideo:id/fast_login_tertiary_btn"));

    private final SelenideElement firstVideFromList =
            $(AppiumBy.xpath("(//android.widget.ImageView[@resource-id=\"com.vk.vkvideo:id/preview\"])[1]"));

    public VkMainPage closeAdvertisementButton() {
        try {
            skipAdvertisementButton.shouldBe(Condition.visible, Duration.ofSeconds(20)).click();
        } catch (AssertionError ignored) {

        }
        return this;
    }

    public VkMainPage closeLoginIfPresents() {
        try {
            skipLoginButton.shouldBe(Condition.visible, Duration.ofSeconds(1));
        } catch (AssertionError e) {
            return this;
        }

        long end = System.currentTimeMillis() + 8_000;

        while (System.currentTimeMillis() < end) {
            skipLoginButton.click();

            try {
                skipLoginButton.shouldNotBe(Condition.visible, Duration.ofSeconds(1));
                return this;
            } catch (AssertionError ignored) {

            }
        }

        Assertions.fail("Skip login кнопка не исчезла");
        return this;
    }

    public VkMainPage clickOnFirstVideoFromList() {
        firstVideFromList.shouldBe(Condition.visible).click();
        return this;
    }

}

