package com.vasanthvz.librarymanagement.model;

import java.util.ArrayList;
import java.util.List;

public class UserBook {
    private User user;
    private List<Book> books;

    public User getUser() {
        if(user == null){
            user = new User();
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Book> getBooks() {
        return books;
    }


    public void addBook(Book book) {
        if(books==null){
            books = new ArrayList<>();
        }
        books.add(book);
    }
}
