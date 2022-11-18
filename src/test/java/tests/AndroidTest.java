package tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class AndroidTest extends TestBase {

    @Test
    void androidTest() {
        $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click();
        step("Search", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys("BrowserStack");
        });

        step("Verify content", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(CollectionCondition.sizeGreaterThan(0)));
    }

    @Test
    void openArticle() {
        $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_skip_button")).click();
        step("Search", () -> {
            $(AppiumBy.accessibilityId("Search Wikipedia")).click();
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text"))
                    .sendKeys("Appium");
        });

        step("Verify content", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"))
                .findBy(Condition.text("Appium")).shouldBe(Condition.visible));
    }

    @Test
    void checkSetupFlow() {
        $(AppiumBy.id("org.wikipedia.alpha:id/option_label")).shouldHave(enabled);
        $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        $(AppiumBy.id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("New ways to explore"));
        $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        $(AppiumBy.id("org.wikipedia.alpha:id/imageViewCentered")).shouldHave(enabled);
        $(AppiumBy.id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        $(AppiumBy.id("org.wikipedia.alpha:id/switchView")).shouldHave(not(checked));
    }
}
