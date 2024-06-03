package page.admin;

import core.BasePage;
import helpers.CommonHelper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CarManagePage extends BasePage {
    @FindBy(id = "com.example.myapplication:id/search_button")
    private WebElement searchButton;

    @FindBy(id = "com.example.myapplication:id/search_src_text")
    private WebElement searchInput;

    @FindBy(id = "com.example.myapplication:id/action_add")
    private WebElement addItemMenu;

    @FindBy(id = "edtTenLoaiXe")
    private WebElement tenLoaiXeField;

    @FindBy(id = "edtSoLuongGhe")
    private WebElement soLuongGheField;

    @FindBy(id = "btnThemLoaiXe")
    private WebElement addButton;

    @FindBy(id = "com.example.myapplication:id/action_show")
    private WebElement listItemMenu;

    @FindBy(id = "btnEdit")
    private WebElement editButton;

    @FindBy(id = "btnUpdateLoaiXe")
    private WebElement updateButton;

    @FindBy(xpath = "(//android.widget.ImageView[@resource-id='com.example.myapplication:id/btnDelete'])[4]")
    private WebElement deleteButton;

    public CarManagePage() {
        super();
        PageFactory.initElements(driver, this);
    }

    public int searchCarType(String keyword) {
        System.out.println("Search car type, key search: " + keyword);
        searchButton.click();
        CommonHelper.sendKeys(searchInput, keyword);
        return 1;
    }

    public void addNewCar(String carType, String numberOfChair) {
        addItemMenu.click();
        tenLoaiXeField.sendKeys(carType);
        soLuongGheField.sendKeys(numberOfChair);
        addButton.click();
    }
}
