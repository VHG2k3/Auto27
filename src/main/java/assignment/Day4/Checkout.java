package assignment.Day4;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Checkout {

   static WebDriver driver = null;
    public static void main(String[] args) throws InterruptedException {

        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try{
            // Login web mua hàng sau: https://www.saucedemo.com/
            // nhap username
            WebElement userName = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))
            );
            userName.sendKeys("standard_user");
            // nhap password
            WebElement password = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("password"))
            );
            password.sendKeys("secret_sauce");
            // Click button
            WebElement login = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("login-button"))
            );
            login.click();
            System.out.println("Dang nhap thanh cong");
            //Step 2: Chọn tìm kiếm droplist Price (low to high)
            WebElement droplist = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.className("product_sort_container"))
            );
            Select dropSelect = new Select(droplist);
            dropSelect.selectByVisibleText("Price (low to high)");
            System.out.println("Sap xep san pham theo low to high thanh cong");

            //Step 3: Add to cart 2 sản phẩm bất kì
            //Add san pham co ten Sauce Labs Onesie vao gio hang
            WebElement addButton1 = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Sauce Labs Onesie')]//ancestor::div[contains(@class,'inventory_item_description')]//button"))
            );
            addButton1.click();
            //Add san pham co ten Sauce Labs Onesie vao gio hang
            WebElement addButton2 = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Sauce Labs Bike Light')]//ancestor::div[contains(@class,'inventory_item_description')]//button"))
            );
            addButton2.click();
            // Expect: ở giỏ hàng hiển thị số 2
            WebElement shopping_cart_badge = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge"))
            );
            System.out.println(shopping_cart_badge.getText());
            if (shopping_cart_badge.getText().equals("2")){
                System.out.println("Them 2 san pham vao gio hang thanh cong");
            }
            else {
                System.out.println("Them san pham vao gio hang that bai");
            }
            //Step 4: Click vào giỏ hàng
            WebElement cartLink = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_link"))
            );
            cartLink.click();
            //Expect 1: Thông tin Your Cart hiển thị đúng 2 sản phẩm với tên và giá tiền đúng
            CheckInfor();
            //Expect 2: Màn hình có hiển thị 2 button Remove
            List<WebElement> removeBtn = driver.findElements(By.xpath("//button[contains(@class,'cart_button')]"));
            if(removeBtn.size()==2){
                System.out.println("Màn hình có hiển thị 2 button Remove");
            }
            else {
                System.out.println("Màn hình có" + removeBtn.size()+ " button Remove");
            }

            //Step 5: Click checkout_button  và nhập các thông tin Firts name, Last name, Zip code
            WebElement checkoutBtn = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class,'checkout_button ')]"))
            );
            checkoutBtn.click();
            // Nhap thong tin Man Checkout: Your Information
            WebElement firstName = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("first-name"))
            );
            firstName.sendKeys("Giang");
            WebElement lastName = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("last-name"))
            );
            lastName.sendKeys("Vu");
            WebElement code = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("postal-code"))
            );
            code.sendKeys("093746354");
            //Step 6: Click continute
            WebElement continueBtn = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("continue"))
            );
            continueBtn.click();
            System.out.println("Nhap thong tin mua hanh thanh cong");
            //Expect 1 : Thông tin Description hiển thị đúng 2 sản phẩm với số lượng, tên và giá tiền đúng
            CheckInfor();
            //Expect 2: Shipping Information hiển thị đúng "Free Pony Express Delivery!"
            String shipInfoData = driver.findElement(By.xpath("//div[contains(text(),'Shipping Information:')]//following-sibling::div[@class='summary_value_label']")).getText();
            if(shipInfoData.equals("Free Pony Express Delivery!")){
                System.out.println("Shipping Information is True");
            }
            else{
                System.out.println("Shipping Information is False");
            }
            //xpect 3: Price Total hiển thị đúng tổng tiền 2 sản phẩm
            List<WebElement> itemPrices = driver.findElements(By.className("inventory_item_price"));
            double totalPrice =0;
            for(WebElement itemPrice : itemPrices) {
                String priceWithoutDollar = itemPrice.getText().replace("$", "");
                double priceValue = Double.parseDouble(priceWithoutDollar);
                totalPrice += priceValue;
            }
            String totalItem = driver.findElement(By.className("summary_subtotal_label")).getText();
            if(totalPrice == Double.parseDouble(totalItem.replace("Item total: $",""))){
                System.out.println("Price Total hiển thị đúng tổng tiền 2 sản phẩm");
            }
            else {
                System.out.println("Price Total hiển thị sai tổng tiền 2 sản phẩm");
            }
            //Expect 4: Total hiển thị đúng tổng tiền của Item total + Tax
            Double total = totalPrice + Double.parseDouble(driver.findElement(By.className("summary_tax_label")).getText().replace("Tax: $",""));
            if (total == Double.parseDouble(driver.findElement(By.className("summary_total_label")).getText().replace("Total: $",""))){
                System.out.println("Total hiển thị đúng tổng tiền của Item total + Tax");
            }
            else {
                System.out.println("Total hiển thị sai tổng tiền của Item total + Tax");
            }
            //Expect 5: Button Finish hiển thị
            WebElement finishBtn = driver.findElement(By.id("finish"));
            Boolean checkFinishBtn = finishBtn.isDisplayed();
            if (checkFinishBtn){
                System.out.println("Button Finish hiển thị");
            }
            else {
                System.out.println("Button Finish khong hiển thị");
            }
            //Step 7: Click Finish
            finishBtn.click();
            //Expect 1 : hiển thị "Checkout: Complete!"
            Boolean titleDisplay = driver.findElement(By.xpath("//span[contains(text(),'Checkout: Complete!')]")).isDisplayed();
            if(titleDisplay){
                System.out.println("hiển thị \"Checkout: Complete!\"");
            }
            else {
                System.out.println("Khong hiển thị \"Checkout: Complete!\"");
            }
            //Expect 2 : hiển thị "Thank you for your order!"
            Boolean headerDisplay = driver.findElement(By.xpath("//h2[contains(text(),'Thank you for your order!')]")).isDisplayed();
            if(headerDisplay){
                System.out.println(" hiển thị \"Thank you for your order!\"");
            }
            else {
                System.out.println("Khong h hiển thị \"Thank you for your order!\"");
            }
            //Expect 3 : hiển thị "Your order has been dispatched, and will arrive just as fast as the pony can get there!"
            Boolean textDisplay = driver.findElement(By.xpath("//div[text()='Your order has been dispatched, and will arrive just as fast as the pony can get there!']")).isDisplayed();
            if(textDisplay){
                System.out.println("hiển thị \"Your order has been dispatched, and will arrive just as fast as the pony can get there!\"");
            }
            else {
                System.out.println("Khong hiển thị \"Your order has been dispatched, and will arrive just as fast as the pony can get there!\"");
            }

            //Expect 4 : hiển thị button Back Home
            Boolean backHomeBtn = driver.findElement(By.id("back-to-products")).isDisplayed();
            if(backHomeBtn){
                System.out.println("hiển thị button Back Home");
            }
            else {
                System.out.println("Khong hiển thị button Back Home");
            }
        }
        catch (Exception e){
            System.out.println("Loi khi tim phan tu thao tao: " + e.getMessage());
        }
        finally {
            Thread.sleep(5000);
            driver.quit();
        }



    }

    public static void CheckInfor( ) {
        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
        if (cartItems.size()==2){
            boolean match = true;
            WebElement cartItem1 = cartItems.get(0);
            String name1 = cartItem1.findElement(By.className("inventory_item_name")).getText();
            String price1 = cartItem1.findElement(By.className("inventory_item_price")).getText();
            if(!name1.equals("Sauce Labs Onesie") || !price1.equals("$7.99")){
                match = false;
            }
            WebElement cartItem2 = cartItems.get(1);
            String name2 = cartItem2.findElement(By.className("inventory_item_name")).getText();
            String price2 = cartItem2.findElement(By.className("inventory_item_price")).getText();
            if(!name2.equals("Sauce Labs Bike Light") || !price2.equals("$9.99")){
                match = false;
            }

            if (match) {
                System.out.println(" Tên và giá của cả 2 sản phẩm đều đúng");
            }
            else {
                System.out.println(" Tên và giá của cả 2 sản phẩm khong dung");
            }
        }
        else{
            System.out.println("Ton tai " + cartItems.size() + " San pham");
        }

    }
}
