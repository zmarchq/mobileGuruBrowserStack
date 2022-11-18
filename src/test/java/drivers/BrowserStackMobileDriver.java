package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.BrowserStackConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserStackMobileDriver implements WebDriverProvider {


    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);

        BrowserStackConfig config = ConfigFactory.create(BrowserStackConfig.class, System.getProperties());
        System.out.println(config.projectName());

        // Set your access credentials
        mutableCapabilities.setCapability("browserstack.user", config.browserStackUser());
        mutableCapabilities.setCapability("browserstack.key", config.browserStackKey());

        // Set URL of the application under test
        mutableCapabilities.setCapability("app", config.appUrl());

        // Specify device and os_version for testing
        mutableCapabilities.setCapability("device", config.deviceName());
        mutableCapabilities.setCapability("os_version", config.platformVersion());

        // Set other BrowserStack capabilities
        mutableCapabilities.setCapability("project", config.projectName());
        mutableCapabilities.setCapability("build", config.build());
        mutableCapabilities.setCapability("name", config.name());

        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        try {
            return new RemoteWebDriver(new URL(config.browserStackUrl()), mutableCapabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Не удалось создать драйвер!", e);
        }
    }
}
