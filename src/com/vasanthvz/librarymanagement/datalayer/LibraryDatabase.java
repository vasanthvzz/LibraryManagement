package com.vasanthvz.librarymanagement.datalayer;
import com.vasanthvz.librarymanagement.model.Book;
import com.vasanthvz.librarymanagement.model.Library;
import com.vasanthvz.librarymanagement.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LibraryDatabase {
    private static LibraryDatabase libraryDatabase;
    private static int bookCounter = 1000;
    private static int userCounter = 100;
    private Library library;
    private static final List<Book> bookList = new ArrayList<>();
    private static final List<User> userList = new ArrayList<>();
    private static HashMap<User,List<Book>> userBookMap;
    public static LibraryDatabase getInstance(){
        if(libraryDatabase == null){
            libraryDatabase = new LibraryDatabase();
        }
        if(userBookMap==null){
            userBookMap = new HashMap<>();
        }
        return libraryDatabase;
    }
    public Library getLibrary(){
        return library;
    }
    public Library insertLibrary(Library library2){
        this.library = library2;
        this.library.setLibraryId(1);
        return library;
    }

    public HashMap<User, List<Book>> getUserBookMap() {
        return userBookMap;
    }

    public List<Book> getAllBooks(){
        return bookList;
    }
    public Book getBook(int bookId){
        for(Book book : bookList){
            if(book.getId()==bookId){
                return book;
            }
        }
        return null;
    }
    public boolean isBookAvailable(int bookId){
        Book book = getBook(bookId);
        if(book==null){
            return false;
        }
        return book.getCount() > 0;
    }


    public boolean issueBookToUser(int userId,int bookId){
        User user = getUser(userId);
        Book book = getBook(bookId);
        if(book == null){
        System.out.println("book not available");
        return false;
        }
        if(user == null){
            System.out.println("User not found");
            return false;
        }
        if(userBookMap.containsKey(user)){
            userBookMap.get(user).add(book);
        }else{
            List<Book> list = new ArrayList<>();
            list.add(book);
            userBookMap.put(user,list);
        }
        book.decrementCount();
        return true;

    }
    public List<Book> searchBook(String name){
        List<Book> searchResult = new ArrayList<>();
        for(Book book : bookList){
            if(book.getName().contains(name)){
                searchResult.add(book);
            }
        }
        return searchResult;
    }

    public boolean insertBook(Book book) {
        boolean hasBook = false;
        for (Book addedBook : bookList) {
            if (addedBook.getName().equals(book.getName()) && addedBook.getAuthor().equals(book.getAuthor())) {
                hasBook = true;
                break;
            }
        }
        if (hasBook) {
            return false;
        } else {
            book.setId(libraryDatabase.getBookCounter());
            bookList.add(book);
            return true;
        }
    }
    private int getBookCounter(){
        bookCounter++;
        return bookCounter;
    }
    public List<User> getAllUser(){
        return userList;
    }

    public User getUser(int id){
        for(User user : userList){
            if(user.getId()==id){
                return user;
            }
        }
        return null;
    }
    public List<User> searchUser(String name){
        List<User> searchResult = new ArrayList<>();
        for(User user : userList){
            if(user.getName().contains(name)){
                searchResult.add(user);
            }
        }
        return searchResult;
    }

    public boolean insertUser(User user) {
        boolean hasUser = false;
        for (User addedUser : userList) {
            if (addedUser.getEmailId().equals(user.getEmailId())) {
                hasUser = true;
                break;
            }
        }
        if (hasUser) {
            return false;
        } else {
            user.setId(getUserCounter());
            userList.add(user);
            return true;
        }
    }

    private int getUserCounter(){
        userCounter++;
        return userCounter;
    }

    public boolean returnBook(int userId, int bookId) {
        User user = getUser(userId);
        System.out.println("userid : "+userId);
        Book book = getBook(bookId);
        if(book == null){
            System.out.println("book not available");
            return false;
        }
        if(user == null){
            System.out.println("User not found");
            return false;
        }
        if(userBookMap.containsKey(user)){
            userBookMap.get(user).remove(book);
        }else{
            System.out.println("user not found");
            return false;
        }
        book.incrementCount();
        return true;

    }
}

