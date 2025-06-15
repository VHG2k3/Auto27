package feature;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginMultiCaseV2Test {
    WebDriver driver;

    @BeforeMethod  //mỗi test mở lại trang mới
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                // username, password, expectedResult, expectedErrorMessage (nếu có)
                {"standard_user", "secret_sauce", "success", ""},
                {"invalid_user", "secret_sauce", "failure", "Username and password do not match"},
                {"standard_user", "invalid_pass", "failure", "Username and password do not match"},
                {"", "secret_sauce", "failure", "Username is required"},
                {"standard_user", "", "failure", "Password is required"}
        };
    }

    @Test(dataProvider = "loginData")
    public void testLoginFunctionality(String username, String password, String expectedResult, String expectedErrorMsg) {
        // Check title & URL đầu trang
        Assert.assertEquals(driver.getTitle(), "Swag Labs", "Title mismatch!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "URL mismatch!");

        // Điền dữ liệu
        WebElement userInput = driver.findElement(By.id("user-name"));
        WebElement passInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        Assert.assertTrue(userInput.isDisplayed(), "Username field not displayed!");
        Assert.assertTrue(passInput.isDisplayed(), "Password field not displayed!");
        Assert.assertTrue(loginButton.isDisplayed(), "Login button not displayed!");

        userInput.sendKeys(username);
        passInput.sendKeys(password);
        loginButton.click();

        // Kiểm tra theo kỳ vọng
        if (expectedResult.equals("success")) {
            String actualURL = driver.getCurrentUrl();
            Assert.assertEquals(actualURL, "https://www.saucedemo.com/inventory.html", "Login failed when expected to succeed!");

            // Check Logo & Item
            WebElement logo = driver.findElement(By.className("app_logo"));
            Assert.assertTrue(logo.isDisplayed(), "Logo missing on inventory page!");

            int itemCount = driver.findElements(By.className("inventory_item")).size();
            Assert.assertTrue(itemCount > 0, "No products displayed!");
        } else {
            // Có lỗi, kiểm tra error message
            WebElement errorMsg = driver.findElement(By.cssSelector("[data-test='error']"));
            Assert.assertTrue(errorMsg.isDisplayed(), "Error message not displayed!");
            Assert.assertTrue(errorMsg.getText().contains(expectedErrorMsg), "Unexpected error message text!");
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}