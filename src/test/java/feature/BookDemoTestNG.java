package feature;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static untils.ExcelUntils.readExcelData;

public class BookDemoTestNG {
    WebDriver driver;
    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.get("https://saucelabs.com/request-demo");
        driver.manage().window().maximize();
    }
    @DataProvider(name = "bookDemoData")
    public Iterator<Object[]> bookDemoData(){
        List<Map<String,String>> data = readExcelData("book.xlsx","Sheet1");

        List<Object[]> dataArray = new ArrayList<>();
        for (Map<String,String> row : data) {
            dataArray.add(new Object[]{
                    row.get("Business Email"),
                    row.get("First Name"),
                    row.get("Last Name"),
                    row.get("Company"),
                    row.get("Phone Number"),
                    row.get("Country"),
                    row.get("Interest"),
                    row.get("Comments"),
                    row.get("CheckboxStatus"),
                    row.get("ExpectedResult"),
                    row.get("ExpectedErrorMessage")
            });
        }
        return dataArray.iterator();
    }

    @Test(dataProvider = "bookDemoData")
    public void testBookDemoSuccess(String email, String firstname, String lastname, String company, String phone, String country, String interest, String comment, String checkStatus, String expectedResult , String expectedErrorMsg){
        // check title & URL dau trang
        Assert.assertEquals(driver.getTitle(),"Request a Sauce Labs Demo", "Title mismatch!");
        Assert.assertEquals(driver.getCurrentUrl(),"https://saucelabs.com/request-demo", "URL mismatch!");

        //Dien du lieu Email
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement emailInput = driver.findElement(By.id("Email"));
        Assert.assertTrue(emailInput.isDisplayed(), "Email field not displayed!");
        emailInput.sendKeys(email);

        //Dien du lieu FirstName.
        WebElement fnameInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("FirstName"))
        );
        Assert.assertTrue(fnameInput.isDisplayed(),"FirstName field not displayed!");
        fnameInput.sendKeys(firstname);

        //Dien du lieu LastName
        WebElement lnameInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("LastName"))
        );
        Assert.assertTrue(lnameInput.isDisplayed(),"LastName field not displayed!");
        lnameInput.sendKeys(lastname);

        //Dien du lieu Company
        WebElement companyInput = driver.findElement(By.id("Company"));
        Assert.assertTrue(companyInput.isDisplayed(),"Company field not displayed!");
        companyInput.sendKeys(company);

        //Dien du lieu Phone Number
        WebElement phoneInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("Phone"))
        );
        Assert.assertTrue(phoneInput.isDisplayed(),"Phone Number field not displayed!");
        phoneInput.sendKeys(phone);

        //Chon du lieu truong Country
        WebElement countryCbx = driver.findElement(By.id("Country"));
        Assert.assertTrue(countryCbx.isDisplayed(),"Country Combobox not displayed!");
        Select cSelect = new Select(countryCbx);
        cSelect.selectByVisibleText(country);

        //Chon du lieu truong Interest
        WebElement interestCbx = driver.findElement(By.id("Solution_Interest__c"));
        Assert.assertTrue(interestCbx.isDisplayed(),"Interest Combobox not displayed!");
        Select iSelect = new Select(interestCbx);
        iSelect.selectByVisibleText(interest);

        //Dien du lieu Comment
        WebElement commentInput = driver.findElement(By.id("Sales_Contact_Comments__c"));
        Assert.assertTrue(commentInput.isDisplayed(),"Comments not displayed!");
        commentInput.sendKeys(comment);

        // Tich chon Checkbox Status
        WebElement checkbox = driver.findElement(By.id("LblmktoCheckbox_46340_0"));
        Assert.assertTrue(checkbox.isDisplayed()," Check Box not displayed!");
        if (checkStatus.equalsIgnoreCase("Check") && !checkbox.isSelected()) {
            checkbox.click();
        }

        //Button Let's Talk
        WebElement button = driver.findElement(By.xpath("//button[@class='mktoButton']"));
        Assert.assertTrue(button.isDisplayed()," Button Let's Talk not displayed!");
        button.click();

        //Kiem tra ki vong
        if(expectedResult.equals("success")){
            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait1.until(ExpectedConditions.urlToBe("https://saucelabs.com/thank-you-contact?concierge=true"));
            String actualURL = driver.getCurrentUrl();
            Assert.assertEquals(actualURL, "https://saucelabs.com/thank-you-contact?concierge=true", "Book Demo failed when expected to succeed!");

            String actualTitle = driver.getTitle();
            Assert.assertEquals(actualTitle, "Thank you", "Book Demo failed when expected to succeed!");
        }
        else{
            // Có lỗi, kiểm tra error message
            WebElement errorMsg = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='mktoErrorArrowWrap']//following-sibling::div[@class='mktoErrorMsg']"))
            );

            Assert.assertTrue(errorMsg.isDisplayed(), "Error message not displayed!");
            Assert.assertTrue(errorMsg.getText().contains(expectedErrorMsg), "Unexpected error message text!");
        }
    }

    @AfterMethod public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
