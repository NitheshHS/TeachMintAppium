package Pages;

import base.AppGenericLib;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;

public class ClassRoomPage extends AppGenericLib {

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

    @FindBy(xpath = "//*[@resource-id='com.teachmint.teachmint:id/options_menu' or @text='More']")
    private WebElement moreButton;

    @FindBy(xpath = "//*[@text='Live Polls']")
    private WebElement livePollOption;

    @FindBy(id="com.teachmint.teachmint:id/duration_30")
    private WebElement pollDuration30;

    @FindBy(id="com.teachmint.teachmint:id/duration_60")
    private WebElement pollDuration60;

    @FindBy(id="com.teachmint.teachmint:id/duration_90")
    private WebElement pollDuration90;

    @FindBy(id="com.teachmint.teachmint:id/option_a")
    private WebElement pollOptionA;

    @FindBy(id="com.teachmint.teachmint:id/option_b")
    private WebElement pollOptionB;

    @FindBy(id="com.teachmint.teachmint:id/option_c")
    private WebElement pollOptionC;

    @FindBy(id="com.teachmint.teachmint:id/option_d")
    private WebElement pollOptionD;

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

    @Step("Tapping on go live link in class room page")
    public void clickOnGoLive() {
        //goLiveLink.click();
        awaitForElement(driver,goLiveLink);
        clickOnElement(goLiveLink);
    }

    @Step("tapping on go live button ")
    public void clickOnGoLivebutton(){
       // awaitForElement(driver,goLiveBtn);
        clickOnElement(goLiveBtn);
    }

    @Step("tap on go live button")
    public void clickOnGoLiveBtn() throws IOException {
        File micIconBeforeMeeting = null;
        File videoIconBeforeMeeting = null;
        for (int i = 0; i < 2; i++) {
            try {
                awaitForElement(driver, allowAccess);
                allowAccess.click();
                try {
                    awaitForElement(driver, micIcon);
                    micIconBeforeMeeting=micIcon.getScreenshotAs(OutputType.FILE);
                    micIconBeforeMeeting = takeScreenshot(micIconInMeeting, "micIconBeforeMeeting");
                    videoIconBeforeMeeting = takeScreenshot(videoIconInMeeting, "videoIconBeforeMeeting");
                    goLiveBtn.click();
                    break;
                } catch (Exception e) {

                }
            } catch (Exception e) {
                awaitForElement(driver, micIcon);
                micIconBeforeMeeting=micIcon.getScreenshotAs(OutputType.FILE);
                awaitForElement(driver,micIconInMeeting);
                micIconBeforeMeeting = takeScreenshot(micIcon, "micIconBeforeMeeting");
                awaitForElement(driver,videoIcon);
                videoIconBeforeMeeting = takeScreenshot(videoIcon, "videoIconBeforeMeeting");
                goLiveBtn.click();
                break;
            }
        }
        double micPer = compareImage(micIconBeforeMeeting, new File("./src/test/Images/mute_mic_icon_before_meeting.png"));
        sa.assertTrue(micPer >= 60, "Images are not matching");
        double videoPer = compareImage(videoIconBeforeMeeting, new File("./src/test/Images/video_Icon_Before_Meeting.png"));
        sa.assertTrue(videoPer >= 60, "video icon's are not matching");
        sa.assertAll();
    }

    @Step(" Tap on meeting profile")
    public void tapOnMeetingProfile() throws IOException {
        File miciconmeeting = null;
        File videoIconMeeting = null;
        for (int tapCount = 0; tapCount <= 60; tapCount++) {
            try {
                miciconmeeting = micIconInMeeting.getScreenshotAs(OutputType.FILE);
                miciconmeeting = takeScreenshot(micIconInMeeting, "MikeIconInLive");
                videoIconMeeting = takeScreenshot(videoIconInMeeting, "videoIconInLive");
                break;
            } catch (Exception e) {
                tapOnElement(driver, (MobileElement) profileIcon);
            }
        }
        double micPer = compareImage(miciconmeeting, new File("./src/test/Images/mute_icon_in_live.png"));
        sa.assertTrue(micPer >= 60, "Mic icon's are not matching");
        double videoPer = compareImage(videoIconMeeting, new File("./src/test/Images/video_Icon_In_Live.png"));
        sa.assertTrue(videoPer >= 60, "video icon's are not matching");
        sa.assertAll();
    }

    @Step("Tap on more button in live class")
    public void tapOnMoreButton() {
        for (int tapCount = 1; tapCount <= 60; tapCount++) {
            try {
                awaitForElement(driver,moreButton);
                clickOnElement(moreButton);
                break;
            } catch (Exception e) {
                tapOnElement(driver,(MobileElement) profileIcon);
            }
        }
    }

    @Step("starting the student poll")
    public void startLivePoll(String pollDurationInSeconds, String pollOption) {
        clickOnElement(livePollOption);
        if(pollDurationInSeconds.equalsIgnoreCase("30")){
        clickOnElement(pollDuration30);}
        else if(pollDurationInSeconds.equalsIgnoreCase("40")){
            clickOnElement(pollDuration60);
        }
        else if(pollDurationInSeconds.equalsIgnoreCase("90")){
            clickOnElement(pollDuration90);
        }
        if(pollOption.equalsIgnoreCase("A")){
            clickOnElement(pollOptionA);
        }
        else if(pollOption.equalsIgnoreCase("B")){
            clickOnElement(pollOptionB);
        }
        else if(pollOption.equalsIgnoreCase("C")){
            clickOnElement(pollOptionC);
        }
        else if(pollOption.equalsIgnoreCase("D")){
            clickOnElement(pollOptionD);
        }
        clickOnElement(launchPollButton);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("Stoping the live student poll")
    public void stopLivePoll(){
        sa.assertTrue(stopPollButton.isDisplayed(), "Stop poll button not displayed");
        clickOnElement(stopPollButton);
        sa.assertAll();
    }

    @Step("starting youtube live stream with key {youtubeStreamingId}")
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

    @Step("Tap on student control option in live class")
    public void tapOnStudentControl(){
        clickOnElement(studentControlsOption);
    }

    @Step("Tapping on chat switch")
    public void tapOnChatSwitch(){
        clickOnElement(chatSwitch);
    }

    @Step("Sending a chat message {chatMessage}")
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

    @Step("Tap on share screen option")
    public void tapOnShareScreenOption() {
        for (int tapCount = 0; tapCount <= 60; tapCount++) {
            try {
                shareScreenOption.click();
              //  clickOnElement(shareScreenOption);
                break;
            } catch (Exception e) {
               tapOnElement(driver,(AndroidElement) profileIcon);
            }
        }
    }

    @Step("click on share screen")
    public void clickOnShareScreen(){
        awaitForElement(driver,shareScreen);
        clickOnElement(shareScreen);
    }

    @Step("click on home work ")
    public void clickOnHomeWork(){
        clickOnElement(homeworkLink);
    }

    @Step("Tap on MCQ")
    public void clickOnMCQ(){
        clickOnElement(mcqRadioButton);
    }

    @Step("tap on continue button")
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



