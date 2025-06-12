package assignment.Day3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class BookDemo {
    static WebDriver driver = null;
    public static void main(String[] args) throws InterruptedException {
        Infor in1 = new Infor(
                "giang@ods.vn",
                "Giang",
                "Vu",
                "VPNS",
                "092349823",
                "Vietnam",
                0,
                "Check",
                "Check"
        );

        Demo(in1);
    }

    public static void Demo(Infor infor) throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://saucelabs.com/request-demo");


        WebElement email = driver.findElement(By.id("Email"));
        email.sendKeys(infor.email);
        Thread.sleep(3000);
        WebElement firstName = driver.findElement(By.id("FirstName"));
        firstName.sendKeys(infor.firstName);
        WebElement lastName = driver.findElement(By.id("LastName"));
        lastName.sendKeys(infor.lastName);
        WebElement company = driver.findElement(By.id("Company"));
        company.clear();
        company.sendKeys(infor.company);
        WebElement phone = driver.findElement(By.id("Phone"));
        phone.sendKeys(infor.phone);
        WebElement country = driver.findElement(By.id("Country"));
        Select cSelect = new Select(country);
        //Chon theo text hiển thị
        cSelect.selectByVisibleText(infor.country);
//        WebElement state = driver.findElement(By.id("State"));
//        Select sSelect = new Select(state);
//        sSelect.selectByVisibleText("AK");
        WebElement interest = driver.findElement(By.id("Solution_Interest__c"));
        Select iSelect = new Select(interest);
        iSelect.selectByIndex(infor.interest);
        WebElement comment = driver.findElement(By.id("Sales_Contact_Comments__c"));
        comment.sendKeys(infor.comment);

        WebElement checkbox = driver.findElement(By.id("mktoCheckbox_46340_0"));
        if (infor.checkboxStatus.equalsIgnoreCase("Check") && !checkbox.isSelected()) {
            checkbox.click();
        }

        String lastTab = driver.getCurrentUrl();
//        System.out.println(lastTab);
        WebElement button = driver.findElement(By.xpath("//button[@class='mktoButton']"));
        button.click();
        Thread.sleep(5000);

//        System.out.println(driver.getCurrentUrl());
        if(driver.getCurrentUrl().equals(lastTab)){
            System.out.println("That bai");
            WebElement error = driver.findElement(By.xpath("//div[contains(@class,'mktoError')]//preceding-sibling::label"));
            System.out.println(error.getText() + "Khong duoc de trong");
        }
        else{
            System.out.println("Thanh cong");
        }
        driver.quit();


    }
    public static class Infor{
        String email;
        String firstName;
        String lastName;
        String company;
        String phone;
        String country;
        int interest;
        String comment;
        String checkboxStatus;

        public Infor(String email, String firstName, String lastName, String company, String phone,
                     String country, int interestIndex, String comment, String checkboxStatus) {
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
            this.company = company;
            this.phone = phone;
            this.country = country;
            this.interest = interestIndex;
            this.comment = comment;
            this.checkboxStatus = checkboxStatus;
        }

    }
}
