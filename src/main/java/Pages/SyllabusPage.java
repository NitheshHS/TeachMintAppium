package Pages;

import base.AppGenericLib;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SyllabusPage extends AppGenericLib {
    @FindBy(id = "com.teachmint.teachmint:id/chapterName")
    public List<WebElement> chapterList;
    AppiumDriver driver;
    @FindBy(xpath = "//*[@text='eg. CBSE, ICSE, JEE, NEET etc.']")
    private WebElement courseTextField;
    @FindBy(id = "com.teachmint.teachmint:id/add_more_button")
    private WebElement addMoreButton;
    @FindBy(xpath = "//*[@text='eg. Class 10']")
    private WebElement classTextField;
    @FindBy(xpath = "//*[@text='eg. Mathematics']")
    private WebElement subjectTextField;
    @FindBy(xpath = "//*[@text='Continue']")
    private WebElement continueButton;
    @FindBy(id = "com.teachmint.teachmint:id/save_syllabus_button")
    private WebElement saveSyllabusButton;
    @FindBy(id = "com.teachmint.teachmint:id/add_chapter_item")
    private WebElement addChapter;
    @FindBy(id = "com.teachmint.teachmint:id/chapter_name")
    private WebElement chapterName;
    @FindBy(xpath = "//*[@resource-id='com.teachmint.teachmint:id/course_title']")
    private List<WebElement> courseTitles;
    @FindBy(xpath = "//*[@resource-id='com.teachmint.teachmint:id/more_options']")
    private List<WebElement> moreOptions;
    @FindBy(id = "com.teachmint.teachmint:id/delete_title")
    private WebElement deleteButton;

    @FindBy(xpath = "//*[@text='Delete syllabus' and @resource-id='com.teachmint.teachmint:id/continue_button']")
    private WebElement deleteSyllabusButton;

    public SyllabusPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickOnAddMoreButton() {
        clickOnElement(addMoreButton);
    }

    @Step("create syllabus course name is{courseName} for class {className} and subject {subjectName}")
    public void createSyllabus(String courseName, String className, String subjectName) throws InterruptedException {
        // clickOnElement(courseTextField);
        type(courseTextField, courseName);
        Thread.sleep(3000);
        selectElementFromDropDown(courseName);
        hideKeyboard(driver);
        clickOnElement(classTextField);
        type(classTextField, className);
        Thread.sleep(3000);
        selectElementFromDropDown(className);
        hideKeyboard(driver);
        clickOnElement(subjectTextField);
        Thread.sleep(3000);
        type(subjectTextField, subjectName);
        selectElementFromDropDown(subjectName);
        hideKeyboard(driver);
        clickOnElement(continueButton);
    }

    @Step("create new chapter with name {chapterTitle}")
    public void addChapter(String chapterTitle) {
        clickOnElement(addChapter);
        type(chapterName, chapterTitle);
    }

    @Step("save syllabus")
    public void saveSyllabusAndVerifyTheChapterName(String chapterTitle) {
        clickOnElement(saveSyllabusButton);
        String name = chapterName.getText().trim();
        softAssert.assertEquals(name, chapterTitle);
        softAssert.assertAll();
    }

    @Step("tapping on first syllabus and view chapters")
    public void clickOnFirstSyllabusAndViewChapters() {
        courseTitles.get(0).click();
    }

    public void viewAllChaptersAndVerify(ArrayList<String> chapterListNames) {
        ArrayList<String> chapters = new ArrayList<>();
        for (WebElement ele : chapterList) {
            chapters.add(ele.getText().trim());
        }
        softAssert.assertEquals(chapters, chapterListNames);
        softAssert.assertAll();
    }

    @Step("verify the syllabus list")
    public void verifyTheSyllabusList(ArrayList<String> courseNames) {
        ArrayList<String> courseList = new ArrayList<>();
        for (WebElement ele : courseTitles) {
            courseList.add(ele.getText().trim());
        }
        softAssert.assertEquals(courseList, courseNames);
        softAssert.assertAll();
    }

    @Step("delete course")
    public void deleteCourse() {
        for (WebElement element : moreOptions) {
            clickOnElement(element);
            clickOnElement(deleteButton);
            clickOnElement(deleteSyllabusButton);
        }
    }

    @Step("select {visibleText}")
    public void selectElementFromDropDown(String visibleText) {
        driver.findElement(By.xpath("//*[@text='" + visibleText + "']")).click();
    }


}
