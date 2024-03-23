package com.vasanthvz.librarymanagement.librarysetup;

import com.vasanthvz.librarymanagement.View;
import com.vasanthvz.librarymanagement.librarymenu.LibraryMenuView;

public class LibrarySetupView extends View {
    private final LibrarySetupModel librarySetupModel;
    public LibrarySetupView(){
        this.librarySetupModel = new LibrarySetupModel(this);
    }
    public void init() {
        librarySetupModel.startSetup();
    }

    public void initiateSetup() {
        showText("\n\n Enter the library details  : " );
        showText("Enter the library name : ");
        String libraryName = scanner.next();
        showText("Enter the library email : ");
        String emailId = scanner.next();
        librarySetupModel.createLibrary(libraryName,emailId);
    }

    public void onSetupComplete() {
        showText("\n\nLibrary setup completed");
        showText("\n Library Name : "+
                librarySetupModel.library.getLibraryName());
        new LibraryMenuView().init();

    }



    public void onFail() {
        boolean choice = tryAgain();
        if(choice){
            initiateSetup();
        }else{
            showText("Exiting...........");
        }
    }
}
