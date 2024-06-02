package page;

import core.BasePage;
import helpers.ExcelWriter;
import helpers.RandomHelper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileNotFoundException;
import java.io.IOException;

import static core.SystemDefault.RESULT_FILE;
import static core.SystemDefault.TEST_DATA_FILE;

public class RegisterPage extends BasePage {
    @FindBy(id = "txtUserName")
    private WebElement usernameField;

    @FindBy(id = "txtName")
    private WebElement nameField;

    @FindBy(id = "txtLastName")
    private WebElement lastNameField;

    @FindBy(id = "txtPhoneNumber")
    private WebElement phoneNumberField;

    @FindBy(id = "txtEmail")
    private WebElement emailField;

    @FindBy(id = "txtPassword")
    private WebElement passwordField;

    @FindBy(id = "txtPasswordAgain")
    private WebElement confirmPasswordField;

    @FindBy(id = "switchDieuKhoan")
    private WebElement switchButton;

    @FindBy(id = "btnSignUp")
    private WebElement signUpButton;

    @FindBy(id = "com.example.myapplication:id/button_signup_home")
    private WebElement signInButtonHome;

    private static boolean isClickPolicy = false;

    public static ExcelWriter excelWriter;

    public RegisterPage() throws FileNotFoundException {
        super();
        PageFactory.initElements(driver, this);
        excelWriter = new ExcelWriter(RESULT_FILE, "RegisterTest");
    }

    public void fillRegistrationForm(String username, String name, String lastName, String phoneNumber, String email, String password, String confirmPassword) {
        System.out.println("Enter Register Form");
        usernameField.sendKeys(username);
        nameField.sendKeys(name);
        lastNameField.sendKeys(lastName);
        phoneNumberField.sendKeys(phoneNumber);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(confirmPassword);

        if (!isClickPolicy) {
            isClickPolicy = true;
            switchButton.click();
        }

        signUpButton.click();
    }

    public void randomValue(int i) {
        String username = i == 7 ? "ngocnt" : (i != 1 ? RandomHelper.generateRandomString(6) : "");
        String name = i == 7 ? "Ngoc" : (i != 2 ? RandomHelper.generateRandomString(6) : "");
        String lastName = i == 7 ? "Nguyen" : (i != 3 ? RandomHelper.generateRandomString(6) : "");
        String phoneNumber = i == 7 ? "0538592386" : (i != 4 ? "0" + RandomHelper.generateRandomPhoneNumber(9) : "");
        String email = i == 7 ? "ngocnt@gmail.com" : (i != 5 ? RandomHelper.generateRandomString(6) + "@gmail.com" : "");
        String password = i == 7 ? "123456" : (i != 6 ? RandomHelper.generateRandomString(6) : "");

        fillRegistrationForm(username, name, lastName, phoneNumber, email, password, password);

        String message = "Success";
        try {
            message = getToastMessage();
        } catch (Exception e) {

        }

        excelWriter.setCellValue(i, 0, username);
        excelWriter.setCellValue(i, 1, name);
        excelWriter.setCellValue(i, 2, lastName);
        excelWriter.setCellValue(i, 3, phoneNumber);
        excelWriter.setCellValue(i, 4, email);
        excelWriter.setCellValue(i, 5, password);
        excelWriter.setCellValue(i, 6, password);
        excelWriter.setCellValue(i, 7, message);
    }

    public void registerNewUserTest(int numberOfUser) throws IOException {
        signInButtonHome.click();

        excelWriter.setCellValue(0, 0, "Username");
        excelWriter.setCellValue(0, 1, "Name");
        excelWriter.setCellValue(0, 2, "Last Name");
        excelWriter.setCellValue(0, 3, "Phone Number");
        excelWriter.setCellValue(0, 4, "Email");
        excelWriter.setCellValue(0, 5, "Password");
        excelWriter.setCellValue(0, 6, "Confirm Password");
        excelWriter.setCellValue(0, 7, "Result");

        for (int i = 1; i <= numberOfUser; i++) {
            try {
                randomValue(i);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        excelWriter.writeFile();
    }
}
