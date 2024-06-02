package config;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ThreadGuard;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class EmulatorConfig {
    private static AppiumDriver driver;

    public static void setDriver() throws MalformedURLException {
        System.out.println("Init Android Driver");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName", "emulator-5554");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appPackage", "com.example.myapplication");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appActivity", "com.example.myapplication.view.MainActivity");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), desiredCapabilities);
        System.out.println("Successfully init driver");
    }

    public static AppiumDriver getAndroidDriver() {
        if(driver == null) {
            try {
                setDriver();
                return driver;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
        return driver;
    }

    /**
     * set wait implicit
     *
     * @param timeout in millis second
     */
    public static void setWaitImplicit(int timeout) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
    }

    public static void quit() {
        driver.quit();
        driver = null;
    }

    public static void startActivity(){
        String appActivity = driver.getCapabilities().getCapability("appPackage").toString()+"/.view.MainActivity";
        driver.executeScript("mobile: startActivity", ImmutableMap.of("intent", appActivity));

    }

    public static void clearApp() {
        String appPackage = driver.getCapabilities().getCapability("appPackage").toString();
        driver.executeScript("mobile: clearApp", ImmutableMap.of("appId", appPackage));
    }


    public static void resetApp(){
        clearApp();
        startActivity();
    }
}
