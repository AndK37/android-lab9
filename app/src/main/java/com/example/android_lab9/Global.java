package com.example.android_lab9;
public class Global {

    private Global() {
        // Initialization code here (e.g., set up database connection)
    }

    private static class GlobalHolder {
        private static final Global INSTANCE = new Global();
    }
    public static Global getInstance() {
        return GlobalHolder.INSTANCE;
    }

    public Boolean isStartup = true;

}
