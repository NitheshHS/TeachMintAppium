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

public class StudyMaterialPage extends AppGenericLib {
    SoftAssert sa=new SoftAssert();
    AppiumDriver driver;

    public StudyMaterialPage(AppiumDriver driver){
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @FindBy(xpath="//*[@resource-id='com.teachmint.teachmint:id/tablayout2']//child::*[@text='Study Material']")
    private WebElement studyMaterialButton;

    @FindBy(xpath="//*[@resource-id='com.teachmint.teachmint:id/add_sm']")
    private WebElement plusButton;

    @FindBy(xpath="//*[@resource-id='com.teachmint.teachmint:id/upload_document_layout']")
    private WebElement uploadDocumentButton;

    @FindBy(xpath="//*[@text='Allow']")
    private WebElement allowButton;


    @Step("Upload the study material")
    public void uploadPdf()
    {
        clickOnElement(studyMaterialButton);
        clickOnElement(plusButton);
        clickOnElement(uploadDocumentButton);
            try {
                clickOnElement(allowButton);
            }
            catch (Exception e) {
                //clickonfile
            }
    }
    }
