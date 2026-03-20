package com.growkids.base;

import com.growkids.utils.ConfigReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.options.BaseOptions;

import java.net.URL;
import java.time.Duration;
import io.github.cdimascio.dotenv.Dotenv;

public class DriverManager {

    private static final Dotenv dotenv = Dotenv.load();
    private static final String KOBITON_APIKEY = dotenv.get("KOBITON_APIKEY");

    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    private DriverManager() {
    }

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
            URL url = new URL(getServerUrl(useKobiton));

            AppiumDriver appiumDriver;

            if ("ios".equalsIgnoreCase(platform)) {
                XCUITestOptions options = buildIosOptions(useKobiton);
                appiumDriver = new IOSDriver(url, options);
            } else {
                UiAutomator2Options options = buildAndroidOptions(useKobiton);
                appiumDriver = new AndroidDriver(url, options);
            }

            int implicitWait = ConfigReader.getIntProperty("implicit.wait", 10);
            appiumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

            driver.set(appiumDriver);

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Appium driver", e);
        }
    }

    // ================= ANDROID =================
    private static UiAutomator2Options buildAndroidOptions(boolean useKobiton) {
        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");

        if (useKobiton) {
            options.setDeviceName(ConfigReader.getProperty("kobiton.device.name", "Galaxy S21"));
            options.setPlatformVersion(ConfigReader.getProperty("android.platform.version", "13"));

            addKobitonCapabilities(options);

            String kobitonApp = ConfigReader.getProperty("kobiton.app");
            if (kobitonApp != null && !kobitonApp.isEmpty()) {
                options.setCapability("app", kobitonApp); // ex: kobiton-store:755535
            } else {
                options.setAppPackage(ConfigReader.getProperty("android.app.package", "com.growkids"));
                options.setAppActivity(ConfigReader.getProperty("android.app.activity", ".MainActivity"));
            }

        } else {
            options.setDeviceName(ConfigReader.getProperty("android.device.name", "emulator-5554"));
            options.setPlatformVersion(ConfigReader.getProperty("android.platform.version", "13"));

            String appPath = ConfigReader.getProperty("android.app.path");
            if (appPath != null && !appPath.isEmpty()) {
                options.setApp(appPath);
            } else {
                options.setAppPackage(ConfigReader.getProperty("android.app.package", "com.growkids"));
                options.setAppActivity(ConfigReader.getProperty("android.app.activity", ".MainActivity"));
            }
        }

        return options;
    }

    // ================= IOS =================
    private static XCUITestOptions buildIosOptions(boolean useKobiton) {
        XCUITestOptions options = new XCUITestOptions();

        options.setPlatformName("iOS");
        options.setAutomationName("XCUITest");

        if (useKobiton) {
            options.setDeviceName(ConfigReader.getProperty("kobiton.device.name", "iPhone 15"));
            options.setPlatformVersion(ConfigReader.getProperty("ios.platform.version", "17.0"));

            addKobitonCapabilities(options);

            String kobitonApp = ConfigReader.getProperty("kobiton.app");
            if (kobitonApp != null && !kobitonApp.isEmpty()) {
                options.setCapability("app", kobitonApp);
            } else {
                options.setBundleId(ConfigReader.getProperty("ios.bundle.id", "com.growkids"));
            }

        } else {
            options.setDeviceName(ConfigReader.getProperty("ios.device.name", "iPhone 15"));
            options.setPlatformVersion(ConfigReader.getProperty("ios.platform.version", "17.0"));

            String appPath = ConfigReader.getProperty("ios.app.path");
            if (appPath != null && !appPath.isEmpty()) {
                options.setApp(appPath);
            } else {
                options.setBundleId(ConfigReader.getProperty("ios.bundle.id", "com.growkids"));
            }
        }

        return options;
    }

    // ================= KOBITON =================
    private static void addKobitonCapabilities(BaseOptions<?> options) {
        options.setCapability("deviceGroup", "KOBITON");
        options.setCapability("sessionName", "Growkids Automation");
        options.setCapability("sessionDescription", "Test session from Growkids framework");
        options.setCapability("captureScreenshots", true);

        String username = ConfigReader.getProperty("kobiton.username");
        String apiKey = ConfigReader.getProperty("kobiton.apiKey");

        if (username == null || apiKey == null || username.isEmpty() || apiKey.isEmpty()) {
            throw new RuntimeException("Missing Kobiton credentials in config.properties");
        }

        options.setCapability("username", username);
        options.setCapability("accessKey", apiKey);
    }

    // ================= SERVER URL =================
    private static String getServerUrl(boolean useKobiton) {
        if (useKobiton) {
            return "https://api.kobiton.com/wd/hub";
        }
        return ConfigReader.getProperty("appium.url", "http://127.0.0.1:4723");
    }

    // ================= QUIT =================
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}