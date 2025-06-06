package example.Day4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SingleRadioButtonSelect {
    static WebDriver driver = null;
    public static void main(String[] args) throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.guru99.com/test/radio.html");

        // Tim radio button muon chon
        WebElement radioButon = driver.findElement(By.id("vfb-7-3"));
        radioButon.click();

        Thread.sleep(3000);
        String value = radioButon.getAttribute("value");
        System.out.println("Radio Button  value selected: " + value);
        Boolean status  = radioButon.isSelected();
        System.out.println("Is selected: " + value);

        driver.quit();

    }
}
