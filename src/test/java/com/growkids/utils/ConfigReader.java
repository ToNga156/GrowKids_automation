package com.growkids.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Reads configuration from config.properties.
 * Hỗ trợ .env và biến môi trường (${VAR} được thay thế).
 */
public class ConfigReader {

    private static final Properties properties = new Properties();
    private static final Properties envProperties = new Properties();
    private static final String CONFIG_FILE = "config.properties";
    private static final Pattern VAR_PATTERN = Pattern.compile("\\$\\{([^}]+)}");

    static {
        loadConfig();
        loadEnvFile();
    }

    private static void loadConfig() {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    private static void loadEnvFile() {
        Path[] paths = {
            Paths.get(System.getProperty("user.dir"), ".env"),
            Paths.get(System.getProperty("user.dir"), "..", ".env")
        };
        for (Path path : paths) {
            if (Files.exists(path)) {
                try {
                    Files.readAllLines(path).forEach(line -> {
                        line = line.trim();
                        if (line.isEmpty() || line.startsWith("#")) return;
                        int eq = line.indexOf('=');
                        if (eq > 0) {
                            String k = line.substring(0, eq).trim();
                            String v = line.substring(eq + 1).trim();
                            if (v.startsWith("\"") && v.endsWith("\"")) v = v.substring(1, v.length() - 1);
                            if (v.startsWith("'") && v.endsWith("'")) v = v.substring(1, v.length() - 1);
                            envProperties.setProperty(k, v);
                        }
                    });
                    break;
                } catch (IOException ignored) { }
            }
        }
    }

    private static String resolve(String value) {
        if (value == null) return null;
        Matcher m = VAR_PATTERN.matcher(value);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String varName = m.group(1);
            String replacement = System.getenv(varName);
            if (replacement == null) replacement = envProperties.getProperty(varName);
            if (replacement == null) replacement = m.group(0);
            m.appendReplacement(sb, Matcher.quoteReplacement(replacement));
        }
        m.appendTail(sb);
        return sb.toString();
    }

    private ConfigReader() {
    }

    public static String getProperty(String key) {
        return getProperty(key, null);
    }

    public static String getProperty(String key, String defaultValue) {
        // Ưu tiên biến môi trường (key dạng KOBITON_USERNAME)
        String envKey = key.replace(".", "_").toUpperCase();
        String envValue = System.getenv(envKey);
        if (envValue != null && !envValue.isEmpty()) {
            return envValue;
        }
        String value = properties.getProperty(key);
        if (value != null && value.contains("${")) {
            value = resolve(value);
        }
        return (value != null && !value.isEmpty()) ? value : defaultValue;
    }

    public static int getIntProperty(String key, int defaultValue) {
        String value = properties.getProperty(key);
        if (value == null || value.isEmpty()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static boolean getBooleanProperty(String key, boolean defaultValue) {
        String value = properties.getProperty(key);
        if (value == null || value.isEmpty()) {
            return defaultValue;
        }
        return Boolean.parseBoolean(value.trim());
    }
}
