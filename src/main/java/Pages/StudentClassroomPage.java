package Pages;

import base.AppGenericLib;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.sikuli.script.App;


public class StudentClassroomPage extends AppGenericLib {

    AppiumDriver driver;
    public StudentClassroomPage(AppiumDriver driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    @FindBy(xpath="//*[@text='Learn']")
    private WebElement learnButton;

    @FindBy(xpath="//*[@text='NCERT']")
    private WebElement ncertCourseButton;

    @FindBy(xpath="//*[@text='Class 1']")
    private WebElement class1Button;

    @FindBy(xpath="//*[@text='Maths']")
    private WebElement mathSubjectButton;

    @FindBy(xpath="//*[@text='Addition']")
    private WebElement additionTopicButton;

    @FindBy(xpath="(//*[@text='Auto-render test'])[5]")
    private WebElement optionDButton;

    @FindBy(xpath="(//*[@text='Auto-render test'])[4]")
    private WebElement optionCButton;

    @FindBy(xpath="//*[@text='Submit']")
    private WebElement submitButton ;

    @FindBy(xpath="//*[@text='Next >>']")
    private WebElement nextButton ;

    @FindBy(xpath="//*[@text='Correct']")
    private WebElement correctButton ;

    @FindBy(xpath="//*[@text='Wrong']")
    private WebElement wrongButton ;


    @Step(" Student can select cource, class and subject")
    public void selectCourseClassSubject() throws InterruptedException {

        clickOnElement(learnButton);
        Thread.sleep(1000);
        clickOnElement(ncertCourseButton);
        clickOnElement(class1Button);
        clickOnElement(mathSubjectButton);

        clickOnElement(additionTopicButton);
        clickOnElement(optionDButton);
        clickOnElement(submitButton);
        clickOnElement( nextButton);

        softAssert.assertTrue(correctButton.getText().contains("Correct"),"There is an error adding the slots.");
        softAssert.assertAll();

        clickOnElement(optionCButton);
        clickOnElement(submitButton);
        softAssert.assertTrue(correctButton.getText().contains("Wrong"),"There is an error adding the slots.");
        softAssert.assertAll();
        }
        }