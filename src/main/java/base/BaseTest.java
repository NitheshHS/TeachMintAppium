package base;


import enums.AppInfo;
import enums.Langauges;
import io.appium.java_client.remote.MobileCapabilityType;
import io.qameta.allure.Attachment;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * @author Nitheesha
 */
public class BaseTest extends AppGenericLib {

    @BeforeSuite
    public void configBS() {
        logger();
    }

    @BeforeClass
    public void configBC() {
        sheetName = setLangauge(Langauges.HINDI.toString());
    }

    @BeforeMethod
    public void launchApp(ITestResult result) throws MalformedURLException {
        startStudentAppiumServer(setupStudentAppiumServer(6666, result.getMethod().getMethodName()));
        startTeacherAppiumServer(setupTeacherAppiumServer(7777, result.getMethod().getMethodName()));

        DesiredCapabilities capabilities =
                setDesiredCapability(AppInfo.PLATFORM.getLabel());
        capabilities.setCapability(MobileCapabilityType.UDID, "be7890e80508");//emulator-5554 R9ZRB050WWT
        driver = launchTeacherDriver(teacherAppiumService.getUrl(), Platform.ANDROID, capabilities);
       studentDriver = launchStudentDriver(studentAppiumService.getUrl(), Platform.ANDROID);

    }

    @AfterMethod(alwaysRun = true)
    public void closeApp(ITestResult result) throws IOException {

        if (result.getStatus() == ITestResult.SUCCESS) {

        } else if (result.getStatus() == ITestResult.FAILURE) {
            if (driver != null) {
                saveScreenshot(getScreenshot(driver));
            }
            if (studentDriver != null) {
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
        stopTeacherAppiumServer();
        stopStudentAppiumServer();
    }

    @AfterClass(alwaysRun = true)
    public void configAC() {

    }

    @AfterSuite(alwaysRun = true)
    public void configAS() {
        System.out.println("Flushing the report");
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }
}
