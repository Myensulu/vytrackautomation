package com.vytrack.tests.smoke_tests;

import com.vytrack.utilities.SeleniumUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.*;

public class MenuOptionsTest {

    WebDriver driver;  // we have to make this as  "instance variable" to use in the (@Test) test cases

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://qa2.vytrack.com/user/login");
    }

    @Test(priority = 1, description = "Menu options, driver")
    public void test1() {

        driver.findElement(By.xpath("//input[@name='_username']")).sendKeys("user155");
        driver.findElement(By.xpath("//input[@name='_password']")).sendKeys("UserUser123");
        SeleniumUtils.waitPlease(3);
        driver.findElement(By.xpath("//button[@name='_submit']")).click();
        SeleniumUtils.waitPlease(3);

        WebElement Fleet = driver.findElement(By.xpath("(//span[@class='title title-level-1'])[1]"));
        Fleet.click();
        SeleniumUtils.waitPlease(3);

        WebElement CarsModule = driver.findElement(By.xpath("(//span[@class='title title-level-2'])[1]"));
        CarsModule.click();
        SeleniumUtils.waitPlease(3);
        assertTrue(  driver.getTitle().equals("Car - Entities - System - Car - Entities - System"));

        WebElement CarsPage = driver.findElement(By.xpath("//*[@class='oro-subtitle']"));
        assertTrue(CarsPage.getText().equals("Cars"));
        SeleniumUtils.waitPlease(3);

        WebElement Customers = driver.findElement(By.xpath("(//span[@class='title title-level-1'])[2]"));
        Customers.click();
        SeleniumUtils.waitPlease(3);

        WebElement AccountsModule = driver.findElement(By.partialLinkText("Accounts"));
        AccountsModule.click();
        SeleniumUtils.waitPlease(3);
        assertTrue(driver.getTitle().equals("Accounts - Customers"));
        SeleniumUtils.waitPlease(3);

        WebElement AccountsPage = driver.findElement(By.xpath("//*[@class='oro-subtitle']"));
        assertTrue(AccountsPage.getText().equals("Accounts"));
        SeleniumUtils.waitPlease(3);

        WebElement Customers1 = driver.findElement(By.xpath("(//span[@class='title title-level-1'])[2]"));
        Customers1.click();
        SeleniumUtils.waitPlease(3);

        WebElement ContactsModule = driver.findElement(By.partialLinkText("Contacts"));
        ContactsModule.click();
        SeleniumUtils.waitPlease(3);
        assertTrue(driver.getTitle().equals("Contacts - Customers"));
        SeleniumUtils.waitPlease(3);

        WebElement ContactsPage = driver.findElement(By.xpath("//*[@class='oro-subtitle']"));
        assertTrue(ContactsPage.getText().equals("Contacts"));
        SeleniumUtils.waitPlease(3);

        WebElement Activities = driver.findElement(By.xpath("(//span[@class='title title-level-1'])[3]"));
        Activities.click();
        SeleniumUtils.waitPlease(3);

        WebElement CalendarEventsModule = driver.findElement(By.partialLinkText("Calendar"));
        CalendarEventsModule.click();
        SeleniumUtils.waitPlease(3);
        assertTrue(driver.getTitle().equals("Calendar Events - Activities"));
        SeleniumUtils.waitPlease(3);

        WebElement CalendarEventsPage = driver.findElement(By.xpath("//*[@class='oro-subtitle']"));
        assertTrue(CalendarEventsPage.getText().equals("Calendar Events"));
        SeleniumUtils.waitPlease(3);
    }

    @Test(priority = 2, description = "Menu options, store manager")
    public void test2() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//input[@name='_username']")).sendKeys("storemanager88");
        driver.findElement(By.xpath("//input[@name='_password']")).sendKeys("UserUser123");
        SeleniumUtils.waitPlease(4);
        driver.findElement(By.xpath("//button[@name='_submit']")).click();
        SeleniumUtils.waitPlease(3);

        WebElement Dashboards = driver.findElement(By.xpath("(//span[@class='title title-level-1'])[1]"));
        Dashboards.click();
        SeleniumUtils.waitPlease(3);

        WebElement DashboardModule = driver.findElement(By.xpath("(//span[@class='title title-level-2'])[1]"));
        DashboardModule.click();
        SeleniumUtils.waitPlease(3);
        assertTrue(  driver.getTitle().equals("Dashboard - Dashboards"));

        WebElement DashboardPage = driver.findElement(By.xpath("//*[@class='oro-subtitle']"));
        assertTrue(DashboardPage.getText().equals("Dashboard"));
        SeleniumUtils.waitPlease(3);

        WebElement Fleet = driver.findElement(By.xpath("(//span[@class='title title-level-1'])[2]"));
        Fleet.click();
        SeleniumUtils.waitPlease(3);

        WebElement VehiclesModule = driver.findElement(By.partialLinkText("Vehicles"));
        VehiclesModule.click();
        SeleniumUtils.waitPlease(3);
        assertTrue(driver.getTitle().equals("All - Car - Entities - System - Car - Entities - System"));
        SeleniumUtils.waitPlease(3);

        WebElement AllCarsPage = driver.findElement(By.xpath("//*[@class='oro-subtitle']"));
        assertTrue(AllCarsPage.getText().equals("All Cars"));
        SeleniumUtils.waitPlease(3);

        WebElement Customers = driver.findElement(By.xpath("(//span[@class='title title-level-1'])[3]"));
        Customers.click();
        SeleniumUtils.waitPlease(3);

        WebElement AccountsModule = driver.findElement(By.partialLinkText("Accounts"));
        AccountsModule.click();
        SeleniumUtils.waitPlease(3);
        assertTrue(driver.getTitle().equals("All - Accounts - Customers"));
        SeleniumUtils.waitPlease(3);

        WebElement AllAccountsPage = driver.findElement(By.xpath("//*[@class='oro-subtitle']"));
        assertTrue(AllAccountsPage.getText().equals("All Accounts"));
        SeleniumUtils.waitPlease(3);

        WebElement Customers2 = driver.findElement(By.xpath("(//span[@class='title title-level-1'])[3]"));
        Customers2.click();
        SeleniumUtils.waitPlease(3);

        WebElement ContactsModule = driver.findElement(By.partialLinkText("Contacts"));
        ContactsModule.click();
        SeleniumUtils.waitPlease(3);
        assertTrue(driver.getTitle().equals("All - Contacts - Customers"));
        SeleniumUtils.waitPlease(3);

        WebElement AllContactsPage = driver.findElement(By.xpath("//*[@class='oro-subtitle']"));
        assertTrue(AllContactsPage.getText().equals("All Contacts"));
        SeleniumUtils.waitPlease(3);

        WebElement Sales = driver.findElement(By.xpath("(//span[@class='title title-level-1'])[4]"));
        Sales.click();
        SeleniumUtils.waitPlease(3);

        WebElement OpportunitiesModule = driver.findElement(By.partialLinkText("Opportunities"));
        OpportunitiesModule.click();
        SeleniumUtils.waitPlease(3);
        assertTrue(driver.getTitle().equals("Open Opportunities - Opportunities - Sales"));
        SeleniumUtils.waitPlease(3);

        WebElement OpenOpportunitiesPage = driver.findElement(By.xpath("//*[@class='oro-subtitle']"));
        assertTrue(OpenOpportunitiesPage.getText().equals("Open Opportunities"));
        SeleniumUtils.waitPlease(3);

        WebElement Activities = driver.findElement(By.xpath("(//span[@class='title title-level-1'])[5]"));
        Activities.click();
        SeleniumUtils.waitPlease(3);

        WebElement CallsModule = driver.findElement(By.partialLinkText("Calls"));
        CallsModule.click();
        SeleniumUtils.waitPlease(3);
        assertTrue(driver.getTitle().equals("All - Calls - Activities"));
        SeleniumUtils.waitPlease(3);

        WebElement AllCallsPage = driver.findElement(By.xpath("//*[@class='oro-subtitle']"));
        assertTrue(AllCallsPage.getText().equals("All Calls"));
        SeleniumUtils.waitPlease(3);

        WebElement Activities1 = driver.findElement(By.xpath("(//span[@class='title title-level-1'])[5]"));
        Activities1.click();
        SeleniumUtils.waitPlease(3);

        WebElement CalendarEventsModule = driver.findElement(By.partialLinkText("Calendar"));
        CalendarEventsModule.click();
        SeleniumUtils.waitPlease(3);
        assertTrue(driver.getTitle().equals("All - Calendar Events - Activities"));
        SeleniumUtils.waitPlease(3);

        WebElement AllCalendarEventsPage = driver.findElement(By.xpath("//*[@class='oro-subtitle']"));
        assertTrue(AllCalendarEventsPage.getText().equals("All Calendar Events"));
        SeleniumUtils.waitPlease(3);

    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }

}