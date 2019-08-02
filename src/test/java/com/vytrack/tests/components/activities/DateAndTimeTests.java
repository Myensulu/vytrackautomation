package com.vytrack.tests.components.activities;

import com.vytrack.pages.activities.CalendarEventsPage;
import com.vytrack.utilities.TestBase;
import com.vytrack.pages.login_navigation.LoginPage;
import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.SeleniumUtils;
import com.vytrack.utilities.VYTrackUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DateAndTimeTestsExample extends TestBase {  // We are extending it,cuz before & after class is coming from Test Base

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
    public void verifyDateTest(){
        extentLogger = report.createTest("Verify that date auto adjustable");
        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calendarPage = new CalendarEventsPage();

        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        //login
        loginPage.login(username, password);

        //go to Calendar Events page
        VYTrackUtils.navigateToModule("Activities", "Calendar Events");

        calendarPage.clickOnCreateCalendarEvent();
        SeleniumUtils.waitPlease(2);
        calendarPage.selectStartOrEndDate("8/15/2019", "start");
        SeleniumUtils.waitPlease(3);
        //    verify start date is the same as end date
        Assert.assertEquals(calendarPage.getStartDate(), calendarPage.getEndDate());
    }


    @Test(priority = 2, description = "Verify that date auto adjustable with today's and tomorrow's date ")
    public void verifyTodaysDateTest(){
        extentLogger = report.createTest("Verify that date auto adjustable with today's and tomorrow's date ");
        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calendarPage = new CalendarEventsPage();

        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        //login
        loginPage.login(username, password);

        //go to Calendar Events page
        VYTrackUtils.navigateToModule("Activities", "Calendar Events");

        //click to create calendar event
        calendarPage.clickOnCreateCalendarEvent();

        //select tomorrow date
        calendarPage.selectTomorrowDay();
        SeleniumUtils.waitPlease(2);

        Assert.assertEquals(calendarPage.getStartDate(), calendarPage.getEndDate());

        //select today's date
        calendarPage.selectTodaysDay();
        SeleniumUtils.waitPlease(2);

        //verify that start and end date is the same
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

    @Test(priority = 3, description = "Verify that end time changes exactly 1 hours later")
    public void verifyTimeTest(){
        extentLogger = report.createTest("Verify that end time changes exactly 1 hours later");

        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calendarPage = new CalendarEventsPage();

        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        //login
        loginPage.login(username, password);

        //go to Calendar Events page
        VYTrackUtils.navigateToModule("Activities", "Calendar Events");

        //click to create calendar event
        calendarPage.clickOnCreateCalendarEvent();

        //select any time
        calendarPage.selectStartTime("1:00 PM");

        //Verify that end time changes exactly 1 hours later
        Assert.assertEquals(calendarPage.differenceBetweenStartTimeAndEndTime(), 1);

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

    @Test(priority = 4, description = "Verify that end time is 12:30 AM")
    public void verifyTimeTest2(){
        extentLogger = report.createTest("Verify that end time is 12:30 AM");
        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calendarPage = new CalendarEventsPage();

        String username = ConfigurationReader.getProperty("storemanagerusername");
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        //login
        loginPage.login(username, password);

        //go to Calendar Events page
        VYTrackUtils.navigateToModule("Activities", "Calendar Events");

        //click to create calendar event
        calendarPage.clickOnCreateCalendarEvent();

        //select 11:30 PM
        calendarPage.selectStartTime("11:30 PM");
        SeleniumUtils.waitPlease(3);

        //Verify that end time is 12:30 AM
        Assert.assertEquals(calendarPage.getEndTime(), "12:30 AM");
        SeleniumUtils.waitPlease(2);
    }
}
