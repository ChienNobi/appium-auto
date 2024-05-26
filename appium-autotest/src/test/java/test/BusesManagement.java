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

public class BusesManagement {
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
        String[] words = {"Tây Ninh", "TP.Nha Trang", "TP.Cần Thơ", "TP.Gia Nghĩa", "Hà Nội", "Đà Nẵng", "Hà Nam", "Quảng Bình"};
        Random random = new Random();
        int index = random.nextInt(words.length);
        String wordToSearch = words[index];
        MobileElement searchInput = driver.findElement(By.id("com.example.myapplication:id/search_src_text"));
        searchInput.sendKeys(wordToSearch);

        MobileElement addItemMenu = driver.findElement(By.id("action_add_chuyen_xe"));
        addItemMenu.click();
        MobileElement tenField = driver.findElement(By.id("edtTenChuyenXe"));
        tenField.sendKeys(generateRandomString());
        MobileElement diaDiemDiField = driver.findElement(By.id("edtDiaDiemDi"));
        diaDiemDiField.sendKeys(generateRandomString());
        MobileElement diaDiemDenField = driver.findElement(By.id("edtDiaDiemDen"));
        diaDiemDenField.sendKeys(generateRandomString());
        MobileElement thoiGianBatDauField = driver.findElement(By.id("edtThoiGianBatDau"));
        thoiGianBatDauField.sendKeys(generateRandomString());
        MobileElement thoiGianDenField = driver.findElement(By.id("edtThoiGianDen"));
        thoiGianDenField.sendKeys(generateRandomString());
        MobileElement giaTienField = driver.findElement(By.id("edtGiaTien"));
        giaTienField.sendKeys(generateRandomNumber(500, 5000));
        MobileElement addButton = driver.findElement(By.id("btnThemChuyenXe"));
        addButton.click();
        MobileElement editButton = driver.findElement(By.id("btnEditChuyenXe"));
        editButton.click();
        MobileElement edittenField = driver.findElement(By.id("edtTenChuyenXe"));
        edittenField.clear();
        edittenField.sendKeys(generateRandomString());
        MobileElement motaField = driver.findElement(By.id("edtMoTa"));
        motaField.sendKeys(generateRandomString());
        MobileElement updateButton = driver.findElement(By.id("btnUpdateChuyenXe"));
        updateButton.click();
        MobileElement detailItem = driver.findElement(By.xpath("(//android.widget.FrameLayout[@resource-id=\"com.example.myapplication:id/itemChuyenXe\"])[5]/android.widget.RelativeLayout"));
        detailItem.click();
        MobileElement listItemMenu = driver.findElement(By.id("action_show_chuyen_xe"));
        listItemMenu.click();
        List<MobileElement> listItems = driver.findElements(By.id("itemChuyenXe"));
        if (listItems.size() >= 5) {
            MobileElement itemToRemove = listItems.get(4);
            MobileElement deleteButton = itemToRemove.findElement(By.id("btnDeleteChuyenXe"));
            deleteButton.click();
            MobileElement dongYButton = driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]"));
            dongYButton.click();
        } else {
            System.out.println("Danh sách không đủ phần tử để xóa.");
        }
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
    public static AppiumDriver<MobileElement> initializeDriver() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName", "emulator-5554");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appPackage", "com.example.myapplication");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appActivity", "com.example.myapplication.view.MainActivity");
        return new AndroidDriver<>(new URL("http://192.168.1.44:4723"), desiredCapabilities);
    }
}


