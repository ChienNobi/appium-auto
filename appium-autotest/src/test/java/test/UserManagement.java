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
public class UserManagement {
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
        MobileElement layoutManagerUser = driver.findElement(By.id("layoutManagerUser"));
        layoutManagerUser.click();
        MobileElement searchButton = driver.findElement(By.id("com.example.myapplication:id/search_button"));
        searchButton.click();
        String[] words = {"thaigiavuong", "dinhtanhuy", "dangtrungminh", "khachhang", "nhanvien"};
        Random random = new Random();
        int index = random.nextInt(words.length);
        String wordToSearch = words[index];
        MobileElement searchInput = driver.findElement(By.id("com.example.myapplication:id/search_src_text"));
        searchInput.sendKeys(wordToSearch);
        MobileElement addItemMenu = driver.findElement(By.id("action_add"));
        addItemMenu.click();
        MobileElement tenDangNhapField = driver.findElement(By.id("edtTenDangNhap"));
        tenDangNhapField.sendKeys(generateRandomString());
        MobileElement hoField = driver.findElement(By.id("edtHo"));
        hoField.sendKeys(generateRandomString());
        MobileElement tenField = driver.findElement(By.id("edtTen"));
        tenField.sendKeys(generateRandomString());
        MobileElement matKhauField = driver.findElement(By.id("edtMatKhau"));
        matKhauField.sendKeys(generateRandomString());
        MobileElement ngaySinhField = driver.findElement(By.id("edtNgaySinh"));
        ngaySinhField.sendKeys(generateRandomDateOfBirth());
        MobileElement soDienThoaiField = driver.findElement(By.id("edtSoDienThoai"));
        soDienThoaiField.sendKeys(generateRandomPhoneNumber());
        MobileElement emailField = driver.findElement(By.id("edtEmail"));
        emailField.sendKeys(generateRandomEmail());
        MobileElement avatarField = driver.findElement(By.id("edtAvatar"));
        avatarField.sendKeys(generateRandomString());
        MobileElement addButton = driver.findElement(By.id("btnAdd"));
        addButton.click();
        MobileElement editButton = driver.findElement(By.id("btnEdit"));
        editButton.click();
        MobileElement editngaySinhField = driver.findElement(By.id("edtNgaySinh"));
        editngaySinhField.clear();
        editngaySinhField.sendKeys(generateRandomDateOfBirth());
        MobileElement edittenField = driver.findElement(By.id("edtTen"));
        edittenField.clear();
        edittenField.sendKeys(generateRandomString());
        MobileElement updateButton = driver.findElement(By.id("btnUpdate"));
        updateButton.click();
        MobileElement detailItem = driver.findElement(By.xpath("(//android.widget.FrameLayout[@resource-id=\"com.example.myapplication:id/itemThanhVien\"])[6]/android.widget.RelativeLayout"));
        detailItem.click();
        MobileElement listItemMenu = driver.findElement(By.id("action_show"));
        listItemMenu.click();
        List<MobileElement> listItems = driver.findElements(By.id("itemThanhVien"));
        if (listItems.size() >= 6) {
            MobileElement itemToRemove = listItems.get(5);
            MobileElement deleteButton = itemToRemove.findElement(By.id("btnDelete"));
            deleteButton.click();
            MobileElement dongYButton = driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]"));
            dongYButton.click();
        } else {
            System.out.println("Danh sách không đủ phần tử để xóa.");
        }
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
    public static String generateRandomDateOfBirth() {
        int year = randBetween(1950, 2003); // Ngày sinh trong khoảng từ 1950 đến 2003
        int month = randBetween(1, 12);
        int day = randBetween(1, 28); // Giả sử tháng 2 chỉ tối đa 28 ngày
        return String.format("%02d/%02d/%04d", day, month, year);
    }
    private static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }
    public static String generateRandomPhoneNumber() {
        Random random = new Random();
        StringBuilder phoneNumber = new StringBuilder();
        phoneNumber.append("0");
        for (int i = 0; i < 9; i++) {
            phoneNumber.append(random.nextInt(10));
        }
        return phoneNumber.toString();
    }
    public static String generateRandomEmail() {
        String[] domains = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com"};
        String[] providers = {"john", "mike", "emma", "sarah", "alex", "jessica", "david", "laura", "tom", "chris"};
        Random random = new Random();
        String username = providers[random.nextInt(providers.length)] + random.nextInt(1000);
        String domain = domains[random.nextInt(domains.length)];
        return username + "@" + domain;
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