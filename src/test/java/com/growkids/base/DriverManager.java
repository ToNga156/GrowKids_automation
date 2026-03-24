package com.growkids.base;

import com.growkids.utils.ConfigReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.options.BaseOptions;

import org.openqa.selenium.SessionNotCreatedException;

import java.net.URL;
import java.time.Duration;

public class DriverManager {

    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    private DriverManager() { }

    public static AppiumDriver getDriver() {
        if (driver.get() == null) {
            initDriver();
        }
        return driver.get();
    }

    public static void initDriver() {
        String platform = ConfigReader.getProperty("platform.name", "android");
        boolean useKobiton = ConfigReader.getBooleanProperty("kobiton.enabled", false);

        try {
            URL serverUrl = new URL(getServerUrl(useKobiton));

            AppiumDriver appiumDriver;
            if ("ios".equalsIgnoreCase(platform)) {
                XCUITestOptions options = buildIosOptions(useKobiton);
                appiumDriver = new IOSDriver(serverUrl, options);
            } else {
                UiAutomator2Options options = buildAndroidOptions(useKobiton);
                appiumDriver = new AndroidDriver(serverUrl, options);
            }

            int implicitWait = ConfigReader.getIntProperty("implicit.wait", 10); // seconds
            appiumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

            driver.set(appiumDriver);

        } catch (SessionNotCreatedException e) {
            throw new RuntimeException("Failed to start Appium session: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Appium driver", e);
        }
    }

    private static UiAutomator2Options buildAndroidOptions(boolean useKobiton) {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android").setAutomationName("UiAutomator2");

        String packageName = ConfigReader.getProperty("android.app.package", "com.growkids");
        String mainActivity = ConfigReader.getProperty("android.app.activity", ".MainActivity");
        String deviceName = useKobiton
            ? ConfigReader.getProperty("kobiton.device.name", "*Galaxy*").replace("\"", "").trim()
            : ConfigReader.getProperty("android.device.name", "emulator-5554");

        String platformVersion = ConfigReader.getProperty("android.platform.version", useKobiton ? "15" : "13");

        if (useKobiton) {
            String kobitonApp = ConfigReader.getProperty("kobiton.app");
           
            options.setDeviceName(deviceName);
            options.setPlatformVersion(platformVersion);
            addKobitonCapabilities(options);

            if (kobitonApp != null && !kobitonApp.isEmpty()) {
                options.setApp(kobitonApp);
            } else {
                options.setAppPackage(packageName);
                options.setAppActivity(mainActivity);
            }
        } else {
            String appPath = ConfigReader.getProperty("android.app.path");

            options.setDeviceName(deviceName);
            options.setPlatformVersion(platformVersion);

            
            if (appPath != null && !appPath.isEmpty()) {
                options.setApp(appPath);
            } else {
                options.setAppPackage(packageName);
                options.setAppActivity(mainActivity);
            }
        }
        return options;
    }

    private static XCUITestOptions buildIosOptions(boolean useKobiton) {
        XCUITestOptions options = new XCUITestOptions();
        options.setPlatformName("iOS").setAutomationName("XCUITest");

        String buddleId = ConfigReader.getProperty("ios.bundle.id", "com.growkids");
        String deviceName = useKobiton
            ? ConfigReader.getProperty("kobiton.device.name", "iPhone 15").replace("\"", "").trim()
            : ConfigReader.getProperty("ios.device.name", "iPhone 15");
        String platformVersion = ConfigReader.getProperty("ios.platform.version", "17.0");

        if (useKobiton) {
            String kobitonApp = ConfigReader.getProperty("kobiton.app");

            options.setDeviceName(deviceName);
            options.setPlatformVersion(platformVersion);
            addKobitonCapabilities(options);

            if (kobitonApp != null && !kobitonApp.isEmpty()) {
                options.setApp(kobitonApp);
            } else {
                options.setBundleId(buddleId);
            }
        } else {
            String appPath = ConfigReader.getProperty("ios.app.path");

            options.setDeviceName(deviceName);
            options.setPlatformVersion(platformVersion);
            
            if (appPath != null && !appPath.isEmpty()) {
                options.setApp(appPath);
            } else {
                options.setBundleId(buddleId);
            }
        }
        return options;
    }

    private static void addKobitonCapabilities(BaseOptions<?> options) {
        options.setCapability("deviceGroup", "KOBITON");
        options.setCapability("sessionName", "Growkids Automation");
        options.setCapability("sessionDescription", "Test session from Growkids framework");
        options.setCapability("captureScreenshots", true);

        String username = ConfigReader.getProperty("kobiton.username");
        String apiKey = ConfigReader.getProperty("kobiton.apiKey");

        if (username == null || username.isEmpty() || apiKey == null || apiKey.isEmpty()) {
            throw new RuntimeException("Kobiton credentials missing in config.properties");
        }

        options.setCapability("username", username);
        options.setCapability("accessKey", apiKey);
    }

    private static String getServerUrl(boolean useKobiton) {
        if (useKobiton) {
            return "https://api.kobiton.com/wd/hub";
        }
        return ConfigReader.getProperty("appium.url", "http://127.0.0.1:4723/wd/hub");
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}