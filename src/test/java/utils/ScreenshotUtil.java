package utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {

    public static void takeScreenshot(WebDriver driver, String name) {
        try {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String fileName = name + "_" + timestamp + ".png";

            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            Path screenshotDir = Paths.get("allure-results/screenshots");
            Files.createDirectories(screenshotDir);
            Path destination = screenshotDir.resolve(fileName);
            Files.copy(source.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

            Allure.addAttachment(fileName, new FileInputStream(destination.toFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}