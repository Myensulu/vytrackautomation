package com.vytrack.tests.components.activities;

import com.vytrack.pages.activities.CalendarEventsPage;
import com.vytrack.pages.login_navigation.LoginPage;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.SeleniumUtils;
import com.vytrack.utilities.TestBase;
import com.vytrack.utilities.VYTrackUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DateAndTimeTestsPractice extends TestBase { // We are extending it,cuz before & after class is coming from Test Base

    /*
     * Test Case 1  (Date Time, End date auto adjust)
     * 1. Log in as Valid user
     * 2. Go to Activities -> Calendar Events
     * 3. Click on create new calendar event
     * 4. Change the start date to future date
     * 5. Verify that end date changes to the same date
     * 6. Change back the start date to today's date
     * 7. Verify that end date changes back to today's date
     */

    @Test(priority = 1, description = "Verify that date auto adjustable")
    public void test_1(){
        extentLogger = report.createTest("Verify that date auto adjustable");
        // created object of LoginPage
        // that has method to log in
        LoginPage loginPage = new LoginPage();
        // created object of CalendarEventsPage
        CalendarEventsPage calendarPage = new CalendarEventsPage();

        // read the username and password from .properties file
        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        loginPage.login(username, password);

        // go to calEv page
        VYTrackUtils.navigateToModule("Activities", "Calendar Events");
        SeleniumUtils.waitPlease(2);

        calendarPage.clickOnCreateCalendarEvent();
        SeleniumUtils.waitPlease(2);

        calendarPage.selectStartOrEndDate("8/5/2019", "start");
        SeleniumUtils.waitPlease(3);

        Assert.assertEquals(calendarPage.getStartDate(), calendarPage.getEndDate());

        calendarPage.selectTodaysDay();
        SeleniumUtils.waitPlease(2);

        Assert.assertEquals(calendarPage.getStartDate(), calendarPage.getEndDate());
    }

    /*
     * Test Case 2  (Date Time, End time auto adjust)
     * 1. Log in as Valid user
     * 2. Go to Activities -> Calendar Events
     * 3. Click on create new calendar event
     * 4. Change the start time to any other time
     * 5. Verify that end time changes exactly 1 hours later
     */

    @Test(priority = 2, description = "Verify that end time changes exactly 1 hours later")
    public void test_2(){
        extentLogger = report.createTest("Verify that end time changes exactly 1 hours later");
        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calendarPage = new CalendarEventsPage();

        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        loginPage.login(username, password);

        VYTrackUtils.navigateToModule("Activities", "Calendar Events");
        SeleniumUtils.waitPlease(2);

        calendarPage.clickOnCreateCalendarEvent();
        SeleniumUtils.waitPlease(2);

        calendarPage.selectStartTime("2:00 PM");
        Assert.assertEquals(calendarPage.differenceBetweenStartTimeAndEndTime(),1);
    }

    /*
     * Test Case 3 (Date Time, End date/time auto adjust)
     * 1. Log in as Valid user
     * 2. Go to Activities -> Calendar Events
     * 3. Click on create new calendar event
     * 4. Change the start time to 11.30 PM
     * 5. Verify that end date shows tomorrows date
     * 6. Verify that end time is 12:30 AM
     */

    @Test(priority = 3, description = "Verify that end time is 12:30 AM")
    public void test_3(){
        extentLogger = report.createTest("Verify that end time is 12:30 AM");
        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calendarPage = new CalendarEventsPage();

        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        loginPage.login(username, password);

        VYTrackUtils.navigateToModule("Activities", "Calendar Events");
        SeleniumUtils.waitPlease(2);

        calendarPage.clickOnCreateCalendarEvent();
        SeleniumUtils.waitPlease(2);

        calendarPage.selectStartTime("11:30 PM");
        SeleniumUtils.waitPlease(2);

        Assert.assertEquals(calendarPage.getEndTime(), "12:30 AM");
        SeleniumUtils.waitPlease(2);
    }

}
