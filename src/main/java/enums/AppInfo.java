package enums;

public enum AppInfo {

    ANDROID_AUTOMATION_NAME("uiautomator2"),
    ANDROID_PLATFORM_NAME("android"),
    ANDROID_APP_PACKAGE("com.teachmint.teachmint"),
    ANDROID_APP_ACTIVITY("com.teachmint.teachmint.MainActivity"),
    PLATFORM("android"),
    SERVER_URL("http://localhost:4723")
    ;




    String label;
    AppInfo(String label){
        this.label=label;
    }

    public String getLabel(){
        return  label;
    }
}
