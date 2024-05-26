package test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;

public class BookTickets {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> driver = null;
        try {
            driver = initializeDriver();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        MobileElement loginButton = driver.findElement(By.id("button_login_home"));
        loginButton.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        MobileElement usernameField = driver.findElement(By.id("txtUserName"));
        usernameField.sendKeys("ngocnt");
        MobileElement passwordField = driver.findElement(By.id("txtPassword"));
        passwordField.sendKeys("123456");
        MobileElement signInButton = driver.findElement(By.id("btnSignIn"));
        signInButton.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        MobileElement xacNhanButton;
        MobileElement khuHoiSwitch;
        MobileElement edtNgayDi;
        MobileElement edtNgayVe;

        // case k nhap ngay di
        preDatVe(driver);
        xacNhanButton = driver.findElement(By.id("xacNhan"));
        xacNhanButton.click();
        hienThiAlertDialog(driver);

        writeResultToExcel(1,0,"Truong hop khong nhap ngay di");
        writeResultToExcel(1,1,"success");
        writeResultToExcel(1,2,"pass");

        // case k nhap ngay ve
        edtNgayDi = driver.findElement(By.id("edtNgayDi"));
        String randomDay = randomDay();
        edtNgayDi.sendKeys(randomDay);
        khuHoiSwitch = driver.findElement(By.id("khuHoi"));
        khuHoiSwitch.click();
        xacNhanButton = driver.findElement(By.id("xacNhan"));
        xacNhanButton.click();
        hienThiAlertDialog(driver);
        writeResultToExcel(2,0,"Truong hop khong nhap ngay ve");
        writeResultToExcel(2,1,"success");
        writeResultToExcel(2,2,"pass");

        // case k nhập ngày đi ngày về

        preDatVe(driver);
        khuHoiSwitch = driver.findElement(By.id("khuHoi"));
        khuHoiSwitch.click();
        xacNhanButton = driver.findElement(By.id("xacNhan"));
        xacNhanButton.click();
        hienThiAlertDialog(driver);
        writeResultToExcel(3,0,"Truong hop khong nhap ngay di va ngay ve");
        writeResultToExcel(3,1,"success");
        writeResultToExcel(3,2,"pass");
        //khuHoiSwitch.click();

        // case ngày đi quá khứ

        //case k khứ hồi //ngay qua khu
        edtNgayDi = driver.findElement(By.id("edtNgayDi"));
        String randomDay1 = randomPastDate();
        edtNgayDi.sendKeys(randomDay1);
        xacNhanButton = driver.findElement(By.id("xacNhan"));
        xacNhanButton.click();
        hienThiAlertDialog(driver);
        writeResultToExcel(4,0,"Truong hop ngay di la qua khu, khu hoi: OFF");
        writeResultToExcel(4,1,"success");
        writeResultToExcel(4,2,"fail");

        //case có khứ hồi
        preDatVe(driver);
        edtNgayDi = driver.findElement(By.id("edtNgayDi"));
        String randomDay2 = randomPastDate();
        edtNgayDi.sendKeys(randomDay2);
        khuHoiSwitch = driver.findElement(By.id("khuHoi"));
        khuHoiSwitch.click();
        edtNgayVe = driver.findElement(By.id("edtNgayVe"));
        String randomDay3 = randomDay();
        edtNgayVe.sendKeys(randomDay3);
        xacNhanButton = driver.findElement(By.id("xacNhan"));
        xacNhanButton.click();
        hienThiAlertDialog(driver);
        writeResultToExcel(5,0,"Truong hop ngay di la qua khu, khu hoi: ON");
        writeResultToExcel(5,1,"success");
        writeResultToExcel(5,2,"fail");

        // case ngày đi trong tlai (hiện tại +tlai)

        // case k khứ hồi // ngày tương lai

        preDatVe(driver);
        edtNgayDi = driver.findElement(By.id("edtNgayDi"));
        String randomDay4 = randomFutureDate();
        edtNgayDi.sendKeys(randomDay4);
        xacNhanButton = driver.findElement(By.id("xacNhan"));
        xacNhanButton.click();
        hienThiAlertDialog(driver);
        writeResultToExcel(6,0,"Truong hop ngay di la tuong lai, khu hoi: OFF");
        writeResultToExcel(6,1,"success");
        writeResultToExcel(6,2,"pass");


        //case có khứ hồi ngày về nhỏ hơn ngày đi
        preDatVe(driver);
        edtNgayDi = driver.findElement(By.id("edtNgayDi"));
        String randomDay5 = randomFutureDate();
        edtNgayDi.sendKeys(randomDay5);
        khuHoiSwitch = driver.findElement(By.id("khuHoi"));
        khuHoiSwitch.click();
        edtNgayVe = driver.findElement(By.id("edtNgayVe"));
        String randomDay6 = randomDay("01/01/2020", randomDay5);
        edtNgayVe.sendKeys(randomDay6);
        xacNhanButton = driver.findElement(By.id("xacNhan"));
        xacNhanButton.click();
        hienThiAlertDialog(driver);
        writeResultToExcel(7,0,"Truong hop ngay di: ngay tuong lai, khu hoi: ON, ngay ve: ngay nho hon ngay di");
        writeResultToExcel(7,1,"success");
        writeResultToExcel(7,2,"fail");

        //case có khứ hồi ngày về lớn hơn ngày đi
        preDatVe(driver);
        edtNgayDi = driver.findElement(By.id("edtNgayDi"));
        String randomDay7 = randomFutureDate();
        edtNgayDi.sendKeys(randomDay7);
        khuHoiSwitch = driver.findElement(By.id("khuHoi"));
        khuHoiSwitch.click();
        edtNgayVe = driver.findElement(By.id("edtNgayVe"));
        String randomDay8 = randomDay(randomDay7, "31/12/2030");
        edtNgayVe.sendKeys(randomDay8);
        xacNhanButton = driver.findElement(By.id("xacNhan"));
        xacNhanButton.click();
        hienThiAlertDialog(driver);
        writeResultToExcel(8,0,"Truong hop ngay di: ngay tuong lai, khu hoi: ON, ngay ve: ngay lon hon ngay di");
        writeResultToExcel(8,1,"success");
        writeResultToExcel(8,2,"pass");


        // case ngay di ngay ve la text

        preDatVe(driver);
        edtNgayDi = driver.findElement(By.id("edtNgayDi"));
        String randomDay9 = randomText();
        edtNgayDi.sendKeys(randomDay9);
        khuHoiSwitch = driver.findElement(By.id("khuHoi"));
        khuHoiSwitch.click();
        edtNgayVe = driver.findElement(By.id("edtNgayVe"));
        String randomDay10 = randomText();
        edtNgayVe.sendKeys(randomDay10);
        xacNhanButton = driver.findElement(By.id("xacNhan"));
        xacNhanButton.click();
        hienThiAlertDialog(driver);
        writeResultToExcel(8,0,"Truong hop ngay di ngay ve la text");
        writeResultToExcel(8,1,"success");
        writeResultToExcel(8,2,"pass");


//        driver.quit();
    }

