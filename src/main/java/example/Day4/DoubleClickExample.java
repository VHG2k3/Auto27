package example.Day4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DoubleClickExample {
    static WebDriver driver = null;
    public static void main(String[] args) throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.guru99.com/test/simple_context_menu.html");

        Actions action = new Actions(driver);
        WebElement button = driver.findElement(By.xpath("//button[text()='Double-Click Me To See Alert']"));
        action.doubleClick(button).perform();
        Thread.sleep(3000);

        // Xu ly canh bao (alert) xuat thien sau double-click;
        String alertText = driver.switchTo().alert().getText();
        System.out.println("Alert text after double click: " + alertText);

        // Dong alert
        driver.switchTo().alert().accept();

        Thread.sleep(5000);
        driver.quit();
    }
}
