package test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
public class XSSSearch {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> driver = null;
        try {
            driver = initializeDriver();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        MobileElement loginButton = driver.findElement(By.id("button_login_home"));
        loginButton.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        MobileElement usernameField = driver.findElement(By.id("txtUserName"));
        usernameField.sendKeys("thaigiavuong");
        MobileElement passwordField = driver.findElement(By.id("txtPassword"));
        passwordField.sendKeys("admin");
        MobileElement signInButton = driver.findElement(By.id("btnSignIn"));
        signInButton.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        MobileElement layoutManagerUser = driver.findElement(By.id("layoutManagerChuyenXe"));
        layoutManagerUser.click();
        MobileElement searchButton = driver.findElement(By.id("com.example.myapplication:id/search_button"));
        searchButton.click();
        String[] words = {"alert`1`", "<x 1='1'onxxx=1", "top[/al/.source+/ert/.source](1)", "navigator.vibrate(500)", "<body>Hello</body>", "Nha Trang"};
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("XSS Results");
        int rowNum = 0;
        Row headerRow = sheet.createRow(rowNum++);
        headerRow.createCell(0).setCellValue("Search Term");
        headerRow.createCell(1).setCellValue("Result");
        for (String word : words) {
            try {
                MobileElement searchInput = driver.findElement(By.id("com.example.myapplication:id/search_src_text"));
                searchInput.clear();
                searchInput.sendKeys(word);
                String result;
                if (word.equals("Nha Trang")) {
                    result = "Kết quả đã được hiển thị.";
                } else {
                    result = "Dữ liệu tìm kiếm không bị lỗi, tìm kiếm không thấy kết quả";
                }
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(word);
                row.createCell(1).setCellValue(result);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try (FileOutputStream fileOut = new FileOutputStream("resultXSS.xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (driver != null) {
            driver.quit();
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