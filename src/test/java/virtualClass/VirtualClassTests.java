package virtualClass;

import Pages.ClassRoomPage;
import Pages.LandingPage;
import base.BaseTest;
import org.testng.annotations.Test;

public class VirtualClassTests extends BaseTest {
	
	@Test
	public void TC_VC_003_ValidateWhetherPreviewScreenDisplaysTheDetailsCorrectlyAndClickFunctionalityOfButtons(){
		String classRoom ="9th";

		LandingPage landingPage = new LandingPage(driver);
		landingPage.clickOnClassRoom(classRoom);
		ClassRoomPage classRoomPage = new ClassRoomPage(driver);
		classRoomPage.clickOnGoLive();
		classRoomPage.clickOnMicVideoRecIcon();
		classRoomPage.verifyClassNameInLiveClassPreview(classRoom);


	}

	@Test
	public void TC_VC_008_ValidateWhetherTeacherIsAbleToTurnONOFFTheChatSettings() throws InterruptedException {
		String classRoom ="9th";

		LandingPage landingPage = new LandingPage(driver);
		landingPage.clickOnClassRoom(classRoom);
		ClassRoomPage classRoomPage = new ClassRoomPage(driver);
		classRoomPage.clickOnGoLive();
		Thread.sleep(2000);
		classRoomPage.clickOnGoLive();
		classRoomPage.tapOnOptionsButton();
		classRoomPage.tapOnStudentControl();
		classRoomPage.clickOnAllowStudentstoChat();



	}



}
