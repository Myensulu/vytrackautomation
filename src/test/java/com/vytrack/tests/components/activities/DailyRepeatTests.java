package com.vytrack.tests.components.activities;

import com.vytrack.pages.activities.CalendarEventsPage;
import com.vytrack.pages.login_navigation.LoginPage;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.SeleniumUtils;
import com.vytrack.utilities.TestBase;
import com.vytrack.utilities.VYTrackUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class DailyRepeatTests extends TestBase {

    /*  1) Daily repeat option, Repeat every, summary
     * 1. Log in as Valid user
     * 2. Go to Activities -> Calendar Events
     * 3. Click on create new calendar event
     * 4. Click on Repeat checkbox
     * 5. Verify that Daily is selected by default
     * 6. Verify day(s) checkbox is selected and default value is 1
     * 7. Verify summary says Daily every 1 day
     * 8. Check the weekday checkbox
     * 9. Verify that days input now disabled
     * 10. Verify summary says Daily every weekday
     */

    @Test(priority = 1)
    public void test_1(){
        extentLogger = report.createTest("Daily repeat option, Repeat every, summary");
        // created object of LoginPage
        // that has method to log in
        LoginPage loginPage = new LoginPage();
        // created object of CalendarEventsPage
        CalendarEventsPage calendarPage = new CalendarEventsPage();

        // read the username and password from .properties file
        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        loginPage.login(username, password);

        // go to Activities->CalendarEvents
        VYTrackUtils.navigateToModule("Activities", "Calendar Events");
        SeleniumUtils.waitPlease(2);

        // click on Create calendar events button
        calendarPage.clickOnCreateCalendarEvent();
        SeleniumUtils.waitPlease(3);

        // Click on Repeat checkbox
        calendarPage.clickOnRepeatCheckbox();
        SeleniumUtils.waitPlease(3);

        WebElement repeatsButton = driver.findElement(By.cssSelector("span[style^='width: 292']"));

        String expectedRepeatDefault = "Daily";
        String actualRepeatDefault = repeatsButton.getText();

        // verify Daily is default
        Assert.assertEquals(actualRepeatDefault, expectedRepeatDefault);

        //check radio button
        List<WebElement> radioButtons = driver.findElements(By.cssSelector("input[type='radio']"));

        // it returns boolean
        Assert.assertEquals(true, radioButtons.get(0).isSelected());

        // check the default day is 1
        List<WebElement> repeatDaysButton = driver.findElements(By.cssSelector("input[class='recurrence-subview-control__number']"));

        // expected day is "1"
        Assert.assertEquals(repeatDaysButton.get(0).getAttribute("value"), "1");

        // Verify summary says Daily every 1 day
        String actualSummaryText = driver.findElement(By.xpath("//div[@class='control-group recurrence-summary alert-info']/div[2]/div")).getText();
        String expectedSummary = "Daily every 1 day";
        System.out.println(actualSummaryText);

        Assert.assertEquals(actualSummaryText, expectedSummary);

        // check the weekday box
        radioButtons.get(1).click();
        SeleniumUtils.waitPlease(2);

        // Verify that days input now disabled
        boolean isEnabled = driver.findElement(By.cssSelector("input[class='recurrence-subview-control__number']")).isEnabled();
        Assert.assertEquals(isEnabled, false);

        // Verify summary says Daily every weekday
        String actualSummaryTextForWeekday = driver.findElement(By.xpath("//div[@class='control-group recurrence-summary alert-info']/div[2]/div")).getText();
        String expectedSummaryWeekday = "Daily, every weekday";
        System.out.println(actualSummaryTextForWeekday);

        Assert.assertEquals(actualSummaryTextForWeekday, expectedSummaryWeekday);
    }

    /*  2) Daily repeat option, Repeat every, default values
     * 1. Log in as Valid user
     * 2. Go to Activities -> Calendar Events
     * 3. Click on create new calendar event
     * 4. Click on Repeat checkbox
     * 5. Verify that Daily is selected by default
     * 6. Verify day(s) checkbox is selected and default value is 1
     * 7. Verify summary says Daily every 1 day
     */

    @Test(priority = 2)
    public void test_2(){
        extentLogger = report.createTest("Daily repeat option, Repeat every, default values");
        // created object of LoginPage
        // that has method to log in
        LoginPage loginPage = new LoginPage();
        // created object of CalendarEventsPage
        CalendarEventsPage calendarPage = new CalendarEventsPage();

        // read the username and password from .properties file
        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        loginPage.login(username, password);

        // go to Activities->CalendarEvents
        VYTrackUtils.navigateToModule("Activities", "Calendar Events");
        SeleniumUtils.waitPlease(2);

        // click on Create calendar events button
        calendarPage.clickOnCreateCalendarEvent();
        SeleniumUtils.waitPlease(3);

        // Click on Repeat checkbox
        calendarPage.clickOnRepeatCheckbox();
        SeleniumUtils.waitPlease(3);

        WebElement repeatsButton = driver.findElement(By.cssSelector("span[style^='width: 292']"));

        String expectedRepeatDefault = "Daily";
        String actualRepeatDefault = repeatsButton.getText();

        // verify Daily is default
        Assert.assertEquals(actualRepeatDefault, expectedRepeatDefault);

        //check radio button
        List<WebElement> radioButtons = driver.findElements(By.cssSelector("input[type='radio']"));

        // it returns boolean
        Assert.assertEquals(true, radioButtons.get(0).isSelected());

        // check the default day is 1
        List<WebElement> repeatDaysButton = driver.findElements(By.cssSelector("input[class='recurrence-subview-control__number']"));

        // expected day is "1"
        Assert.assertEquals(repeatDaysButton.get(0).getAttribute("value"), "1");

        // Verify summary says Daily every 1 day
        String actualSummaryText = driver.findElement(By.xpath("//div[@class='control-group recurrence-summary alert-info']/div[2]/div")).getText();
        String expectedSummary = "Daily every 1 day";
        System.out.println(actualSummaryText);

        Assert.assertEquals(actualSummaryText, expectedSummary);
    }

     /*3) Daily repeat option, Repeat every day(s), error messages
        1. Log in as Valid user
        2. Go to Activities -> Calendar Events
        3. Click on create new calendar event
        4. Click on Repeat checkbox
        5. Test the day(s) input entering different values (boundary value analysis)
        6. Verify error messages The value have not to be less than 1. and The value have not to be
            more than 99. occur when values are too big or small
        7. Verify that error messages disappear when valid values are entered
     */

    @Test(priority = 3)
    public void test_3(){
        extentLogger = report.createTest("Daily repeat option, Repeat every day(s), error messages");
        // created object of LoginPage
        // that has method to log in
        LoginPage loginPage = new LoginPage();
        // created object of CalendarEventsPage
        CalendarEventsPage calendarPage = new CalendarEventsPage();

        // read the username and password from .properties file
        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        loginPage.login(username, password);

        // go to Activities->CalendarEvents
        VYTrackUtils.navigateToModule("Activities", "Calendar Events");
        SeleniumUtils.waitPlease(2);

        // click on Create calendar events button
        calendarPage.clickOnCreateCalendarEvent();
        SeleniumUtils.waitPlease(3);

        // Click on Repeat checkbox
        calendarPage.clickOnRepeatCheckbox();
        SeleniumUtils.waitPlease(3);

        // Test the day(s) input entering different values (boundary value analysis)
        // entered 0
        WebElement dayValueBox = calendarPage.dayBox;
        dayValueBox.click();
        dayValueBox.clear();
        dayValueBox.sendKeys("0");

        String error_1 = calendarPage.errorMessage.getText();
        Assert.assertTrue(error_1.equals("The value have not to be less than 1."));

        // clear and enter 100
        dayValueBox.click();
        dayValueBox.clear();
        dayValueBox.sendKeys("100");

        String error_2 = calendarPage.errorMessage.getText();
        Assert.assertTrue(error_2.equals("The value have not to be more than 99."));

        // clear and enter valid number
        // entered 10
        dayValueBox.click();
        dayValueBox.clear();
        dayValueBox.sendKeys("10");

        // Verify that error messages disappear when valid values are entered
        dayValueBox.click();
        dayValueBox.clear();
        dayValueBox.sendKeys("10");
        Assert.assertTrue(calendarPage.eNewMessage.getText().equals(""));
    }

    /* 4) Daily repeat option, Repeat every day(s), functionality
    1. Log in as Valid user
    2. Go to Activities -> Calendar Events
    3. Click on create new calendar event
    4. Click on Repeat checkbox
    5. Enter random value to the day(s) field
    6. Verify that Summary says Daily every <random number> day
    7. Enter another random value to the day(s) field
    8. Verify that Summary updated with Daily every <random number> day
    */

    @Test(priority = 4)
    public void test_4(){
        extentLogger = report.createTest("Daily repeat option, Repeat every day(s), functionality");
        // created object of LoginPage
        // that has method to log in
        LoginPage loginPage = new LoginPage();
        // created object of CalendarEventsPage
        CalendarEventsPage calendarPage = new CalendarEventsPage();

        // read the username and password from .properties file
        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        loginPage.login(username, password);

        // go to Activities->CalendarEvents
        VYTrackUtils.navigateToModule("Activities", "Calendar Events");
        SeleniumUtils.waitPlease(2);

        // click on Create calendar events button
        calendarPage.clickOnCreateCalendarEvent();
        SeleniumUtils.waitPlease(3);

        // Click on Repeat checkbox
        calendarPage.clickOnRepeatCheckbox();
        SeleniumUtils.waitPlease(3);

        // Enter random value to the day(s) field
        calendarPage.enterRandomDays(calendarPage.dailyValueLocator);
        String valuetext1 = calendarPage.dailyValueLocator.getAttribute("value");

        // Verify that Summary says Daily every <random number> day
        Assert.assertTrue(calendarPage.summaryLocator.getText().equals("Daily every "+valuetext1+" days"));

        // Enter another random value to the day(s) field
        calendarPage.enterRandomDays(calendarPage.dailyValueLocator);

        // Verify that Summary updated with Daily every <random number> day
        String valuetext2 = calendarPage.dailyValueLocator.getAttribute("value");
        Assert.assertTrue(calendarPage.summaryLocator.getText().equals("Daily every "+valuetext2+" days"));
    }

    /*  5) Daily repeat option, blank fields
    1. Log in as Valid user
    2. Go to Activities -> Calendar Events
    3. Click on create new calendar event
    4. Click on Repeat checkbox
    5. Clear the value of the day(s) field
    6. Message This value should not be blank. should come up
    7. Enter a valid value to the day(s) field the
    8. Message This value should not be blank. should disappear
    9. Clear the value of the After occurrences field
    10. Message This value should not be blank. should come up
    11. Enter a valid value to the After occurrences field the
    12. Message This value should not be blank. should disappear
    */

    @Test(priority = 5)
    public void test_5(){
        extentLogger = report.createTest("Daily repeat option, blank fields");
        // created object of LoginPage
        // that has method to log in
        LoginPage loginPage = new LoginPage();
        // created object of CalendarEventsPage
        CalendarEventsPage calendarPage = new CalendarEventsPage();

        // read the username and password from .properties file
        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        loginPage.login(username, password);

        // go to Activities->CalendarEvents
        VYTrackUtils.navigateToModule("Activities", "Calendar Events");
        SeleniumUtils.waitPlease(2);

        // click on Create calendar events button
        calendarPage.clickOnCreateCalendarEvent();
        SeleniumUtils.waitPlease(3);

        // Click on Repeat checkbox
        calendarPage.clickOnRepeatCheckbox();
        SeleniumUtils.waitPlease(3);

        WebElement dayValueBox = calendarPage.dayBox;
        dayValueBox.click();
        dayValueBox.clear();

        // Message This value should not be blank. should come up
        String errorMessage = calendarPage.errorMessage.getText();
        Assert.assertTrue(errorMessage.equals("This value should not be blank."));

        // Enter a valid value to the day(s) field the
        dayValueBox.click();
        dayValueBox.clear();
        dayValueBox.sendKeys("10");

        // Message This value should not be blank. should disappear
        Assert.assertTrue(calendarPage.eNewMessage.getText().equals(""));

        // Clear the value of the After occurrences field
        WebElement afterValueBox = calendarPage.afterOccurrencesValueBox;
        afterValueBox.click(); afterValueBox.sendKeys("3"); afterValueBox.clear();

        // Message This value should not be blank. should come up
        Assert.assertTrue(calendarPage.msg1.getText().equals("This value should not be blank."));

        // Enter a valid value to the After occurrences field.
        afterValueBox.click(); afterValueBox.clear(); afterValueBox.sendKeys("10", Keys.ENTER);

        //12. Message This value should not be blank. should disappear
        Assert.assertTrue(calendarPage.msg2.getText().equals(""));
    }

    /* 6) Daily repeat option, Ends, error messages
    1. Log in as Valid user
    2. Go to Activities -> Calendar Events
    3. Click on create new calendar event
    4. Click on Repeat checkbox
    5. Test the After occurrences input entering different values (boundary value analysis)
    6. Verify error messages The value have not to be less than 1. and The value have not to be
        more than 99. occur when values are too big or small
    7. Verify that error messages disappear when valid values are entered
    */

    @Test(priority = 6)
    public void test_6(){
        extentLogger = report.createTest("Daily repeat option, Ends, error messages");
        // created object of LoginPage
        // that has method to log in
        LoginPage loginPage = new LoginPage();
        // created object of CalendarEventsPage
        CalendarEventsPage calendarPage = new CalendarEventsPage();

        // read the username and password from .properties file
        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        loginPage.login(username, password);

        // go to Activities->CalendarEvents
        VYTrackUtils.navigateToModule("Activities", "Calendar Events");
        SeleniumUtils.waitPlease(2);

        // click on Create calendar events button
        calendarPage.clickOnCreateCalendarEvent();
        SeleniumUtils.waitPlease(3);

        // Click on Repeat checkbox
        calendarPage.clickOnRepeatCheckbox();
        SeleniumUtils.waitPlease(3);

        // Test the After occurrences input entering different values (boundary value analysis)
        //first enter -1
        WebElement afterValueBox = calendarPage.afterOccurrencesValueBox;
        afterValueBox.click(); afterValueBox.clear(); afterValueBox.sendKeys("-1",Keys.ENTER);
        String errorMessage1 = calendarPage.msg1.getText();
        //clear and enter 100
        afterValueBox.click(); afterValueBox.clear(); afterValueBox.sendKeys("1000",Keys.ENTER);
        String errorMessage2 = calendarPage.msg1.getText();

        // Verify error messages The value have not to be less than 1. and The value have not to be
        //more than 99. occur when values are too big or small
        Assert.assertTrue(errorMessage1.equals("The value have not to be less than 1."));
        Assert.assertTrue(errorMessage2.equals("The value have not to be more than 999."));

        // Verify that error messages disappear when valid values are entered
        afterValueBox.click(); afterValueBox.clear(); afterValueBox.sendKeys("10");
        Assert.assertTrue(calendarPage.msg2.getText().equals(""));
    }

    /* 7) Daily repeat option, Ends, functionality
    1. Log in as Valid user
    2. Go to Activities -> Calendar Events
    3. Click on create new calendar event
    4. Click on Repeat checkbox
    5. Enter random value to the After occurrences field
    6. Verify that Summary says Daily every <random number> day
    7. Enter another random value to the After occurrences field
    8. Verify that Summary updated with Daily every <random number> day
    */

    @Test(priority = 7)
    public void test_7(){
        extentLogger = report.createTest("Daily repeat option, Ends, functionality");
        // created object of LoginPage
        // that has method to log in
        LoginPage loginPage = new LoginPage();
        // created object of CalendarEventsPage
        CalendarEventsPage calendarPage = new CalendarEventsPage();

        // read the username and password from .properties file
        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        loginPage.login(username, password);

        // go to Activities->CalendarEvents
        VYTrackUtils.navigateToModule("Activities", "Calendar Events");
        SeleniumUtils.waitPlease(2);

        // click on Create calendar events button
        calendarPage.clickOnCreateCalendarEvent();
        SeleniumUtils.waitPlease(3);

        // Click on Repeat checkbox
        calendarPage.clickOnRepeatCheckbox();
        SeleniumUtils.waitPlease(3);

        // Enter random value to the After occurrences field
        calendarPage.enterRandomDays(calendarPage.afterOccurrencesValueBox);
        String valuetext1 = calendarPage.afterOccurrencesValueBox.getAttribute("value");

        // Verify that Summary says Daily every <random number> day
        Assert.assertTrue(calendarPage.summaryLocator.getText().equals("Daily every 1 day"));
        Assert.assertTrue(calendarPage.summaryLocator2.getText().equals(", end after "+valuetext1+" occurrences"));

        // Enter another random value to the day(s) field
        calendarPage.enterRandomDays(calendarPage.afterOccurrencesValueBox);

        // Verify that Summary updated with Daily every <random number> day
        String valuetext2 = calendarPage.afterOccurrencesValueBox.getAttribute("value");
        Assert.assertTrue(calendarPage.summaryLocator.getText().equals("Daily every 1 day"));
        Assert.assertTrue(calendarPage.summaryLocator2.getText().equals(", end after "+valuetext2+" occurrences"));
    }

}
