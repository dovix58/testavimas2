package uzd3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

public class UserRegistration {
    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder string = new StringBuilder();
        Random rnd = new Random();
        while (string.length() < length) {
            int index = (int) (rnd.nextFloat() * characters.length());
            string.append(characters.charAt(index));
        }
        return string.toString();
    }

    public static String generateRandomEmail() {
        return generateRandomString(10) + "@example.com";
    }
    public static String generateRandomPassword(){
        return generateRandomString(10)+ "@123";
    }
    public static class UserDetails {
        public String email;
        public String password;
    }

    public static UserDetails  registerNewUser() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demowebshop.tricentis.com/");

        String firstName = generateRandomString(8);
        String lastName = generateRandomString(8);
        String email = generateRandomEmail();
        String password = generateRandomPassword();

        driver.findElement(By.xpath(" //a[@href='/login']")).click();
        driver.findElement(By.xpath(" //a[@href='/register']")).click();

        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys(lastName);
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@id='register-button']")).click();


        driver.findElement(By.xpath("//input[contains(@class, 'register-continue-button')]")).click();
        driver.quit();

        UserDetails userDetails = new UserDetails();
        userDetails.email = email;
        userDetails.password = password;
        return userDetails;
    }

    public static void main(String[] args) {
        UserDetails user = registerNewUser();
        System.out.println("New user created with email: " + user.email + " and password: " + user.password);
    }
}
