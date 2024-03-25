package com.vasanthvz.librarymanagement.librarysetup;

import com.vasanthvz.librarymanagement.Model;
import com.vasanthvz.librarymanagement.datalayer.LibraryDatabase;
import com.vasanthvz.librarymanagement.model.Library;

public class LibrarySetupModel extends Model {
    private LibrarySetupView librarySetupView;
    Library library= LibraryDatabase.getInstance().getLibrary();
    LibrarySetupModel(LibrarySetupView librarySetupView) {
        this.librarySetupView = librarySetupView;
    }
    public void startSetup(){
        if(library==null){
            librarySetupView.initiateSetup();
        }else{
            librarySetupView.onSetupComplete();
        }
    }

    public void createLibrary(String libraryName, String emailId) {
        if(isValidName(libraryName) && isValidEmail(emailId)){
            this.library = new Library();
            this.library.setLibraryName(libraryName);
            this.library.setEmailId(emailId);
            LibraryDatabase.getInstance().setLibrary(library);
            librarySetupView.onSetupComplete();
        }else if(isValidName(libraryName)){
            librarySetupView.showAlert("Invalid Email");
            librarySetupView.onFail();
        }else if(isValidEmail(emailId)){
            librarySetupView.showAlert("Invalid name");
            librarySetupView.onFail();
        }else{
            librarySetupView.showAlert("Invalid name and email");
            librarySetupView.onFail();
        }

    }
}
