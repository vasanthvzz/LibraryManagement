package com.vasanthvz.librarymanagement.datalayer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vasanthvz.librarymanagement.model.Book;
import com.vasanthvz.librarymanagement.model.Library;
import com.vasanthvz.librarymanagement.model.User;
import com.vasanthvz.librarymanagement.model.UserBook;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryDatabase {
    private static LibraryDatabase libraryDatabase;
    private static Gson gson;
    private static int bookCounter = 1000;
    private static int userCounter = 100;
    private Library library;
    private static List<Book> bookList = new ArrayList<>();
    private static List<User> userList = new ArrayList<>();
    private static HashMap<User,List<Book>> userBookMap;
    public static LibraryDatabase getInstance(){
        if(libraryDatabase == null){
            libraryDatabase = new LibraryDatabase();
        }
        if(gson == null){
            gson = new Gson();
        }
        if(userBookMap==null){
            userBookMap = new HashMap<>();
        }
        return libraryDatabase;
    }
    public Library getLibrary(){
        return library;
    }

    public void loadData(){
        String json = readData("books");
        Type listType = new TypeToken<List<Book>>(){}.getType();
        bookList = gson.fromJson(json, listType);
        json = readData("users");
        listType = new TypeToken<List<User>>(){}.getType();
        userList = gson.fromJson(json,listType);
        fromJson();

    }

    private String readData(String name){
        String json = "";
        try (Reader reader = new FileReader("data/"+name+".json")) {
            // Create a buffer to read the file content
            StringBuilder stringBuilder = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                stringBuilder.append((char) c);
            }
            json = stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public String mapToJson() {
        Gson gson = new Gson();
        List<UserBook> userBooks = new ArrayList<>();
        for (Map.Entry<User, List<Book>> entry : userBookMap.entrySet()) {
            UserBook userBook = new UserBook();
            userBook.setUser(entry.getKey());
            userBook.setBooks(entry.getValue());
            userBooks.add(userBook);
        }
        return gson.toJson(userBooks);
    }

    public void fromJson() {
        String json = readData("bookUserMap");
        Type type = new TypeToken<List<UserBook>>(){}.getType();
        List<UserBook> userBooks = gson.fromJson(json, type);
        if(userBooks==null){
            return;
        }
        for (UserBook userBook : userBooks) {
            userBookMap.put(userBook.getUser(), userBook.getBooks());
        }
    }

    public void saveData(){
        String json = gson.toJson(bookList);
        writeJson(json,"books");
        json = gson.toJson(userList);
        writeJson(json,"users");
        json = mapToJson();
        writeJson(json,"bookUserMap");
    }


    public void writeJson(String json,String name){
        try (FileWriter writer = new FileWriter("data/"+name+".json")) {
            writer.write(json);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setLibrary(Library library){
        this.library = library;
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

