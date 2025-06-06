package example.Day4;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class JavascriptExecutorExampleTest {
    static WebDriver driver = null;

    public static void main(String[] args) throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://saucelabs.com/request-demo");
        driver.manage().window().maximize();

        // Lay phan tu DropList (interest) bang cach dung Xpath
        WebElement in = driver.findElement(By.name("Solution_Interest__c"));

        //Tao doi tuong JJavascriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Thuc thi chon gia tri
        js.executeScript("arguments[0].value='Visual Testing';", in);

        // Thuc thi click dropdown
         js.executeScript("arguments[0].click();", in);

        Thread.sleep(3000);

        System.out.println("Selected value from droplist" + in.getAttribute("value"));
        driver.quit();
    }

}
