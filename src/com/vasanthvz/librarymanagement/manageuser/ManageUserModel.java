package com.vasanthvz.librarymanagement.manageuser;

import com.vasanthvz.librarymanagement.datalayer.LibraryDatabase;
import com.vasanthvz.librarymanagement.model.User;
import com.vasanthvz.librarymanagement.model.User;

import java.util.List;

public class ManageUserModel {
    private final ManageUserView manageUserView;
    private final LibraryDatabase libraryDatabase;
    ManageUserModel(ManageUserView manageUserView) {
        this.manageUserView = manageUserView;
        libraryDatabase = LibraryDatabase.getInstance();
    }
    public void redirectChoice(String choice) {
        switch (choice){
            case "1":{
                manageUserView.getUserDetail();
                break;
            }
            case "2":{
                searchUser(manageUserView.getUserName());
                break;
            }
            case "3":{
                viewAllUser();
                break;
            }
            case "4":{
                editUser();
                break;
            }
            case "5":{
                deleteUser();
                break;
            }
            case "0":{
                manageUserView.goBack();
                break;
            }
            default:{
                if(manageUserView.tryAgain()){
                    manageUserView.showMenu();
                }else{
                    manageUserView.goBack();
                }
                break;
            }
        }
    }

    private void deleteUser() {
        int id = manageUserView.getUserId();
        User user = libraryDatabase.getUser(id);
        if(user == null){
            manageUserView.showAlert("User Not found");
        }else{
            libraryDatabase.getAllUser().remove(user);
            manageUserView.showText("User has been removed successfully");
        }
        manageUserView.showMenu();
    }

    private void editUser() {
        int id = manageUserView.getUserId();
        User user = libraryDatabase.getUser(id);
        if(user == null){
            manageUserView.showAlert("User not found");
        }else{
            manageUserView.showAlert("User id :"+user.getId()+
                    "\tUsername:"+user.getName()+"\tEmail:"+user.getEmailId());
            manageUserView.getUserDetail(user);
        }
        manageUserView.showMenu();
    }

    private void viewAllUser() {
        manageUserView.showText("ID\t\tName\t\temail\t\tPhone\t\tAddress");
        for(User user :libraryDatabase.getAllUser()){
            manageUserView.showText(user.getId()+"\t\t"+user.getName()+"\t\t"+
                    user.getEmailId()+ "\t\t"+user.getPhoneNo()+"\t\t"
                    +user.getAddress());
        }
        manageUserView.showMenu();

    }

    private void searchUser(String userName) {
        List<User> userList = libraryDatabase.searchUser(userName);
        manageUserView.showText("Name\t\tEmail\t\tPhone\t\tAddress");
        for(User user : userList){
            manageUserView.showText(user.getName()+"\t\t"+user.getEmailId()+
                    "\t\t"+user.getPhoneNo()+"\t\t"+user.getAddress());
        }
        manageUserView.showMenu();
    }


    public void addUser(String name, String phoneNo, String email, String address) {
        User user = new User();
        user.setName(name);user.setEmailId(email);user.setPhoneNo(phoneNo);
        user.setAddress(address);
        boolean userAdded = libraryDatabase.insertUser(user);
        if(userAdded){
            manageUserView.showText("User added with id "+user.getId());
            manageUserView.successUserAdd();
        }else{
            manageUserView.showAlert("The user is already present !");
        }
    }


    public void setUserDetail(String name, String email, User user) {
        if(! name.equals("0")){
            user.setName(name);
        }
        if(! name.equals("0")){
            user.setEmailId(email);
        }
        manageUserView.showText("User has been update successfully");
        manageUserView.showMenu();
    }
}
