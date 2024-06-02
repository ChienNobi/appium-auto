package test;

import core.BaseTest;
import helpers.RandomHelper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.BusManagementPage;

import java.io.IOException;
import java.util.Random;

public class BusManagementTest extends BaseTest {
    private BusManagementPage page;

    @BeforeClass
    public void setUp() throws IOException {
        page = new BusManagementPage();
    }

    @Test
    public void TC01() throws InterruptedException {
        loginPage.login("thaigiavuong", "admin");

        String[] words = {"Tây Ninh", "TP.Nha Trang", "TP.Cần Thơ", "TP.Gia Nghĩa", "Hà Nội", "Đà Nẵng", "Hà Nam", "Quảng Bình"};
        Random random = new Random();
        int index = random.nextInt(words.length);

        page.search(words[index]);
        page.add(RandomHelper.generateRandomString(), RandomHelper.generateRandomString(), RandomHelper.generateRandomString(), RandomHelper.generateRandomString(), RandomHelper.generateRandomString(), RandomHelper.generateRandomNumber(500, 5000));
        page.editBus(RandomHelper.generateRandomString(), RandomHelper.generateRandomString());
        page.delete();
    }
}


