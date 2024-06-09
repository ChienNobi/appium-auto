package test;

import config.EmulatorConfig;
import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.SelectTripPage;

import java.io.IOException;

import static java.lang.Thread.sleep;


public class SelectTripTest extends BaseTest {
    SelectTripPage page;
    @BeforeClass
    public void setUp() throws IOException {
        page = new SelectTripPage();
    }

    @BeforeMethod
    public void startTest() throws InterruptedException {
        loginPage.login("ngocnt", "123456");
        page.selectSearchMenu();
        sleep(1000);
    }

    @AfterMethod
    public void refreshApp() {
        EmulatorConfig.resetApp();
        System.out.println("Reset App");
    }

    @Test(priority = 1, description = "Select only departure")
    public void TC01() throws InterruptedException {
        try {
            page.selectDeparture();
            String message = page.searchAndGetResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Assert.fail();
        }
    }

    @Test(priority = 2, description = "Select only destination")
    public void TC02() {
        try {
            page.selectDestination();
            String message = page.searchAndGetResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Assert.fail();
        }
    }

    @Test(priority = 3, description = "Select only busType")
    public void TC03() {
        try {
            page.selectBusType();
            String message = page.searchAndGetResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Assert.fail();
        }
    }

    @Test(priority = 4, description = "Select only time start")
    public void TC04() {
        try {
            page.selectTime();
            String message = page.searchAndGetResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Assert.fail();
        }
    }

    @Test(priority = 5, description = "Select departure + bus type")
    public void TC05() {
        try {
            page.selectDeparture().selectBusType().searchAndGetResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Assert.fail();
        }
    }

    @Test(priority = 6, description = "Select all data")
    public void TC06() {
        try {
            page.selectDeparture().selectBusType().selectDestination().selectTime().searchAndGetResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Assert.fail();
        }
    }
}
