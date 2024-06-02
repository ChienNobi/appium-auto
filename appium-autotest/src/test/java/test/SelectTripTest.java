package test;

import core.BaseTest;
import org.testng.annotations.BeforeClass;
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

    @Test
    public void TC01() throws InterruptedException {
        loginPage.login("ngocnt", "123456");
        loginPage.selectSearchMenu();
        sleep(1000);

        page.selectData();
    }
}
