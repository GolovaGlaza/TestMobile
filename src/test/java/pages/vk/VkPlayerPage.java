package pages.vk;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import utils.LogcatUtils;

import java.time.Duration;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.Wait;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VkPlayerPage {

    private final SelenideElement pauseButton =
            $(AppiumBy.accessibilityId("Pause"));

    private final SelenideElement playButton =
            $(AppiumBy.accessibilityId("Play"));

    private final SelenideElement videoDisplay =
            $(AppiumBy.id("com.vk.vkvideo:id/video_display"));


    public VkPlayerPage shouldBePlaying() {

        LogcatUtils.clearLogcat();

        Pattern playingPattern = Pattern.compile(
                "(STATE_PLAYING)|(state=3)|(onPlaybackStateChanged.*3)",
                Pattern.CASE_INSENSITIVE
        );

        assertTrue(
                LogcatUtils.waitForPattern(playingPattern, Duration.ofSeconds(10)),
                "Видео не перешло в состояние PLAYING по (логам)"
        );

        return this;
    }

    public VkPlayerPage shouldNotBePlaying(Duration timeout) {
        Pattern playingPattern = Pattern.compile(
                "(STATE_PLAYING)|(state=3)|(onPlaybackStateChanged.*3)",
                Pattern.CASE_INSENSITIVE
        );

        LogcatUtils.clearLogcat();

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        assertFalse(
                LogcatUtils.waitForPattern(playingPattern, timeout),
                "Видео перешло в PLAYING, хотя не должно было"
        );

        return this;
    }


    public VkPlayerPage clickOnPauseButton(){
        Wait().withTimeout(Duration.ofSeconds(8))
                .pollingEvery(Duration.ofMillis(500))
                .until(__ -> {
                    videoDisplay.click();
                    return pauseButton.is(Condition.visible);
                });

        pauseButton.click();
        return this;
    }
}
