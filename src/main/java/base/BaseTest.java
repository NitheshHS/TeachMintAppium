package base;


import enums.AppInfo;
import io.appium.java_client.remote.MobileCapabilityType;
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
    public void configBS() {
        System.out.println("configuring report");
        //configExtentReport();
        //ExtentManager.configExtent();
    }

    @BeforeClass
    public void configBC() {

    }

    @BeforeMethod
    public void launchApp() throws MalformedURLException {
        DesiredCapabilities capabilities =
                setDesiredCapability(AppInfo.PLATFORM.getLabel());
        capabilities.setCapability(MobileCapabilityType.UDID,"R9ZRB050WWT");//emulator-5554
        driver=launchApp(new URL("http://localhost:4723/wd/hub"), Platform.ANDROID,capabilities);
        studentDriver = launchStudentDriver(new URL("http://localhost:6666/wd/hub"), Platform.ANDROID);
    }

    @AfterMethod(alwaysRun = true)
    public void closeApp(ITestResult result) throws IOException {

        if (result.getStatus() == ITestResult.SUCCESS) {

        } else if (result.getStatus() == ITestResult.FAILURE) {
            if(driver!=null) {
                saveScreenshot(getScreenshot(driver));
            }
            if(studentDriver!=null) {
                saveScreenshot(getScreenshot(studentDriver));
            }
        } else if (result.getStatus() == ITestResult.SKIP) {

        }

        if (driver != null) {
            driver.closeApp();
        }
        if (studentDriver != null) {
            studentDriver.closeApp();
        }
    }

    @AfterClass
    public void configAC() {

    }

    @AfterSuite
    public void configAS() {
        System.out.println("Flushing the report");
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }
}
