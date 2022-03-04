package tests;

import Pages.StudentClassroomPage;
import base.CapabailitySettingLib;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Description;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class StudentTests{

    @Description("TC_E_014_ValidateStudentCanPracticeUsingLearnTab")
    @Test
    public void TC_E_014_ValidateStudentCanPracticeUsingLearnTab() throws InterruptedException, MalformedURLException {
        // ExtentManager.testName("TC_E_014_ValidateStudentCanPracticeUsingLearnTab()","Nithesh");
        AppiumDriver studentDriver = new CapabailitySettingLib()
                .launchStudentDriver(new URL("http://localhost:6666/wd/hub"), Platform.ANDROID);
        StudentClassroomPage studentClassroomPage=new StudentClassroomPage(studentDriver);
        studentClassroomPage.selectCourseClassSubject();
    }
}
