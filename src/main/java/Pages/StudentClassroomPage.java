package Pages;

import base.AppGenericLib;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.sikuli.script.App;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import javax.xml.xpath.XPath;

public class StudentClassroomPage extends AppGenericLib {
    SoftAssert sa=new SoftAssert();
    AppiumDriver driver;

    public StudentClassroomPage(AppiumDriver driver) {
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @FindBy(xpath = "//android.widget.LinearLayout[@content-desc=\"Chat\"]/android.widget.TextView")
    private WebElement chatButton;
    @FindBy(xpath = "//android.widget.TextView[@text='Nithesh']")
    private WebElement Teacher;

    @FindBy(xpath = "//android.widget.ImageButton[@resource-id='com.teachmint.teachmint:id/add_attachment']")
    private WebElement add_attachment;

    @FindBy(xpath = "//android.widget.Button[@resource-id='com.android.permissioncontroller:id/permission_allow_button']")
    private WebElement AllowPermission;

    @FindBy(xpath = "//android.widget.TextView[@text='Camera']")
    private WebElement Camera;

    @FindBy(xpath = "//android.widget.ImageView[@resource-id='com.android.camera2:id/shutter_button']")
    private WebElement cameraShutter;
    @FindBy(xpath = "//android.widget.ImageButton[@resource-id='com.android.camera2:id/done_button']")
    private WebElement clickForPic;

    @FindBy(xpath = "//*[@resource-id='com.teachmint.teachmint:id/send']")
    private WebElement sendPicButton;

    @FindBy(xpath = "//android.widget.TextView[contains(@text,'.jpeg')]")
    private WebElement sentPicture;



    @Step
    public void clickOnChatButtonAndSelectTeacher()
    {
        clickOnElement(chatButton);
        clickOnElement(Teacher);
    }
    @Step
    public void addAttachment() throws InterruptedException {
        clickOnElement(add_attachment);
        try{
            awaitForElement(driver,AllowPermission);
            clickOnElement(AllowPermission);
            awaitForElement(driver,Camera);
            clickOnElement(Camera);
            clickOnElement(cameraShutter);
            clickOnElement(clickForPic);
            awaitForElement(driver,sendPicButton);
            clickOnElement(sendPicButton);
        }
        catch (Exception e)
        {
            awaitForElement(driver,Camera);
            custmWait(driver,Camera);
            clickOnElement(Camera);
            clickOnElement(cameraShutter);
            clickOnElement(clickForPic);
            awaitForElement(driver,sendPicButton);
            clickOnElement(sendPicButton);
        }

    }
    public void verifySentPicture(){
        awaitForElement(driver,sentPicture);
        sa.assertTrue(sentPicture.getText().contains(".jpeg"),"pic is not there");
    }



}