package core;

import config.EmulatorConfig;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasePage {
    public AppiumDriver driver;

    @FindBy(xpath = "//android.widget.Toast")
    public WebElement toastMessage;

    @FindBy(id = "action_filter")
    public WebElement searchMenuBtn;

    public BasePage() {
        this.driver = EmulatorConfig.getAndroidDriver();
    }

    public String getToastMessage() {
        try {
            return toastMessage.getText();
        } catch (Exception e) {
            System.out.println("No toast message");
            return "";
        }
    }

    public void selectSearchMenu() {
        searchMenuBtn.click();
    }
}
