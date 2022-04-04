package tests;

import TestAnnotations.TestInfo;
import base.AppGenericLib;
import base.ExcelUtility;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class test {

    @TestInfo(testcaseID = "TC_01",testCaseName = "Read data from annotation")
    @Test
    public void test() throws NoSuchMethodException {
        String data=ExcelUtility.getExcelData("hindi","TC_VC_005","video off");
        System.out.println(data);
       String id = new AppGenericLib().getTestCaseId(test.class, "test");
        System.out.println(id);
//        Method method= test.class.getMethod("test");
//        TestInfo testinfo = method.getAnnotation(TestInfo.class);
//
//        System.out.println(testinfo);
//        System.out.println(testinfo.testcaseID());
    }

    @Test
    public void appiumServer() throws InterruptedException {
        AppiumServiceBuilder builder=new AppiumServiceBuilder()
                .withAppiumJS(new File("C:\\Users\\User\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
                .withIPAddress("127.0.0.1")
                .usingPort(7777);
               // .withLogFile(new File("./appiumServer.log"));

        AppiumDriverLocalService service=builder.build();
        service.start();
        System.out.println(service.getUrl());
        Thread.sleep(3000);
        service.stop();
    }
}
