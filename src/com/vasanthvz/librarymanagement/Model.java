package com.vasanthvz.librarymanagement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Model {
    public boolean isValidEmail(String email){
        //String regex = "^[A-Za-z0-9+_.-]+@(.+)$"; // Code provided by Bard

        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";//using rfc5322 pattern
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isValidName(String name){
        return name.length()>=3;
    }
    public boolean isValidPhoneNo(String phoneNo){
        return phoneNo.length()==10;
    }
}
