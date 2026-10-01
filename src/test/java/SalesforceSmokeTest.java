import java.time.Duration;
import java.net.URI;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

public class SalesforceSmokeTest {

    private WebDriver driver;

    @Test
    void navigateToSalesforceAccounts() {

        String username = System.getenv("SALESFORCE_USERNAME");
        String password = System.getenv("SALESFORCE_PASSWORD");

        System.out.println(
                "Java sees username: " + (username != null));

        System.out.println(
                "Java sees password: " + (password != null));

        if (username == null || password == null) {
            throw new IllegalStateException(
                    "Salesforce environment variables are not configured.");
        }

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--user-data-dir=C:\\Users\\lawre\\AppData\\Local\\Google\\Chrome\\User Data\\Default");

        driver = new ChromeDriver(options);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get("https://login.salesforce.com/");

        WebElement usernameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("username")));
        usernameField.sendKeys(username);

        List<WebElement> passwordFields = driver.findElements(By.id("password"));

        if (passwordFields.isEmpty()) {
            List<WebElement> nextButtons = driver.findElements(
                    By.xpath(
                            "//input[@type='submit'] | //button[@type='submit']"));

            if (!nextButtons.isEmpty()) {
                nextButtons.get(0).click();
            }
        }
        WebElement passwordField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("password")));

        passwordField.sendKeys(password);

        WebElement loginButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath(
                                "//input[@type='submit'] | //button[@type='submit']")));

        loginButton.click();

        wait.until(
                ExpectedConditions.not(
                        ExpectedConditions.urlContains("login.salesforce.com")));

        URI currentUri = URI.create(driver.getCurrentUrl());

        String baseUrl = currentUri.getScheme() + "://" + currentUri.getHost();

        driver.get(
                baseUrl + "/lightning/o/Account/list");

        wait.until(
                ExpectedConditions.urlContains("/lightning/o/Account/list"));

        assertTrue(
                driver.getCurrentUrl().contains("/lightning/o/Account/list"),
                "Expected salesforce Accounts page to load.");

        System.out.println(
                "PASS: Salesforce Accounts page loaded successfully.");

    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}