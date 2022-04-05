package Pages;

import base.AppGenericLib;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class StudyMaterialPage extends AppGenericLib {
    SoftAssert sa = new SoftAssert();
    AppiumDriver driver;
    @FindBy(xpath = "//*[@resource-id='com.teachmint.teachmint:id/tablayout2']//child::*[@text='Study Material']")
    private WebElement studyMaterialButton;
    @FindBy(xpath = "//*[@resource-id='com.teachmint.teachmint:id/add_sm']")
    private WebElement addMaterial;
    @FindBy(xpath = "//*[@resource-id='com.teachmint.teachmint:id/upload_document_layout']")
    private WebElement uploadDocumentButton;
    @FindBy(xpath = "//*[@text='Allow']")
    private WebElement allowButton;
    @FindBy(xpath = "//*[@resource-id='android:id/title' and @text='agile.pdf']")
    private WebElement pdfFile;
    @FindBy(id = "com.teachmint.teachmint:id/click_save")
    private WebElement saveAndCreateButton;
    @FindBy(xpath = "//*[@resource-id='com.teachmint.teachmint:id/sm_titile']")
    private List<WebElement> studyMaterialList;

    public StudyMaterialPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Upload the study material")
    public void uploadPdf() {
        clickOnElement(studyMaterialButton);
        clickOnElement(addMaterial);
        clickOnElement(uploadDocumentButton);
        try {
            clickOnElement(allowButton);
        } catch (Exception e) {
            //clickonfile
        }
    }

    @Step("tap on study material")
    public void clickOnStudyMaterial() {
        clickOnElement(studyMaterialButton);
    }

    @Step("click on add material")
    public void clickOnAddMaterial() {
        clickOnElement(addMaterial);
    }

    @Step("Uploading pdf file")
    public void uploadDocumentAndVerify(String materialName) {
        try {
            clickOnElement(uploadDocumentButton);
        } catch (Exception e) {
            clickOnElement(allowButton);
        }
        clickOnElement(pdfFile);
        clickOnElement(saveAndCreateButton);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pressNavigationBack(driver);
        String studyMaterialName = studyMaterialList.get(0).getText();
        softAssert.assertEquals(studyMaterialName, studyMaterialName, "Study material name is not matching");
        softAssert.assertAll();
    }

}
