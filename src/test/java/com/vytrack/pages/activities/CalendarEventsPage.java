package com.vytrack.pages.activities;

import com.vytrack.utilities.Driver;
import com.vytrack.utilities.SeleniumUtils;
import com.vytrack.utilities.VYTrackUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

public class CalendarEventsPage {

    @FindBy(css = "[title='Create Calendar event']")
    public WebElement createCalendarEventBtn;

    @FindBy(css = "a[title='Grid Settings']")
    public WebElement gridSettingsElement;

    @FindBy(css = "a[title='Reset']")
    public WebElement resetBtnElement;

    @FindBy(css = ".grid-header-cell__label") // . means className in css selector
    public List<WebElement> headers;

    @FindBy(css = "[id^='date_selector_oro_calendar_event_form_start']")
    public WebElement startDate;
    @FindBy(css = "[id^='date_selector_oro_calendar_event_form_end']")
    public WebElement endDate;

    @FindBy(css = "[id^='time_selector_oro_calendar_event_form_start']")
    public WebElement startTime;

    @FindBy(css = "[id^='time_selector_oro_calendar_event_form_end']")
    public WebElement endTime;

    @FindBy(css = "select[class='ui-timepicker-wrapper']")
    public WebElement timeDropdown;

    @FindBy(css = "select[class='ui-datepicker-month']")
    public WebElement monthDropdown;

    @FindBy(css = "select[class='ui-datepicker-year']")
    public WebElement yearDropdown;

    @FindBy(css = "[id^='recurrence-repeat-view']")
    public WebElement repeatCheckbox;

    @FindBy(css = "span[style^='width: 292']")
    public WebElement dailyIsDefault;

    @FindBy(css = "div[data-name='recurrence-daily']>div>div>div:nth-of-type(2)>div>label>input:nth-of-type(3)")
    public WebElement dayBox;

    @FindBy(css = "span[id^='temp-validation-name-']")
    public WebElement errorMessage;

    @FindBy(css = "label[class='fields-row']+span[class='validation-failed']")//"//label[@class='fields-row']/following-sibling::span[@class='validation-failed']")
    public WebElement eNewMessage;

    @FindBy(css = "div[data-name='recurrence-daily']>div>div>div:nth-of-type(2)>div>label>input:nth-of-type(3)")
    public WebElement dailyValueLocator;

    @FindBy(xpath = "//div[@class='control-label wrap']/following-sibling::div[@data-name='recurrence-summary']//div//span[1]")
    public WebElement summaryLocator;

    @FindBy(xpath = "//div[@class='control-label wrap']/following-sibling::div[@data-name='recurrence-summary']//div//span[2]")
    public WebElement summaryLocator2;

    @FindBy(css ="div[data-name='recurrence-ends']>div>div:nth-of-type(2)>div>label>span+input")
    public WebElement afterOccurrencesValueBox;

    @FindBy(css = "div[data-name='recurrence-ends']>div>div:nth-of-type(2)>div:nth-of-type(2)>span>span>span")
    public WebElement msg1;

    @FindBy(css = "div[data-name='recurrence-ends']>div>div:nth-of-type(2)>div:nth-of-type(2)>span")
    public WebElement msg2;


    //this method will enter a value between
    public void enterRandomDays(WebElement element) {
        Random rndNum = new Random();
        int value = rndNum.ints(1,1,100).findFirst().getAsInt();
        element.click();
        element.clear();
        element.sendKeys(value + "", Keys.ENTER);
    }






    public CalendarEventsPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    public void selectGridSetting(String name, boolean yesOrNo) {
        //click on grid options
        gridSettingsElement.click();
        //create locator for grid option based on the name
        String locator = "//td//label[text()='" + name + "']/../following-sibling::td//input";
        //find element
        //you can also call Driver.get()
        WebElement gridOption = Driver.getDriver().findElement(By.xpath(locator));
        //if param yesOrNo is true, and checkbox is not selected yet
        //click on it
        //or
        //ckeckbox is selected and you want to unselect it
        if ((yesOrNo == true && !gridOption.isSelected()) || (
                yesOrNo == false && gridOption.isSelected())) {
            gridOption.click();
        }
    }

