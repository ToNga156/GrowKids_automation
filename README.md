# Growkids Mobile Automation Framework

This is a mobile automation testing framework built using **Java**, **Appium**, **TestNG**, **Maven**, and the **Page Object Model (POM)** design pattern.

## Project Structure

```
growkids-auto/
├── pom.xml
├── src/test/
│   ├── java/com/growkids/
│   │   ├── base/           # BaseTest, DriverManager
│   │   ├── pages/          # LoginPage, HomePage, BasePage
│   │   ├── tests/          # LoginTest
│   │   └── utils/          # ConfigReader, WaitUtils
│   └── resources/
│       ├── config.properties
│       └── testng.xml
```

## Prerequisites

Make sure you have the following installed:

- Java 11+
- Maven 3.6+
- Appium Server 2.x
- Android SDK / Xcode (cho iOS)

## Setup & Installation

1. Start the Appium server: `appium`
2. Update the configuration file: `config.properties`
3. Run the tests: `mvn clean test`

## Configuration

Edit the file: `src/test/resources/config.properties`:

### Kobiton Cloud (kobiton.enabled=true)
- `kobiton.username`: Username Kobiton
- `kobiton.apiKey`: API Key từ portal.kobiton.com
- `kobiton.app`: App từ Kobiton Store (vd: `kobiton-store:754789`)
- `kobiton.device.name`: Device name (* for any available device)

### Local Appium (kobiton.enabled=false)
- `appium.url`: URL Appium server
- `platform.name`: android or ios
- `android.app.package`, `android.app.activity`: For installed apps
- `android.app.path`: Path to .apk file (if installing during test execution)

## Running Tests

Run all tests:

```bash
mvn clean test
```

Run with a specific TestNG suite:

```bash
mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml
```
