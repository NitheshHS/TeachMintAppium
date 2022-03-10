package base;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    int retry=2;
    @Override
    public boolean retry(ITestResult iTestResult) {
        if(retry>=0){
            retry--;
            return  true;
        }
        return false;
    }
}
