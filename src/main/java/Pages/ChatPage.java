package Pages;

import base.AppGenericLib;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChatPage extends AppGenericLib {

    AppiumDriver driver;
    public ChatPage(AppiumDriver driver){
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    @FindBy(xpath = "//*[@text='Guru']")
    private WebElement studentChat;

    @FindBy(xpath = "(//*[@resource-id='com.teachmint.teachmint:id/filename'])[last()]")
    private WebElement studentImagetext;

    @Step("Tap on student")
    public void clickOnStudent(String studentName){
        driver.findElement(By.xpath("//*[@text='"+studentName+"']")).click();
    }

    @Step("Get student image text")
    public String getStudentImageText(){
        return  studentImagetext.getText().trim();
    }
}
