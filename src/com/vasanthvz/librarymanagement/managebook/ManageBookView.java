package com.vasanthvz.librarymanagement.managebook;

import com.vasanthvz.librarymanagement.View;
import com.vasanthvz.librarymanagement.librarymenu.LibraryMenuView;
import com.vasanthvz.librarymanagement.model.Book;

public class ManageBookView extends View {
    private final ManageBookModel manageBookModel;
    public ManageBookView(){
        manageBookModel = new ManageBookModel(this);
    }

    public void init(){
        showTitle("Manage Books Menu");
        showMenu();
    }
    void showMenu() {
        showText("1.Add Book");
        showText("2.Search Book");
        showText("3.View all books");
        showText("4.Edit Books");
        showText("5.Delete book");
        showText("0.Go back");
        manageBookModel.redirectChoice(scanner.next());
    }

    public void goBack() {
        new LibraryMenuView().init();
    }

    public void getBookDetail() {
        showTitle("Add book Menu");
        System.out.print("Enter the name of the book : ");
        String name = scanner.next();
        System.out.print("Enter the author of the book : ");
        String author = scanner.next();
        showText("Enter the count of the book : ");
        Integer count = scanner.nextInt();
        showText("Enter the edition of the book : ");
        Integer edition = scanner.nextInt();
        manageBookModel.addBook(name, author, count, edition);
    }



    public int getBookId() {
        showText("Enter the id of the book : ");
        return scanner.nextInt();
    }


    public void successBookAdd() {
        showText("book has been added ");
        if(checkForAddNewItems("Book")){
            getBookDetail();
        }else {
            showMenu();
        }
    }

    public String getBookName() {
        showText("Enter the name of the book");
        return scanner.next();

    }

    public void getBookDetails(Book book) {
        showText("Enter the book name : (press '0' if you dont want to change)");
        String name = scanner.next();
        showText("Enter the name of book author :  (press '0' if you dont want to change)");
        String authorName = scanner.next();
        manageBookModel.setBookDetails(name,authorName,book);

    }
}
