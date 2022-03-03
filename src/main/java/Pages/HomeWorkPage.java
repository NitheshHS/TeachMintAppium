package Pages;

import base.AppGenericLib;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

public class HomeWorkPage extends AppGenericLib {
    SoftAssert sa=new SoftAssert();
    AppiumDriver driver;

    public HomeWorkPage(AppiumDriver driver){
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @FindBy(id="com.teachmint.teachmint:id/question_edit_text")
    private WebElement questionTextField;

    @FindBy(id="com.teachmint.teachmint:id/option_A_edit_text")
    private WebElement optionATextField;

    @FindBy(id="com.teachmint.teachmint:id/option_B_edit_text")
    private WebElement optionBTextField;

    @FindBy(id="com.teachmint.teachmint:id/option_C_edit_text")
    private WebElement optionCTextField;

    @FindBy(id="com.teachmint.teachmint:id/option_D_edit_text")
    private WebElement optionDTextField;

    @FindBy(id="com.teachmint.teachmint:id/option_E_edit_text")
    private WebElement optionETextField;

    @FindBy(id="com.teachmint.teachmint:id/option_A_button")
    private WebElement optionAButton;

    @FindBy(id="com.teachmint.teachmint:id/option_B_button")
    private WebElement optionBButton;

    @FindBy(xpath = "//*[@text='Save Question']")
    private WebElement saveQuestionButton;

    @FindBy(xpath = "//*[@text='Save Question Paper']")
    private WebElement saveQuestionPaperButton;

    @FindBy(xpath = "//*[@text='Enter topic here']")
    private WebElement homeWorkTopicTextField;

    @FindBy(xpath = "//*[@resource-id='com.teachmint.teachmint:id/click_save' and @text='Create Homework']")
    private WebElement createHomeworkButton;

    public void typeQuestion(String question, String optionA, String optionB,String optionC,String optionD,String optionE){
        type(questionTextField,question);
        hideKeyboard(driver);
        type(optionATextField,optionA);
        hideKeyboard(driver);
        type(optionBTextField,optionB);
        hideKeyboard(driver);
        type(optionCTextField,optionC);
        hideKeyboard(driver);
        type(optionDTextField,optionD);
        hideKeyboard(driver);
        type(optionETextField,optionE);
        hideKeyboard(driver);
        scrollToElement(driver);
        awaitForElement(driver,optionAButton);
        clickOnElement(optionAButton);

    }


    @Step("Tap on save question button")
    public void clickOnSaveQuestionButton(){
        clickOnElement(saveQuestionButton);
    }

    @Step("Save question paper")
    public void saveQuestionPaper(){
        awaitForElement(driver,saveQuestionPaperButton);
        clickOnElement(saveQuestionPaperButton);
    }

    @Step("Creating home work with name {homeWorkTopicName}")
    public void createHomeWork(String homeWorkTopicName){
        type(homeWorkTopicTextField,homeWorkTopicName);
        clickOnElement(createHomeworkButton);
        String topicName=null;
        try {
            Thread.sleep(3000);
            topicName = driver.findElement(By
                    .xpath("//*[@resource-id='com.teachmint.teachmint:id/test_name_text' and @text='"+homeWorkTopicName+"']"))
                    .getText();
        } catch (Exception e) {
            pressNavigationBack(driver);
        }
        topicName = driver.findElement(By
                .xpath("//*[@resource-id='com.teachmint.teachmint:id/test_name_text' and @text='"+homeWorkTopicName+"']"))
                .getText();
       sa.assertEquals(topicName,homeWorkTopicName,"Homework topic name is not matching");
       sa.assertAll();
    }
}
