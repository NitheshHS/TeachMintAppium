package Pages;

import base.WebActions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends WebActions {

    AppiumDriver driver;
    public LandingPage(AppiumDriver driver){
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    @FindBy(xpath="//*[@text='Create Classroom']")
    private MobileElement createClassroom;

    @FindBy(id="com.teachmint.teachmint:id/edit_name")
    private MobileElement editclassroomName;

    @FindBy(id="com.teachmint.teachmint:id/edit_subject")
    private MobileElement editSubject;

    @FindBy(xpath="//*[@resource-id='com.teachmint.teachmint:id/title' or @text='Class10']")
    private MobileElement classNameText;

    public void clickOnClassRoom(){
       // awaitForElement(driver,classNameText);
        try {
            clickOnElement(classNameText);
        }
        catch (StaleElementReferenceException e){
            clickOnElement(classNameText);
        }
    }



}