    //let's write a method
    //that will take a headerName as a parameter
    //and will try to look up for header name in the collection of headers
    //if header name was not found
    //return false
    //otherwise return true
    public boolean verifyHeaderExists(String headerNameOrColumnName) {
        for (WebElement tableHeader : headers) {
            if (tableHeader.getText().equalsIgnoreCase(headerNameOrColumnName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Simple method that can select start or end date on create calendar event page
     *
     * @param date       format MM/dd/yyy for example 12/12/2019
     * @param startOrEnd which date to click on start or end
     */
    public void selectStartOrEndDate(String date, String startOrEnd) {
        VYTrackUtils.waitUntilLoaderScreenDisappear();
        LocalDate ld = LocalDate.of(Integer.parseInt(date.substring(date.lastIndexOf("/") + 1)),
                Integer.parseInt(date.substring(0, date.indexOf("/"))),
                Integer.parseInt(date.substring(date.indexOf("/") + 1, date.lastIndexOf("/"))));

        String month = DateTimeFormatter.ofPattern("MMM").format(ld);
        int year = ld.getYear();
        int day = ld.getDayOfMonth();


        //locator for day
        String dayLocator = "//a[@class='ui-state-default' and text()='" + day + "']";

        //click on start or end date
        if (startOrEnd.equalsIgnoreCase("start")) {
            SeleniumUtils.waitForVisibility(startDate, 5);
            startDate.click();
        } else {
            SeleniumUtils.waitForVisibility(endDate, 5);
            endDate.click();
        }

        //select month
        new Select(monthDropdown).selectByVisibleText(month);


        //select year
        new Select(yearDropdown).selectByVisibleText(year + "");

        //select day
        Driver.getDriver().findElement(By.xpath(dayLocator)).click();
    }

    public void selectTomorrowDay() {
        int day = LocalDate.now().plusDays(1).getDayOfMonth();
        int month = LocalDate.now().plusDays(1).getMonth().getValue();
        VYTrackUtils.waitUntilLoaderScreenDisappear();
        startDate.click();
        Select monthSelect = new Select(monthDropdown);
        monthSelect.selectByIndex(month - 1);
        String dayLocator = "//table[@class='ui-datepicker-calendar']//a[text()='" + day + "']";
        Driver.getDriver().findElement(By.xpath(dayLocator)).click();
    }

    public void selectADay(int plusDays) {
        int day = LocalDate.now().plusDays(plusDays).getDayOfMonth();
        int month = LocalDate.now().plusDays(plusDays).getMonth().getValue();
        VYTrackUtils.waitUntilLoaderScreenDisappear();
        startDate.click();
        Select monthSelect = new Select(monthDropdown);
        monthSelect.selectByIndex(month - 1);
        String dayLocator = "//table[@class='ui-datepicker-calendar']//a[text()='" + day + "']";
        Driver.getDriver().findElement(By.xpath(dayLocator)).click();
    }

    public void selectTodaysDay() {
        int day = LocalDate.now().getDayOfMonth();
        startDate.click();
        String dayLocator = "//table[@class='ui-datepicker-calendar']//a[text()='" + day + "']";
        try {
            Driver.getDriver().findElement(By.xpath(dayLocator)).click();
        } catch (Exception e) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            Driver.getDriver().findElement(By.xpath(dayLocator)).click();
        }
    }

    public void selectStartTime(int plusHours) {
        String time = LocalDateTime.now().plusHours(plusHours).format(DateTimeFormatter.ofPattern("h:00 a"));
        VYTrackUtils.waitUntilLoaderScreenDisappear();
        String startTimeToSelect = "(//li[text()='" + time + "'])[1]";
        startTime.click();
        new WebDriverWait(Driver.getDriver(), 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(startTimeToSelect)));
        Driver.getDriver().findElement(By.xpath(startTimeToSelect)).click();
    }

    public void selectStartTime(String time) {
        VYTrackUtils.waitUntilLoaderScreenDisappear();
        String startTimeToSelect = "(//li[text()='" + time + "'])[1]";
        startTime.click();
        new WebDriverWait(Driver.getDriver(), 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(startTimeToSelect)));
        Driver.getDriver().findElement(By.xpath(startTimeToSelect)).click();
    }

    public void selectEndTime(String time) {
        VYTrackUtils.waitUntilLoaderScreenDisappear();
        String endTimeToSelect = "(//li[text()='" + time + "'])[2]";
        startTime.click();
        Driver.getDriver().findElement(By.xpath(endTimeToSelect)).click();
    }

    public long differenceBetweenStartTimeAndEndTime() {
        LocalTime actualStartTime = LocalTime.parse(startTime.getAttribute("value"), DateTimeFormatter.ofPattern("h:mm a"));
        try {
            new WebDriverWait(Driver.getDriver(), 3).until(ExpectedConditions.invisibilityOf(startTime));
        } catch (Exception e) {
            System.out.println(e);
        }
        LocalTime actualEndTime = LocalTime.parse(endTime.getAttribute("value"), DateTimeFormatter.ofPattern("h:mm a"));
        return ChronoUnit.HOURS.between(actualStartTime, actualEndTime);
    }

    public void clickOnCreateCalendarEvent() {
        VYTrackUtils.waitUntilLoaderScreenDisappear();
        createCalendarEventBtn.click();
    }
    public void clickOnRepeatCheckbox(){
        VYTrackUtils.waitUntilLoaderScreenDisappear();
        repeatCheckbox.click();
    }

    public String getStartDate() {
        return startDate.getAttribute("value");
    }

    public String getEndDate() {
        return endDate.getAttribute("value");
    }

    public String getStartTime() {
        return startTime.getAttribute("value");
    }

    public String getEndTime() {
        return endTime.getAttribute("value");
    }

}

