package virtualClass;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import Pages.ClassRoomPage;
import Pages.LandingPage;
import base.BaseTest;
import base.CapabailitySettingLib;
import io.appium.java_client.AppiumDriver;

public class VirtualClassTests extends BaseTest{
	
	@Test(enabled=false)
	public void TC_VC_001_ValidateWhetherGoLiveButtonWorksOnSummarytab() throws Throwable{
		 LandingPage landingPage = new LandingPage(driver);
		    scrollToElement(driver, "text", "Class10");
	        landingPage.clickOnClassRoom();
	        ClassRoomPage classRoomPage = new ClassRoomPage(driver);
	        classRoomPage.clickOnGoLive();
	        classRoomPage.disableVideo();
	        classRoomPage.clickOnGoLivebutton();
	        Thread.sleep(3000);
	        File img1=takeScreenshot(driver, "LiveSessionImage");
	       File img2= new File("./screenshots/Live_SessionImage.PNG");
	       double percentage=compareImage(img1, img2);
	       softAssert.assertTrue(percentage>=60);
	       softAssert.assertAll();
			}
	
    @Test
    public void TC_VC_002_ValidateWhetherGoLiveButtonWorksFromTodaysScheduleSection() throws Throwable{
    	
    	LandingPage landingPage = new LandingPage(driver);
    	landingPage.verifydate();
    	landingPage.clickOnGoLive();
    	ClassRoomPage classRoomPage = new ClassRoomPage(driver);
    	classRoomPage.clickOnGoLivebutton();
    	Thread.sleep(3000);
    	File img1=takeScreenshot(driver, "LiveSessionImage");
	    File img2= new File("./screenshots/Live_SessionImage.PNG");
	    double percentage=compareImage(img1, img2);
	    softAssert.assertTrue(percentage>=60);
	    softAssert.assertAll();
    }
    
}
