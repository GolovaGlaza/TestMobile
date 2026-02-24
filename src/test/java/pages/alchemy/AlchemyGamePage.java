package pages.alchemy;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import pages.alchemy.components.HintMenu;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class AlchemyGamePage {

    private final SelenideElement addTipsButton =
            $(AppiumBy.xpath("(//*[@class='android.view.View'])[7]"));

    public HintMenu clickOnAddTipsButton() {

        HintMenu hintMenu = new HintMenu();

        long end = System.currentTimeMillis() + 10_000;

        while (System.currentTimeMillis() < end) {
            try {
                addTipsButton
                        .shouldBe(Condition.visible, Duration.ofSeconds(2))
                        .click();

                if (hintMenu.getTotalHintsLocator().exists()) {
                    hintMenu.getTotalHintsLocator()
                            .shouldBe(Condition.visible, Duration.ofSeconds(2));
                    return hintMenu;
                }

            } catch (Exception ignored) {}

            sleep(500);
        }

        throw new AssertionError("Не удалось открыть HintMenu");
    }
}
