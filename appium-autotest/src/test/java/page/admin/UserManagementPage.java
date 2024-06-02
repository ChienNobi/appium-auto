package page.admin;

import core.BasePage;
import helpers.CommonHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UserManagementPage extends BasePage {
    @FindBy(id = "com.example.myapplication:id/search_button")
    private WebElement searchButton;

    @FindBy(id = "com.example.myapplication:id/search_src_text")
    private WebElement searchInput;

    @FindBy(id = "action_add")
    private WebElement addItemMenu;

    @FindBy(id = "edtTenDangNhap")
    private WebElement tenDangNhapField;

    @FindBy(id = "edtHo")
    private WebElement hoField;

    @FindBy(id = "edtTen")
    private WebElement tenField;

    @FindBy(id = "edtMatKhau")
    private WebElement matKhauField;

    @FindBy(id = "edtNgaySinh")
    private WebElement ngaySinhField;

    @FindBy(id = "edtSoDienThoai")
    private WebElement soDienThoaiField;

    @FindBy(id = "edtEmail")
    private WebElement emailField;

    @FindBy(id = "edtAvatar")
    private WebElement avatarField;

    @FindBy(id = "btnAdd")
    private WebElement addButton;

    @FindBy(id = "btnEdit")
    private WebElement editButton;

    @FindBy(id = "btnUpdate")
    private WebElement updateButton;

    @FindBy(id = "btnDelete")
    private WebElement deleteButton;
    @FindBy(xpath = "(//android.widget.FrameLayout[@resource-id=\"com.example.myapplication:id/itemThanhVien\"])[6]/android.widget.RelativeLayout")
    private WebElement detailItem;

    @FindBy(id = "action_show")
    private WebElement listItemMenu;

    @FindBy(id = "itemThanhVien")
    private List<WebElement> listItems;

    public UserManagementPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    public void searchUser(String query) {
        CommonHelper.sendKeys(searchInput, query);
    }

    public void addUser(String userName, String firstName, String lastName, String password, String dateOfBirth, String soDienThoai, String email, String avatar) {
        addItemMenu.click();
        tenDangNhapField.sendKeys(userName);
        hoField.sendKeys(firstName);
        tenField.sendKeys(lastName);
        matKhauField.sendKeys(password);
        ngaySinhField.sendKeys(dateOfBirth);
        soDienThoaiField.sendKeys(soDienThoai);
        emailField.sendKeys(email);
        avatarField.sendKeys(avatar);
        addButton.click();
    }

    public void editUser(String dateOfBirth, String name) {
        listItems.get(0).findElement(By.id("btnEdit")).click();
        CommonHelper.sendKeys(ngaySinhField, dateOfBirth);
        CommonHelper.sendKeys(tenField, name);

        updateButton.click();
    }

    public void viewDetailItem() {
        detailItem.click();
    }

    public void deleteUser() {
        listItemMenu.click();
        if (listItems.size() >= 6) {
            WebElement deleteButton = listItems.get(5).findElement(By.id("btnDelete"));
            deleteButton.click();
            popUpConfirmBtn.click();
        } else {
            System.out.println("Danh sách không đủ phần tử để xóa.");
        }
    }
}
