package test.admin;

import core.BaseTest;
import helpers.RandomHelper;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.admin.UserManagementPage;

import java.io.IOException;
import java.util.Random;


public class UserManagementTest extends BaseTest {
    UserManagementPage page;
    @BeforeClass
    public void setUp() throws IOException {
        page = new UserManagementPage();
    }

    @Test(priority = 1)
    public void TC01_SearchUser() throws InterruptedException {
//        TODO: move this to before method
        loginPage.login("thaigiavuong", "admin");
        loginPage.userManagementMenuClick();

        String[] words = {"thaigiavuong", "dinhtanhuy", "dangtrungminh", "khachhang", "nhanvien"};
        Random random = new Random();
        int index = random.nextInt(words.length);
        String wordToSearch = words[index];
        page.searchUser(wordToSearch);
    }

    @Test(description = "[ADMIN] Add a new user")
    public void TC02_AddUser() throws InterruptedException {
        loginPage.login("thaigiavuong", "admin");
        loginPage.userManagementMenuClick();
        page.addUser(
                RandomHelper.generateRandomString(10),
                RandomHelper.generateRandomString(6),
                RandomHelper.generateRandomString(6),
                RandomHelper.generateRandomPassword(6),
                RandomHelper.generateRandomDateOfBirth(),
                RandomHelper.generateRandomPhoneNumber(10),
                RandomHelper.generateRandomEmail(),
                RandomHelper.generateRandomString(10)
        );
    }

    @Test
    public void TC03_EditUser() throws InterruptedException {
        loginPage.login("thaigiavuong", "admin");
        loginPage.userManagementMenuClick();
        page.editUser(
                RandomHelper.generateRandomDateOfBirth(),
                RandomHelper.generateRandomString(10)
        );

        Assert.assertEquals(page.getToastMessage(), "Chỉnh sửa thành công");
    }

    @Test
    public void TC04_DeleteUser() throws InterruptedException {
        loginPage.login("thaigiavuong", "admin");
        loginPage.userManagementMenuClick();

        page.deleteUser();

        loginPage.getToastMessage();
    }
}