package com.vasanthvz.librarymanagement.login;

import com.vasanthvz.librarymanagement.LibraryManagement;
import com.vasanthvz.librarymanagement.View;
import com.vasanthvz.librarymanagement.librarysetup.LibrarySetupView;

public class LoginView extends View {
    private final LoginModel loginModel;
    public LoginView(){
        this.loginModel = new LoginModel(this);
    }
    public void init(){
        showTitle(LibraryManagement.getInstance().getAppName());
        showText("version:"+LibraryManagement.getInstance().getAppVersion());
        showText("Please Login to proceed.....");
        proceedLogin();
    }

    private void proceedLogin() {
        showText("Enter your user name : ");
        String userName = scanner.next();
        showText("Enter your password : ");
        String password =scanner.next();
        loginModel.validateUser(userName , password);
    }
    public void onLoginFail(){
        checkForLogin();
    }
    private void checkForLogin(){
        boolean choice = tryAgain();
        if(choice){
            proceedLogin();
        }else {
            showTitle("Thank you");
        }
    }

    public void onSuccess() {
        showText("Login successful.......");
        showTitle("Welcome to "+LibraryManagement.getInstance().getAppName());
        new LibrarySetupView().init();
    }
}
