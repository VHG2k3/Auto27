package example.Day5;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import untils.ExcelUntils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class ReadToFile {
    static  WebDriver driver = null;
    public static void main(String[] args) throws IOException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        List<Map<String, String>> excelData = ExcelUntils.readExcelData("dataLogin.xlsx","data_login");

        try {
            // Login web mua hàng sau: https://www.saucedemo.com/
            // nhap username
            for (Map<String,String> rowData : excelData){
                System.out.println("Du lieu hang: " + rowData);
                String user = rowData.get("Username");
                String pass = rowData.get("Password");
                driver.get("https://www.saucedemo.com/");
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                WebElement userName = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))
                );
                userName.sendKeys("standard_user");
                // nhap password
                WebElement password = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("password"))
                );
                password.sendKeys("secret_sauce");
                // Click button
                WebElement login = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("login-button"))
                );
                login.click();
                System.out.println("Dang nhap thanh cong");
            }

        }
            catch (Exception e){
                System.out.println("Loi khi tim phan tu thao tao: " + e.getMessage());
            }
            finally {

                driver.quit();
            }


//        System.out.println(ExcelUntils.readExcelData("dataLogin.xlsx","data_login"));
    }
}