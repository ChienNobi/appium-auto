package test;

import core.BaseTest;
import helpers.RandomHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.UpdateUserInfoPage;

public class UpdateUserInfoTest extends BaseTest {
    UpdateUserInfoPage page;

    @BeforeClass
    public void setUp() {
        page = new UpdateUserInfoPage();
    }

    @Test(priority = 1, description = "[USER] Update with valid data email, phone")
    public void TC01_User_Update_Info() throws InterruptedException {
        loginPage.login("ngocnt", "123456");
        loginPage.selectAccountMenu();
        page.updateData(RandomHelper.generateRandomPhoneNumber(10) ,RandomHelper.generateRandomEmail());
        Assert.assertEquals(page.getToastMessage(), "Cập nhật thành công");
    }

    @Test(priority = 2, description = "[ADMIN] Update with valid data email, phone")
    public void TC02_Admin_Update_Info() throws InterruptedException {
        loginPage.login("thaigiavuong", "admin");
        loginPage.selectAccountMenu();
        page.updateData(RandomHelper.generateRandomPhoneNumber(10) ,RandomHelper.generateRandomEmail());
        Assert.assertEquals(page.getToastMessage(), "Cập nhật thành công");
    }
}
