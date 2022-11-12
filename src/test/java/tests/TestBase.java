package tests;

import com.codeborne.selenide.Configuration;
import drivers.BrowserStackMobileDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    @BeforeAll
    public static void setUp() {
        Configuration.browser = BrowserStackMobileDriver.class.getName();
        Configuration.browserSize = null;

    }

    @BeforeEach
    public void startDriver() {
        open();
    }

    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }
}
