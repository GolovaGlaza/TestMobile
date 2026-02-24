package pages.alchemy;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class AlchemyMainPage {

    private final SelenideElement playButton =
            $(AppiumBy.xpath("(//*[@class='android.widget.Button'])[4]"));


    public AlchemyMainPage clickOnPlayButton() {
        playButton.click();
        return this;
    }

}
