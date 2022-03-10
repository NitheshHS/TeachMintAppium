package tests;

import Pages.*;
import base.BaseTest;
import io.qameta.allure.Description;
import org.python.antlr.ast.Str;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * author : Nithesh
 */
public class TestRun extends BaseTest {
    /**
     * @throws InterruptedException
     * @throws IOException
     */
    @Test
    public void TC_E_001_ValidateTheTeacherIsAbleToStartTheLiveClassTest() throws InterruptedException, IOException {
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickOnClassRoom();
        ClassRoomPage classRoomPage = new ClassRoomPage(driver);
        classRoomPage.clickOnGoLive();
        classRoomPage.clickOnGoLiveBtn();
        classRoomPage.tapOnMeetingProfile();
    }

    @Description("TC_E_008_Validate If The Teacher Is Able To Create Live Poll Test")
    @Test
    public void TC_E_008_ValidateIfTheTeacherIsAbleToCreateLivePollTest() throws IOException {
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickOnClassRoom();
        ClassRoomPage classRoomPage = new ClassRoomPage(driver);
        classRoomPage.clickOnGoLive();
        classRoomPage.clickOnGoLivebutton();
        classRoomPage.tapOnMoreButton();
        classRoomPage.startLivePoll("30", "A");
        takeScreenshot(driver,"TC_E_008");
        classRoomPage.stopLivePoll();
    }

    @Description("TC_E_005 Validate network switch in teacher side during live class")
    @Test(retryAnalyzer = base.Retry.class)
    public void TC_E_005_ValidateNetworkSwitchInTeacherSideDuringLiveClassTest() throws IOException, InterruptedException {
        // ExtentManager.testName("TC_E_005_ValidateNetworkSwitchInTeacherSideDuringLiveClassTest","Nithesh");
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickOnClassRoom();
        ClassRoomPage classRoomPage = new ClassRoomPage(driver);
        classRoomPage.clickOnGoLive();
        classRoomPage.clickOnGoLivebutton();
        openNotification(driver);
        turnOffMobileDataAndWifi(driver);
        pressNavigationBack(driver);
        classRoomPage.verifyTheNoInternetMsg("Check your network");
        takeScreenshot(driver,"TC_E_005");
        turnWifiOn(driver);
    }

    @Description("TC_E_007_ValidateIfTheTeacherIsAbleToStartYoutubeStreamTest")
    @Test(retryAnalyzer = base.Retry.class)
    public void TC_E_007_ValidateIfTheTeacherIsAbleToStartYoutubeStreamTest() throws IOException {
        // ExtentManager.testName("TC_E_004_ValidateTheTeacherIsAbleToVideoStreamInLiveClassTest","Nithesh");
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickOnClassRoom();
        ClassRoomPage classRoomPage = new ClassRoomPage(driver);
        classRoomPage.clickOnGoLive();
        classRoomPage.disableVideo();
        classRoomPage.clickOnGoLivebutton();
        classRoomPage.tapOnMoreButton();
        classRoomPage.startYoutubeStreaming("youtube", "");
    }

    @Description("TC_E_009_ValidateIfTheTeacherIsAbleToChatWithChatDisabledForStudents")
    @Test
    public void TC_E_009_ValidateIfTheTeacherIsAbleToChatWithChatDisabledForStudents() throws IOException {
        // ExtentManager.testName("TC_E_009_ValidateIfTheTeacherIsAbleToChatWithChatDisabledForStudents","Nithesh");
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickOnClassRoom();
        ClassRoomPage classRoomPage = new ClassRoomPage(driver);
        classRoomPage.clickOnGoLive();
        classRoomPage.clickOnGoLivebutton();
        classRoomPage.tapOnMoreButton();
        classRoomPage.tapOnStudentControl();
        classRoomPage.tapOnChatSwitch();
        pressNavigationBack(driver);
        classRoomPage.typeChat("Hello");
    }


    @Description("TC_E_002_ValidateTheTeacherIsAbleToShareScreenInLiveClass")
    @Test
    public void TC_E_002_ValidateTheTeacherIsAbleToShareScreenInLiveClass() throws InterruptedException, IOException {
        // ExtentManager.testName("TC_E_002_ValidateTheTeacherIsAbleToShareScreenInLiveClass","Nithesh");
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickOnClassRoom();
        ClassRoomPage classRoomPage = new ClassRoomPage(driver);
        classRoomPage.clickOnGoLive();
        classRoomPage.clickOnGoLivebutton();
        classRoomPage.tapOnShareScreenOption();
        classRoomPage.clickOnShareScreen();
        classRoomPage.tapOnStartSharingScreen();
        StudentClassroomPage studentClassroomPage = new StudentClassroomPage(studentDriver);
        studentClassroomPage.tapOnClassroom();
        studentClassroomPage.tapOnJoinLiveButton();
        Thread.sleep(15000);
        File TeacherLive = takeScreenshot(driver, "TeacherSharedScreen.png");
        // File StudentLive = takeScreenshot(studentDriver,"StudentScreen.png");
        double compPer = compareImage(TeacherLive, new File("./screenshots/StaticTeacherSharedScreen.PNG"));
        softAssert.assertTrue(compPer >= 60, "Both images are not same");
        softAssert.assertAll();
    }

