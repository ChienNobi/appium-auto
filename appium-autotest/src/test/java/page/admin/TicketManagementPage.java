package page.admin;

import core.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TicketManagementPage extends BasePage {
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id='com.example.myapplication:id/cvVe']/android.widget.LinearLayout")
    private WebElement firstTicket;

    @FindBy(id = "spnTrangThai")
    private WebElement ticketStatus;

    @FindBy(xpath = "//android.widget.ListView/android.widget.TextView[2]")
    private WebElement selectedValue;

    @FindBy(id = "btnXacNhan")
    private WebElement confirmBtn;

    public TicketManagementPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    public void changeTicketStatus() {
        goToAdminMangageTicket();
        firstTicket.click();
        ticketStatus.click();
        selectedValue.click();
        confirmBtn.click();

        driver.navigate().back();
        logout();
    }

}
