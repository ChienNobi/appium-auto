package test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.Random;

public class SelectTrip {
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
        usernameField.sendKeys("ngocnt");
        MobileElement passwordField = driver.findElement(By.id("txtPassword"));
        passwordField.sendKeys("123456");
        MobileElement signInButton = driver.findElement(By.id("btnSignIn"));
        signInButton.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        selectMenuItem(driver);



        // Chọn ngẫu nhiên loại xe, địa điểm đi và địa điểm đến

        selectRandomSpinnerValue(driver, "spinnerDiaDiemDi");
        clickButton(driver, "btnLoc");

        selectRandomSpinnerValue(driver, "spinnerDiaDiemDen");
        clickButton(driver, "btnLoc");

        selectRandomSpinnerValue(driver, "spinnerLoaiXe");
        clickButton(driver, "btnLoc");

        fillEditText(driver, "edtGioDi", randomTime());
        clickButton(driver, "btnLoc");

        selectRandomSpinnerValue(driver, "spinnerDiaDiemDi");
        selectRandomSpinnerValue(driver, "spinnerDiaDiemDen");
        selectRandomSpinnerValue(driver, "spinnerLoaiXe");
        clickButton(driver, "btnLoc");


    }

    public static void selectMenuItem(AppiumDriver<MobileElement> driver) {
        MobileElement menuItem = driver.findElement(By.id("action_filter"));
        menuItem.click();
    }

    public static void fillEditText(AppiumDriver<MobileElement> driver, String editTextId, String text) {
        MobileElement editText = driver.findElement(By.id(editTextId));
        editText.sendKeys(text);
    }

    public static String randomTime(){
        Random random = new Random();
        int hour = random.nextInt(24);
        int min = random.nextInt(60);
        Date date = new Date();
        date.setHours(hour);
        date.setMinutes(min);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(date);
    }

    public static void clickButton(AppiumDriver<MobileElement> driver, String buttonId) {
        MobileElement button = driver.findElement(By.id(buttonId));
        button.click();
    }

    public static void selectRandomSpinnerValue(AppiumDriver<MobileElement> driver, String spinnerId) {
        MobileElement spinner = driver.findElement(By.id(spinnerId));
        spinner.click();

        // Danh sách các loại xe hoặc địa điểm
        String[] options = null;
        if (spinnerId.equals("spinnerLoaiXe")) {
            options = new String[]{"Ford Transit", "Thaco Kinglong", "Mercedes Sprinter Limousine", "Luxury Limousine", "Sakura", "Huyndai", "Long Limousine", "Travel Bus", "Mercedes VIP", "Transport Vehicle"};
        } else if (spinnerId.equals("spinnerDiaDiemDi")) {
            options = new String[]{"Bến xe Thành phố 1", "Bến xe Miền Tây", "Bến xe Giáp Bát", "Bến xe Thành phố 2", "Bến xe Mỹ Đình", "Bến xe Hà Nam", "Bến xe Nghệ An", "Bến xe Sơn La", "Bến xe Sa Pa", "Bến xe Hà Tĩnh"};
        } else if (spinnerId.equals("spinnerDiaDiemDen")) {
            options = new String[]{"Bến xe Vũng Tàu","Bến xe Nha Trang","Bến xe phía Bắc","Bến xe Quảng Bình","Bến xe Hải Dương","Bến xe Quảng Ninh","Bến xe Thừa Thiên Huế","Bến xe Cao Bằng","Bến xe Mỹ Đình","Bến xe Hà Nam"};
        }

        // Chọn một phần tử ngẫu nhiên từ danh sách options
        Random random = new Random();
        int randomIndex = random.nextInt(options.length);
        MobileElement spinnerValue = driver.findElement(By.xpath("//android.widget.ListView/android.widget.CheckedTextView["+randomIndex+"]"));
        spinnerValue.click();
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
