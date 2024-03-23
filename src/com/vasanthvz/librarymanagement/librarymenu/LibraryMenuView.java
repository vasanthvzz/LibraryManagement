package com.vasanthvz.librarymanagement.librarymenu;

import com.vasanthvz.librarymanagement.View;
import com.vasanthvz.librarymanagement.issuebook.IssueBookView;
import com.vasanthvz.librarymanagement.login.LoginView;
import com.vasanthvz.librarymanagement.managebook.ManageBookView;
import com.vasanthvz.librarymanagement.manageuser.ManageUserView;

public class LibraryMenuView extends View {
    private final LibraryMenuModel libraryMenuModel;
    public LibraryMenuView(){
        libraryMenuModel = new LibraryMenuModel(this);
    }
    public void init() {
        showTitle("Library Menu");
        showLibraryMenu();
    }

    private void showLibraryMenu() {
        showText("""
                 1. Manage Book
                 2. Manage User
                 3. Manage IssueBook
                 4. Logout
                 0. Exit
                """);
        showText("Enter your choice : ");
        String choice = scanner.next();
        libraryMenuModel.redirectChoice(choice);

    }

    public void redirectLogout() {
        new LoginView().init();
    }

    public void redirectManageIssueBook() {
        new IssueBookView().init();
    }

    public void redirectManageBook() {
        new ManageBookView().init();
    }

    public void redirectManageUser() {
        new ManageUserView().init();
    }
}
