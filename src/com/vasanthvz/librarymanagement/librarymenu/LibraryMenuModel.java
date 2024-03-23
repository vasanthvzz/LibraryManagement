package com.vasanthvz.librarymanagement.librarymenu;

public class LibraryMenuModel {
    private final LibraryMenuView libraryMenuView;
    public LibraryMenuModel(LibraryMenuView libraryMenuView) {
        this.libraryMenuView = libraryMenuView;
    }

    public void redirectChoice(String choice) {
        switch (choice){
            case "1":{
                libraryMenuView.redirectManageBook();
                break;
            }
            case "2":{
                libraryMenuView.redirectManageUser();
                break;
            }
            case "3":{
                libraryMenuView.redirectManageIssueBook();
                break;
            }
            case "4":{
                libraryMenuView.redirectLogout();
                break;
            }
            case "0":{
                libraryMenuView.onExit();
                break;
            }
            default:{
                libraryMenuView.tryAgain();
                break;
            }
        }
    }
}