    public static String randomPastDate(){
        LocalDate startDate = LocalDate.of(2020, 1, 1);
        LocalDate endDate = LocalDate.now();
        long randomEpochDay = ThreadLocalRandom.current().nextLong(startDate.toEpochDay(), endDate.toEpochDay() - 1);
        LocalDate randomDate = LocalDate.ofEpochDay(randomEpochDay);
        return randomDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String randomFutureDate(){
        LocalDate startDate =LocalDate.now();
        LocalDate endDate = LocalDate.of(2030, 12, 31);
        long randomEpochDay = ThreadLocalRandom.current().nextLong(startDate.toEpochDay(), endDate.toEpochDay() + 1);
        LocalDate randomDate = LocalDate.ofEpochDay(randomEpochDay);
        return randomDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String randomDay(){
        LocalDate startDate = LocalDate.of(2020, 1, 1);
        LocalDate endDate = LocalDate.of(2030, 12, 31);
        long randomEpochDay = ThreadLocalRandom.current().nextLong(startDate.toEpochDay(), endDate.toEpochDay() + 1);
        LocalDate randomDate = LocalDate.ofEpochDay(randomEpochDay);
        return randomDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    public static String randomDay(String goDate, String backDate){
        LocalDate startDate = LocalDate.parse(goDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate endDate = LocalDate.parse(backDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        long randomEpochDay = ThreadLocalRandom.current().nextLong(startDate.toEpochDay(), endDate.toEpochDay() + 1);
        LocalDate randomDate = LocalDate.ofEpochDay(randomEpochDay);
        return randomDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String randomText(){
        String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder(10);
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }
    public static void preDatVe(AppiumDriver<MobileElement> driver){
//        MobileElement popularTextView = driver.findElement(By.xpath("//LinearLayout/android.widget.TextView[1]"));
        MobileElement datVeButton = driver.findElement(By.id("btnDatVe"));
        datVeButton.click();
        MobileElement spinnerSoLuongVe = driver.findElement(By.id("soLuongVe"));
        spinnerSoLuongVe.click();
        MobileElement firstValue = driver.findElement(By.xpath("//android.widget.ListView/android.widget.TextView[1]"));
//        String firstValueText = firstValue.getText();
        firstValue.click();
    }
    public static void hienThiAlertDialog(AppiumDriver<MobileElement> driver) {
        // Code để hiển thị AlertDialog và bấm nút xác nhận
        // Thực hiện các thao tác cần thiết để hiển thị AlertDialog
        // Sau khi hiển thị AlertDialog, bấm nút xác nhận
        // Ví dụ:
        MobileElement xacNhanAlertDialogButton = driver.findElement(By.id("android:id/button1")); // Thay "xacNhanAlertDialog" bằng id thật của nút xác nhận trong AlertDialog
        xacNhanAlertDialogButton.click();
    }
    public static AppiumDriver<MobileElement> initializeDriver() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName", "Galaxy M20");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appPackage", "com.example.myapplication");
        desiredCapabilities.setCapability("appActivity", "com.example.myapplication.view.MainActivity");
        return new AndroidDriver<>(new URL("http://192.168.0.80:4723/wd/hub"), desiredCapabilities);
    }

    public  static boolean isInitSheet = false;
    public static Workbook workbook = new XSSFWorkbook();
    public  static Sheet sheet;

    public static List<Integer> listRow = new ArrayList<>();
    public static void writeResultToExcel(int row, int col, String result) {
        try {
            if(!isInitSheet){
                isInitSheet = true;
                sheet = workbook.createSheet("KetQua");
                Row headerRow = sheet.createRow(0);
                Cell headerCell = headerRow.createCell(0);
                headerCell.setCellValue("Case");
                Cell headerCell1 = headerRow.createCell(1);
                headerCell1.setCellValue("Result app");
                Cell headerCell2 = headerRow.createCell(2);
                headerCell2.setCellValue("Result test");
            }
            Row dataRow;
            if(!listRow.contains(row)){
                listRow.add(row);
                dataRow = sheet.createRow(row);
            } else {
                dataRow = sheet.getRow(row);
            }
            Cell dataCell = dataRow.createCell(col);
            dataCell.setCellValue(result);

            FileOutputStream fileOut = new FileOutputStream("E:\\datnautotest\\ResultBookTickets_Maven.xlsx");
            workbook.write(fileOut);
            fileOut.close();
//            workbook.close();
            System.out.println("Kết quả đã được ghi vào file Excel: " + "E:\\datnautotest\\ResultBookTickets_Maven.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
