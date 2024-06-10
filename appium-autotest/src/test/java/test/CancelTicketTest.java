package test;

import core.BaseTest;
import helpers.RandomHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.BookTicketPage;
import page.CancelTicketPage;
import page.admin.TicketManagementPage;

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

    @Test(priority = 2)
    public void TC02_CancelTicketWithOtherStatus() throws InterruptedException {
        loginPage.login("khachhang", "khachhang");
        bookTicketPage.placeBooking(RandomHelper.randomFutureDate(), "", false);
        page.logout();
        loginPage.loginWithClick("thaigiavuong", "admin");

        TicketManagementPage ticketManagementPage = new TicketManagementPage();
        ticketManagementPage.changeTicketStatus();

        loginPage.loginWithClick("khachhang", "khachhang");
        page.goToHistoryPage();
        page.cancelTicket();

        Assert.assertEquals(page.getToastMessage(), "Bạn không thể hủy vé khi vé đang được duyệt bởi admin");
    }
}
