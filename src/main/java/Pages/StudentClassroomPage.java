package Pages;

import base.AppGenericLib;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;


public class StudentClassroomPage extends AppGenericLib {

    SoftAssert sa = new SoftAssert();
    AppiumDriver driver;
    @FindBy(xpath = "//*[@resource-id='com.teachmint.teachmint:id/title' and @text='Class10']")
    private MobileElement classNameText;
    @FindBy(xpath = "//*[@text='Join Live']")
    private WebElement joinLiveButton;
    @FindBy(xpath = "//android.widget.LinearLayout[@content-desc=\"Chat\"]/android.widget.TextView")
    private WebElement chatButton;
    @FindBy(xpath = "//android.widget.TextView[@text='Nithesh']")
    private WebElement Teacher;
    @FindBy(xpath = "//android.widget.ImageButton[@resource-id='com.teachmint.teachmint:id/add_attachment']")
    private WebElement add_attachment;
    @FindBy(xpath = "//android.widget.Button[@resource-id='com.android.permissioncontroller:id/permission_allow_button']")
    private WebElement AllowPermission;
    @FindBy(xpath = "//*[@text='Camera']")
    private WebElement Camera;
    @FindBy(id = "com.sec.android.app.camera:id/shutter_button")
    private WebElement cameraShutter;
    @FindBy(xpath = "//android.widget.ImageButton[@resource-id='com.android.camera2:id/done_button']")
    private WebElement clickForPic;
    @FindBy(xpath = "//*[@resource-id='com.teachmint.teachmint:id/send']")
    private WebElement sendPicButton;
    @FindBy(xpath = "(//*[@resource-id='com.teachmint.teachmint:id/filename'])[last()]")
    private WebElement sentPicture;
    @FindBy(id = "com.sec.android.app.camera:id/done_button")
    private WebElement okButton;
    @FindBy(id = "com.teachmint.teachmint:id/send")
    private WebElement sendButton;
    @FindBy(id = "com.teachmint.teachmint:id/add_attachment")
    private WebElement attachmentButton;
    @FindBy(xpath = "//*[@text='Learn']")
    private WebElement learnButton;
    @FindBy(xpath = "//*[@text='NCERT']")
    private WebElement ncertCourseButton;
    @FindBy(xpath = "//*[@text='Class 1']")
    private WebElement class1Button;
    @FindBy(xpath = "//*[@text='Maths']")
    private WebElement mathSubjectButton;
    @FindBy(xpath = "//*[@text='Addition']")
    private WebElement additionTopicButton;
    @FindBy(xpath = "(//*[@text='Auto-render test'])[5]")
    private WebElement optionDButton;
    @FindBy(xpath = "(//*[@text='Auto-render test'])[4]")
    private WebElement optionCButton;
    @FindBy(xpath = "//*[@text='Submit']")
    private WebElement submitButton;
    @FindBy(xpath = "//*[@text='Next >>']")
    private WebElement nextButton;
    @FindBy(xpath = "//*[@text='Wrong' or @text='Correct']")
    private WebElement correctOrWrongText;
    @FindBy(xpath = "//*[@text='Addition']")
    private WebElement addition;

    public StudentClassroomPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("click on chat and select teacher")
    public void clickOnChatButtonAndSelectTeacher() {
        clickOnElement(chatButton);
        clickOnElement(Teacher);
    }

    @Step
    public void addAttachment() throws InterruptedException {
        clickOnElement(add_attachment);
        try {
            awaitForElement(driver, AllowPermission);
            clickOnElement(AllowPermission);
            awaitForElement(driver, Camera);
            clickOnElement(Camera);
            clickOnElement(cameraShutter);
            clickOnElement(clickForPic);
            awaitForElement(driver, sendPicButton);
            clickOnElement(sendPicButton);
        } catch (Exception e) {
            awaitForElement(driver, Camera);
            customWait(driver, Camera);
            clickOnElement(Camera);
            clickOnElement(cameraShutter);
            clickOnElement(clickForPic);
            awaitForElement(driver, sendPicButton);
            clickOnElement(sendPicButton);
        }

    }

    @Step("tap on attachment")
    public void clickOnAttachementButton() {
        clickOnElement(attachmentButton);
    }

    @Step("select camara options")
    public void chooseCamara() {
        try {
            clickOnElement(AllowPermission);
            clickOnElement(Camera);
        } catch (Exception e) {
            clickOnElement(Camera);
        }
    }

    @Step("click the photo and click on ok")
    public void clickPhoto() {
        clickOnElement(cameraShutter);
        //awaitForElement(studentDriver,okButton);
        clickOnElement(okButton);
    }

    @Step("tap on send button")
    public void clickOnSendButton() {
        clickOnElement(sendButton);
    }

    @Step("Tap on learn tab")
    public void clickOnLearn() {
        clickOnElement(learnButton);
    }

    @Step("select course and class and subject")
    public void selectCourseAndClass(String course, String className) {
        clickOnElement(ncertCourseButton);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//*[@text='" + course + "']")).click();
        driver.findElement(By.xpath("//*[@text='" + className + "']")).click();
        clickOnElement(mathSubjectButton);
    }

    @Step("Selecting topic {topicName}")
    public void selectTopic(String topicName) {
        //driver.findElement(By.xpath("//*[@text='"+topicName+"']")).click();
        clickOnElement(addition);
    }

    @Step("choose first answer {optionForFirstAnswer} second answer {optionForSecondQuestion}")
    public void chooseAnswersAndVerify(char optionForFirstAnswer, char optionForSecondQuestion) {
        awaitForElement(driver, driver.findElement(By.xpath("//*[@text='" + optionForFirstAnswer + "']")));
        driver.findElement(By.xpath("//*[@text='" + optionForFirstAnswer + "']")).click();
        scrollToElement(driver);
        clickOnElement(submitButton);
        softAssert.assertTrue((correctOrWrongText.getText().contains("Correct") ||
                correctOrWrongText.getText().contains("Correct")), "answers not matching");
        clickOnElement(nextButton);
        driver.findElement(By.xpath("//*[@text='" + optionForSecondQuestion + "']")).click();
        scrollToElement(driver);
        clickOnElement(submitButton);
        softAssert.assertTrue((correctOrWrongText.getText().contains("Correct") ||
                correctOrWrongText.getText().contains("Wrong")), "answers not matching");
        softAssert.assertAll();
    }

//    @Step(" Student can select cource, class and subject")
//    public void selectCourseClassSubject() throws InterruptedException {
//
//        clickOnElement(learnButton);
//        Thread.sleep(1000);
//        clickOnElement(ncertCourseButton);
//        clickOnElement(class1Button);
//        clickOnElement(mathSubjectButton);
//
//        clickOnElement(additionTopicButton);
//        clickOnElement(optionDButton);
//        clickOnElement(submitButton);
//        clickOnElement( nextButton);
//
//        softAssert.assertTrue(correctButton.getText().contains("Correct"),"There is an error adding the slots.");
//        softAssert.assertAll();
//
//        clickOnElement(optionCButton);
//        clickOnElement(submitButton);
//        softAssert.assertTrue(correctButton.getText().contains("Wrong"),"There is an error adding the slots.");
//        softAssert.assertAll();
//        }

    @Step("send image and verify")
    public String verifySentPicture() {
        awaitForElement(driver, sentPicture);
        return sentPicture.getText().trim();
    }

    @Step("Tap on your classroom")
    public void tapOnClassroom() {
        clickOnElement(classNameText);
    }

    @Step("Tap on join live button")
    public void tapOnJoinLiveButton() {
        clickOnElement(joinLiveButton);
    }


}

