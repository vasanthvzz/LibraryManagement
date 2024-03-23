package com.vasanthvz.librarymanagement.managebook;

import com.vasanthvz.librarymanagement.Model;
import com.vasanthvz.librarymanagement.View;
import com.vasanthvz.librarymanagement.datalayer.LibraryDatabase;
import com.vasanthvz.librarymanagement.model.Book;

import java.util.List;

public class ManageBookModel extends Model {
    private final ManageBookView manageBookView;
    private final LibraryDatabase libraryDatabase;
    public ManageBookModel(ManageBookView manageBookView) {
        this.manageBookView = manageBookView;
        libraryDatabase = LibraryDatabase.getInstance();
    }


    public void redirectChoice(String choice) {
        switch (choice){
            case "1":{
                manageBookView.getBookDetail();
                break;
            }
            case "2":{
                searchBook(manageBookView.getBookName());
                break;
            }
            case "3":{
                viewAllBook();
                break;
            }
            case "4":{
                editBook();
                break;
            }
            case "5":{
                deleteBook();
                break;
            }
            case "0":{
                manageBookView.goBack();
                break;
            }
            default:{
                if(manageBookView.tryAgain()){
                    manageBookView.showMenu();
                }else{
                    manageBookView.goBack();
                }
                break;
            }
        }
    }

    private void deleteBook() {
        int id = manageBookView.getBookId();
        Book book = libraryDatabase.getBook(id);
        if(book == null){
            manageBookView.showAlert("Book not found ");
        }else{
            libraryDatabase.getAllBooks().remove(book);
            manageBookView.showAlert("Book has been removed successfully");
        }
        manageBookView.showMenu();
    }

    private void viewAllBook() {
        manageBookView.showText("Name\t\tAuthor\t\tEdition\t\tCount");
        for(Book book :libraryDatabase.getAllBooks()){
            manageBookView.showText(book.getId()+book.getName()+"\t\t"+
                    book.getAuthor()+ "\t\t"+book.getEdition()+"\t\t"
                    +book.getCount());
        }
        manageBookView.showMenu();
    }

    public void addBook(String name, String author, Integer count, Integer edition) {

        Book book = new Book();
        book.setName(name);book.setAuthor(author);book.setCount(count);book.setEdition(edition);
        boolean bookAdded = libraryDatabase.insertBook(book);
        if(bookAdded){
            manageBookView.showText("Book is added with id : "+book.getId());
            manageBookView.successBookAdd();
        }else{
            manageBookView.showAlert("The book is already present !");
        }
    }
    void editBook(){
        int id =manageBookView.getBookId();
        Book book = libraryDatabase.getBook(id);
        if(book == null){
            manageBookView.showAlert("Book not found ");
        }else{
            manageBookView.showAlert("Book id : "+book.getId()+
                    "\tBook name : "+book.getName()+"\t"+book.getAuthor());
            manageBookView.getBookDetails(book);
        }
        manageBookView.showMenu();
    }
    void setBookDetails(String name , String author,Book book){
        if(! name.equals("0")){
            book.setName(name);
        }
        if(! author.equals("0")){
            book.setAuthor(author);
        }
        manageBookView.showText("Book has been update successfully");
        manageBookView.showMenu();
    }


    void searchBook(String name){
        List<Book> bookList = libraryDatabase.searchBook(name);
        manageBookView.showText("Name\t\tAuthor\t\tEdition\t\tCount");
        for(Book book : bookList){
            manageBookView.showText(book.getName()+"\t\t"+book.getAuthor()+
                    "\t\t"+book.getEdition()+"\t\t"+book.getCount());
        }
        manageBookView.showMenu();
    }


}
