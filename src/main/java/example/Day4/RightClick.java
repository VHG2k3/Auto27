package example.Day4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class RightClick {
    static WebDriver driver = null;
    public static void main(String[] args) throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.guru99.com/test/simple_context_menu.html");

        // Tao doi tuong action thuc hien Right-Click
        WebElement rightClick = driver.findElement(By.xpath("//span[contains(@class,'btn-neutral')]"));
        Actions action = new Actions(driver);
        action.contextClick(rightClick).perform();

        // Chọn option sau aansa rightClick
        WebElement option = driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-delete')]"));
        option.click();
        Thread.sleep(3000);
        //Thong bao alert xuat hien
        String alertText = driver.switchTo().alert().getText();
        System.out.println( alertText);
        //Dong alert
        driver.switchTo().alert().accept();
        Thread.sleep(3000);


        driver.quit();

    }
}
