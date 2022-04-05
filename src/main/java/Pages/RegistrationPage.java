package Pages;

import base.AppGenericLib;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RegistrationPage extends AppGenericLib {
    AppiumDriver driver;
    @FindBy(xpath = "//*[@text='English']")
    private WebElement english;
    @FindBy(xpath = "//*[@text='Hindi']")
    private WebElement hindi;
    @FindBy(xpath = "//*[@resource-id='com.teachmint.teachmint:id/select_lang' " +
            "or @resource-id='com.teachmint.teachmint:id/continue_button' or " +
            "@resource-id='com.teachmint.teachmint:id/create_profile']")
    private WebElement continueButton;
    @FindBy(id = "com.teachmint.teachmint:id/mobile_number_text")
    private WebElement phoneNumberTextField;
    @FindBy(xpath = "//*[@resource-id='com.teachmint.teachmint:id/textinput_placeholder' or @text='Mobile Number' " +
            "or @text='मोबाइल नंबर']")
    private WebElement phoneNumberTextfield;
    @FindBy(id = "com.teachmint.teachmint:id/send_otp_button")
    private WebElement sendOTP;
    @FindBy(id = "com.teachmint.teachmint:id/otp_box")
    private WebElement OTPbox;
    @FindBy(xpath = "//android.widget.EditText")
    private List<WebElement> otpTextBoxs;
    @FindBy(id = "com.teachmint.teachmint:id/teacher_layout_bottom")
    private WebElement teacher;
    @FindBy(id = "com.teachmint.teachmint:id/student_layout_bottom")
    private WebElement student;
    @FindBy(xpath = "//*[@text='Enter your name']")
    private WebElement enterName;
    @FindBy(xpath = "//*[@resource-id='com.teachmint.teachmint:id/name']/descendant::tandroid.widget.EditText[@text]")
    private WebElement className;
    @FindBy(xpath = "//*[@resource-id='com.teachmint.teachmint:id/subject']/descendant::android.widget.EditText[@text]")
    private WebElement subjectTextField;
    @FindBy(id = "com.teachmint.teachmint:id/tuition_coaching_text")
    private WebElement tuitionCoaching;
    @FindBy(id = "com.teachmint.teachmint:id/school_text")
    private WebElement school;
    @FindBy(id = "com.teachmint.teachmint:id/hobby_text")
    private WebElement hobby;
    @FindBy(id = "com.teachmint.teachmint:id/create_class")
    private WebElement createClassroom;

    public RegistrationPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Langauge selecetd: {langauage}")
    public void selectLangaugeAndClickOnContinue(String langauage) {
        if (langauage.equalsIgnoreCase("english")) {
            clickOnElement(english);
        } else if (langauage.equalsIgnoreCase("hindi")) {
            clickOnElement(hindi);
        } else {
            clickOnElement(english);
        }
        clickOnElement(continueButton);
    }

    @Step("Entering phone number {phoneNumber} and otp {otp}")
    public void enterPhoneNumberAndclickOnOTP(String phoneNumber, String otp) {
        awaitForElement(driver, phoneNumberTextfield);
        clickOnElement(phoneNumberTextField);
        try {
            type(phoneNumberTextfield, phoneNumber);
        } catch (Exception e) {
            type(phoneNumberTextfield, phoneNumber);
        }
        clickOnElement(sendOTP);
        for (int i = otpTextBoxs.size() - 1; i >= 0; i--) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            char[] arr = otp.toCharArray();
            otpTextBoxs.get(i).sendKeys(arr[i] + "");
        }
        clickOnElement(continueButton);
    }

    public void clickOnTeacherAndEnterName(String teacherName, String classname, String subject) {
        try {
            clickOnElement(teacher);
            type(enterName, teacherName);
            hideKeyboard(driver);
            clickOnElement(continueButton);
            type(className, classname);
            hideKeyboard(driver);
            type(subjectTextField, subject);
            hideKeyboard(driver);
            clickOnElement(tuitionCoaching);
            clickOnElement(createClassroom);
        } catch (Exception e) {
            type(className, classname);
            hideKeyboard(driver);
            type(subjectTextField, subject);
            hideKeyboard(driver);
            clickOnElement(tuitionCoaching);
            clickOnElement(createClassroom);
        }
    }

}
