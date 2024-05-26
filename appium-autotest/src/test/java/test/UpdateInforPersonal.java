package test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class UpdateInforPersonal {
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
        usernameField.sendKeys("khachhang");
        MobileElement passwordField = driver.findElement(By.id("txtPassword"));
        passwordField.sendKeys("khachhang");
        MobileElement signInButton = driver.findElement(By.id("btnSignIn"));
        signInButton.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        selectMenuItem(driver);

        // Tìm và nhập giá trị ngẫu nhiên vào trường email
        MobileElement emailField = driver.findElement(By.xpath("//android.widget.EditText[@text=\"khachhang.1205@gmail.com\"]"));
        String randomEmail = generateRandomEmail();
        emailField.sendKeys(randomEmail);

        // Tìm và nhập giá trị ngẫu nhiên vào trường số điện thoại
        MobileElement phoneField = driver.findElement(By.xpath("//android.widget.EditText[@text=\"908734098\"]"));
        String randomPhoneNumber = generateRandomPhoneNumber();
        phoneField.sendKeys(randomPhoneNumber);

        // Tìm và nhấn vào nút "Cập nhật thông tin"
        MobileElement updateButton = driver.findElement(By.id("btnUpdateAccount"));
        updateButton.click();

        // Đợi 10 giây sau khi nhấn nút cập nhật
        try {
            Thread.sleep(10000); // 10 giây
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Nhấn nút "Đăng xuất"
        MobileElement logoutButton = driver.findElement(By.id("btnLogout"));
        logoutButton.click();

        // Đợi 5 giây sau khi nhấn nút đăng xuất
        try {
            Thread.sleep(5000); // 5 giây
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Thực hiện lại chức năng đăng nhập
        MobileElement usernameFieldLogin = driver.findElement(By.id("txtUserName"));
        usernameFieldLogin.clear();
        usernameFieldLogin.sendKeys("khachhang");
        MobileElement passwordFieldLogin = driver.findElement(By.id("txtPassword"));
        passwordFieldLogin.clear();
        passwordFieldLogin.sendKeys("khachhang");
        MobileElement signInButtonLogin = driver.findElement(By.id("btnSignIn"));
        signInButtonLogin.click();
        selectMenuItem(driver);
    }

    public static void selectMenuItem(AppiumDriver<MobileElement> driver) {
        MobileElement menuItem = driver.findElement(By.id("action_account"));
        menuItem.click();
    }

    public static AppiumDriver<MobileElement> initializeDriver() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName", "Galaxy M20");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appPackage", "com.example.myapplication");
        desiredCapabilities.setCapability("appActivity", "com.example.myapplication.view.MainActivity");
        return new AndroidDriver<>(new URL("http://192.168.0.80:4723/wd/hub"), desiredCapabilities);
    }

    // Phương thức này tạo một địa chỉ email ngẫu nhiên
    public static String generateRandomEmail() {
        String allowedChars = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder email = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            email.append(allowedChars.charAt(random.nextInt(allowedChars.length())));
        }
        email.append("@gmail.com");
        return email.toString();
    }

    // Phương thức này tạo một số điện thoại ngẫu nhiên
    public static String generateRandomPhoneNumber() {
        StringBuilder phoneNumber = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 9; i++) {
            phoneNumber.append(random.nextInt(10)); // Tạo số ngẫu nhiên từ 0 đến 9 và nối vào chuỗi
        }
        return phoneNumber.toString();
    }
}
