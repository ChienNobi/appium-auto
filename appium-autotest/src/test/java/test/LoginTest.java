package test;

import config.EmulatorConfig;
import core.BaseTest;
import core.SystemDefault;
import helpers.CommonHelper;
import helpers.ExcelWriter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Map;


public class LoginTest extends BaseTest {

    @BeforeClass
    public void setUp() throws IOException {
        initExcelData("LoginTest", new String[]{"id", "password"});
    }

    @AfterMethod
    public void refreshApp() {
        EmulatorConfig.resetApp();
    }

    @Test
    public void TC01() throws InterruptedException, IOException {
        for (int i = 0; i < testData.size(); i++) {
            Map<String, String> data = testData.get(i);
            System.out.println("Current row " + data.get("row_index") + ": " + data.get("id") + "-" + data.get("password"));

            try {
                loginPage.login(data.get("id"), data.get("password"));
                excelReader.setCellValue(Integer.parseInt(data.get("row_index")), 2, loginPage.isLoginSuccessfully());
                excelReader.setCellValue(Integer.parseInt(data.get("row_index")), 3, CommonHelper.getCurrentLocalDateTime());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                excelReader.setCellValue(Integer.parseInt(data.get("row_index")), 2, e.getMessage());
                excelReader.setCellValue(Integer.parseInt(data.get("row_index")), 3, CommonHelper.getCurrentLocalDateTime());
            }

            EmulatorConfig.resetApp();
        }
        excelReader.closeFile(); // must close file input for write
        ExcelWriter excelWriter = new ExcelWriter(SystemDefault.TEST_DATA_FILE);
        excelWriter.writeByOtherData(excelReader.getWorkbook());
    }
}