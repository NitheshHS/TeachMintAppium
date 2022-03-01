package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import enums.AppInfo;

import java.io.File;

public class ExtentManager {
    private static ThreadLocal<ExtentSparkReporter> sparkReporter=new ThreadLocal<>();
    private static ExtentSparkReporter spark;
    private static ThreadLocal<ExtentReports> extentReport=new ThreadLocal<>();
    private  static ExtentReports reports;
    public static ThreadLocal<ExtentTest> extentTest=new ThreadLocal<>();
    private static ExtentTest test;


    public static ExtentReports configExtent(){
        spark=new ExtentSparkReporter(new File("./reports/report.html"));
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("TeachMint");
        sparkReporter.set(spark);
        reports =new ExtentReports();
        reports.attachReporter(sparkReporter.get());
        reports.setSystemInfo("APP Package", AppInfo.ANDROID_APP_PACKAGE.getLabel());
        reports.setSystemInfo("APP Activity",AppInfo.ANDROID_APP_ACTIVITY.getLabel());
        reports.setSystemInfo("Platform",AppInfo.PLATFORM.getLabel());
        reports.setSystemInfo("URL",AppInfo.SERVER_URL.getLabel());
        extentReport.set(reports);
        return extentReport.get();
    }

    public static void flushReport(){
        extentReport.get().flush();
    }

    public static void testName(String testcaseName,String author){
        test=extentReport.get().createTest(testcaseName).assignAuthor(author);
        extentTest.set(test);
    }

    public static void info(String message){
        extentTest.get().log(Status.INFO,message);
    }


}