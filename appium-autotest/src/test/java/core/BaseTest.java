package core;

import config.EmulatorConfig;
import helpers.ExcelReader;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import page.LoginPage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class BaseTest {
    public AppiumDriver driver;
    public LoginPage loginPage;
    public ExcelReader excelReader;
    public List<Map<String, String>> testData;

    @BeforeSuite
    public void preRun() throws IOException, InterruptedException {
        driver = EmulatorConfig.getAndroidDriver();
        EmulatorConfig.setWaitImplicit(10);
        loginPage = new LoginPage();
    }

    @AfterMethod
    public void refreshApp() {
        EmulatorConfig.resetApp();
    }

    @AfterClass()
    public void endRun() throws IOException {
        EmulatorConfig.quit();
    }

    public void initExcelData(String sheetName, String[] headers) throws IOException {
        excelReader = new ExcelReader(SystemDefault.TEST_DATA_FILE, sheetName);
        testData = excelReader.parseData(headers, true);
    }
}