    @Description("TC_E_017_ValidateThatTeacherCanCreateHomeworkUsingBlueFloatingIconOnSummaryTab")
    @Test
    public void TC_E_017_ValidateThatTeacherCanCreateHomeworkUsingBlueFloatingIconOnSummaryTab() {
        // ExtentManager.testName("TC_E_017_ValidateThatTeacherCanCreateHomeworkUsingBlueFloatingIconOnSummaryTab","Nithesh");
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickOnClassRoom();
        ClassRoomPage classRoomPage = new ClassRoomPage(driver);
        classRoomPage.clickOnHomeWork();
        classRoomPage.clickOnCreateHomework();
        classRoomPage.clickOnMCQ();
        classRoomPage.clickOnContinueButton();
        HomeWorkPage homeWorkPage = new HomeWorkPage(driver);
        homeWorkPage.clickOnAttachment();
        classRoomPage.clickOnGallery();
        classRoomPage.addAttachment();
        homeWorkPage.typeQuestion("Image attachment", "1", "2",
                "0", "none of the above", "all the above");
        homeWorkPage.clickOnSaveQuestionButton();
        homeWorkPage.saveQuestionPaper();
        homeWorkPage.createHomeWorkAndVerify("Homework4");
    }

    @Description("TC_E_012_ValidateThatUnderChatTabStudentIsAbleToSendAnAttachmentUsingTheCamera")
    @Test
    public void TC_E_012_ValidateThatUnderChatTabStudentIsAbleToSendAnAttachmentUsingTheCamera() throws InterruptedException {
        LandingPage landingPage = new LandingPage(studentDriver);
        StudentClassroomPage studentclassRoomPage = new StudentClassroomPage(studentDriver);
        landingPage.clickOnClassRoom();
        studentclassRoomPage.clickOnChatButtonAndSelectTeacher();
        studentclassRoomPage.clickOnAttachementButton();
        studentclassRoomPage.chooseCamara();
        studentclassRoomPage.clickPhoto();
        studentclassRoomPage.clickOnSendButton();
        String studentImageText = studentclassRoomPage.verifySentPicture();
        System.out.println(studentImageText);
        LandingPage teacherLandingPage = new LandingPage(driver);
        teacherLandingPage.clickOnClassRoom();
        ClassRoomPage classRoomPage = new ClassRoomPage(driver);
        classRoomPage.clickOnChat();
        ChatPage chatPage = new ChatPage(driver);
        chatPage.clickOnStudent("Guru");
        String teacherImageText = chatPage.getStudentImageText();
        System.out.println(teacherImageText);
        softAssert.assertEquals(teacherImageText, studentImageText, "Both images are different");
    }

    @Description("TC_E_010 Validate if the teacher is able to share files")
    @Test
    public void TC_E_010_ValidateIfTheTeacherIsAbleToShareFiles() throws InterruptedException {
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickOnClassRoom();
        ClassRoomPage classRoomPage = new ClassRoomPage(driver);
        classRoomPage.clickOnGoLive();
        classRoomPage.clickOnGoLivebutton();
        classRoomPage.tapOnShareScreenOption();
        classRoomPage.clickOnShareImage();
        classRoomPage.clickOnGallery();
        Thread.sleep(5000);
        classRoomPage.verifySharedImage();
        Thread.sleep(10000);
    }

    @Description("TC_E_011_Validate The Teacher Is Able To Create Study Material Pdf")
    @Test
    public void TC_E_011_ValidateTheTeacherIsAbleToCreateStudyMaterialPdf() {
        // ExtentManager.testName("TC_E_011_ValidateTheTeacherIsAbleToCreateStudyMaterialPdf","Nithesh");
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickOnClassRoom();
        StudyMaterialPage studyMaterialPage = new StudyMaterialPage(driver);
        studyMaterialPage.clickOnStudyMaterial();
        studyMaterialPage.clickOnAddMaterial();
        studyMaterialPage.uploadDocumentAndVerify("agile.pdf");
    }

