package uzd1;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Uzd1 {
    public static void uzd1run() throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://demowebshop.tricentis.com/");

        var giftCards = driver.findElement(By.xpath("//div[@class='listbox']//a[@href='/gift-cards']"));
        giftCards.click();

        driver.findElement(By.xpath("//div[@class='details']//span[text()>99]/../../../h2/a")).click();

        var recName = driver.findElement(By.xpath("//div[@class='giftcard']//input[contains(@class, 'recipient')]"));
        recName.sendKeys("Petras");

        var myName = driver.findElement(By.xpath("//div[@class='giftcard']//input[contains(@class, 'sender')]"));
        myName.sendKeys("Dovydas");

        var Qty = driver.findElement(By.xpath("//div[@class='add-to-cart']//input[contains(@class, 'qty')]"));
        Qty.clear();
        Qty.sendKeys("5000");

        driver.findElement(By.xpath("//input[contains(@id,'add-to-cart-button')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[contains(@id,'add-to-wishlist')]")).click();

        driver.findElement(By.xpath("//div[@class='listbox']//a[@href='/jewelry']")).click();

        driver.findElement(By.xpath("//a[contains(text(), 'Create Your Own Jewelry')]")).click();

        driver.findElement(By.xpath("//select//option[contains(text(),'Silver')]")).click();
        driver.findElement(By.xpath("//input[@name='product_attribute_71_10_16']")).sendKeys("80");

        driver.findElement(By.xpath(" //label[contains(text(),'Star')]/preceding-sibling::input[@type='radio']")).click();

        var Qty2 = driver.findElement(By.xpath("//div[@class='add-to-cart']//input[contains(@class, 'qty')]"));
        Qty2.clear();
        Qty2.sendKeys("26");

        driver.findElement(By.xpath("//input[contains(@id,'add-to-cart-button')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[contains(@id,'add-to-wishlist')]")).click();


        driver.findElement(By.xpath(" //a[@href='/wishlist']")).click();


        List<WebElement> addToCartCheckboxes = driver.findElements(By.xpath("//input[@name='addtocart']"));

        for (WebElement checkbox : addToCartCheckboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }
        driver.findElement(By.xpath("//input[@name='addtocartbutton']")).click();

        var totalPriceElement = driver.findElement(By.xpath("//span[@class='product-price order-total']//strong"));

        String totalPriceText = totalPriceElement.getText();

        Assert.assertEquals("The total price is not as expected", "1002600.00", totalPriceText);

        Thread.sleep(10000);
        driver.quit();

    }
}
