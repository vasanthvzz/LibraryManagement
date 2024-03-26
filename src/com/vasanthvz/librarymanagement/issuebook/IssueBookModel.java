package com.vasanthvz.librarymanagement.issuebook;

import com.vasanthvz.librarymanagement.datalayer.LibraryDatabase;
import com.vasanthvz.librarymanagement.librarymenu.LibraryMenuView;
import com.vasanthvz.librarymanagement.model.Book;
import com.vasanthvz.librarymanagement.model.User;
import com.vasanthvz.librarymanagement.model.UserBook;

import java.util.List;
import java.util.Map;

public class IssueBookModel {
    private IssueBookView issueBookView;
    private LibraryDatabase libraryDatabase;
    IssueBookModel(IssueBookView issueBookView) {
        this.issueBookView = issueBookView;
        libraryDatabase = LibraryDatabase.getInstance();
    }

    void redirectChoice(String choice) {
        switch (choice){
            case "1":{//view all books ---STATUS : COMPLETED
                viewAllBooks();
                break;

            }
            case "2":{//issue a book     STATUS : COMPLETED
                issueBook();
                break;
            }
            case "3":{//view issued books ---STATUS : COMPLETED
                viewIssuedBooks();
                break;
            }
            case "4":{//return a book   ----STATUS : INCOMPLETE
                returnBook();
                break;
            }
            case "0":{//go back
                new LibraryMenuView().init();
                break;
            }
            default:{
                if(issueBookView.tryAgain()){
                    issueBookView.showMenu();
                }else{
                    new LibraryMenuView().init();
                }
            }
        }
    }

    private void returnBook() {
        int bookId = issueBookView.getBookId();
        int userId = issueBookView.getUserId();
        boolean isBookIssued = LibraryDatabase.getInstance().returnBook(userId,bookId);
        if(isBookIssued){
            issueBookView.showText("book has been returned successfully");
            issueBookView.showMenu();
        }else{
            if(issueBookView.tryAgain()){
                issueBook();
            }else {
                issueBookView.showMenu();
            }
        }
    }

    private void viewIssuedBooks() {
        issueBookView.showTitle("View Issued Books");
        issueBookView.showText("User id\t\tUserName\t\tBookId\t\tBookName");
        if(libraryDatabase.getUserBookList()==null){
            issueBookView.showMenu();
            return;
        }
        for(UserBook userBook : libraryDatabase.getUserBookList()){
            if(userBook.getBooks()==null){
                continue;
            }
            for(Book book : userBook.getBooks()){
                System.out.println(userBook.getUser().getId()+"\t\t"+userBook.getUser().getName()+"\t\t"+book.getId()+"\t\t"+book.getName());
            }
        }
        issueBookView.showMenu();
    }

    private void viewAllBooks() {
        issueBookView.showText("Name\t\tAuthor\t\tEdition\t\tAvailability");
        for(Book book :LibraryDatabase.getInstance().getAllBooks()){
            issueBookView.showText(book.getId()+book.getName()+"\t\t"+
                    book.getAuthor()+ "\t\t"+book.getEdition()+"\t\t"
                    +book.isAvailable());
        }
        issueBookView.showMenu();
    }

    void issueBook() {
        int bookId = issueBookView.getBookId();
        int userId = issueBookView.getUserId();
        boolean isBookIssued = LibraryDatabase.getInstance().issueBookToUser(userId,bookId);
        if(isBookIssued){
            issueBookView.showText("book has been issued to successfully");
            issueBookView.showMenu();
        }else{
            if(issueBookView.tryAgain()){
                issueBook();
            }else {
                issueBookView.showMenu();
            }
        }
    }
}
