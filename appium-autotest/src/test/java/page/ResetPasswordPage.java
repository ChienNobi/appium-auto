package page;

import core.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResetPasswordPage extends BasePage {
    @FindBy(id = "textViewResetPassword")
    private WebElement textViewResetPassword;

    @FindBy(id = "edtEmail")
    private WebElement emailField;

    @FindBy(id = "edtResetPassword")
    private WebElement newPasswordField;

    @FindBy(id = "edtResetAgainPassword")
    private WebElement confirmPasswordField;

    @FindBy(id = "btnResetPassword")
    private WebElement resetButton;

    @FindBy(id = "button_login_home")
    private WebElement loginButton;

    public ResetPasswordPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    public void openResetPasswordPage() {
        loginButton.click();
        textViewResetPassword.click();
    }

    public void resetPassword(String email, String newPassword, String confirmPassword) {
        System.out.println("Reset password info: email: " + email + ", new password: " + newPassword + ", confirm password: " + confirmPassword);
        emailField.sendKeys(email);
        newPasswordField.sendKeys(newPassword);
        confirmPasswordField.sendKeys(confirmPassword);
        resetButton.click();
    }
}
