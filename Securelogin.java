package junit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Securelogin {

    WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();  
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/login");
    }

    // ✅ Valid login test
    @Test
    void validLoginTest() {
        driver.findElement(By.id("username"))
              .sendKeys("tomsmith");
        driver.findElement(By.id("password"))
              .sendKeys("SuperSecretPassword!");
        driver.findElement(By.className("radius"))
              .click();

        assertTrue(driver.getCurrentUrl().contains("/secure"));
    }

    // ❌ Invalid login test
    @Test
    void invalidLoginTest() {
        driver.findElement(By.id("username"))
              .sendKeys("wronguser");
        driver.findElement(By.id("password"))
              .sendKeys("wrongpass");
        driver.findElement(By.className("radius"))
              .click();

        WebElement errorMsg = driver.findElement(By.id("flash"));
        assertTrue(errorMsg.isDisplayed());
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}



