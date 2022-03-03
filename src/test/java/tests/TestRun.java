package tests;

import Pages.*;
import Pages.StudyMaterialPage;
import base.BaseTest;
import base.ExtentManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.util.function.Function;

/**
 * author : Nithesh
 */
public class TestRun extends BaseTest {
    /**
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
        classRoomPage.startLivePoll("30","A");
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

    @Description("TC_E_007_ValidateIfTheTeacherIsAbleToStartYoutubeStreamTest")
    @Test

    public void TC_E_007_ValidateIfTheTeacherIsAbleToStartYoutubeStreamTest() throws IOException {
       // ExtentManager.testName("TC_E_004_ValidateTheTeacherIsAbleToVideoStreamInLiveClassTest","Nithesh");
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickOnClassRoom();
        ClassRoomPage classRoomPage = new ClassRoomPage(driver);
        classRoomPage.clickOnGoLive();
        classRoomPage.clickOnGoLivebutton();
        classRoomPage.tapOnMoreButton();
        classRoomPage.startYoutubeStreaming("youtube", "YouTube live streaming failed. Try again");
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
    public void TC_E_017_ValidateThatTeacherCanCreateHomeworkUsingBlueFloatingIconOnSummaryTab() {
        // ExtentManager.testName("TC_E_017_ValidateThatTeacherCanCreateHomeworkUsingBlueFloatingIconOnSummaryTab","Nithesh");
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickOnClassRoom();
        ClassRoomPage classRoomPage = new ClassRoomPage(driver);
        classRoomPage.clickOnHomeWork();
        classRoomPage.clickOnMCQ();
        classRoomPage.clickOnContinueButton();
        HomeWorkPage homeWorkPage = new HomeWorkPage(driver);
        homeWorkPage.typeQuestion("sin 0 + cos 0 =", "1", "2",
                "0", "none of the above", "all the above");
        homeWorkPage.clickOnSaveQuestionButton();
        homeWorkPage.saveQuestionPaper();
        homeWorkPage.createHomeWork("Homework3");
    }

    @Description("TC_E_011_ValidateTheTeacherIsAbleToCreateStudyMaterialPdf")
    @Test
    public void TC_E_011_ValidateTheTeacherIsAbleToCreateStudyMaterialPdf() {
        // ExtentManager.testName("TC_E_011_ValidateTheTeacherIsAbleToCreateStudyMaterialPdf","Nithesh");
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickOnClassRoom();
        StudyMaterialPage studyMaterialPage = new StudyMaterialPage(driver);
        studyMaterialPage.uploadPdf();
    }

    @Description("TC_E_013_ValidateTeacherIsAbleToAddTimetableForAClass")
    @Test
    public void TC_E_013_ValidateTeacherIsAbleToAddTimetableForAClass() {
        // ExtentManager.testName("TC_E_013_ValidateTeacherIsAbleToAddTimetableForAClass","Nithesh");
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickOnClassRoom();
        TimeTablePage timeTablePage = new TimeTablePage(driver);
        timeTablePage.clickOnEdit();
        timeTablePage.addTimetableForSlot1("7" ,"30", "8", "00");
        timeTablePage.clickOnPlus();
        timeTablePage.addTimetableForSlot2("5" ,"00", "7", "00");
        timeTablePage.verifyTimetableSlots("7" ,"30" , "8" , "00" , "5" ,"00", "7", "00" );
        timeTablePage.clickOnToggleButton();
    }

    @Description("TC_E_014_ValidateStudentCanPracticeUsingLearnTab")
    @Test
    public void TC_E_014_ValidateStudentCanPracticeUsingLearnTab() throws InterruptedException {
        // ExtentManager.testName("TC_E_014_ValidateStudentCanPracticeUsingLearnTab()","Nithesh");
        StudentClassroomPage studentClassroomPage=new StudentClassroomPage(driver);
        studentClassroomPage.selectCourseClassSubject();
    }
}

