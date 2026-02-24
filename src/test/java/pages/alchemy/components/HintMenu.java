package pages.alchemy.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;


public class HintMenu {

    private final SelenideElement totalHintsLocator =
            $(AppiumBy.xpath("//*[@text='Your hints']/following-sibling::android.view.View/android.widget.TextView"));

    private final SelenideElement watchAdvertisementButton =
            $(AppiumBy.xpath("//android.widget.TextView[@text='Watch']"));

    public SelenideElement getTotalHintsLocator(){
        return totalHintsLocator;
    }

    public String getTotalHintCount() {
        totalHintsLocator.shouldBe(Condition.visible, Duration.ofSeconds(8));
        String totalHintCount = totalHintsLocator.getText();
        System.out.println(totalHintCount);
        return totalHintCount;
    }

    public HintMenu clickOnWatchAdvertisementButton(){
        watchAdvertisementButton.shouldBe(Condition.visible, Duration.ofSeconds(60)).click(); //долго проподает лоадер
        return this;
    }

    public void getHintViaAdvertisement(AndroidDriver driver, String appPackage, Advertisement advertisement) {

        int before = Integer.parseInt(getTotalHintCount());

        for (int i = 0; i < 20; i++) {

            clickOnWatchAdvertisementButton();

            advertisement.handleAd(driver, appPackage);

            int after = Integer.parseInt(getTotalHintCount());

            if (after > before) {
                if (after != before + 2) {
                    throw new AssertionError("Подсказки увеличились некорректно");
                }
                return;
            }
        }

        throw new AssertionError("Не удалось получить подсказку через рекламу");
    }
}
