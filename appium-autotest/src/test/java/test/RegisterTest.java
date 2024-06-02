package test;
import core.BaseTest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.RegisterPage;

import java.io.IOException;
public class RegisterTest extends BaseTest {
    public RegisterPage page;

    @BeforeClass
    public void setUp() throws IOException {
        page = new RegisterPage();
    }

    @Test(description = "Test register new user")
    public void TC01() throws IOException {
        page.registerNewUserTest(8);
    }
}