package page;

import core.BasePage;
import helpers.CommonHelper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UpdateUserInfoPage extends BasePage {
    @FindBy(xpath = "//android.widget.EditText")
    private List<WebElement> editableField;

    @FindBy(id = "btnUpdateAccount")
    private WebElement updateBtn;

    @FindBy(id = "btnLogout")
    private WebElement logoutBtn;

    public UpdateUserInfoPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    public void updateData(String phoneNumber, String email) {
        System.out.println("Update user with info: phone number " + phoneNumber + ", email " + email);

        CommonHelper.sendKeys(editableField.get(2), phoneNumber);
        CommonHelper.sendKeys(editableField.get(3), email);
        updateBtn.click();
    }
}
