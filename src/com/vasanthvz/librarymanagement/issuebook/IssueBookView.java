package com.vasanthvz.librarymanagement.issuebook;

import com.vasanthvz.librarymanagement.View;

public class IssueBookView extends View {
    private IssueBookModel issueBookModel;
    public IssueBookView(){
        issueBookModel = new IssueBookModel(this);
    }
    public void init(){
        showMenu();
    }

    void showMenu() {
        showTitle("Manage Issue Books");
        showText("""
                1. View all books
                2. Issue a book
                3. View Issued Books
                4. Return a book
                0. Go back
                """);
        issueBookModel.redirectChoice(scanner.next());
    }


    public int getBookId() {
        showText("Enter the book id :");
        return scanner.nextInt();
    }
    public int getUserId(){
        showText("Enter the user id : ");
        return scanner.nextInt();
    }
}
