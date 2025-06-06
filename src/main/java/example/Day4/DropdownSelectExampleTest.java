package example.Day4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DropdownSelectExampleTest {
    static WebDriver driver = null;
    public static void main(String[] args) throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://saucelabs.com/request-demo");
        driver.manage().window().maximize();

        WebElement in = driver.findElement(By.name("Solution_Interest__c"));
        Select oSelect = new Select(in);

        // Chon option theo index
        oSelect.selectByIndex(2);
        Thread.sleep(5000);
        // Chon option theo value
        oSelect.selectByValue("Crash & Error Reporting");
        Thread.sleep(5000);
        // Chon option theo text
        oSelect.selectByVisibleText("Emulator/Simulator Testing ");
        Thread.sleep(5000);

        driver.quit();
    }
}
