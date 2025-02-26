
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import uzd3.UserRegistration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MyTest {
    private WebDriver driver;
    private static UserRegistration.UserDetails userDetails;

    @BeforeClass
    public static void setUpBeforeClass() {
        userDetails = UserRegistration.registerNewUser();
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    private void login() {
        driver.get("https://demowebshop.tricentis.com/");
        driver.findElement(By.xpath("//a[@href='/login']")).click();
        driver.findElement(By.xpath("//input[@name = 'Email']")).sendKeys(userDetails.email);
        driver.findElement(By.xpath("//input[@name = 'Password']")).sendKeys(userDetails.password);
        driver.findElement(By.xpath("//input[contains(@value, 'Log in')]")).click();
    }

    private void addProductsToCart(String datafile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(datafile));
        String product;
        driver.findElement(By.linkText("Digital downloads")).click();
        while ((product = reader.readLine()) != null) {
            var str = "//a[contains(text(),'" + product + "')]";
            List<WebElement> productLinks = driver.findElements(By.xpath("//a[contains(text(),'" + product + "')]"));
            if (!productLinks.isEmpty()) {
                productLinks.get(0).click();
                driver.findElement(By.xpath("//div[@class='add-to-cart']//input[@value='Add to cart']")).click();
                driver.navigate().back();
            }
        }
        reader.close();
    }

    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            result.append(characters.charAt(random.nextInt(characters.length())));
        }
        return result.toString();
    }

    public static String generateRandomNumericString(int length) {
        String digits = "0123456789";
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            result.append(digits.charAt(random.nextInt(digits.length())));
        }
        return result.toString();
    }

    private void checkoutProcess() {
        driver.findElement(By.xpath("//a[text()='Shopping cart']")).click();

        driver.findElement(By.xpath("//input[@id='termsofservice']")).click();

        driver.findElement(By.xpath("//button[@id='checkout']")).click();

        if (driver.findElements(By.xpath("//label[@for='billing-address-select']")).isEmpty()) {
            driver.findElement(By.xpath("//select[@id='BillingNewAddress_CountryId']")).click();
            driver.findElement(By.xpath("//select[@id='BillingNewAddress_CountryId']/option[text()='United States']")).click();

            driver.findElement(By.xpath("//select[@id='BillingNewAddress_StateProvinceId']")).click();
            driver.findElement(By.xpath("//select[@id='BillingNewAddress_StateProvinceId']/option[text()='Alabama']")).click();

            String city = generateRandomString(10);
            String address1 = generateRandomString(15);
            String zipCode = generateRandomNumericString(5);
            String phoneNumber = generateRandomNumericString(10);

            driver.findElement(By.xpath("//input[@id='BillingNewAddress_City']")).sendKeys(city);
            driver.findElement(By.xpath("//input[@id='BillingNewAddress_Address1']")).sendKeys(address1);
            driver.findElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']")).sendKeys(zipCode);
            driver.findElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']")).sendKeys(phoneNumber);
            driver.findElement(By.xpath("//input[contains(@class, 'new-address-next-step-button')]")).click();

        }
        else {
            driver.findElement(By.xpath("//input[contains(@class, 'new-address-next-step-button')]")).click();
        }



        driver.findElement(By.xpath("//input[contains(@class, 'payment-method-next-step-button')]")).click();
        driver.findElement(By.xpath("//input[contains(@class, 'payment-info-next-step-button')]")).click();
        driver.findElement(By.xpath("//input[contains(@class, 'confirm-order-next-step-button')]")).click();

        String successMessage = driver.findElement(By.xpath("//strong[contains(text(), 'Your order has been successfully processed!')]")).getText();
        Assert.assertTrue(successMessage.contains("Your order has been successfully processed!"));
    }

    @Test
    public void test1() throws IOException {
        login();
        addProductsToCart("src//main//java//uzd3//data1.txt");
        checkoutProcess();
    }

    @Test
    public void test2() throws IOException {
        login();
        addProductsToCart("src//main//java//uzd3//data2.txt");
        checkoutProcess();
    }
}
