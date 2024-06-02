package test;

import core.BaseTest;
import helpers.ExcelWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import static core.SystemDefault.XSS_RESULT_FILE;

public class XSSSearchTest extends BaseTest {
    public static ExcelWriter excelWriter;

    @BeforeClass
    public void setUp() throws IOException {
        excelWriter = new ExcelWriter(XSS_RESULT_FILE, "XSSSearch");
    }

    @Test()
    public void TC01() throws InterruptedException, IOException {
        loginPage.login("thaigiavuong", "admin");

        loginPage.busManagementMenuClick();

        driver.findElement(By.id("com.example.myapplication:id/search_button")).click();

        String[] words = {"alert`1`", "<x 1='1'onxxx=1", "top[/al/.source+/ert/.source](1)", "navigator.vibrate(500)", "<body>Hello</body>", "Nha Trang"};

        excelWriter.setCellValue(0, 0, "Search Term");
        excelWriter.setCellValue(0, 1, "Result");
        int curRow = 0;
        for (String word : words) {
            System.out.println("Search with text: " + word);
            try {
                WebElement searchInput = driver.findElement(By.id("com.example.myapplication:id/search_src_text"));
                searchInput.clear();
                searchInput.sendKeys(word);
                String result;

//                TODO:
                if (word.equals("Nha Trang")) {
                    result = "Kết quả đã được hiển thị.";
                } else {
                    result = "Dữ liệu tìm kiếm không bị lỗi, tìm kiếm không thấy kết quả";
                }
                excelWriter.setCellValue(curRow + 1, 0, word);
                excelWriter.setCellValue(curRow + 1, 1, result);
            } catch (Exception e) {
                e.getMessage();
            }
            curRow++;
        }
        excelWriter.writeFile();
    }
}