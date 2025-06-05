package example.Day3;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.Set;

public class SwitchTabsExample {
    static WebDriver driver= null;
    public static void main(String[] args) throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://saucelabs.com/request-demo");
        System.out.println("Tab 1 title: " + driver.getTitle());
        String originalTab = driver.getWindowHandle();
        ((JavascriptExecutor)driver).executeScript("window.open('https://www.youtube.com/','_blank');", new Object[0]);
        Set<String> allTabs = driver.getWindowHandles();
        Iterator var3 = allTabs.iterator();

        while(var3.hasNext()) {
            String tab = (String)var3.next();
            if (!tab.equals(originalTab)) {
                driver.switchTo().window(tab);
                break;
            }
        }

        System.out.println("Tab 2 Title: " + driver.getTitle());
        WebElement searchBox = driver.findElement(By.name("search_query"));
        searchBox.sendKeys(new CharSequence[]{"Selenium"});
        searchBox.submit();
        Thread.sleep(3000L);
        driver.switchTo().window(originalTab);
        System.out.println("Tab ban đầu: " + driver.getTitle());
    }
    }

