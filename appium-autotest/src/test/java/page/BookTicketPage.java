package page;

import core.BasePage;
import org.openqa.selenium.support.PageFactory;

public class BookTicketPage extends BasePage {

    public BookTicketPage() {
        super();
        PageFactory.initElements(driver, this);
    }
}
