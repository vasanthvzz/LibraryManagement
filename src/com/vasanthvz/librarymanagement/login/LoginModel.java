package com.vasanthvz.librarymanagement.login;

public class LoginModel {
    private final LoginView loginView;
    public LoginModel(LoginView loginView) {
        this.loginView = loginView;
    }

    public void validateUser(String userName, String password) {
        if(userName.equals("123")){
            if (password.equals("123")){
               loginView.onSuccess();
            }else{
                loginView.showAlert("Invalid password");
                loginView.onLoginFail();
            }
        }else {
            loginView.showAlert("Invalid user name");
            loginView.onLoginFail();
        }
    }
}
