package test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class CancellationTicket {
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
//        MobileElement popularTextView = driver.findElement(By.xpath("//LinearLayout/android.widget.TextView[1]"));
        MobileElement datVeButton = driver.findElement(By.id("btnDatVe"));
        datVeButton.click();
        MobileElement spinnerSoLuongVe = driver.findElement(By.id("soLuongVe"));
        spinnerSoLuongVe.click();
        MobileElement firstValue = driver.findElement(By.xpath("//android.widget.ListView/android.widget.TextView[1]"));
        String firstValueText = firstValue.getText();
        firstValue.click();

        MobileElement edtNgayDi = driver.findElement(By.id("edtNgayDi"));
        String randomDay7 = randomFutureDate();
        edtNgayDi.sendKeys(randomDay7);
        MobileElement khuHoiSwitch = driver.findElement(By.id("khuHoi"));
        khuHoiSwitch.click();
        MobileElement edtNgayVe = driver.findElement(By.id("edtNgayVe"));
        String randomDay8 = randomDay(randomDay7, "31/12/2030");
        edtNgayVe.sendKeys(randomDay8);
        MobileElement xacNhanButton = driver.findElement(By.id("xacNhan"));
        xacNhanButton.click();
        MobileElement xacNhanAlertDialogButton = driver.findElement(By.id("android:id/button1")); // Thay "xacNhanAlertDialog" bằng id thật của nút xác nhận trong AlertDialog
        xacNhanAlertDialogButton.click();
        selectMenuItem(driver);
    }
    public static String randomFutureDate(){
        LocalDate startDate =LocalDate.now();
        LocalDate endDate = LocalDate.of(2030, 12, 31);
        long randomEpochDay = ThreadLocalRandom.current().nextLong(startDate.toEpochDay(), endDate.toEpochDay() + 1);
        LocalDate randomDate = LocalDate.ofEpochDay(randomEpochDay);
        return randomDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    public static String randomDay(String goDate, String backDate){
        LocalDate startDate = LocalDate.parse(goDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate endDate = LocalDate.parse(backDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        long randomEpochDay = ThreadLocalRandom.current().nextLong(startDate.toEpochDay(), endDate.toEpochDay() + 1);
        LocalDate randomDate = LocalDate.ofEpochDay(randomEpochDay);
        return randomDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static AppiumDriver<MobileElement> initializeDriver() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName", "Galaxy M20");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appPackage", "com.example.myapplication");
        desiredCapabilities.setCapability("appActivity", "com.example.myapplication.view.MainActivity");
        return new AndroidDriver<>(new URL("http://192.168.0.80:4723/wd/hub"), desiredCapabilities);
    }
    public static void selectMenuItem(AppiumDriver<MobileElement> driver) {
        MobileElement menuItem = driver.findElement(By.id("action_history"));
        menuItem.click();
        // Đợi cho trang lịch sử được tải hoàn chỉnh
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Tìm và click vào nút "Hủy vé"
        MobileElement cancelButton = driver.findElement(By.id("huyVe"));
        cancelButton.click();
        // Đợi cho dialog xác nhận hủy vé xuất hiện
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Xác nhận hủy vé trong AlertDialog
        MobileElement confirmButton = driver.findElement(By.id("android:id/button1"));
        confirmButton.click();
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
            return ngayHienTai; // Trả về ngày hiện tại nếu có lỗi xảy ra
        }
    }
}
