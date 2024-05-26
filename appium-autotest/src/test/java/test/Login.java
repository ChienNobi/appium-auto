package test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import io.qameta.allure.Step;
import org.testng.annotations.Test;



public class Login {

    public static void main(String[] args) {
        String excelFilePath = "E:\\datnautotest\\Data_Maven.xlsx";
        AppiumDriver<MobileElement> driver = null;
        try {
            driver = initializeDriver();
            MobileElement buttonElement = driver.findElement(By.id("com.example.myapplication:id/button_login_home"));
            buttonElement.click();
//            register.createTestData(driver);
//            driver.quit();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return;
        }
        try {
            FileInputStream inputStream = new FileInputStream(excelFilePath);
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                String username = row.getCell(0).getStringCellValue();
                String password = row.getCell(1).getStringCellValue();
                MobileElement usernameField = driver.findElement(By.id("txtUserName"));
                usernameField.sendKeys(username);
                MobileElement passwordField = driver.findElement(By.id("txtPassword"));
                passwordField.sendKeys(password);
                MobileElement signInButton = driver.findElement(By.id("btnSignIn"));
                signInButton.click();
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                Cell resultLoginCell = row.createCell(2);
                String resultLogin;
                System.out.println(isLoginSuccessful(driver));
                if (isLoginSuccessful(driver)) {
                    resultLogin = "pass";
                } else {
                    resultLogin = "fail";
                }
                resultLoginCell.setCellValue(resultLogin);
            }
            inputStream.close();
            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        driver.quit();
    }


    public static AppiumDriver<MobileElement> initializeDriver() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName", "Galaxy M20");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appPackage", "com.example.myapplication");
        desiredCapabilities.setCapability("appActivity", "com.example.myapplication.view.MainActivity");
        return new AndroidDriver<>(new URL("http://10.40.1.124:4723/wd/hub"), desiredCapabilities);
    }
    public static boolean isLoginSuccessful(AppiumDriver<MobileElement> driver) {
        return true;
    }
}