package virtualClass;

import Pages.ClassRoomPage;
import Pages.LandingPage;
import TestAnnotations.TestInfo;
import base.BaseTest;
import base.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class VirtualClassTests extends BaseTest {
	
	@Test
	public void TC_VC_001_ValidateWhetherGoLiveButtonWorksOnSummarytab(){
		
	}

	@TestInfo(testcaseID = "TC_VC_004",testCaseName = "ValidateIfTheTeacherIsAbleToUnmuteAndMuteTheAudio")
	@Test(alwaysRun = true, retryAnalyzer = base.Retry.class)
	public void TC_VC_004_ValidateIfTheTeacherIsAbleToUnmuteAndMuteTheAudio(){
		String testCaseid=getTestCaseId(VirtualClassTests.class,"TC_VC_004_ValidateIfTheTeacherIsAbleToUnmuteAndMuteTheAudio");
		LandingPage landingPage=new LandingPage(driver);
		landingPage.clickOnClassRoom();
		ClassRoomPage classRoomPage=new ClassRoomPage(driver);
		classRoomPage.clickOnGoLive();
		String micOffStatus=ExcelUtility.getExcelData(sheetName,testCaseid,"Mike off");
		String micOnStatus=ExcelUtility.getExcelData(sheetName,testCaseid,"Mike on");
		classRoomPage.turnOffMicAndVerifyTheText(micOnStatus,micOffStatus);
		classRoomPage.turnOnMicAndVerifyTheText(micOffStatus,micOnStatus);
	}

	@TestInfo(testcaseID = "TC_VC_005",testCaseName = "")
	@Test(alwaysRun = true, retryAnalyzer = base.Retry.class , description = "TC_VC_005_ValidateIfTheTeacherIsAbleToSwitchOffAndOnTheVideo")
	public void TC_VC_005(){
		String testcaseId=getTestCaseId(VirtualClassTests.class,"TC_VC_005");
		LandingPage landingPage=new LandingPage(driver);
		landingPage.clickOnClassRoom();
		ClassRoomPage classRoomPage=new ClassRoomPage(driver);
		classRoomPage.clickOnGoLive();
		String videoOnStatus=ExcelUtility.getExcelData(sheetName,testcaseId,"video on");
		String videoOffStatus=ExcelUtility.getExcelData(sheetName,testcaseId,"Video Off");
		classRoomPage.turnOffVideoAndVerifyText(videoOnStatus,videoOffStatus);
		classRoomPage.turnOnVideoAndVerifyText(videoOnStatus, videoOffStatus);
	}

	@Test(alwaysRun = true, retryAnalyzer = base.Retry.class)
	public void TC_VC_006_ValidateWhetherTeacherIsAbleToSetSettingsForStudentsInStudentControl(){
		LandingPage landingPage=new LandingPage(driver);
		landingPage.clickOnClassRoom();
		ClassRoomPage classRoomPage=new ClassRoomPage(driver);
		classRoomPage.clickOnGoLive();
		String videoOnStatus=ExcelUtility.getExcelData(sheetName,3,2);
		classRoomPage.disableVideo(videoOnStatus);
		classRoomPage.clickOnGoLivebutton();
		classRoomPage.tapOnMoreButton();
		classRoomPage.tapOnStudentControl();
		classRoomPage.clickOnStudentAudioSwitch();
	}

	@Test(alwaysRun = true, retryAnalyzer = base.Retry.class)
	public void TC_VC_007_ValidateWhetherTeacherIsAbleToSetSettingsForStudentsInStudentControlVideoControl(){
		LandingPage landingPage=new LandingPage(driver);
		landingPage.clickOnClassRoom();
		ClassRoomPage classRoomPage=new ClassRoomPage(driver);
		classRoomPage.clickOnGoLive();
		String videoOnStatus=ExcelUtility.getExcelData(sheetName,3,2);
		classRoomPage.disableVideo(videoOnStatus);
		classRoomPage.clickOnGoLivebutton();
		classRoomPage.tapOnMoreButton();
		classRoomPage.tapOnStudentControl();
		classRoomPage.tapOnChatSwitch();
	}




}
