package page;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class BusManagementPage extends BasePage {
    @FindBy(id = "com.example.myapplication:id/search_button")
    private WebElement searchButton;

    @FindBy(id = "com.example.myapplication:id/search_src_text")
    private WebElement searchInput;

    @FindBy(id = "action_add_chuyen_xe")
    private WebElement addItemMenu;

    @FindBy(id = "edtTenChuyenXe")
    private WebElement tenField;

    @FindBy(id = "edtDiaDiemDi")
    private WebElement diaDiemDiField;

    @FindBy(id = "edtDiaDiemDen")
    private WebElement diaDiemDenField;

    @FindBy(id = "edtThoiGianBatDau")
    private WebElement thoiGianBatDauField;

    @FindBy(id = "edtThoiGianDen")
    private WebElement thoiGianDenField;

    @FindBy(id = "edtGiaTien")
    private WebElement giaTienField;

    @FindBy(id = "btnThemChuyenXe")
    private WebElement addButton;

    @FindBy(id = "btnEditChuyenXe")
    private WebElement editButton;

    @FindBy(id = "edtMoTa")
    private WebElement motaField;

    @FindBy(id = "btnUpdateChuyenXe")
    private WebElement updateButton;

    @FindBy(xpath = "(//android.widget.FrameLayout[@resource-id=\"com.example.myapplication:id/itemChuyenXe\"])[5]/android.widget.RelativeLayout")
    private WebElement detailItem;

    @FindBy(id = "action_show_chuyen_xe")
    private WebElement listItemMenu;

    @FindBy(id = "itemChuyenXe")
    private List<WebElement> listItems;

    public BusManagementPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    public void search(String word) {
        busManagementMenuClick();
        System.out.println("Search bus with keyword: " + word);
        searchButton.click();
        searchInput.sendKeys(word);
    }

    public void add(String ten, String diaDiemDi, String diaDiemDen, String thoiGianBatDau, String thoiGianDen, String giaTien) {
        System.out.println("Add bus with data: " );
        addItemMenu.click();
        tenField.sendKeys(ten);
        diaDiemDiField.sendKeys(diaDiemDi);
        diaDiemDenField.sendKeys(diaDiemDen);
        thoiGianBatDauField.sendKeys(thoiGianBatDau);
        thoiGianDenField.sendKeys(thoiGianDen);
        giaTienField.sendKeys(giaTien);
        addButton.click();
    }

    public void editBus(String ten, String moTa) {
        System.out.println("Edit bus with data: ");
        editButton.click();
        tenField.clear();
        tenField.sendKeys(ten);
        motaField.sendKeys(moTa);
        updateButton.click();
    }

    public void delete() {
        System.out.println("Delete bus");
        detailItem.click();
        listItemMenu.click();
        if (listItems.size() >= 5) {
            WebElement itemToRemove = listItems.get(4);
            WebElement deleteButton = itemToRemove.findElement(By.id("btnDeleteChuyenXe"));
            deleteButton.click();
            popUpConfirmBtn.click();
        } else {
            System.out.println("Danh sách không đủ phần tử để xóa.");
        }
    }
}
