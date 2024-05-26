package test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CarManagement {
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
        MobileElement layoutManagerUser = driver.findElement(By.id("layoutManagerLoaiXe"));
        layoutManagerUser.click();
        MobileElement searchButton = driver.findElement(By.id("com.example.myapplication:id/search_button"));
        searchButton.click();
        String[] words = {"Ford Transit", "Thaco Kinglong", "Mercedes Sprinter Limousine", "Limousine", "Huyndai", "Sakura", "Long Bus"};
        Random random = new Random();
        int index = random.nextInt(words.length);
        String wordToSearch = words[index];
        MobileElement searchInput = driver.findElement(By.id("com.example.myapplication:id/search_src_text"));
        searchInput.sendKeys(wordToSearch);
        MobileElement addItemMenu = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.example.myapplication:id/navigation_bar_item_small_label_view\"]"));
        addItemMenu.click();
        MobileElement tenLoaiXeField = driver.findElement(By.id("edtTenLoaiXe"));
        tenLoaiXeField.sendKeys(generateRandomString());
        MobileElement soLuongGheField = driver.findElement(By.id("edtSoLuongGhe"));
        soLuongGheField.sendKeys(generateRandomNumber(10, 40));
        MobileElement addButton = driver.findElement(By.id("btnThemLoaiXe"));
        addButton.click();
        MobileElement listItemMenu = driver.findElement(By.id("com.example.myapplication:id/action_show"));
        listItemMenu.click();
        MobileElement editButton = driver.findElement(By.id("btnEdit"));
        editButton.click();
        MobileElement edittenField = driver.findElement(By.id("edtTenLoaiXe"));
        edittenField.clear();
        edittenField.sendKeys(generateRandomString());
        MobileElement updateButton = driver.findElement(By.id("btnUpdateLoaiXe"));
        updateButton.click();
        MobileElement deleteButton = driver.findElement(By.xpath("(//android.widget.ImageView[@resource-id='com.example.myapplication:id/btnDelete'])[4]"));
        deleteButton.click();
        showDialogAndConfirm(driver);
    }
    public static AppiumDriver<MobileElement> initializeDriver() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName", "Galaxy M20");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appPackage", "com.example.myapplication");
        desiredCapabilities.setCapability("appActivity", "com.example.myapplication.view.MainActivity");
        return new AndroidDriver<>(new URL("http://192.168.0.80:4723/wd/hub"), desiredCapabilities);
    }
    public static void showDialogAndConfirm(AppiumDriver<MobileElement> driver) {
        MobileElement dongYButton = driver.findElement(By.xpath("//android.widget.Button[@resource-id='android:id/button1']"));
        dongYButton.click();
    }

    public static String generateRandomNumber(int min, int max) {
        Random random = new Random();
        int randomNumber = random.nextInt(max - min + 1) + min;
        return String.valueOf(randomNumber);
    }
    public static String generateRandomString() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 8;
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }
}


