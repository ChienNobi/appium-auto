package page;

import core.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CancelTicketPage extends BasePage {

    @FindBy(id = "huyVe")
    private WebElement cancelTicketBtn;


    public CancelTicketPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    public boolean cancelTicket() {
        try {
            System.out.println("Cancel a ticket");
            cancelTicketBtn.click();
            popUpConfirmBtn.click();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
