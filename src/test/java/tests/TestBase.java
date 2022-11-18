package tests;

import com.codeborne.selenide.Configuration;
import drivers.BrowserStackMobileDriver;
import drivers.LocalMobileDriver;
import drivers.RealMobileDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

public class TestBase {

    @BeforeAll
    public static void setUp() {
        switch (System.getProperty("deviceHost").toUpperCase()) {
            case "LOCAL":
                Configuration.browser = LocalMobileDriver.class.getName();
                Configuration.browserSize = null;
                break;
            case "REAL":
                Configuration.browser = RealMobileDriver.class.getName();
                Configuration.browserSize = null;
                break;
            case "BROWSERSTACK":
                Configuration.browser = BrowserStackMobileDriver.class.getName();
                Configuration.browserSize = null;
                break;
        }
    }

    @BeforeEach
    public void startDriver()
    {
        addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    public void tearDown() {
        String sessionId = sessionId().toString();
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        closeWebDriver();

   if (System.getProperty("deviceHost")
           .equalsIgnoreCase("BROWSERSTACK")) Attach.video(sessionId);
    }
}
