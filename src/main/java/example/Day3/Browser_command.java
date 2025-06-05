package example.Day3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Browser_command {
    static WebDriver driver = null;
    public static void main(String[] args) throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://saucelabs.com/request-demo");
        //Mở trực tiếp trên 1 tab
//        driver.navigate().to("https://www.youtube.com/");
        //back lại trang trước đó
//        driver.navigate().back();
        //forword lại trang hiện tại
//        driver.navigate().forward();
        //refresh lại trang hiện tại
//        driver.navigate().refresh();
//        driver.close();
//        String url = driver.getCurrentUrl();
//        System.out.println("String url" + url);

        //        WebElement element = driver.findElement(By.xpath("//input[@id='Email']"));
//        WebElement element = driver.findElement(By.id("Email"));
//        WebElement element = driver.findElement(By.name("Email"));
//        WebElement element = driver.findElement(By.tagName("input"));
        WebElement element = driver.findElement(By.cssSelector("#Email"));
        element.sendKeys("giangvh@gmail.com");
        Thread.sleep(3000);
        element.clear();

//        WebElement element = driver.findElement(By.linkText("Try it free"));
//        WebElement element = driver.findElement(By.partialLinkText("Try it"));
//        element.click();
    }
}
