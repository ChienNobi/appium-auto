package test;

import core.BaseTest;
import helpers.RandomHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.BookTicketPage;
import page.CancelTicketPage;

import java.io.IOException;

public class CancelTicketTest extends BaseTest {
    CancelTicketPage page;
    BookTicketPage bookTicketPage;
    @BeforeClass
    public void setUp() throws IOException {
        page = new CancelTicketPage();
        bookTicketPage = new BookTicketPage();
    }

    @Test(priority = 1)
    public void TC01_CancelTicket() throws InterruptedException {
        loginPage.login("khachhang", "khachhang");
        bookTicketPage.placeBooking(RandomHelper.randomFutureDate(), "", false);
        page.goToHistoryPage();
        Assert.assertTrue(page.cancelTicket());
    }
}
