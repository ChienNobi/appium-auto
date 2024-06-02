package test;

import config.EmulatorConfig;
import core.BaseTest;
import helpers.ExcelWriter;
import helpers.RandomHelper;
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

    int currentRow = 0;
    int START_DATE_INDEX = 0;
    int END_DATE_INDEX = 1;
    int ROUND_TRIP_INDEX = 2;
    int RESULT_INDEX = 3;
    @BeforeClass
    public void setUp() throws IOException {
        page = new BookTicketPage();
        excelWriter = new ExcelWriter(RESULT_FILE, "BookTicketTest");
    }

    @Override
    @AfterClass()
    public void endRun() throws IOException {
        EmulatorConfig.quit();
        excelWriter.writeFile();
    }

    @BeforeMethod
    public void increaseRow() {
        currentRow++;
        EmulatorConfig.resetApp();
    }

    @Test(priority = 1, description = "Do not enter start date, isRoundTrip = true")
    public void TC01() throws InterruptedException {
        loginPage.login("ngocnt", "123456");

        String endDate = RandomHelper.randomDay();
        page.placeBooking("", endDate, true);
        excelWriter.setCellValue(currentRow, END_DATE_INDEX, endDate);
        excelWriter.setCellValue(currentRow, ROUND_TRIP_INDEX, "Vé khứ hồi");
        excelWriter.setCellValue(currentRow, RESULT_INDEX, loginPage.getToastMessage());
    }

    @Test(priority = 2, description = "Do not enter end date")
    public void TC02() throws InterruptedException {
        loginPage.login("ngocnt", "123456");

        String startDate = RandomHelper.randomDay();
        page.placeBooking(startDate, "", true);
        excelWriter.setCellValue(currentRow, START_DATE_INDEX, startDate);
        excelWriter.setCellValue(currentRow, ROUND_TRIP_INDEX, "Vé khứ hồi");
        excelWriter.setCellValue(currentRow, RESULT_INDEX, loginPage.getToastMessage());
    }

    @Test(priority = 3, description = "Start date < End date, isRoundTrip = true")
    public void TC03() throws InterruptedException {
        loginPage.login("ngocnt", "123456");

        String startDate = RandomHelper.randomPastDate();
        String endDate = RandomHelper.randomFutureDate();
        page.placeBooking(startDate, endDate, true);

        excelWriter.setCellValue(currentRow, START_DATE_INDEX, startDate);
        excelWriter.setCellValue(currentRow, END_DATE_INDEX, endDate);
        excelWriter.setCellValue(currentRow, ROUND_TRIP_INDEX, "Vé khứ hồi");
        excelWriter.setCellValue(currentRow, RESULT_INDEX, loginPage.getToastMessage());
    }

    @Test(priority = 4, description = "Start date > End date, isRoundTrip = true")
    public void TC04() throws InterruptedException {
        loginPage.login("ngocnt", "123456");

        String startDate = RandomHelper.randomFutureDate();
        String endDate = RandomHelper.randomPastDate();
        page.placeBooking(startDate, endDate, true);

        excelWriter.setCellValue(currentRow, START_DATE_INDEX, startDate);
        excelWriter.setCellValue(currentRow, END_DATE_INDEX, endDate);
        excelWriter.setCellValue(currentRow, ROUND_TRIP_INDEX, "Vé khứ hồi");
        excelWriter.setCellValue(currentRow, RESULT_INDEX, loginPage.getToastMessage());
    }

    @Test(priority = 5, description = "Start date > now, isRoundTrip = false")
    public void TC05() throws InterruptedException {
        loginPage.login("ngocnt", "123456");

        String startDate = RandomHelper.randomFutureDate();
        page.placeBooking(startDate, "", false);

        excelWriter.setCellValue(currentRow, START_DATE_INDEX, startDate);
        excelWriter.setCellValue(currentRow, ROUND_TRIP_INDEX, "Vé 1 chiều");
        excelWriter.setCellValue(currentRow, RESULT_INDEX, loginPage.getToastMessage());
    }

    @Test(priority = 6, description = "Invalid start date, isRoundTrip = false")
    public void TC06() throws InterruptedException {
        loginPage.login("ngocnt", "123456");

        String startDate = RandomHelper.generateRandomString();
        page.placeBooking(startDate, "", false);

        excelWriter.setCellValue(currentRow, START_DATE_INDEX, startDate);
        excelWriter.setCellValue(currentRow, ROUND_TRIP_INDEX, "Vé 1 chiều");
        excelWriter.setCellValue(currentRow, RESULT_INDEX, loginPage.getToastMessage());
    }
}
