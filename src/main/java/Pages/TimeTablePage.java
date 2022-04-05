package Pages;

import base.AppGenericLib;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

public class TimeTablePage extends AppGenericLib {
    SoftAssert sa = new SoftAssert();
    AppiumDriver driver;
    @FindBy(xpath = "//*[@text='Add Timetable' or @text='Edit Timetable']")
    private WebElement editButton;
    @FindBy(xpath = "//*[@text='Sunday']/following-sibling::*[@resource-id='com.teachmint.teachmint:id/switch_button']")
    private WebElement sundaySwitchButton;
    @FindBy(xpath = "(//*[@resource-id='com.teachmint.teachmint:id/time_from'])[1]")
    private WebElement slot1FromButton;
    @FindBy(xpath = "(//*[@text='From'])[2]")
    private WebElement slot2FromButton;
    @FindBy(xpath = "(//*[@resource-id='com.teachmint.teachmint:id/time_to'])[1]")
    private WebElement slot1toButton;
    @FindBy(xpath = "(//*[@text='To'])[2]")
    private WebElement slot2toButton;
    @FindBy(xpath = "//*[@text='Sunday']//following-sibling::*[@resource-id='com.teachmint.teachmint:id/switch_button']")
    private WebElement sundayToggleButton;
    @FindBy(xpath = "(//*[@resource-id='com.teachmint.teachmint:id/start_container'])[1]")
    private WebElement from1;
    @FindBy(xpath = "(//*[@resource-id='com.teachmint.teachmint:id/start_container'])[2]")
    private WebElement from2;
    @FindBy(xpath = "(//*[@resource-id='com.teachmint.teachmint:id/end_container'])[1]")
    private WebElement to1;
    @FindBy(xpath = "(//*[@resource-id='com.teachmint.teachmint:id/end_container'])[2]")
    private WebElement to2;
    @FindBy(xpath = "//*[@resource-id='android:id/hours']")
    private WebElement hoursField;
    @FindBy(xpath = "//*[@resource-id='android:id/minutes']")
    private WebElement minutesField;
    @FindBy(xpath = "//*[@text='OK']")
    private WebElement okButton;
    @FindBy(xpath = "//*[@text='Update Classroom']")
    private WebElement updateClassroomButton;
    @FindBy(xpath = "//*[@resource-id='com.teachmint.teachmint:id/add_timing_slot_button']")
    private WebElement plusButton;
    @FindBy(xpath = "//*[@resource-id='com.teachmint.teachmint:id/second']")
    private WebElement slotsButton;
    @FindBy(xpath = "//*[@text='PM']")
    private WebElement pmButton;
    @FindBy(xpath = "//*[@content-desc='Switch to text input mode for the time input.']")
    private WebElement switchToKeyBoardButton;
    @FindBy(xpath = "//*[@resource-id='android:id/input_hour']")
    private WebElement inputHour;
    @FindBy(xpath = "//*[@resource-id='android:id/input_minute']")
    private WebElement inputMinute;
    @FindBy(xpath = "//*[@resource-id='android:id/am_pm_spinner']")
    private WebElement dropdown;
    @FindBy(id = "com.teachmint.teachmint:id/second")
    private WebElement scheduledTimeText;

    public TimeTablePage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Click on edit button")
    public void clickOnEdit() {
        clickOnElement(editButton);
    }

    @Step("Tap on sunday switch button")
    public void toggleOnSundayTimeTable() {
        try {
            plusButton.click();
        } catch (Exception e) {
            clickOnElement(sundaySwitchButton);
        }
    }

    @Step("Add first slot in the timetable")
    public void addTimetableForSlot1(String fromHours, String toMinutes, String toHours1, String toMinutes1) {
        scrollToElement(driver);
        clickOnElement(from1);
        clickOnElement(switchToKeyBoardButton);
        clickOnElement(inputHour);
        type(inputHour, fromHours);
        clickOnElement(inputMinute);
        type(inputMinute, toMinutes);
        clickOnElement(okButton);
        clickOnElement(to1);
        clickOnElement(switchToKeyBoardButton);
        clickOnElement(inputHour);
        type(inputHour, toHours1);
        clickOnElement(inputMinute);
        type(inputMinute, toMinutes1);
        clickOnElement(okButton);
    }


    @Step("Click on plus button")
    public void clickOnPlus() {
        clickOnElement(plusButton);
    }

    @Step("Choose PM")
    public void clickOnPM() {
        clickOnElement(pmButton);
    }

    @Step("Add second slot in the timetable")
    public void addTimetableForSlot2(String fromHours, String fromMinutes, String toHours, String toMinutes) {
        scrollToElement(driver);
        clickOnElement(from2);
        clickOnElement(switchToKeyBoardButton);
        clickOnElement(inputHour);
        type(inputHour, fromHours);
        clickOnElement(inputMinute);
        type(inputMinute, fromMinutes);
        clickOnElement(dropdown);
        clickOnPM();
        clickOnElement(okButton);
        clickOnElement(to2);
        clickOnElement(switchToKeyBoardButton);
        clickOnElement(inputHour);
        type(inputHour, toHours);
        clickOnElement(inputMinute);
        type(inputMinute, toMinutes);
        clickOnElement(dropdown);
        clickOnPM();
        clickOnElement(okButton);
    }

    public void verifyTheTimeSlot(String timeSlot1, String timeSlot2) {
        clickOnElement(updateClassroomButton);
        System.out.println(scheduledTimeText.getText().trim());
        String timeSlot = scheduledTimeText.getText().trim();
        softAssert.assertTrue((timeSlot.contains(timeSlot1) || timeSlot.contains(timeSlot2)), "Time slots are not matching");
        clickOnElement(editButton);
        clickOnElement(sundaySwitchButton);
        clickOnElement(updateClassroomButton);
        softAssert.assertAll();
    }

    @Step("Verify the added timetables")
    public void verifyTimetableSlots(String slot1FromHours, String slot1fFromMinutes, String slot1ToHours, String slot1ToMinutes, String slot2FromHours, String slot2FromMinutes, String slot2ToHours, String slot2ToMinutes) {
        softAssert.assertTrue(slotsButton.getText().trim().contains(slot1FromHours + ":" + slot1fFromMinutes + " AM-" + slot1ToHours + ":" + slot1ToMinutes + " AM\n" + slot2FromHours + ":" + slot2FromMinutes + " PM-" + slot2ToHours + ":" + slot2ToMinutes + " PM"), "There is an error adding the slots.");
        softAssert.assertAll();
    }

    @Step("Verify the added timetables")
    public void clickOnToggleButton() {
        awaitForElement(driver, editButton);
        clickOnElement(editButton);
        clickOnElement(sundayToggleButton);
    }
}
