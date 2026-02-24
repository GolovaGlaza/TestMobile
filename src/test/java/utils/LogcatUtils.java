package utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;

import java.time.Duration;
import java.time.Instant;
import java.util.regex.Pattern;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LogcatUtils {
    private static AppiumDriver driver() {
        return (AppiumDriver) getWebDriver();
    }

    public static void clearLogcat() {
        driver().manage().logs().get("logcat");
    }

    public static boolean waitForPattern(Pattern pattern, Duration timeout) {
        Instant end = Instant.now().plus(timeout);

        while (Instant.now().isBefore(end)) {
            LogEntries logs = driver().manage().logs().get("logcat");

            for (LogEntry entry : logs) {
                if (pattern.matcher(entry.getMessage()).find()) {
                    return true;
                }
            }

            sleep(300);
        }

        return false;
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

