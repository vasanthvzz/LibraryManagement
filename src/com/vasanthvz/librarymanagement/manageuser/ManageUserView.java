package com.vasanthvz.librarymanagement.manageuser;

import com.vasanthvz.librarymanagement.View;
import com.vasanthvz.librarymanagement.librarymenu.LibraryMenuView;
import com.vasanthvz.librarymanagement.model.User;

public class ManageUserView extends View {
    private final ManageUserModel manageUserModel;
    public ManageUserView(){
        this.manageUserModel = new ManageUserModel(this);
    }


    public void init(){
        showTitle("Manage User Menu");
        showMenu();
    }
    void showMenu() {
        showText("1.Add User");
        showText("2.Search user");
        showText("3.View all users");
        showText("4.Update user");
        showText("5.Remove user");
        showText("0.Go back");
        manageUserModel.redirectChoice(scanner.next());
    }

    public void goBack() {
        new LibraryMenuView().init();
    }

    public void getUserDetail() {
        showTitle("Add User Menu");
        System.out.print("Enter the name of the user : ");
        String name = scanner.next();
        System.out.print("Enter the email of the user : ");
        String email = scanner.next();
        showText("Enter the phoneNo of the user : ");
        String phoneNo = scanner.next();
        showText("Enter the address of the user : ");
        String address = scanner.next();
        manageUserModel.addUser(name, phoneNo, email, address);
    }



    public int getUserId() {
        showText("Enter the id of the user : ");
        return scanner.nextInt();
    }


    public void successUserAdd() {
        showText("user has been added ");
        if(checkForAddNewItems("User")){
            getUserDetail();
        }else {
            showMenu();
        }
    }

    public String getUserName() {
        showText("Enter the name of the user");
        return scanner.next();

    }

    public void getUserDetail(User user) {
        showText("Enter the name of the user: (press '0' if you don't want to change)");
        String name = scanner.next();
        showText("Enter the email of the user :  (press '0' if you don't want to change)");
        String email = scanner.next();
        manageUserModel.setUserDetail(name,email,user);

    }
}
