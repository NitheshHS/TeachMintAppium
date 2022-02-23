package Pages;

import base.WebActions;
import com.sun.jna.platform.win32.Wevtapi;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import javafx.scene.layout.StackPane;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class ClassRoomPage extends WebActions {

    AppiumDriver driver;
    SoftAssert sa = new SoftAssert();

    public ClassRoomPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    @FindBy(xpath = "//*[@text='Go Live']")
    private WebElement goLiveLink;

    @FindBy(id = "com.android.permissioncontroller:id/permission_allow_foreground_only_button")
    private WebElement allowAccess;

    @FindBy(id = "com.teachmint.teachmint:id/mic_icon")
    private WebElement micIcon;

    @FindBy(id = "com.teachmint.teachmint:id/video_icon")
    private WebElement videoIcon;

    @FindBy(id = "com.teachmint.teachmint:id/go_live_button")
    private WebElement goLiveBtn;

    @FindBy(id = "com.teachmint.teachmint:id/recording_icon")
    private WebElement recordIcon;

    @FindBy(xpath = "//*[@resource-id='com.teachmint.teachmint:id/mic_layout']/*[@resource-id='com.teachmint.teachmint:id/mic']")
    private WebElement micIconInMeeting;

    @FindBy(xpath = "//*[@resource-id='com.teachmint.teachmint:id/video']")
    private WebElement videoIconInMeeting;

    @FindBy(xpath = "//*[@resource-id='com.teachmint.teachmint:id/zoom_touch_view']")
    private WebElement profileIcon;

    @FindBy(id = "com.teachmint.teachmint:id/options_menu")
    private WebElement moreButton;

    @FindBy(xpath = "//*[@text='Live Polls']")
    private WebElement livePollOption;

    @FindBy(id="com.teachmint.teachmint:id/duration_30")
    private WebElement pollDuration30;

    @FindBy(id="com.teachmint.teachmint:id/option_a")
    private WebElement pollOptionA;

    @FindBy(id="com.teachmint.teachmint:id/launch_poll_button")
    private WebElement launchPollButton;

    @FindBy(id="com.teachmint.teachmint:id/stop_poll_button")
    private WebElement stopPollButton;

    @FindBy(xpath = "//*[@text='YouTube Stream']")
    private WebElement youtubeStreamOption;

    @FindBy(xpath = "//android.widget.EditText")
    private WebElement youtubeStreamingKeyTextField;

    @FindBy(id="com.teachmint.teachmint:id/start_youtube_live_stream_button")
    private WebElement startLiveStreamButton;

    @FindBy(id="com.teachmint.teachmint:id/info_bar")
    private WebElement youtubeStreamErrorMsg;

    @FindBy(xpath = "//*[@text='Student Controls']")
    private WebElement studentControlsOption;

    @FindBy(id="com.teachmint.teachmint:id/student_chat_switch")
    private WebElement chatSwitch;

    @FindBy(id="com.teachmint.teachmint:id/chat_button")
    private WebElement chatOption;

    @FindBy(id="com.teachmint.teachmint:id/chatbox_message")
    private WebElement chatBoxTextField;

    @FindBy(id="com.teachmint.teachmint:id/send_chat")
    private WebElement sendChatButton;

    @FindBy(id="com.teachmint.teachmint:id/screen_share")
    private WebElement shareScreenOption;

    @FindBy(xpath = "//*[@text='Share Screen' or @resource-id='Share Screen']")
    private WebElement shareScreen;

    @FindBy(xpath = "(//*[@text='Homework'])[2]")
    private WebElement homeworkLink;

    @FindBy(id="com.teachmint.teachmint:id/radio_btn_mcq")
    private WebElement mcqRadioButton;

    @FindBy(id="com.teachmint.teachmint:id/click_save")
    private WebElement continueButton;

    public void clickOnGoLive() {
        //goLiveLink.click();
        awaitForElement(driver,goLiveLink);
        clickOnElement(goLiveLink);
    }

    public void clickOnGoLivebutton(){
        awaitForElement(driver,goLiveBtn);
        clickOnElement(goLiveBtn);
    }

    public void clickOnGoLiveBtn() throws IOException {
        File micIconBeforeMeeting = null;
        File videoIconBeforeMeeting = null;
        for (int i = 0; i < 2; i++) {
            try {
                awaitForElement(driver, allowAccess);
                allowAccess.click();
                try {
                    awaitForElement(driver, micIcon);
                    // micIconBeforeMeeting=micIcon.getScreenshotAs(OutputType.FILE);
                    micIconBeforeMeeting = takeScreenshot(micIconInMeeting, "micIconBeforeMeeting");
                    videoIconBeforeMeeting = takeScreenshot(videoIconInMeeting, "videoIconBeforeMeeting");
                    goLiveBtn.click();
                    break;
                } catch (Exception e) {

                }
            } catch (Exception e) {
                //awaitForElement(driver, micIcon);
                //micIconBeforeMeeting=micIcon.getScreenshotAs(OutputType.FILE);
                awaitForElement(driver,micIconInMeeting);
                micIconBeforeMeeting = takeScreenshot(micIcon, "micIconBeforeMeeting");
                awaitForElement(driver,videoIcon);
                videoIconBeforeMeeting = takeScreenshot(videoIcon, "videoIconBeforeMeeting");
                goLiveBtn.click();
                break;
            }
        }
//        double micPer = compareImage(micIconBeforeMeeting, new File("./src/test/Images/mute_mic_icon_before_meeting.png"));
//        sa.assertTrue(micPer >= 60, "Images are not matching");
//        double videoPer = compareImage(videoIconBeforeMeeting, new File("./src/test/Images/video_Icon_Before_Meeting.png"));
//        sa.assertTrue(videoPer >= 60, "video icon's are not matching");
        sa.assertAll();
    }

    public void tapOnMeetingProfile() throws IOException {
        File miciconmeeting = null;
        File videoIconMeeting = null;
        for (int tapCount = 0; tapCount <= 60; tapCount++) {
            try {
                // miciconmeeting = micIconInMeeting.getScreenshotAs(OutputType.FILE);
                miciconmeeting = takeScreenshot(micIconInMeeting, "MikeIconInLive");
                videoIconMeeting = takeScreenshot(videoIconInMeeting, "videoIconInLive");
                break;
            } catch (Exception e) {
                tapOnElement(driver, (MobileElement) profileIcon);
            }
        }
//        double micPer = compareImage(miciconmeeting, new File("./src/test/Images/mute_icon_in_live.png"));
//        sa.assertTrue(micPer >= 60, "Mic icon's are not matching");
//        double videoPer = compareImage(videoIconMeeting, new File("./src/test/Images/video_Icon_In_Live.png"));
//        sa.assertTrue(videoPer >= 60, "video icon's are not matching");
        sa.assertAll();
    }

    public void tapOnMoreButton() {
        for (int tapCount = 1; tapCount <= 60; tapCount++) {
            try {
                moreButton.click();
                break;
            } catch (Exception e) {
                tapOnElement(driver,(MobileElement) profileIcon);
            }
        }
    }

    public void startLivePoll() {
        clickOnElement(livePollOption);
        clickOnElement(pollDuration30);
        clickOnElement(pollOptionA);
        clickOnElement(launchPollButton);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stopLivePoll(){
        sa.assertTrue(stopPollButton.isDisplayed(), "Stop poll button not displayed");
        clickOnElement(stopPollButton);
        sa.assertAll();
    }

    public void startYoutubeStreaming(String youtubeStreamingId,String infoMessage){
        awaitForElement(driver, youtubeStreamOption);
        clickOnElement(youtubeStreamOption);
        awaitForElement(driver,youtubeStreamingKeyTextField);
        type(youtubeStreamingKeyTextField,youtubeStreamingId);
        awaitForElement(driver,startLiveStreamButton);
        clickOnElement(startLiveStreamButton);
        try{
            //start youtube app
        }catch(Exception e){
            awaitForElement(driver,youtubeStreamErrorMsg);
            sa.assertEquals(youtubeStreamErrorMsg.getText(),infoMessage,"info messages are not matching");
        }
        sa.assertAll();
    }

    public void tapOnStudentControl(){
        clickOnElement(studentControlsOption);
    }

    public void tapOnChatSwitch(){
        clickOnElement(chatSwitch);
    }

    public void typeChat(String chatMessage){
        awaitForElement(driver,chatOption);
        clickOnElement(chatOption);
        awaitForElement(driver,chatBoxTextField);
        type(chatBoxTextField,chatMessage);
        clickOnElement(sendChatButton);
        awaitForElement(driver,driver.findElement(By.xpath("//*[@resource-id='com.teachmint.teachmint:id/body' and @text='" + chatMessage + "']")));
        String msgText = driver.findElement(By.xpath("//*[@resource-id='com.teachmint.teachmint:id/body' and @text='" + chatMessage + "']")).getText();
        sa.assertEquals(chatMessage, msgText,"chat message and displayed chat not matching");
        sa.assertAll();
    }

    public void tapOnShareScreenOption() {
        for (int tapCount = 0; tapCount <= 60; tapCount++) {
            try {
                clickOnElement(shareScreenOption);
                break;
            } catch (Exception e) {
                tapOnElement(driver, (MobileElement) profileIcon);
            }
        }
    }
    public void clickOnShareScreen(){
        awaitForElement(driver,shareScreen);
        clickOnElement(shareScreen);
    }

    public void clickOnHomeWork(){
        clickOnElement(homeworkLink);
    }

    public void clickOnMCQ(){
        clickOnElement(mcqRadioButton);
    }

    public void clickOnContinueButton(){
        clickOnElement(continueButton);
    }
}



//    public void comapareImage() throws IOException {
//       // awaitForElement(driver,micIcon);
//        File micIconBeforeMeeting=micIcon.getScreenshotAs(OutputType.FILE);
//       // awaitForElement(driver,micIconInMeeting);
//        File miciconmeeting = null;
//        for(int tapCount = 0; tapCount<=60;tapCount++) {
//            try {
//                miciconmeeting = micIconInMeeting.getScreenshotAs(OutputType.FILE);
//            } catch (Exception e) {
//                tapOnElement(driver, (MobileElement) micIconInMeeting);
//            }
//        }
//       compareImage(micIconBeforeMeeting,miciconmeeting);
//    }



