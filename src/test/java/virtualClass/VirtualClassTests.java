package virtualClass;

import Pages.ClassRoomPage;
import Pages.LandingPage;
import Pages.StudentClassroomPage;
import TestAnnotations.TestInfo;
import base.BaseTest;
import base.ExcelUtility;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class VirtualClassTests extends BaseTest {

    @Test
    public void TC_VC_001_ValidateWhetherGoLiveButtonWorksOnSummarytab() {

    }

    @TestInfo(testcaseID = "TC_VC_004", testCaseName = "ValidateIfTheTeacherIsAbleToUnmuteAndMuteTheAudio")
    @Test(alwaysRun = true, retryAnalyzer = base.Retry.class)
    public void TC_VC_004_ValidateIfTheTeacherIsAbleToUnmuteAndMuteTheAudio() throws IOException {
        String testCaseid = getTestCaseId(VirtualClassTests.class, "TC_VC_004_ValidateIfTheTeacherIsAbleToUnmuteAndMuteTheAudio");
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickOnClassRoom();
        ClassRoomPage classRoomPage = new ClassRoomPage(driver);
        classRoomPage.clickOnGoLive();
        String micOffStatus = ExcelUtility.getExcelData(sheetName, testCaseid, "Mike off");
        String micOnStatus = ExcelUtility.getExcelData(sheetName, testCaseid, "Mike on");
        classRoomPage.turnOffMicAndVerifyTheText(micOnStatus, micOffStatus);
        classRoomPage.turnOnMicAndVerifyTheText(micOffStatus, micOnStatus);
        classRoomPage.clickOnGoLivebutton();
        StudentClassroomPage studentClassroomPage = new StudentClassroomPage(studentDriver);
        studentClassroomPage.tapOnClassroom();
        studentClassroomPage.tapOnJoinLiveButton();
        waitOrPause(15);
        File TeacherLive = takeScreenshot(driver, "TeacherLiveSession");
        File StudentLive = takeScreenshot(studentDriver, "StudentLiveSession");
        double compPer = compareImage(TeacherLive, StudentLive);
        softAssert.assertTrue(compPer >= 60, "Both images are not same");
    }

    @TestInfo(testcaseID = "TC_VC_005", testCaseName = "ValidateIfTheTeacherIsAbleToSwitchOffAndOnTheVideo")
    @Test(alwaysRun = true, description = "TC_VC_005_ValidateIfTheTeacherIsAbleToSwitchOffAndOnTheVideo")
    public void TC_VC_005() {
        String testcaseId = getTestCaseId(VirtualClassTests.class, "TC_VC_005");
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickOnClassRoom();
        ClassRoomPage classRoomPage = new ClassRoomPage(driver);
        classRoomPage.clickOnGoLive();
        String videoOnStatus = ExcelUtility.getExcelData(sheetName, testcaseId, "video on");
        String videoOffStatus = ExcelUtility.getExcelData(sheetName, testcaseId, "Video Off");
        classRoomPage.turnOffVideoAndVerifyText(videoOnStatus, videoOffStatus);
        classRoomPage.turnOnVideoAndVerifyText(videoOnStatus, videoOffStatus);
    }

    @Test(alwaysRun = true, retryAnalyzer = base.Retry.class)
    public void TC_VC_006_ValidateWhetherTeacherIsAbleToSetSettingsForStudentsInStudentControl() {
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickOnClassRoom();
        ClassRoomPage classRoomPage = new ClassRoomPage(driver);
        classRoomPage.clickOnGoLive();
        String videoOnStatus = ExcelUtility.getExcelData(sheetName, 3, 2);
        classRoomPage.disableVideo(videoOnStatus);
        classRoomPage.clickOnGoLivebutton();
        classRoomPage.tapOnMoreButton();
        classRoomPage.tapOnStudentControl();
        classRoomPage.clickOnStudentAudioSwitch();

    }

    @Test(alwaysRun = true, retryAnalyzer = base.Retry.class)
    public void TC_VC_007_ValidateWhetherTeacherIsAbleToSetSettingsForStudentsInStudentControlVideoControl() {
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickOnClassRoom();
        ClassRoomPage classRoomPage = new ClassRoomPage(driver);
        classRoomPage.clickOnGoLive();
        String videoOnStatus = ExcelUtility.getExcelData(sheetName, 3, 2);
        classRoomPage.disableVideo(videoOnStatus);
        classRoomPage.clickOnGoLivebutton();
        classRoomPage.tapOnMoreButton();
        classRoomPage.tapOnStudentControl();
        classRoomPage.tapOnChatSwitch();
    }


}
