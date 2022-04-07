package base;

import com.aventstack.extentreports.ExtentTest;
import enums.AppInfo;
import enums.LoginAs;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @author Nitheesha
 */
public class CapabailitySettingLib {
    public static Logger logger;
    public AppiumDriver driver;//teacher
    public AppiumDriver studentDriver;
    public SoftAssert softAssert = new SoftAssert();
    public String sheetName;
    public AppiumDriverLocalService teacherAppiumService;
    public AppiumDriverLocalService studentAppiumService;

    public AppiumDriverLocalService setupTeacherAppiumServer(int portNum, String testcasename) {
        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withAppiumJS(new File(FilePaths.APPIUM_JS_FILE))
                .withIPAddress(FilePaths.APPIUM_SERVER_IP)
                .usingDriverExecutable(new File(FilePaths.APPIUM_NODEJS))
                .usingPort(portNum)
                .withLogFile(new File("./ServerLogs/appiumTeacher" + testcasename + ".log"));
        teacherAppiumService = AppiumDriverLocalService.buildService(builder);
        return teacherAppiumService;
    }

    public AppiumDriverLocalService setupStudentAppiumServer(int portNum, String testcasename) {
        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withAppiumJS(new File(FilePaths.APPIUM_JS_FILE))
                .withIPAddress(FilePaths.APPIUM_SERVER_IP)
                .usingDriverExecutable(new File(FilePaths.APPIUM_NODEJS))
                .usingPort(portNum)
                .withLogFile(new File("./ServerLogs/appiumStudent" + testcasename + ".log"));
        return studentAppiumService = AppiumDriverLocalService.buildService(builder);
    }

    public void startTeacherAppiumServer(AppiumDriverLocalService service) {
        if (service != null) {
            service.start();
        }
    }

    public void stopTeacherAppiumServer() {
        if (teacherAppiumService != null) {
            teacherAppiumService.stop();
        }
    }

    public void startStudentAppiumServer(AppiumDriverLocalService service) {
        if (service != null) {
            service.start();
        }
    }

    public void stopStudentAppiumServer() {
        if (studentAppiumService != null) {
            studentAppiumService.stop();
        }
    }

    public DesiredCapabilities setDesiredCapability(String platform) {
        logger.info("Setting device capability");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platform", platform);
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, AppInfo.ANDROID_PLATFORM_NAME.getLabel());
        //capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, AppInfo.ANDROID_APP_PACKAGE.getLabel());
        // capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, AppInfo.ANDROID_APP_ACTIVITY.getLabel());
        capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
        capabilities.setCapability(MobileCapabilityType.APP, FilePaths.TEACHMINT_APK);
        //capabilities.setCapability(MobileCapabilityType.FULL_RESET,true);
        capabilities.setCapability("automationName", AppInfo.ANDROID_AUTOMATION_NAME.getLabel());
        capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        // capabilities.setCapability("appWaitForLaunch ",false);
        //capabilities.setCapability("adbExecTimeout",500000);
        logger.info("capabilities: " + capabilities.toString());
        return capabilities;
    }

    public AppiumDriver launchTeacherDriver(URL url, Platform platForm) {
        logger.info("launching app: server url: " + url + "\n platform: " + platForm);
        DesiredCapabilities capabilities = null;
        switch (platForm) {
            case ANDROID:
                capabilities = setDesiredCapability("android");
                capabilities.setCapability(MobileCapabilityType.UDID, FileUtility.getPropertyValue("teacherUDID"));
                driver = new AndroidDriver(url, capabilities);
                break;
            case IOS:
                driver = new IOSDriver(url, capabilities);
                break;
            default:
                System.out.println("Invalid url: " + url + " platform: " + platForm + " capabilities: " + capabilities.toJson().toString());
        }
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        return driver;
    }

    public AppiumDriver launchStudentDriver(URL url, Platform platform) throws MalformedURLException {
        logger.info("launching student driver in appium server url: " + url);
        DesiredCapabilities capabilities = null;
        switch (platform) {
            case ANDROID:
                capabilities = setDesiredCapability("android");
                capabilities.setCapability(MobileCapabilityType.UDID, FileUtility.getPropertyValue("studentUDID"));// R9ZRB0CHV3T emulator-5554
                studentDriver = new AndroidDriver(url, capabilities);
                break;
            case IOS:
                studentDriver = new IOSDriver(url, capabilities);
                break;
            default:
                System.out.println("Invalid capabilities: " + capabilities);
                break;
        }
        studentDriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        return studentDriver;
    }

    public void logonType(LoginAs teacherOrStudent) throws MalformedURLException {
        switch (teacherOrStudent) {
            case TEACHER:
                DesiredCapabilities capabilities =
                        setDesiredCapability(AppInfo.PLATFORM.getLabel());
                capabilities.setCapability(MobileCapabilityType.UDID, "R9ZRB050WWT");//emulator-5554
                driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
                driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
                break;
            case STUDENT:
                DesiredCapabilities studentcapabilities = null;
                studentcapabilities = setDesiredCapability("android");
                studentcapabilities.setCapability(MobileCapabilityType.UDID, "R9ZRB0CHV3T");//emulator-5556
                studentDriver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), studentcapabilities);
                studentDriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
                break;
            default:
                System.out.println("Invalid student capabilities: ");
                break;
        }
    }

}
