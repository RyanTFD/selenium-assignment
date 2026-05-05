package utils;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import tests.BaseTest;

import org.openqa.selenium.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScreenshotListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult result) {
        Object currentClass = result.getInstance();
        WebDriver driver = ((BaseTest)currentClass).getDriver();

        try {
            Path dir = Paths.get("screenshots");
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }

            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String className = result.getTestClass().getRealClass().getSimpleName();
            String methodName = result.getMethod().getMethodName();
            String timestamp = String.valueOf(System.currentTimeMillis());

            String fileName = className + "_" + methodName + "_" + timestamp + ".png";

            Path destination = dir.resolve(fileName);
            Files.copy(screenshot.toPath(), destination, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Screenshot saved: " + destination);
        } catch (Exception e) {
            System.out.println("Screenshot failed: " + e.getMessage());
        }
    }
}