package com.vasanthvz.librarymanagement.datalayer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vasanthvz.librarymanagement.model.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LibraryDatabase {
    private static LibraryDatabase libraryDatabase;
    private static Gson gson;
    private static Counter counter;
    private Library library;
    private static List<Book> bookList = new ArrayList<>();
    private static List<User> userList = new ArrayList<>();
    private static List<UserBook> userBookList = new ArrayList<>();
    public static LibraryDatabase getInstance(){
        if(libraryDatabase == null){
            libraryDatabase = new LibraryDatabase();
        }
        if(gson == null){
            gson = new Gson();
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
        json = readData("bookUser");
        Type list = new TypeToken<List<UserBook>>(){}.getType();
        userBookList = gson.fromJson(json,list);
        //fromJson();
        nullcheck();
        counter = readCountersFromFile();
    }

    private void nullcheck() {
        if(bookList==null){bookList = new ArrayList<>();}
        if(userList==null){userList=new ArrayList<>();};
        if(userBookList==null){userBookList=new ArrayList<>();};
    }

    private String readData(String name){
        String json = "";
        try (Reader reader = new FileReader("data/"+name+".json")) {
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




    public void saveData(){
        String json = gson.toJson(bookList);
        writeJson(json,"books");
        json = gson.toJson(userList);
        writeJson(json,"users");
        json = gson.toJson(userBookList);
        writeJson(json,"bookUser");
        json = gson.toJson(counter);
        writeJson(json,"counters");
        //writeCountersToFile(counter);
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

    public List<UserBook> getUserBookList(){
        return userBookList;
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
        for(UserBook userBook : userBookList){
            if(userBook.getUser().equals(user)){
                userBook.addBook(book);
                return true;
            }
        }
        UserBook userBook = new UserBook();
        userBook.setUser(user);
        userBook.addBook(book);
        userBookList.add(userBook);
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
        if(bookList==null){
            bookList.add(book);
            return true;
        }
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
        counter.incrementBookCounter();
        return counter.getBookCounter();
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
        counter.incrementUserCounter();
        return counter.getUserCounter();
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
        boolean hasBook = false;
        List<Book>currentUserBook = getUserBookList(user);
        if(currentUserBook == null){
            System.out.println("User does not have any books");
        }else{
            if(currentUserBook.contains(book)){
                currentUserBook.remove(book);
                System.out.println("Book has been returned successfully");
            }else {
                System.out.println("book not found in user's section");
            }
        }
        if(hasBook){
            book.incrementCount();
            return true;
        }else{
            System.out.println("Book not assigned to the user");
        }
        return false;
    }

    public List<Book> getUserBookList(User user){
        for(UserBook userBook : userBookList ){
            if(user.getId() == userBook.getUser().getId()){
                return userBook.getBooks();
            }
        }
        return null;
    }
    public static Counter readCountersFromFile() {
        try {
            File file = new File("data/counters.json");
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                byte[] data = new byte[(int) file.length()];
                fis.read(data);
                fis.close();

                String jsonString = new String(data);
                Gson gson = new Gson();
                return gson.fromJson(jsonString, Counter.class);
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        return new Counter(1000,100);
    }
//    public static void writeCountersToFile(Counter counters) {
//        try {
//            File file = new File("data/counters.json"); // Replace with your desired file path
//            Gson gson = new Gson();
//            String jsonString = gson.toJson(counters);
//
//            FileOutputStream fos = new FileOutputStream(file);
//            fos.write(jsonString.getBytes());
//            fos.close();
//        } catch (IOException e) {
//            // Handle exceptions gracefully (e.g., log the error)
//            e.printStackTrace();
//        }
//    }


}

