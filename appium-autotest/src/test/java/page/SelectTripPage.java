package page;

import core.BasePage;
import helpers.RandomHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Random;

public class SelectTripPage extends BasePage {
    @FindBy(id = "spinnerDiaDiemDi")
    private WebElement departureField;

    @FindBy(id = "spinnerDiaDiemDen")
    private WebElement destinationField;

    @FindBy(id = "spinnerLoaiXe")
    private WebElement busTypeField;

    @FindBy(id = "edtGioDi")
    private WebElement timeField;

    @FindBy(id = "btnLoc")
    private WebElement btnSearch;

    @FindBy(id = "tvThongBao")
    private WebElement notFoundMessage;

    public SelectTripPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    String[] destinationOptions =
            new String[]{"Bến xe Vũng Tàu","Bến xe Nha Trang","Bến xe phía Bắc","Bến xe Quảng Bình","Bến xe Hải Dương","Bến xe Quảng Ninh","Bến xe Thừa Thiên Huế","Bến xe Cao Bằng","Bến xe Mỹ Đình","Bến xe Hà Nam"};
    String[] busTypes =
            new String[]{"Ford Transit", "Thaco Kinglong", "Mercedes Sprinter Limousine", "Luxury Limousine", "Sakura", "Huyndai", "Long Limousine", "Travel Bus", "Mercedes VIP", "Transport Vehicle"};
    String[] departureOptions =
            new String[]{"Bến xe Thành phố 1", "Bến xe Miền Tây", "Bến xe Giáp Bát", "Bến xe Thành phố 2", "Bến xe Mỹ Đình", "Bến xe Hà Nam", "Bến xe Nghệ An", "Bến xe Sơn La", "Bến xe Sa Pa", "Bến xe Hà Tĩnh"};
    public SelectTripPage selectDeparture() {
        departureField.click();
        selectOption(random.nextInt(departureOptions.length));
        return this;
    }

    public SelectTripPage selectDestination() {
        destinationField.click();
        selectOption(random.nextInt(destinationOptions.length));
        return this;
    }

    public SelectTripPage selectTime() {
        timeField.sendKeys(RandomHelper.randomTime());
        return this;
    }

    public SelectTripPage selectBusType() {
        busTypeField.click();
        selectOption(random.nextInt(busTypes.length));
        return this;
    }

    public String searchAndGetResult() {
        btnSearch.click();
        String result = getResult();
        System.out.println("Search result: " + result);
        return result;
    }

    public void selectOption(int index) {
        driver.findElement(By.xpath("//android.widget.ListView/android.widget.CheckedTextView["+index+"]")).click();
    }

    public String getResult() {
        if(!notFoundMessage.isDisplayed()) {
            return "Found Trip";
        }
        return notFoundMessage.getText();
    }
}
