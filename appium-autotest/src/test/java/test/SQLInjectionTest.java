package test;

import config.EmulatorConfig;
import core.BaseTest;
import helpers.ExcelWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;
import static core.SystemDefault.SQL_INJECTION_RESULT_FILE;

public class SQLInjectionTest extends BaseTest {
    public static ExcelWriter excelWriter;


    @BeforeClass
    public void setUp() throws IOException {
        excelWriter = new ExcelWriter(SQL_INJECTION_RESULT_FILE, "SQLInjectionTest");
    }

    @Test()
    public void TC01() throws IOException {
        excelWriter.setCellValue(0, 0, "Test Case");
        excelWriter.setCellValue(0, 1, "Result");

        String[] testCases = {"khachhang", "or true--", "or 1=1;", "or 1=1--", "admin' or '1'='1;", "like '%'"};
        int curRow = 1;
        for (String testCase : testCases) {
            excelWriter.setCellValue(curRow, 0, testCase);

            try {
                loginPage.login(testCase, "khachhang");
                boolean isFrameLayoutPresent = isElementPresentByXPath("//android.widget.FrameLayout[@resource-id='android:id/content']");
                if (testCase.equals("khachhang")) {
                    excelWriter.setCellValue(curRow, 1, isFrameLayoutPresent ? "Pass" : "Fail");
                } else {
                    excelWriter.setCellValue(curRow, 1, !isFrameLayoutPresent ? "Pass" : "Fail");
                }
            } catch (Exception e) {
                e.getMessage();
            }
            curRow++;
            EmulatorConfig.resetApp();
        }
        excelWriter.writeFile();
    }

    private boolean isElementPresentByXPath(String xPath) {
        try {
            driver.findElement(By.xpath(xPath));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}

