package base;


import enums.AppInfo;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest extends CommonAction{

    @BeforeSuite
    public void configBS(){

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
    public void closeApp(){
        softAssert.assertAll();
        driver.closeApp();
    }

    @AfterClass
    public void configAC(){

    }

    @AfterSuite
    public void configAS(){

    }
}
