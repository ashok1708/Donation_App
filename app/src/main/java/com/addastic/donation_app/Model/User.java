package com.addastic.donation_app.Model;

public class User {

    private String Name;
    private String Mail;

    public User() {
    }

    public User(String name, String mail) {
        Name = name;
        Mail = mail;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMail() {
        return Mail;
    }

    public void setmail(String mail) {
        Mail = mail;
    }
}


