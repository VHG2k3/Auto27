package example.Day4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExplicitwaiExample {
    static WebDriver driver = null;

    public static void main(String[] args) {
        driver = new ChromeDriver();
        driver.get("https://saucelabs.com/request-demo");
        driver.manage().window().maximize();
        try {
            //Tao WebDriverWait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            //Cho va tim phan tu Email
            WebElement Email = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("Email1"))
            );
            Email.sendKeys("giangvh@gmail.com");

            //Cho va tim phan tu Submit
            WebElement button = wait.until(
                    ExpectedConditions.visibilityOfElementLocated((By.xpath("//button[@class='mktoButton']"))
                    ));
            button.click();

        } catch (Exception e) {
            System.out.println("Loi khi tim phan tu thao tao: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
