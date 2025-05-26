package utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScreenshotUtil {

    public static void takeScreenshot(WebDriver driver, String screenshotName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);

            // Anexa ao Allure direto
            Allure.addAttachment(screenshotName, new ByteArrayInputStream(screenshot));

            // (Opcional) salva em disco
            Path screenshotPath = Paths.get("target", "screenshots", screenshotName + ".png");
            Files.createDirectories(screenshotPath.getParent());
            Files.write(screenshotPath, screenshot);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}