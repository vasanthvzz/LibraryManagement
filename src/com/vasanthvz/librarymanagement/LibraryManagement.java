package com.vasanthvz.librarymanagement;

import com.vasanthvz.librarymanagement.login.LoginView;

public class LibraryManagement {
    private static LibraryManagement libraryManagement;
    // Providing app name and its version
    private final String  appName = "Library Management System";
    private final String  appVersion = "1.0.0";
    private LibraryManagement(){

    }
    //Creating a single instance function to get main class
    public static LibraryManagement getInstance(){
        if(libraryManagement == null){
            libraryManagement = new LibraryManagement();
        }
        return libraryManagement;
    }

    public String getAppName() {
        return appName;
    }

    public String getAppVersion() {
        return appVersion;
    }
    public void create(){
        LoginView loginView = new LoginView();
        loginView.init();
    }

    public static void main(String[] args) {
        LibraryManagement.getInstance().create();
    }
}
