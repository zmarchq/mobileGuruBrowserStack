package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.RealDeviceConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public class RealMobileDriver implements WebDriverProvider {

    RealDeviceConfig config = ConfigFactory.create(RealDeviceConfig.class, System.getProperties());

    public static URL getAppiumServerUrl() {
        try {
            return new URL("http://localhost:4723/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }


    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        File app = getApp();

        UiAutomator2Options options = new UiAutomator2Options();
        options.merge(capabilities);

        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        options.setPlatformName(config.platformName());
        options.setDeviceName(config.deviceName());
        options.setPlatformVersion(config.platformVersion());
        options.setApp(app.getAbsolutePath());
        options.setAppPackage(config.appPackage());
        options.setAppActivity(config.appActivity());

        return new AndroidDriver(getAppiumServerUrl(), options);
    }

    private File getApp() {
        String appUrl = config.appUrl();
        String appPath = "src/test/resources/apps/app-alpha-universal-release.apk";

        File app = new File(appPath);
        if (!app.exists()) {
            try (InputStream in = new URL(appUrl).openStream()) {
                copyInputStreamToFile(in, app);
            } catch (IOException e) {
                throw new AssertionError("Failed to download application", e);
            }
        }
        return app;
    }
}