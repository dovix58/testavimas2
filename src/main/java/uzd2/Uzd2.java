package uzd2;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Uzd2 {
    public static void run1() throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofNanos(2000));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://demoqa.com/");

        driver.findElement(By.xpath("//h5[text() = 'Widgets']")).click();

        driver.findElement(By.xpath("//span[text() ='Progress Bar']")).click();

        driver.findElement(By.xpath("//button[text() ='Start']")).click();

        boolean isFinished = driver.findElement(By.xpath("//div[@role = 'progressbar' and text()='100%']")).isDisplayed();

        if(isFinished){
            driver.findElement(By.xpath("//button[text() ='Reset']")).click();
            var finalPercentage = driver.findElement(By.xpath("//div[@role = 'progressbar']")).getText();
            Assert.assertEquals("The percentage is not zero", "0%", finalPercentage);
            System.out.println("All good");
        }


        Thread.sleep(5000);
        driver.quit();
    }
    public static void run2() throws InterruptedException {
        record Data(String name, String lastName, String email, int age, int salary, String department) {}

        Data data = new Data("dovydas", "Kavol", "Kavol@gmail.com", 21, 100000, "UniVU");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        driver.manage().window().maximize();

        driver.get("https://web.archive.org/web/20240112153757/https://demoqa.com/");
        driver.findElement(By.xpath("//h5[text() = 'Elements']")).click();
        driver.findElement(By.xpath("//span[text() ='Web Tables']")).click();

        wait.until(driver1 -> {
            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text() ='Add']")));
            addButton.click();

            driver1.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys(RandomStringUtils.randomAlphabetic(5));
            driver1.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys(RandomStringUtils.randomAlphabetic(5));
            driver1.findElement(By.xpath("//input[@placeholder='name@example.com']")).sendKeys(RandomStringUtils.randomAlphabetic(5) + "@email.com");
            driver1.findElement(By.xpath("//input[@placeholder='Age']")).sendKeys(String.valueOf(RandomStringUtils.randomNumeric(5)));
            driver1.findElement(By.xpath("//input[@placeholder='Salary']")).sendKeys(String.valueOf(RandomStringUtils.randomNumeric(5)));
            driver1.findElement(By.xpath("//input[@placeholder='Department']")).sendKeys(RandomStringUtils.randomNumeric(5));
            driver1.findElement(By.xpath("//button[text() ='Submit']")).click();

            WebElement totalPagesElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='-totalPages']")));
            return Integer.parseInt(totalPagesElement.getText()) == 2;
        });

        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text() ='Next']")));
        nextButton.click();

        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@title='Delete']")));
        deleteButton.click();

        List<WebElement> amount = driver.findElements(By.xpath("//div[@class='rt-tr-group']"));
        System.out.println(amount.size());
        Assert.assertEquals(amount.size(), 10);

        int amountOfPages = Integer.parseInt(driver.findElement(By.xpath("//span[@class='-totalPages']")).getText());
        Assert.assertEquals(amountOfPages, 1);

        System.out.println("All good");
        Thread.sleep(10000);
        driver.quit();
    }
}
