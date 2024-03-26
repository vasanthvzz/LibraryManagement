package com.vasanthvz.librarymanagement.model;

public class Counter {
    private int bookCounter;
    private int userCounter;

    public Counter(int bookCounter, int userCounter) {
        this.bookCounter = bookCounter;
        this.userCounter = userCounter;
    }

    public int getBookCounter() {
        return bookCounter;
    }
    public void incrementBookCounter(){
        bookCounter++;
    }
    public void decrementBookCounter(){
        bookCounter--;
    }
    public void incrementUserCounter(){
        userCounter++;
    }
    public void decrementUserCounter(){
        userCounter--;
    }

    public void setBookCounter(int bookCounter) {
        this.bookCounter = bookCounter;
    }

    public int getUserCounter() {
        return userCounter;
    }

    public void setUserCounter(int userCounter) {
        this.userCounter = userCounter;
    }
}
