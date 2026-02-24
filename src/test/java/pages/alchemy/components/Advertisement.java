package pages.alchemy.components;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class Advertisement {

    private final SelenideElement yourHintsTitle =
            $(AppiumBy.xpath("//*[@text='Your hints']"));


    private final SelenideElement playableSkip =
            $(AppiumBy.id("m-playable-skip"));

    private final SelenideElement playableClose =
            $(AppiumBy.id("m-playable-close"));

    private final SelenideElement closeByDesc =
            $(AppiumBy.xpath("//*[contains(@content-desc,'close') or contains(@content-desc,'Close')]"));

    private final SelenideElement closeById =
            $(AppiumBy.xpath("//*[contains(@resource-id,'close') or contains(@resource-id,'Close')]"));

    private final SelenideElement closeAd =
            $(AppiumBy.xpath("//RelativeLayout[@resource-id=\"com.ilyin.alchemy:id/mbridge_video_templete_container\"]/RelativeLayout/FrameLayout/RelativeLayout[2]/ImageView[2]"));


    /**
     * Возвращает true если рекламу закрыли (вернулись на Your hints)
     * Возвращает false если это был Yandex AdActivity (мы просто сделали back и вышли)
     */
    public boolean handleAd(AndroidDriver driver, String appPackage) {

        long end = System.currentTimeMillis() + 160_000;

        while (System.currentTimeMillis() < end) {

            String act = safeActivity(driver);

            // Яндекс-реклама — выходим сразу
            if (act.contains("com.yandex.mobile.ads.common.AdActivity")) {
                try { driver.navigate().back(); } catch (Exception ignored) {}
                try { driver.activateApp(appPackage); } catch (Exception ignored) {}
                return false;
            }

            //  Если вернулись на подсказки — успех
            if (yourHintsTitle.exists() && yourHintsTitle.is(visible)) {
                return true;
            }

            if (tryClick(playableSkip)) { sleep(400); continue; }
            if (tryClick(playableClose)) { sleep(400); continue; }
            if (tryClick(closeById)) { sleep(400); continue; }
            if (tryClick(closeByDesc)) { sleep(400); continue;}
            if (tryClick(closeAd)){ sleep(400); continue;}

            sleep(500);
        }

        try { driver.navigate().back(); } catch (Exception ignored) {}
        try { driver.activateApp(appPackage); } catch (Exception ignored) {}
        return false;
    }

    private boolean tryClick(SelenideElement el) {
        try {
            if (!el.exists()) return false;
            el.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private String safeActivity(AndroidDriver driver) {
        try {
            String a = driver.currentActivity();
            return a == null ? "" : a;
        } catch (Exception e) {
            return "";
        }
    }
}

