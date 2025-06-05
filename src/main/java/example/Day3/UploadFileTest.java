package example.Day3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class UploadFileTest {
    static WebDriver driver = null;
    public static void main(String[] args) {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.guru99.com/test/upload/");
        String filePath = "D:\\JAVA\\Assignment\\AT27_VuHuongGiang_Assigment01\\Bao_cao_kich_hoat.xlsx";
        WebElement uploadFile = driver.findElement(By.name("uploadfile_0"));
        uploadFile.sendKeys(new CharSequence[]{filePath});
        WebElement termsCheckBox = driver.findElement(By.id("terms"));
        termsCheckBox.click();
        WebElement uploadButton = driver.findElement(By.id("submitbutton"));
        uploadButton.click();
        //check
    }
}
