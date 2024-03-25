package com.vasanthvz.librarymanagement.model;

import java.util.List;

public class UserBook {
    private User user;
    private List<Book> books;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
