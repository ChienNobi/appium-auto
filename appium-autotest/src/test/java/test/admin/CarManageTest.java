package test.admin;

import config.EmulatorConfig;
import core.BaseTest;
import helpers.RandomHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.admin.CarManagePage;

import java.io.IOException;
import java.util.Random;

public class CarManageTest extends BaseTest {
    CarManagePage page;
    String[] words = {"Ford Transit", "Thaco Kinglong", "Mercedes Sprinter Limousine", "Limousine", "Huyndai", "Sakura", "Long Bus"};
    @BeforeClass
    public void setUp() throws IOException {
        page = new CarManagePage();
    }

    @BeforeMethod
    public void beforeMethod() throws IOException, InterruptedException {
        loginPage.login("thaigiavuong", "admin");
        page.carTypeMenuClick();
    }

    @AfterMethod
    public void afterMethod() {
        EmulatorConfig.resetApp();
    }

    @Test(priority = 0)
    public void TC01_SearchCarType() throws InterruptedException {

        Random random = new Random();
        int index = random.nextInt(words.length);
        String wordToSearch = words[index];

        page.searchCarType(wordToSearch);
    }

    @Test(priority = 1)
    public void TC02_AddNewCarType() throws InterruptedException {
        Random random = new Random();
        int index = random.nextInt(words.length);
        page.addNewCar(words[index], RandomHelper.generateRandomNumber(10, 20));
        String result = page.getToastMessage();
        System.out.println(result);
    }

    @Test(priority = 2)
    public void TC03_DeleteCarType() throws InterruptedException {
        Random random = new Random();
        int index = random.nextInt(words.length);
        page.addNewCar(words[index], RandomHelper.generateRandomNumber(10, 20));
        String result = page.getToastMessage();
        System.out.println(result);
    }

}
