package example.Day4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class MultiCheckboxSelection {
    static WebDriver driver = null;
    public static void main(String[] args) throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.guru99.com/test/radio.html");

        // Tim danh sach cac checkbox
        List<WebElement> checkboxs = new ArrayList<>();
        checkboxs.add(driver.findElement(By.id("vfb-6-0")));
        checkboxs.add(driver.findElement(By.id("vfb-6-1")));
        checkboxs.add(driver.findElement(By.id("vfb-6-2")));

        // Click vao tung checkbox muon chon
        for (WebElement checkbox : checkboxs){
            checkbox.click();
            System.out.println("Checkbox value selected: " + checkbox.getAttribute("value"));
            System.out.println("Is selected? " + checkbox.isSelected());
        }

        Thread.sleep(5000);
        driver.quit();
    }
}
