package assignment.Day5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import untils.ExcelUntils;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class BookDemoReadToFile {
    static WebDriver driver = null;

    public static void main(String[] args) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        List<Map<String, String>> excelData = ExcelUntils.readExcelData("book.xlsx", "Sheet1");
        try {
            // Login web mua hàng sau: https://www.saucedemo.com/
            // nhap username
            for (Map<String, String> rowData : excelData) {
                System.out.println("Du lieu hang: " + rowData);
                String Email = rowData.get("Business Email");
                String firstname = rowData.get("First Name");
                String lastname = rowData.get("Last Name");
                String Company = rowData.get("Company");
                String phoneNumber = rowData.get("Phone Number");
                String Country = rowData.get("Country");
                String Interest = rowData.get("Interest");
                String Comments = rowData.get("Comments");
                String checkboxStatus = rowData.get("CheckboxStatus");
                driver.get("https://saucelabs.com/request-demo");

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                WebElement email = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("Email"))
                );
                email.sendKeys(Email);
                WebElement firstName = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("FirstName"))
                );
                firstName.sendKeys(firstname);
                WebElement lastName = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("LastName"))
                );
                lastName.sendKeys(lastname);
                WebElement company = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("Company"))
                );
                company.clear();
                company.sendKeys(Company);
                WebElement phone = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("Phone"))
                );
                phone.sendKeys(phoneNumber);
                WebElement country = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("Country"))
                );
                Select cSelect = new Select(country);
                //Chon theo text hiển thị
                cSelect.selectByVisibleText(Country);
                WebElement interest = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("Solution_Interest__c"))
                );
                Select iSelect = new Select(interest);
                iSelect.selectByVisibleText(Interest);

                WebElement comment = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.id("Sales_Contact_Comments__c"))
                );
                comment.sendKeys(Comments);

                WebElement checkbox = driver.findElement(By.id("mktoCheckbox_46340_0"));

                if (checkboxStatus.equalsIgnoreCase("Check") && !checkbox.isSelected()) {
                    checkbox.click();
                }

                String lastTab = driver.getCurrentUrl();
//        System.out.println(lastTab);
                WebElement button = driver.findElement(By.xpath("//button[@class='mktoButton']"));
                button.click();

                // Chờ URL thay đổi
                Thread.sleep(10000);
// Kiểm tra kết quả
                if (driver.getCurrentUrl().equals(lastTab)) {
                    System.out.println("That bai");
                    WebElement error = driver.findElement(By.xpath("//div[contains(@class,'mktoError')]//preceding-sibling::label"));
                    System.out.println(error.getText() + " Khong duoc de trong");
                } else {
                    System.out.println("Thanh cong");
                }
            }

        } catch (Exception e) {
            System.out.println("Loi khi tim phan tu thao tao: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
