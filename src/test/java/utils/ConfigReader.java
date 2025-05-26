package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static utils.Log.ERROR;
import static utils.Log.INFO;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try {

            FileInputStream baseProps = new FileInputStream("src/test/resources/config.properties");
            properties.load(baseProps);

            String env = properties.getProperty("env", "dev").toLowerCase();
            String envFilePath = "src/test/resources/config-" + env + ".properties";

            INFO("Ambiente selecionado: " + env);
            FileInputStream envProps = new FileInputStream(envFilePath);
            properties.load(envProps);

        } catch (IOException e) {
            ERROR("Erro ao carregar arquivos de configuração: " + e.getMessage());
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static int getInt(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(properties.getProperty(key));
    }
}