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

    @Test(priority = 1, description = "Empty all data")
    public void TC01() {
        page.resetPassword("", "", "");
        String errorMessage = page.getToastMessage();
        Assert.assertEquals(errorMessage, "Vui lòng nhập dữ liệu đầy đủ");
    }

    @Test(priority = 2, description = "Empty Email")
    public void TC02_EmptyEmail() {
        String password = RandomHelper.generateRandomPassword(6);
        page.resetPassword("", password, password);
        String errorMessage = page.getToastMessage();
        Assert.assertEquals(errorMessage, "Vui lòng nhập dữ liệu đầy đủ");
    }

    @Test(priority = 3, description = "Empty Password")
    public void TC03_EmptyPassword() {
        String password = RandomHelper.generateRandomPassword(6);
        page.resetPassword(RandomHelper.generateRandomEmail(), "", password);
        String errorMessage = page.getToastMessage();
        Assert.assertEquals(errorMessage, "Vui lòng nhập dữ liệu đầy đủ");
    }

    @Test(priority = 4, description = "Empty confirm password")
    public void TC04_EmptyConfirmPassword() {
        String password = RandomHelper.generateRandomPassword(6);
        page.resetPassword("", password, "");
        String errorMessage = page.getToastMessage();
        Assert.assertEquals(errorMessage, "Vui lòng nhập dữ liệu đầy đủ");
    }

    @Test(priority = 5, description = "Test with random email + password")
    public void TC05_RandomEmail() {
        String password = RandomHelper.generateRandomPassword(6);
        page.resetPassword(RandomHelper.generateRandomEmail(), password, password);
        String errorMessage = page.getToastMessage();
        Assert.assertEquals(errorMessage, "Không tìm thấy thông tin người dùng");
    }

    @Test(priority = 6, description = "New pass and confirm pass are different")
    public void TC06_WrongConfirmPass() {
        page.resetPassword("khachhang.1205@gmail.com", "khachhang", RandomHelper.generateRandomPassword(6));
        String errorMessage = page.getToastMessage();
        Assert.assertEquals(errorMessage, "Kiểm tra lại mật khẩu");
    }

    @Test(priority = 7)
    public void TC07_SuccessChangePassword() {
        page.resetPassword("khachhang.1205@gmail.com", "khachhang", "khachhang");
        page.confirmPopUp();
        String message = page.getToastMessage();
        Assert.assertEquals(message, "Đặt lại mật khẩu thành công");
    }

    @Test(priority = 8)
    public void TC08() {
        page.resetPassword("khachhang.1205@gmail.com", "khachhang", RandomHelper.generateRandomPassword(8));
        page.confirmPopUp();
        String message = page.getToastMessage();
        Assert.assertEquals(message, "Kiểm tra lại mật khẩu");
    }
}
