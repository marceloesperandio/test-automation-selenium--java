package utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import static java.lang.System.*;
import static java.nio.file.Paths.get;
import static utils.Logger.ERROR;
import static utils.Logger.INFO;

public class AllureReportSetup {

    private static final Path RESULTS_FOLDER = get(getProperty("allure.results.folder", "allure-results"));
    private static final Path SCREENSHOTS_FOLDER = RESULTS_FOLDER.resolve("screenshots");
    private static final Path PROPERTIES_FILE_PATH = RESULTS_FOLDER.resolve("environment.properties");

    public static void prepareAllureResultsFolder() {
        try {
            recreateFolder(SCREENSHOTS_FOLDER);
            recreateFolder(RESULTS_FOLDER);
            String environment = getProperty("env", "LOCALHOST");
            writeToPropertiesFile("ENVIRONMENT", environment);
        } catch (IOException e) {
            ERROR("ERROR PREPARING ALLURE FOLDERS: " + e.getMessage());
        }
    }

    private static void recreateFolder(Path folderPath) throws IOException {
        deleteRecursively(folderPath.toFile());
        Files.createDirectories(folderPath);
        INFO("FOLDER CREATED: " + folderPath.toAbsolutePath());
    }

    private static void writeToPropertiesFile(String key, String value) {
        try {
            Properties props = new Properties();
            if (Files.exists(PROPERTIES_FILE_PATH)) {
                try (InputStream input = Files.newInputStream(PROPERTIES_FILE_PATH)) {
                    props.load(input);
                }
            }

            props.setProperty(key, value);

            try (OutputStream output = Files.newOutputStream(PROPERTIES_FILE_PATH)) {
                props.store(output, null);
                INFO("PROPERTY REGISTERED: " + key + " = " + value);
            }

        } catch (IOException e) {
            ERROR("ERROR WRITING TO PROPERTIES FILE: " + e.getMessage());
        }
    }

    private static void deleteRecursively(File file) {
        if (file.isDirectory()) {
            File[] entries = file.listFiles();
            if (entries != null) {
                for (File entry : entries) deleteRecursively(entry);
            }
        }
        if (file.delete()) {
            INFO("DELETED: " + file.getAbsolutePath());
        } else {
            ERROR("FAILED TO DELETE: " + file.getAbsolutePath());
        }
    }
}