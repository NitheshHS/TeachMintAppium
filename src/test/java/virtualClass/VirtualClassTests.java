package virtualClass;

import Pages.ClassRoomPage;
import Pages.LandingPage;
import base.BaseTest;
import base.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VirtualClassTests extends BaseTest {
	
	@Test
	public void TC_VC_001_ValidateWhetherGoLiveButtonWorksOnSummarytab(){
		
	}

	@Test(alwaysRun = true, retryAnalyzer = base.Retry.class)
	public void TC_VC_004_ValidateIfTheTeacherIsAbleToUnmuteAndMuteTheAudio(){
		LandingPage landingPage=new LandingPage(driver);
		landingPage.clickOnClassRoom();
		ClassRoomPage classRoomPage=new ClassRoomPage(driver);
		classRoomPage.clickOnGoLive();
		String micOffStatus=ExcelUtility.getExcelData(sheetName,1,3);
		String micOnStatus=ExcelUtility.getExcelData(sheetName,1,2);
		classRoomPage.turnOffMicAndVerifyTheText(micOnStatus,micOffStatus);
		classRoomPage.turnOnMicAndVerifyTheText(micOffStatus,micOnStatus);
	}

	@Test(alwaysRun = true, retryAnalyzer = base.Retry.class)
	public void TC_VC_005_ValidateIfTheTeacherIsAbleToSwitchOffAndOnTheVideo(){
		LandingPage landingPage=new LandingPage(driver);
		landingPage.clickOnClassRoom();
		ClassRoomPage classRoomPage=new ClassRoomPage(driver);
		classRoomPage.clickOnGoLive();
		String videoOnStatus=ExcelUtility.getExcelData(sheetName,3,2);
		String videoOffStatus=ExcelUtility.getExcelData(sheetName,3,3);
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
