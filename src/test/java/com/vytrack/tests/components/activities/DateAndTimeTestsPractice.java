package com.vytrack.tests.components.activities;

import com.vytrack.utilities.TestBase;
import org.testng.annotations.Test;

public class DateAndTimeTests extends TestBase { // We are extending it,cuz before & after class is coming from Test Base

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

    }

}
