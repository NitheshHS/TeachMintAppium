package base;


import Pages.RegistrationPage;
import io.qameta.allure.Attachment;
import org.openqa.selenium.Platform;
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

    }

    @BeforeMethod
    public void launchApp(ITestResult result) throws MalformedURLException {
        startStudentAppiumServer(setupStudentAppiumServer(6666, result.getMethod().getMethodName()));
        startTeacherAppiumServer(setupTeacherAppiumServer(7777, result.getMethod().getMethodName()));

        driver = launchTeacherDriver(teacherAppiumService.getUrl(), Platform.ANDROID);
        studentDriver = launchStudentDriver(studentAppiumService.getUrl(), Platform.ANDROID);

        sheetName = setLangauge(FileUtility.getPropertyValue("language"));
        if (driver != null) {
            RegistrationPage registrationPage = new RegistrationPage(driver);
            registrationPage.selectLangaugeAndClickOnContinue(sheetName);
            String teacherPhoNo = FileUtility.getPropertyValue("teacherPhoneNumber1");
            String teacherOTP = FileUtility.getPropertyValue("teacherotp1");
            registrationPage.enterPhoneNumberAndclickOnOTP(teacherPhoNo, teacherOTP);
        }
        if (studentDriver != null) {
            RegistrationPage studentRegistrationPage = new RegistrationPage(studentDriver);
            studentRegistrationPage.selectLangaugeAndClickOnContinue(sheetName);
            String studentPhNo = FileUtility.getPropertyValue("studentPhoneNumber1");
            String studentOTP = FileUtility.getPropertyValue("studentOtp1");
            studentRegistrationPage.enterPhoneNumberAndclickOnOTP(studentPhNo, studentOTP);
        }

        // registrationPage.clickOnTeacherAndEnterName("Nithesh","Class10","Maths");
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
