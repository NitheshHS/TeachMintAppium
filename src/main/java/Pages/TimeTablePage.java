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
    SoftAssert sa=new SoftAssert();
    AppiumDriver driver;

    public TimeTablePage(AppiumDriver driver){
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @FindBy(xpath="//*[@resource-id='com.teachmint.teachmint:id/liveClassDetailsLayout']//android.widget.ImageView'")
    private WebElement editButton;

    @FindBy(xpath="(//*[@resource-id='com.teachmint.teachmint:id/time_from'])[1]")
    private WebElement slot1FromButton;

    @FindBy(xpath="(//*[@resource-id='com.teachmint.teachmint:id/time_from'])[1]")
    private WebElement slot2FromButton;

    @FindBy(xpath="(//*[@resource-id='com.teachmint.teachmint:id/time_to'])[1]")
    private WebElement slot1toButton;

    @FindBy(xpath="(//*[@resource-id='com.teachmint.teachmint:id/time_to'])[2]")
    private WebElement slot2toButton;

    @FindBy(xpath="//*[@text='Sunday']//following-sibling::*[@resource-id='com.teachmint.teachmint:id/switch_button']")
    private WebElement sundayToggleButton;

    @FindBy(xpath="//*[@resource-id='android:id/hours']")
    private WebElement hoursField;

    @FindBy(xpath="//*[@resource-id='android:id/minutes']")
    private WebElement minutesField;

    @FindBy(xpath="//*[@text='OK']")
    private WebElement okButton;

    @FindBy(xpath="//*[@text='Update Classroom']")
    private WebElement updateClassroomButton;

    @FindBy(xpath="//*[@resource-id='com.teachmint.teachmint:id/add_timing_slot_button']")
    private WebElement plusButton;

    @FindBy(xpath="//*[@resource-id='com.teachmint.teachmint:id/second']")
    private WebElement verifySlotButton;


    @Step("Add timetable for a class")
    public void addTimetable()
    {
        clickOnElement(editButton);
        clickOnElement(slot1FromButton);
        type(hoursField,"7");
        type(minutesField,"30");
        clickOnElement(okButton);

        clickOnElement(slot1toButton);
        type(hoursField,"7");
        type(minutesField,"30



    }
    }
