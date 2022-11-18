package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:device.properties")
public interface LocalDriverConfig extends Config {

    @Key("appPackage")
    String appPackage();

    @Key("appActivity")
    String appActivity();

    @Key("local.deviceName")
    String deviceName();

    @Key("platformName")
    String platformName();

    @Key("local.platformVersion")
    String platformVersion();

    @Key("appUrl")
    String appUrl();
}
