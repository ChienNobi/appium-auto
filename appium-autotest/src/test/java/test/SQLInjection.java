package test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SQLInjection {
    public static void main(String[] args) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("SQL Injection Results");
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Test Case");
        headerRow.createCell(1).setCellValue("Result");
        AppiumDriver<MobileElement> driver = null;
        try {
            driver = initializeDriver();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String[] testCases = {"khachhang", "or true--", "or 1=1;", "or 1=1--", "admin' or '1'='1;", "like '%'"};
        for (int i = 0; i < testCases.length; i++) {
            String testCase = testCases[i];
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(testCase);

            try {
                MobileElement usernameField = driver.findElement(By.id("txtUserName"));
                usernameField.sendKeys(testCase);
                MobileElement passwordField = driver.findElement(By.id("txtPassword"));
                passwordField.sendKeys("khachhang");
                MobileElement signInButton = driver.findElement(By.id("btnSignIn"));
                signInButton.click();
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

                // Kiểm tra kết quả đăng nhập
                boolean isFrameLayoutPresent = isElementPresentByXPath(driver, "//android.widget.FrameLayout[@resource-id='android:id/content']");
                if (testCase.equals("khachhang")) {
                    if (isFrameLayoutPresent) {
                        row.createCell(1).setCellValue("Pass");
                    } else {
                        row.createCell(1).setCellValue("Fail");
                    }
                } else {
                    if (!isFrameLayoutPresent) {
                        row.createCell(1).setCellValue("Pass");
                    } else {
                        row.createCell(1).setCellValue("Fail");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            FileOutputStream fileOut = new FileOutputStream("D:\\Maven\\Test_HVMTravel\\resultSQLInjection.xlsx");
            workbook.write(fileOut);
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (driver != null) {
            driver.quit();
        }
    }

    // Phương thức kiểm tra sự xuất hiện của một phần tử dựa trên XPath
    private static boolean isElementPresentByXPath(AppiumDriver<MobileElement> driver, String xPath) {
        try {
            driver.findElementByXPath(xPath);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static AppiumDriver<MobileElement> initializeDriver() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName", "Galaxy M20");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appPackage", "com.example.myapplication");
        desiredCapabilities.setCapability("appActivity", "com.example.myapplication.view.MainActivity");
        return new AndroidDriver<>(new URL("http://192.168.0.80:4723/wd/hub"), desiredCapabilities);
    }
}

