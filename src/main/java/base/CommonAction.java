package base;

import enums.AppInfo;
import enums.PlatForm;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import sun.security.krb5.internal.crypto.Des;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class CommonAction {
   public  AppiumDriver driver;
   public SoftAssert softAssert=new SoftAssert();
   public WebActions webActions=new WebActions();


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







}
