package test;

import config.EmulatorConfig;
import core.BaseTest;
import helpers.ExcelWriter;
import helpers.RandomHelper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.BookTicketPage;
import java.io.IOException;

import static core.SystemDefault.RESULT_FILE;

public class BookTicketTest extends BaseTest {
    BookTicketPage page;
    ExcelWriter excelWriter;

    @BeforeClass
    public void setUp() throws IOException {
        page = new BookTicketPage();
    }

    @BeforeMethod
    public void resetApp() {
        EmulatorConfig.resetApp();
    }

    @Test(priority = 1, description = "Do not enter start date, isRoundTrip = true")
    public void TC01() throws InterruptedException {
        loginPage.login("ngocnt", "123456");

        String endDate = RandomHelper.randomDay();
        page.placeBooking("", endDate, true);
        String message = page.getToastMessage();
        Assert.assertEquals(message, "Vui lòng nhập thông tin vé");
    }

    @Test(priority = 2, description = "Do not enter end date")
    public void TC02() throws InterruptedException {
        loginPage.login("ngocnt", "123456");

        String startDate = RandomHelper.randomDay();
        page.placeBooking(startDate, "", true);
        String message = page.getToastMessage();
        Assert.assertEquals(message, "Vui lòng nhập thông tin vé");
    }

    @Test(priority = 3, description = "Start date < now < End date, isRoundTrip = true")
    public void TC03() throws InterruptedException {
        loginPage.login("ngocnt", "123456");

        String startDate = RandomHelper.randomPastDate();
        String endDate = RandomHelper.randomFutureDate();
        page.placeBooking(startDate, endDate, true);
        String message = page.getToastMessage();
        Assert.assertEquals(message, "Vui lòng kiểm tra lại thông tin vé");
    }

    @Test(priority = 4, description = "Start date > End date, isRoundTrip = true")
    public void TC04() throws InterruptedException {
        loginPage.login("ngocnt", "123456");

        String startDate = RandomHelper.randomFutureDate();
        String endDate = RandomHelper.randomPastDate();
        page.placeBooking(startDate, endDate, true);
        String message = page.getToastMessage();
        Assert.assertEquals(message, "Vui lòng kiểm tra lại thông tin vé");
    }

    @Test(priority = 5, description = "Start date > now, isRoundTrip = false")
    public void TC05() throws InterruptedException {
        loginPage.login("ngocnt", "123456");

        String startDate = RandomHelper.randomFutureDate();
        page.placeBooking(startDate, "", false);
        String message = page.getToastMessage();
        Assert.assertEquals(message, "Đặt vé thành công, vui lòng chuyển sang tab lịch sử để xem");
    }

    @Test(priority = 6, description = "Invalid start date, isRoundTrip = false")
    public void TC06() throws InterruptedException {
        loginPage.login("ngocnt", "123456");

        String startDate = RandomHelper.generateRandomString();
        page.placeBooking(startDate, "", false);
        String message = page.getToastMessage();
        Assert.assertEquals(message, "Vui lòng kiểm tra lại thông tin vé");
    }

    @Test(priority = 7, description = "Empty start date + end date, isRoundTrip = true")
    public void TC07() throws InterruptedException {
        loginPage.login("ngocnt", "123456");

        page.placeBooking("", "", true);
        String message = page.getToastMessage();
        Assert.assertEquals(message, "Vui lòng nhập thông tin vé");
    }

    @Test(priority = 8, description = "Valid start date + end date, isRoundTrip = true")
    public void TC08() throws InterruptedException {
        loginPage.login("ngocnt", "123456");
        page.placeBooking(RandomHelper.randomFutureDate(), RandomHelper.randomFutureDate(), true);
        String message = page.getToastMessage();
        Assert.assertEquals(message, "Đặt vé thành công, vui lòng chuyển sang tab lịch sử để xem");
    }
}
