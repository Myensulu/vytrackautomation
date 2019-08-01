package com.vytrack.tests.components.login_navigation;

import com.vytrack.utilities.SeleniumUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

// For Tests

public class LoginTest {
    WebDriver driver;
    String logoutLocator= "a[class='no-hash']";
    String invalidMessageLocator = "[class='alert alert-error']";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://qa2.vytrack.com/user/login");
    }

    @Test(priority = 1, description = "Login test, positive")
    public void loginTest1() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@name='_username']")).sendKeys("storemanager88");
        driver.findElement(By.xpath("//input[@name='_password']")).sendKeys("UserUser123");
        SeleniumUtils.waitPlease(4);
        driver.findElement(By.xpath("//button[@name='_submit']")).click();
        SeleniumUtils.waitPlease(2);

        WebElement DropDown = driver.findElement(By.className("dropdown-toggle"));
        assertTrue(DropDown.getText().equals("Lew Emmerich"));

        WebElement DashboardPage = driver.findElement(By.xpath("//*[@class='oro-subtitle']"));
        assertTrue(DashboardPage.getText().equals("Dashboard"));
        SeleniumUtils.waitPlease(2);


        DropDown.click();
        SeleniumUtils.waitPlease(2);
        driver.findElement(By.cssSelector(logoutLocator)).click();
        SeleniumUtils.waitPlease(2);


        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@name='_username']")).sendKeys("salesmanager253");
        driver.findElement(By.xpath("//input[@name='_password']")).sendKeys("UserUser123");
        SeleniumUtils.waitPlease(4);
        driver.findElement(By.xpath("//button[@name='_submit']")).click();
        SeleniumUtils.waitPlease(4);

        WebElement DropDown2 = driver.findElement(By.className("dropdown-toggle"));
        assertTrue(DropDown2.getText().equals("Alberto Bins"));

        WebElement DashboardPage2 = driver.findElement(By.xpath("//*[@class='oro-subtitle']"));
        assertTrue(DashboardPage2.getText().equals("Dashboard"));
        SeleniumUtils.waitPlease(6);

        DropDown2.click();
        SeleniumUtils.waitPlease(3);
        driver.findElement(By.cssSelector(logoutLocator)).click(); //we can also linkedtext locator too
        SeleniumUtils.waitPlease(3);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@name='_username']")).sendKeys("user155");
        driver.findElement(By.xpath("//input[@name='_password']")).sendKeys("UserUser123");
        SeleniumUtils.waitPlease(4);
        driver.findElement(By.xpath("//button[@name='_submit']")).click();
        SeleniumUtils.waitPlease(4);

        WebElement DropDown3 = driver.findElement(By.className("dropdown-toggle"));
        assertTrue(DropDown3.getText().equals("Adolph Zulauf"));

        WebElement DashboardPage3 = driver.findElement(By.xpath("//*[@class='oro-subtitle']"));
        assertTrue(DashboardPage3.getText().equals("Quick Launchpad"));
        SeleniumUtils.waitPlease(6);

        DropDown3.click();
        SeleniumUtils.waitPlease(3);
        driver.findElement(By.cssSelector(logoutLocator)).click();
        SeleniumUtils.waitPlease(3);

    }

    @Test(priority = 2, description = "Login test, negative")
    public void loginTest2() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@name='_username']")).sendKeys("salesmanager");
        driver.findElement(By.xpath("//input[@name='_password']")).sendKeys("UserUser");
        SeleniumUtils.waitPlease(4);
        driver.findElement(By.xpath("//button[@name='_submit']")).click();
        SeleniumUtils.waitPlease(4);

        String expectedMessage = "Invalid user name or password.";
        String actualMessage = driver.findElement(By.cssSelector(invalidMessageLocator)).getText();
        assertEquals(actualMessage, expectedMessage);
        SeleniumUtils.waitPlease(3);

        assertTrue(  driver.getTitle().contains("Login")  );
        assertTrue( driver.getCurrentUrl().equals("http://qa2.vytrack.com/user/login") );

        assertEquals( driver.getTitle(), "Login" );
        assertEquals( driver.getCurrentUrl(), "http://qa2.vytrack.com/user/login"  );

    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
