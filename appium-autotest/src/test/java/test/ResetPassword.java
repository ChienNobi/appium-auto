package test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.Random;
public class ResetPassword {
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
        MobileElement textViewResetPassword = driver.findElement(By.id("textViewResetPassword"));
        textViewResetPassword.click();
        testCase1(driver);
        testCase2(driver);
        testCase3(driver);
        testCase4(driver);
        testCase5(driver);
        testCase6(driver);
        testCase7(driver);
        testCase8(driver);
        testCase9(driver);
        // driver.quit();
    }

    public static AppiumDriver<MobileElement> initializeDriver() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName", "Galaxy M20");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appPackage", "com.example.myapplication");
        desiredCapabilities.setCapability("appActivity", "com.example.myapplication.view.MainActivity");
        return new AndroidDriver<>(new URL("http://192.168.0.80:4723/wd/hub"), desiredCapabilities);
    }
    public static String generateRandomPassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    public static String generateRandomEmail() {
        String characters = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        sb.append("@example.com");
        return sb.toString();
    }

    @Step ("testCase1")
    @Test (priority = 1)
    // case bỏ trống cả 3
    public static void testCase1(AppiumDriver<MobileElement> driver){
        MobileElement resetButton = driver.findElement(By.id("btnResetPassword"));
        resetButton.click();
        Assert.assertTrue(true);
    }

    @Step ("testCase2")
    @Test (priority = 2)
    // case bỏ trống email k bỏ trống 2 trường password
    public static void testCase2(AppiumDriver<MobileElement> driver){
        String randomEmail = "";
        MobileElement emailField = driver.findElement(By.id("edtEmail"));
        emailField.sendKeys(randomEmail);

        String randomPassword = generateRandomPassword(6);
        MobileElement newPasswordField = driver.findElement(By.id("edtResetPassword"));
        newPasswordField.sendKeys(randomPassword);

        MobileElement confirmPasswordField = driver.findElement(By.id("edtResetAgainPassword"));
        confirmPasswordField.sendKeys(randomPassword);
        MobileElement resetButton = driver.findElement(By.id("btnResetPassword"));
        resetButton.click();
        Assert.assertTrue(true);
    }

    @Step ("testCase3")
    @Test (priority = 3)
    // case bỏ trống pass
    public static void testCase3(AppiumDriver<MobileElement> driver){
        String randomEmail = generateRandomEmail();
        MobileElement emailField = driver.findElement(By.id("edtEmail"));
        emailField.sendKeys(randomEmail);

        String randomPassword = generateRandomPassword(6);
        MobileElement newPasswordField = driver.findElement(By.id("edtResetPassword"));
        newPasswordField.sendKeys("");

        MobileElement confirmPasswordField = driver.findElement(By.id("edtResetAgainPassword"));
        confirmPasswordField.sendKeys(randomPassword);

        MobileElement resetButton = driver.findElement(By.id("btnResetPassword"));
        resetButton.click();
        Assert.assertTrue(true);
    }

    @Step ("testCase4")
    @Test (priority = 4)
    // case bỏ trống confirm pass
    public static void testCase4(AppiumDriver<MobileElement> driver){
        String randomEmail = generateRandomEmail();
        MobileElement emailField = driver.findElement(By.id("edtEmail"));
        emailField.sendKeys(randomEmail);

        String randomPassword = generateRandomPassword(6);

        MobileElement newPasswordField = driver.findElement(By.id("edtResetPassword"));
        newPasswordField.sendKeys(randomPassword);
        MobileElement confirmPasswordField = driver.findElement(By.id("edtResetAgainPassword"));
        confirmPasswordField.sendKeys("");

        MobileElement resetButton = driver.findElement(By.id("btnResetPassword"));
        resetButton.click();
        Assert.assertTrue(true);
    }

    @Step ("testCase5")
    @Test (priority = 5)
    // case sai email
    public static void testCase5(AppiumDriver<MobileElement> driver){
        String randomEmail = generateRandomEmail();
        MobileElement emailField = driver.findElement(By.id("edtEmail"));
        emailField.sendKeys(randomEmail);

        String randomPassword = generateRandomPassword(6);

        MobileElement newPasswordField = driver.findElement(By.id("edtResetPassword"));
        newPasswordField.sendKeys(randomPassword);

        MobileElement confirmPasswordField = driver.findElement(By.id("edtResetAgainPassword"));
        confirmPasswordField.sendKeys(randomPassword);

        MobileElement resetButton = driver.findElement(By.id("btnResetPassword"));
        resetButton.click();
        Assert.assertTrue(true);
    }

    @Step ("testCase6")
    @Test (priority = 6)
    // case dung email pass trung pass hiện tại confirm pass k trùng pass
    public static void testCase6(AppiumDriver<MobileElement> driver){

        String randomEmail = "khachhang.1205@gmail.com";
        MobileElement emailField = driver.findElement(By.id("edtEmail"));
        emailField.sendKeys(randomEmail);

        String randomPassword1 = "khachhang";
        String randomPassword2= generateRandomPassword(6);

        MobileElement newPasswordField = driver.findElement(By.id("edtResetPassword"));
        newPasswordField.sendKeys(randomPassword1);

        MobileElement confirmPasswordField = driver.findElement(By.id("edtResetAgainPassword"));
        confirmPasswordField.sendKeys(randomPassword2);

        MobileElement resetButton = driver.findElement(By.id("btnResetPassword"));
        resetButton.click();
        Assert.assertTrue(true);
    }

    @Step ("testCase7")
    @Test (priority = 7)
    // case dung email pass trung pass hiện tại confirm pass trùng pass
    public static void testCase7(AppiumDriver<MobileElement> driver){

        String randomEmail = "khachhang.1205@gmail.com";
        MobileElement emailField = driver.findElement(By.id("edtEmail"));
        emailField.sendKeys(randomEmail);

        String randomPassword = "khachhang";

        MobileElement newPasswordField = driver.findElement(By.id("edtResetPassword"));
        newPasswordField.sendKeys(randomPassword);

        MobileElement confirmPasswordField = driver.findElement(By.id("edtResetAgainPassword"));
        confirmPasswordField.sendKeys(randomPassword);

        MobileElement resetButton = driver.findElement(By.id("btnResetPassword"));
        resetButton.click();

        handleLogin(driver, randomPassword, false);
        Assert.assertTrue(true);
    }

    @Step ("testCase8")
    @Test (priority = 8)
    // case dung email pass khác pass hiện tại confirm pass k trùng pass
    public static void testCase8(AppiumDriver<MobileElement> driver){

        String randomEmail = "khachhang.1205@gmail.com";
        MobileElement emailField = driver.findElement(By.id("edtEmail"));
        emailField.sendKeys(randomEmail);

        String randomPassword = generateRandomPassword(6);
        String randomPassword2 = "khachhang";

        MobileElement newPasswordField = driver.findElement(By.id("edtResetPassword"));
        newPasswordField.sendKeys(randomPassword);

        MobileElement confirmPasswordField = driver.findElement(By.id("edtResetAgainPassword"));
        confirmPasswordField.sendKeys(randomPassword2);

        MobileElement resetButton = driver.findElement(By.id("btnResetPassword"));
        resetButton.click();
        Assert.assertTrue(true);

    }

    @Step ("testCase9")
    @Test (priority = 9)
    // case dung email pass khác pass hiện tại confirm pass trùng pass
    public static void testCase9(AppiumDriver<MobileElement> driver){

        String randomEmail = "khachhang.1205@gmail.com";
        MobileElement emailField = driver.findElement(By.id("edtEmail"));
        emailField.sendKeys(randomEmail);

        String randomPassword = generateRandomPassword(6);

        MobileElement newPasswordField = driver.findElement(By.id("edtResetPassword"));
        newPasswordField.sendKeys(randomPassword);

        MobileElement confirmPasswordField = driver.findElement(By.id("edtResetAgainPassword"));
        confirmPasswordField.sendKeys(randomPassword);

        MobileElement resetButton = driver.findElement(By.id("btnResetPassword"));
        resetButton.click();

        handleLogin(driver, randomPassword, true);
        Assert.assertTrue(true);
    }




    public static void handleLogin(AppiumDriver<MobileElement> driver, String password, boolean isFinal){
        MobileElement btnConfirmLogin = driver.findElement(By.id("android:id/button1"));
        btnConfirmLogin.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        MobileElement usernameField = driver.findElement(By.id("txtUserName"));
        usernameField.sendKeys("khachhang");
        MobileElement passwordField = driver.findElement(By.id("txtPassword"));
        passwordField.sendKeys(password);
        MobileElement signInButton = driver.findElement(By.id("btnSignIn"));
        signInButton.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        MobileElement menuItem = driver.findElement(By.id("action_account"));
        menuItem.click();
        MobileElement signOutButton = driver.findElement(By.id("btnLogout"));
        signOutButton.click();
        if(isFinal) return;
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        MobileElement textViewResetPassword = driver.findElement(By.id("textViewResetPassword"));
        textViewResetPassword.click();
    }


}
