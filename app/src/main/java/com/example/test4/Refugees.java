package com.example.test4;

public class Refugees {
    private String ID;
    private String username;
    private String password;
    private String number;
    private String language;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Refugees(String ID, String username, String password) {
        this.ID = ID;
        this.username = username;
        this.password = password;

    }
}
