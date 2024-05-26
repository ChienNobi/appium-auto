package test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
public class TicketManagement {
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
        MobileElement datVeButton = driver.findElement(By.id("btnDatVe"));
        datVeButton.click();
        MobileElement spinnerSoLuongVe = driver.findElement(By.id("soLuongVe"));
        spinnerSoLuongVe.click();
        MobileElement firstValue = driver.findElement(By.xpath("//android.widget.ListView/android.widget.TextView[4]"));
        String firstValueText = firstValue.getText();
        firstValue.click();
        MobileElement edtNgayDi = driver.findElement(By.id("edtNgayDi"));
        String ngayHienTai = layNgayHienTai();
        edtNgayDi.sendKeys(ngayHienTai);
        MobileElement khuHoiSwitch = driver.findElement(By.id("khuHoi"));
        khuHoiSwitch.click();
        String ngayVe = tinhNgayVe(ngayHienTai);
        MobileElement edtNgayVe = driver.findElement(By.id("edtNgayVe"));
        edtNgayVe.sendKeys(ngayVe);
        MobileElement xacNhanButton = driver.findElement(By.id("xacNhan"));
        xacNhanButton.click();
        MobileElement xacNhanAlertDialogButton = driver.findElement(By.id("android:id/button1"));
        xacNhanAlertDialogButton.click();
        MobileElement menuItem = driver.findElement(By.id("action_history"));
        menuItem.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        MobileElement logoutItem = driver.findElement(By.id("action_account"));
        logoutItem.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        MobileElement logoutButton = driver.findElement(By.id("btnLogout"));
        logoutButton.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        MobileElement usernameFieldLogin = driver.findElement(By.id("txtUserName"));
        usernameFieldLogin.clear();
        usernameFieldLogin.sendKeys("thaigiavuong");
        MobileElement passwordFieldLogin = driver.findElement(By.id("txtPassword"));
        passwordFieldLogin.clear();
        passwordFieldLogin.sendKeys("admin");
        MobileElement signInButtonLogin = driver.findElement(By.id("btnSignIn"));
        signInButtonLogin.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        MobileElement layoutManagerUser = driver.findElement(By.id("layoutManagerVeDat"));
        layoutManagerUser.click();
        MobileElement element = driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id='com.example.myapplication:id/cvVe']/android.widget.LinearLayout"));
        element.click();
        MobileElement spinnerElement = driver.findElement(By.id("spnTrangThai"));
        spinnerElement.click();
        MobileElement selectedValue = driver.findElement(By.xpath("//android.widget.ListView/android.widget.TextView[4]"));
        selectedValue.click();
        MobileElement XacNhan = driver.findElement(By.id("btnXacNhan"));
        XacNhan.click();
        driver.navigate().back();
        MobileElement logoutItems = driver.findElement(By.id("action_account"));
        logoutItems.click();
        MobileElement loButton = driver.findElement(By.id("btnLogout"));
        loButton.click();
        MobileElement usernameLogin = driver.findElement(By.id("txtUserName"));
        usernameLogin.clear();
        usernameLogin.sendKeys("khachhang");
        MobileElement passwordLogin = driver.findElement(By.id("txtPassword"));
        passwordLogin.clear();
        passwordLogin.sendKeys("khachhang");
        MobileElement signInBtnLogin = driver.findElement(By.id("btnSignIn"));
        signInBtnLogin.click();
        MobileElement menuItems = driver.findElement(By.id("action_history"));
        menuItems.click();
    }
    public static AppiumDriver<MobileElement> initializeDriver() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName", "Galaxy M20");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appPackage", "com.example.myapplication");
        desiredCapabilities.setCapability("appActivity", "com.example.myapplication.view.MainActivity");
        return new AndroidDriver<>(new URL("http://192.168.0.80:4723/wd/hub"), desiredCapabilities);
    }
    public static String layNgayHienTai() {
        Date ngayHienTai = new Date();
        SimpleDateFormat dinhDangNgay = new SimpleDateFormat("dd/MM/yyyy");
        return dinhDangNgay.format(ngayHienTai);
    }
    public static String tinhNgayVe(String ngayHienTai) {
        try {
            SimpleDateFormat dinhDangNgay = new SimpleDateFormat("dd/MM/yyyy");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dinhDangNgay.parse(ngayHienTai));
            calendar.add(Calendar.DAY_OF_MONTH, 3);
            return dinhDangNgay.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return ngayHienTai;
        }
    }
}

