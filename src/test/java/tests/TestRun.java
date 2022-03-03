package tests;

import Pages.ClassRoomPage;
import Pages.HomeWorkPage;
import Pages.LandingPage;
import base.BaseTest;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * author : Nithesh
 */
public class TestRun extends BaseTest {
    /**
     *
     * @throws InterruptedException
     * @throws IOException
     */
    @Test(enabled = false)
    public void TC_E_001_ValidateTheTeacherIsAbleToStartTheLiveClassTest() throws InterruptedException, IOException {
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickOnClassRoom();
        ClassRoomPage classRoomPage = new ClassRoomPage(driver);
        classRoomPage.clickOnGoLive();
        classRoomPage.clickOnGoLiveBtn();
        classRoomPage.tapOnMeetingProfile();
    }

    @Description("TC_E_008_ValidateIfTheTeacherIsAbleToCreateLivePollTest")
    @Test(description = "Validate If The Teacher Is Able To Create LivePoll")
    public void TC_E_008_ValidateIfTheTeacherIsAbleToCreateLivePollTest() throws IOException {
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickOnClassRoom();
        ClassRoomPage classRoomPage = new ClassRoomPage(driver);
        classRoomPage.clickOnGoLive();
        classRoomPage.clickOnGoLivebutton();
        // classRoomPage.tapOnMeetingProfile();
        classRoomPage.tapOnMoreButton();
        classRoomPage.startLivePoll();
        classRoomPage.stopLivePoll();
    }

    @Test(enabled = false)
    public void TC_E_005_ValidateNetworkSwitchInTeacherSideDuringLiveClassTest() throws IOException {
       // ExtentManager.testName("TC_E_005_ValidateNetworkSwitchInTeacherSideDuringLiveClassTest","Nithesh");
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickOnClassRoom();
        ClassRoomPage classRoomPage = new ClassRoomPage(driver);
        classRoomPage.clickOnGoLive();
        classRoomPage.clickOnGoLivebutton();
        openNotification(driver);
        turnOffMobileDataAndWifi(driver, "adb shell svc wifi disable");
        pressNavigationBack(driver);
    }

    @Description("TC_E_004_ValidateTheTeacherIsAbleToVideoStreamInLiveClass")
    @Test
    public void TC_E_004_ValidateTheTeacherIsAbleToVideoStreamInLiveClassTest() throws IOException {
       // ExtentManager.testName("TC_E_004_ValidateTheTeacherIsAbleToVideoStreamInLiveClassTest","Nithesh");
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickOnClassRoom();
        ClassRoomPage classRoomPage = new ClassRoomPage(driver);
        classRoomPage.clickOnGoLive();

        classRoomPage.clickOnVideoIconAndVerify("Video on");
        classRoomPage.clickOnGoLivebutton();

    }

    @Description("TC_E_009_ValidateIfTheTeacherIsAbleToChatWithChatDisabledForStudents")
    @Test
    public void TC_E_009_ValidateIfTheTeacherIsAbleToChatWithChatDisabledForStudents() throws IOException {
       // ExtentManager.testName("TC_E_009_ValidateIfTheTeacherIsAbleToChatWithChatDisabledForStudents","Nithesh");
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickOnClassRoom();
        ClassRoomPage classRoomPage = new ClassRoomPage(driver);
        classRoomPage.clickOnGoLive();
        classRoomPage.clickOnGoLivebutton();
        classRoomPage.tapOnMoreButton();
        classRoomPage.tapOnStudentControl();
        classRoomPage.tapOnChatSwitch();
        pressNavigationBack(driver);
        classRoomPage.typeChat("Hello");
    }

    @Description("TC_E_002_ValidateTheTeacherIsAbleToShareScreenInLiveClass")
    @Test
    public void TC_E_002_ValidateTheTeacherIsAbleToShareScreenInLiveClass() {
       // ExtentManager.testName("TC_E_002_ValidateTheTeacherIsAbleToShareScreenInLiveClass","Nithesh");
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickOnClassRoom();
        ClassRoomPage classRoomPage = new ClassRoomPage(driver);
        classRoomPage.clickOnGoLive();
        classRoomPage.clickOnGoLivebutton();
        classRoomPage.tapOnShareScreenOption();
        classRoomPage.clickOnShareScreen();
    }

    @Description("TC_E_017_ValidateThatTeacherCanCreateHomeworkUsingBlueFloatingIconOnSummaryTab")
    @Test
    public void TC_E_017_ValidateThatTeacherCanCreateHomeworkUsingBlueFloatingIconOnSummaryTab(){
       // ExtentManager.testName("TC_E_017_ValidateThatTeacherCanCreateHomeworkUsingBlueFloatingIconOnSummaryTab","Nithesh");
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickOnClassRoom();
        ClassRoomPage classRoomPage = new ClassRoomPage(driver);
        classRoomPage.clickOnHomeWork();
        classRoomPage.clickOnMCQ();
        classRoomPage.clickOnContinueButton();
        HomeWorkPage homeWorkPage=new HomeWorkPage(driver);
        homeWorkPage.typeQuestion("sin 0 + cos 0 =","1","2", "0","none of the above","all the above");
        homeWorkPage.clickOnSaveQuestionButton();
        homeWorkPage.saveQuestionPaper();
        homeWorkPage.createHomeWork("Homework3");
    }

    @Test
    public void TC_E_012_ValidateThatUnderChatTabStudentIsAbleToSendAnAttachmentUsingTheCamera(){
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickOnClassRoom();

    }
}

