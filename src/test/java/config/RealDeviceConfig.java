package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:device.properties")
public interface RealDeviceConfig extends Config {

    @Key("real.deviceName")
    String deviceName();

    @Key("platformName")
    String platformName();

    @Key("real.platformVersion")
    String platformVersion();

    @Key("appPackage")
    String appPackage();

    @Key("appActivity")
    String appActivity();

    @Key("appUrl")
    String appUrl();
}
