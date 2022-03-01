package base;



import com.aventstack.extentreports.Status;
import enums.AppInfo;
import io.qameta.allure.Attachment;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest extends AppGenericLib {

    @BeforeSuite
    public void configBS(){
        System.out.println("configuring report");
        //configExtentReport();
        //ExtentManager.configExtent();
    }

    @BeforeClass
    public void configBC(){

    }

    @BeforeMethod
    public void launchApp() throws MalformedURLException {
        DesiredCapabilities capabilities =
                setDesiredCapability(AppInfo.PLATFORM.getLabel(), AppInfo.ANDROID_PLATFORM_NAME.getLabel(), AppInfo.ANDROID_APP_PACKAGE.getLabel(), AppInfo.ANDROID_APP_ACTIVITY.getLabel(), AppInfo.ANDROID_AUTOMATION_NAME.getLabel());
        driver=launchApp(new URL("http://localhost:4723/wd/hub"),Platform.ANDROID,capabilities);

    }

    @AfterMethod
    public void closeApp(ITestResult result) throws IOException {

       if(result.getStatus()==ITestResult.SUCCESS){
////            ExtentManager.extentTest.get().log(Status.PASS,result.getMethod().getMethodName()+" is Pass");
       }else if(result.getStatus()==ITestResult.FAILURE){
//            ExtentManager.extentTest.get().log(Status.FAIL,result.getMethod().getMethodName()+" is Fail");
//            ExtentManager.extentTest.get().log(Status.FAIL,result.getThrowable());
//            ExtentManager.extentTest.get().addScreenCaptureFromPath(takeScreenshot(driver,result.getMethod().getMethodName()));
            saveScreenshot(getScreenshot());
       }
      else if(result.getStatus()==ITestResult.SKIP){
//            ExtentManager.extentTest.get().log(Status.SKIP,result.getMethod().getMethodName()+" is Skip");
//            ExtentManager.extentTest.get().log(Status.SKIP,result.getThrowable());
       }
        softAssert.assertAll();
            driver.closeApp();
    }

    @AfterClass
    public void configAC(){

    }

    @AfterSuite
    public void configAS(){
        System.out.println("Flushing the report");
        //flushReport();
        //ExtentManager.flushReport();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }
}
