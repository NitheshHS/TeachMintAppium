package base;

import com.google.common.io.Files;
import enums.Langauges;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.connection.ConnectionState;
import io.appium.java_client.android.connection.ConnectionStateBuilder;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import javassist.bytecode.stackmap.BasicBlock;
import org.apache.commons.codec.language.bm.Lang;
import org.apache.commons.codec.language.bm.Languages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.python.antlr.op.And;
import org.sikuli.script.Finder;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



public class AppGenericLib extends CapabailitySettingLib {

    public void awaitForElement(AppiumDriver driver, WebElement element) {
//        ExtentManager.extentTest.get().info("waiting for visibility of element: "+element);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            wait.until(ExpectedConditions.visibilityOf(element));
        }
    }
//    public void compareImage(File image1, File image2) throws IOException {
//        BufferedImage img1 = ImageIO.read(image1);
//        BufferedImage img2 = ImageIO.read(image2);
//        int w1 = img1.getWidth();
//        int w2 = img2.getWidth();
//        int h1 = img1.getHeight();
//        int h2 = img2.getHeight();
//        if ((w1 != w2) || (h1 != h2)) {
//            System.out.println("Both images should have same dimwnsions");
//        } else {
//            long diff = 0;
//            for (int j = 0; j < h1; j++) {
//                for (int i = 0; i < w1; i++) {
//                    //Getting the RGB values of a pixel
//                    int pixel1 = img1.getRGB(i, j);
//                    Color color1 = new Color(pixel1, true);
//                    int r1 = color1.getRed();
//                    int g1 = color1.getGreen();
//                    int b1 = color1.getBlue();
//                    int pixel2 = img2.getRGB(i, j);
//                    Color color2 = new Color(pixel2, true);
//                    int r2 = color2.getRed();
//                    int g2 = color2.getGreen();
//                    int b2 = color2.getBlue();
//                    //sum of differences of RGB values of the two images
//                    long data = Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2);
//                    diff = diff + data;
//                }
//            }
//            double avg = diff / (w1 * h1 * 3);
//            double percentage = (avg / 255) * 100;
//            System.out.println("Difference: " + percentage);
//        }
//    }

    public void tapOnElement(AppiumDriver driver, MobileElement element) {
        //  ExtentManager.extentTest.get().info("Tapping on element: "+element);
        TouchAction touchAction = new TouchAction(driver);
        touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(element))).perform();
    }

    public double compareImage(File image1, File image2) throws IOException {
        Pattern pattern1 = new Pattern(image1.getAbsolutePath());
        Pattern pattern2 = new Pattern(image2.getAbsolutePath());
        Finder f = new Finder(pattern1.getImage());
        f.find(pattern2);
        if (f.hasNext()) {
            Match m = f.next();
            System.out.println("Match found with: " + (m.getScore()) * 100 + "%");
            return m.getScore() * 100;
        } else {
            System.out.println("Image not found similar");
        }
        return 0;
    }

    public File takeScreenshot(AppiumDriver driver, String screenshotName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File dest = new File("./screenshots/" + screenshotName + ".PNG");
        Files.copy(src, dest);
        return dest;
    }

    public File takeScreenshot(WebElement element, String imageName) throws IOException {
        File file = element.getScreenshotAs(OutputType.FILE);
        File dest = new File(".\\src\\test\\Images\\" + imageName + ".png");
        Files.copy(file, dest);
        return dest;
    }

    public void clickOnElement(WebElement element) {
//        ExtentManager.extentTest.get().info("click on element: "+element);
        element.click();
    }

    public void type(WebElement element, String text) {
        //ExtentManager.extentTest.get().info("element: "+element+" entering text: "+text);
        element.sendKeys(text);
    }


    public void openNotification(AppiumDriver driver) {
        if (driver != null) {
            //ExtentManager.extentTest.get().info("Opening notification");
            AndroidDriver androidDriver = (AndroidDriver) driver;
            androidDriver.openNotifications();

        }
    }

    public void turnOffMobileDataAndWifi(AppiumDriver driver) throws IOException, InterruptedException {
        AndroidDriver androidDriver = (AndroidDriver) driver;
        if (androidDriver.getConnection().isWiFiEnabled() || androidDriver.getConnection().isDataEnabled()) {
//            ExtentManager.extentTest.get().info("Turning off mobile data and wifi");
//            ConnectionStateBuilder builder = new ConnectionStateBuilder(ConnectionState.DATA_MASK).withDataDisabled();
//            androidDriver.setConnection(builder.build());
            //androidDriver.toggleAirplaneMode();
            androidDriver.toggleWifi();
            Thread.sleep(10000);
//            androidDriver.toggleData();
            // executeADBcommand(driver,adbCommand);
            //Runtime.getRuntime().exec(adbCommand);
        }

    }

    public void turnOnDataAndWifi(AppiumDriver driver) {
        AndroidDriver androidDriver = (AndroidDriver) driver;
        if (!(androidDriver.getConnection().isWiFiEnabled() || androidDriver.getConnection().isDataEnabled())) {
            // ExtentManager.extentTest.get().info("Turning on mobile data and wifi");
            androidDriver.toggleWifi();
            androidDriver.toggleData();
        }
    }

    public void waitOrPause(long seconds) {
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pressNavigationBack(AppiumDriver driver) {
        AndroidDriver androidDriver = (AndroidDriver) driver;
        // ExtentManager.extentTest.get().info("Pressing navigate back button");
        androidDriver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

    public void pressHomeButton(AppiumDriver driver) {
        AndroidDriver androidDriver = (AndroidDriver) driver;
        //ExtentManager.extentTest.get().info("Pressing home button");
        androidDriver.pressKey(new KeyEvent(AndroidKey.HOME));
    }

    public void executeADBcommand(AppiumDriver driver, String adbCommand) {
        driver.execute(adbCommand);
    }

    public String getToastMessage(AppiumDriver driver) {
        //ExtentManager.extentTest.get().info("Handling Toast message ");
        WebElement toast = driver.findElement(By.xpath("//android.widget.Toast"));
        awaitForElement(driver, toast);
        return toast.getText();
    }

    public void hideKeyboard(AppiumDriver driver) {
        // ExtentManager.extentTest.get().info("hiding the keyboard");
        driver.hideKeyboard();
    }

    public void scrollToElement(AppiumDriver driver, String visibleText) {
        //ExtentManager.extentTest.get().info("Scrolling for elemenet: "+visibleText);
        driver.findElement(MobileBy
                .AndroidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollTextIntoView(" + visibleText + "))"));

    }

    public void scrollToElement(AppiumDriver driver) {
        //ExtentManager.extentTest.get().info("Performing scroll action");
        TouchAction touch = new TouchAction(driver);
        touch.longPress(PointOption.point(414, 1485))
                .moveTo(PointOption.point(421, 618))
                .release()
                .perform();
    }

    public byte[] getScreenshot(AppiumDriver driver) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        return ts.getScreenshotAs(OutputType.BYTES);
    }

    public void custmWait(AppiumDriver driver, WebElement element) throws InterruptedException {
        try {
            WebDriverWait w = new WebDriverWait(driver, 10);
            w.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            Thread.sleep(3000);
        }
    }

    public void scrollToHorizontalElementAndClick(AppiumDriver driver, double startpart, double endPart, WebElement element) {
        //ExtentManager.extentTest.get().info("Performing scroll action");
        TouchAction touch = new TouchAction(driver);
        int xcordstartPoint = driver.manage().window().getSize().getWidth();

        int y = driver.manage().window().getSize().getHeight();
        for (int swipe = 0; swipe < 3; swipe++) {
            if (element.isDisplayed()) {
                clickOnElement(element);
                break;
            } else {
                touch.longPress(PointOption.point((int) (xcordstartPoint * startpart), y / 2))
                        .moveTo(PointOption.point((int) (xcordstartPoint * endPart), y / 2))
                        .release()
                        .perform();
            }
        }
    }
    public void scrollHorizontalAndClick(AppiumDriver driver,WebElement element){
        for(int swipeCount=0;swipeCount<3;swipeCount++) {
            try {
                clickOnElement(element);
                break;
            } catch (Exception e) {
                TouchAction touch = new TouchAction(driver);
                touch.longPress(PointOption.point(871, 329))
                        .moveTo(PointOption.point(248, 341))
                        .release()
                        .perform();
            }
        }
    }

    public void turnWifiOn(AppiumDriver driver){
        AndroidDriver androidDriver=(AndroidDriver)driver;
        if(!androidDriver.getConnection().isWiFiEnabled()) {
            androidDriver.toggleWifi();
        }
    }

    public static String setLangauge(String langauges){
       if(Langauges.ENGLISH.toString().equalsIgnoreCase(langauges)){
            return Langauges.ENGLISH.toString();
       }
       else if(Langauges.HINDI.toString().equalsIgnoreCase(langauges)){
           return  Langauges.HINDI.toString();
       }else{
           return null;
       }
    }
}






