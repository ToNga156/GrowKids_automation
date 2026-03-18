# Growkids Mobile Automation Framework

Framework automation mobile sử dụng **Java**, **Appium**, **TestNG**, **Maven** và **Page Object Model**.

## Cấu trúc dự án

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

## Yêu cầu

- Java 11+
- Maven 3.6+
- Appium Server 2.x
- Android SDK / Xcode (cho iOS)

## Cài đặt

1. Khởi chạy Appium Server: `appium`
2. Cập nhật `config.properties` với thông tin app và thiết bị
3. Chạy test: `mvn clean test`

## Cấu hình

Chỉnh sửa `src/test/resources/config.properties`:

### Kobiton Cloud (kobiton.enabled=true)
- `kobiton.username`: Username Kobiton
- `kobiton.apiKey`: API Key từ portal.kobiton.com
- `kobiton.app`: App từ Kobiton Store (vd: `kobiton-store:754789`)
- `kobiton.device.name`: Tên thiết bị (`*` = bất kỳ)

### Local Appium (kobiton.enabled=false)
- `appium.url`: URL Appium server
- `platform.name`: android hoặc ios
- `android.app.package`, `android.app.activity`: cho app đã cài
- `android.app.path`: đường dẫn file .apk (nếu cài mới mỗi lần chạy)

## Chạy test

```bash
mvn clean test
```

Chạy với suite cụ thể:

```bash
mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml
```
