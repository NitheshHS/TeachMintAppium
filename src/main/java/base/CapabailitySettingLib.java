package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import enums.AppInfo;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class CapabailitySettingLib{
   public  AppiumDriver driver;
   public SoftAssert softAssert=new SoftAssert();

   public ExtentSparkReporter spark;
   public ExtentReports reports;
   public ExtentTest test;


    public DesiredCapabilities setDesiredCapability(String platform, String platformName, String appPackage, String appActivity, String automationName) {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platform", platform);
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, platformName);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appPackage);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appActivity);
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        capabilities.setCapability("automationName", automationName);
        return capabilities;
    }

    public AppiumDriver launchApp(URL url, Platform platForm, DesiredCapabilities capabilities) {

        switch (platForm) {
            case ANDROID:
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

    public void configExtentReport(){
        if(spark==null || reports==null){
            spark=new ExtentSparkReporter(new File("./report.html"));
            spark.config().setTheme(Theme.DARK);
            spark.config().setDocumentTitle("TeachMint");
            reports =new ExtentReports();
            reports.attachReporter(spark);
            reports.setSystemInfo("APP Package", AppInfo.ANDROID_APP_PACKAGE.getLabel());
            reports.setSystemInfo("APP Activity",AppInfo.ANDROID_APP_ACTIVITY.getLabel());
            reports.setSystemInfo("Platform",AppInfo.PLATFORM.getLabel());
            reports.setSystemInfo("URL",AppInfo.SERVER_URL.getLabel());

        }
    }

    public void flushReport(){
        if(reports!=null){
            reports.flush();
        }
    }

    public void testName(String testcaseName,String author){
            test = reports.createTest(testcaseName).assignAuthor(author);
    }

    public void info(String message){
            test.log(Status.INFO,message);
    }









}
