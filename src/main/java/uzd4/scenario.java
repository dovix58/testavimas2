package uzd4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class scenario {
    public static void run() throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://demowebshop.tricentis.com/");

        driver.findElement(By.xpath("//a[@href='/search']")).click();
        driver.findElement(By.xpath("//input[@id='As']")).click();
        driver.findElement(By.xpath("//input[@id='Sid']")).click();
        driver.findElement(By.xpath("//input[@class='search-text']")).sendKeys("Fitted polkadot print cotton top with tie cap sleeves.");

        driver.findElement(By.xpath("//input[@class='button-1 search-button']")).click();


        var item = driver.findElement(By.xpath("//a[@href='/50s-rockabilly-polka-dot-top-jr-plus-size']"));

        assert item.isDisplayed();
        Thread.sleep(5000);

        driver.quit();

    }
}