    @Description("TC_E_004_Validate The Teacher Is Able To Video Stream In Live Class")
    @Test
    public void TC_E_004_ValidateTheTeacherIsAbleToVideoStreamInLiveClassTest() throws IOException, InterruptedException {
        // ExtentManager.testName("TC_E_004_ValidateTheTeacherIsAbleToVideoStreamInLiveClassTest","Nithesh");
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickOnClassRoom();
        ClassRoomPage classRoomPage = new ClassRoomPage(driver);
        classRoomPage.clickOnGoLive();
        classRoomPage.clickOnVideoIconAndVerify("Video on");
        classRoomPage.clickOnGoLivebutton();
        StudentClassroomPage studentClassroomPage = new StudentClassroomPage(studentDriver);
        studentClassroomPage.tapOnClassroom();
        studentClassroomPage.tapOnJoinLiveButton();
        Thread.sleep(15000);
        File TeacherLive = takeScreenshot(driver, "TeacherLiveSession.png");
        File StudentLive = takeScreenshot(studentDriver, "StudentLiveSession.png");
        double compPer = compareImage(TeacherLive, StudentLive);
        softAssert.assertTrue(compPer >= 60, "Both images are not same");

    }

    @Test
    public void TC_E_016_ValidateThatTeacherCanCreateTestUsingQuestionBankRecommendations() throws MalformedURLException, InterruptedException {
        // logonType(LoginAs.TEACHER);
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickOnClassRoom();
        ClassRoomPage classRoomPage = new ClassRoomPage(driver);
        classRoomPage.scrollAndClickOnTest(0.8, 0.5);
        classRoomPage.modifyQuestionBank();
        classRoomPage.deleteQuestionAndVerifyUpdatedMarks();
        classRoomPage.verifyCreatedTest();

    }

    @Description("TC_E_013_ValidateTeacherIsAbleToAddTimetableForAClass")
    @Test
    public void TC_E_013_ValidateTeacherIsAbleToAddTimetableForAClass() throws IOException {
        // ExtentManager.testName("TC_E_013_ValidateTeacherIsAbleToAddTimetableForAClass","Nithesh");
        // logonType(LoginAs.TEACHER);
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickOnClassRoom();
        TimeTablePage timeTablePage = new TimeTablePage(driver);
        timeTablePage.clickOnEdit();
        timeTablePage.toggleOnSundayTimeTable();
        timeTablePage.addTimetableForSlot1("7", "30", "8", "00");
        timeTablePage.clickOnPlus();
        timeTablePage.addTimetableForSlot2("5", "00", "7", "00");
        takeScreenshot(driver,"TC_E_013");
        timeTablePage.verifyTheTimeSlot("7:30 AM-8:00 AM","5:00 PM-7:00 PM");
    }

    @Description("TC_E_014_ValidateStudentCanPracticeUsingLearnTab")
    @Test
    public void TC_E_014_ValidateStudentCanPracticeUsingLearnTab() throws InterruptedException, MalformedURLException {
        // ExtentManager.testName("TC_E_014_ValidateStudentCanPracticeUsingLearnTab()","Nithesh");
        //logonType(LoginAs.STUDENT);
        StudentClassroomPage studentClassroomPage = new StudentClassroomPage(studentDriver);
        studentClassroomPage.clickOnLearn();
        //studentClassroomPage.selectCourseAndClass("NCERT","Class 1");
        studentClassroomPage.selectTopic("Addition");
        studentClassroomPage.chooseAnswersAndVerify('A', 'B');

    }

    @Description("TC_E_018_ Validate that teacher is able to add a syllabus for a class")
    @Test
    public void TC_E_018_ValidateThatTeacherIsAbleToAddASyllabusForAClass() throws InterruptedException {
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickOnClassRoom();
        ClassRoomPage classRoomPage=new ClassRoomPage(driver);
        classRoomPage.clickOnSyllabus();
        SyllabusPage syllabusPage=new SyllabusPage(driver);
        syllabusPage.clickOnAddMoreButton();
        syllabusPage.createSyllabus("CBSE","Class 6","English");
        syllabusPage.clickOnFirstSyllabusAndViewChapters();
        ArrayList<String> chapterLists=new ArrayList<>();
        chapterLists.add("Who Did Patrick’s Homework?");
        chapterLists.add("How the Dog Found Himself a New Master!");
        chapterLists.add("Taro’s Reward");
        chapterLists.add("An Indian – American Woman in Space: Kalpana Chawla");
        chapterLists.add("A Different Kind of School");
        chapterLists.add("Who I Am");
        chapterLists.add("Fair Play");
        chapterLists.add("A Game of Chance");
        chapterLists.add("Desert Animals");
        chapterLists.add("The Banyan Tree");
        syllabusPage.viewAllChaptersAndVerify(chapterLists);

    }


}

