package test;

import com.google.common.collect.ImmutableMap;
import config.EmulatorConfig;
import core.BaseTest;
import helpers.RandomHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.ResetPasswordPage;

import java.net.MalformedURLException;
import java.sql.DriverManager;

public class ResetPasswordTest extends BaseTest {
    private ResetPasswordPage page;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        page = new ResetPasswordPage();
    }

    @AfterMethod
    public void refreshApp() {
        EmulatorConfig.resetApp();
    }

    @BeforeMethod
    public void startTest() {
        page.openResetPasswordPage();
    }

    @Test(priority = 1)
    public void TC01() {
        page.resetPassword("", "", "");
        System.out.println(page.getToastMessage());
    }

    @Test(priority = 2)
    public void TC02() {
        String password = RandomHelper.generateRandomPassword(6);
        page.resetPassword("", password, password);
        driver.resetInputState();
    }

    @Test(priority = 3)
    public void TC03() {
        String password = RandomHelper.generateRandomPassword(6);
    }

    @Test(priority = 4)
    public void TC04() {
        String password = RandomHelper.generateRandomPassword(6);
        page.resetPassword("", password, "");
    }

    @Test(priority = 5, description = "Test with valid email + password")
    public void TC05() {
        String password = RandomHelper.generateRandomPassword(6);
        page.resetPassword(RandomHelper.generateRandomEmail(), password, password);
    }

    @Test(priority = 6, description = "New pass and confirm pass are different")
    public void TC06() {
        page.resetPassword("khachhang.1205@gmail.com", "khachhang", RandomHelper.generateRandomPassword(6));
    }

    @Test(priority = 7)
    public void TC07() throws InterruptedException {
        page.resetPassword("khachhang.1205@gmail.com", "khachhang", "khachhang");
        loginPage.login("khachhang", "khachhang");
    }

    @Test(priority = 8)
    public void TC08() {
        page.openResetPasswordPage();
        page.resetPassword("khachhang.1205@gmail.com", RandomHelper.generateRandomPassword(6), "khachhang");
    }

    @Test(priority = 9)
    public void TC09() throws InterruptedException {
        page.resetPassword("khachhang.1205@gmail.com", RandomHelper.generateRandomPassword(6), RandomHelper.generateRandomPassword(6));
        loginPage.login("khachhang", RandomHelper.generateRandomPassword(6));
    }
}
