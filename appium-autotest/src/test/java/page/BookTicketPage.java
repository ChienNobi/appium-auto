package page;

import core.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookTicketPage extends BasePage {
    @FindBy(id = "btnDatVe")
    private WebElement placeOrderBtn;

    @FindBy(id = "soLuongVe")
    private WebElement ticketNumberField;

    @FindBy(xpath = "//android.widget.ListView/android.widget.TextView[1]")
    private WebElement firstValue;

    @FindBy(id = "edtNgayDi")
    private WebElement startDateField;

    @FindBy(id = "edtNgayVe")
    private WebElement endDateField;

    @FindBy(id = "khuHoi")
    private WebElement roundTripBtn;

    @FindBy(id = "xacNhan")
    private WebElement confirmBtn;

    @FindBy(id = "android:id/button1")
    private WebElement agreeBtn;

    public BookTicketPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    public void placeBooking(String startDate, String endDate, boolean isRoundTrip) {
        System.out.println("Enter booking info: " + "start date " + startDate + ", end date " + endDate);
        placeOrderBtn.click();
        if(!startDate.isEmpty()) {
            startDateField.sendKeys(startDate);
        }

        boolean currentRoundTripStatus = roundTripBtn.getAttribute("checked").equals("true");

        if(isRoundTrip) {
            if(!currentRoundTripStatus) {
                roundTripBtn.click();
            }
            endDateField.sendKeys(endDate);
        }

        confirmBtn.click();
        agreeBtn.click();
    }
}

