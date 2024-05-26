package test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;

import org.testng.annotations.Test;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileOutputStream;
import java.io.IOException;
//import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
public class Register {
    public static void main(String[] args) {
        try {
            AppiumDriver<MobileElement> driver = initializeDriver();
            MobileElement buttonElement = driver.findElementById("com.example.myapplication:id/button_signup_home");
            buttonElement.click();
            createTestData(driver);
//            driver.quit();
        } catch (MalformedURLException e) {
            e.getCause().printStackTrace();
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
    public static void createTestData(AppiumDriver<MobileElement> driver) throws MalformedURLException {
        Workbook workbook = null;
        try {
            workbook = new XSSFWorkbook("E:\\datnautotest\\Registration_Test_Cases.xlsx");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Sheet sheet = workbook.getSheet("Registration_Test_Cases");
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Username");
        headerRow.createCell(1).setCellValue("Name");
        headerRow.createCell(2).setCellValue("Last Name");
        headerRow.createCell(3).setCellValue("Phone Number");
        headerRow.createCell(4).setCellValue("Email");
        headerRow.createCell(5).setCellValue("Password");
        headerRow.createCell(6).setCellValue("Confirm Password");
        headerRow.createCell(7).setCellValue("Result");
        for (int i = 1; i <= 8; i++) {
            randomValue(i, sheet, driver);
        }
        try (FileOutputStream fileOut = new FileOutputStream("E:\\datnautotest\\Registration_Test_Results.xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @Step("randomValue")
    @Test (priority = 1)
    public static void randomValue(int i, Sheet sheet, AppiumDriver<MobileElement> driver){
        Random random = new Random();
        Row row = sheet.createRow(i);
        String username = i == 7 ? "ngocnt" : (i != 1 ? generateRandomString(random, 6) : "");
        String name = i == 7 ? "Ngoc" : (i != 2 ? generateRandomString(random, 6) : "");
        String lastName = i == 7 ? "Nguyen" : (i != 3 ? generateRandomString(random, 6) : "");
        String phoneNumber = i == 7 ? "0538592386" : (i != 4 ? "0" + generateRandomNumber(random, 9) : "");
        String email = i == 7 ? "ngocnt@gmail.com" : (i != 5 ? generateRandomString(random, 6) + "@gmail.com" : "");
        String password = i == 7 ? "123456" : (i != 6 ? generateRandomString(random, 6) : "");
        String confirmPassword = password;
        fillRegistrationForm(driver, username, name, lastName, phoneNumber, email, password, confirmPassword);

        // wait

        String result = "Success";
        row.createCell(0).setCellValue(username);
        row.createCell(1).setCellValue(name);
        row.createCell(2).setCellValue(lastName);
        row.createCell(3).setCellValue(phoneNumber);
        row.createCell(4).setCellValue(email);
        row.createCell(5).setCellValue(password);
        row.createCell(6).setCellValue(confirmPassword);
        row.createCell(7).setCellValue(result);

    }

    public static boolean isClickPolicy = false;
    public static void fillRegistrationForm(AppiumDriver<MobileElement> driver, String username, String name, String lastName,
                                            String phoneNumber, String email, String password, String confirmPassword) {
        MobileElement usernameField = driver.findElement(By.id("txtUserName"));
        usernameField.sendKeys(username);

        MobileElement nameField = driver.findElement(By.id("txtName"));
        nameField.sendKeys(name);

        MobileElement lastNameField = driver.findElement(By.id("txtLastName"));
        lastNameField.sendKeys(lastName);

        MobileElement phoneNumberField = driver.findElement(By.id("txtPhoneNumber"));
        phoneNumberField.sendKeys(phoneNumber);

        MobileElement emailField = driver.findElement(By.id("txtEmail"));
        emailField.sendKeys(email);

        MobileElement passwordField = driver.findElement(By.id("txtPassword"));
        passwordField.sendKeys(password);

        MobileElement confirmPasswordField = driver.findElement(By.id("txtPasswordAgain"));
        confirmPasswordField.sendKeys(confirmPassword);

        if(!isClickPolicy){
            isClickPolicy = true;
            MobileElement switchButton = driver.findElement(By.id("switchDieuKhoan"));
            switchButton.click();
        }

        MobileElement signUpButton = driver.findElement(By.id("btnSignUp"));
        signUpButton.click();
    }
    public static String generateRandomString(Random random, int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }

    public static String generateRandomNumber(Random random, int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}