package com.example.test4;

public class Accounts {

    private int id;
    private String username;
    private String password;
    private String notes;
    private String danish;

    private String english;

    // Constructors
    public Accounts(String username) {
        this.username = username;
        this.password = password;
        this.notes = notes;
        this.danish = danish;
        this.english = english;
    }

    public Accounts() {

    }

    @Override
    public String toString() {
        return "Accounts{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", notes='" + notes + '\'' +
                ", danish='" + danish + '\'' +
                ", english='" + english + '\'' +
                '}';
    }


    public void set_id(int _id) {
        this.id = id;
    }

    public void set_username(String _username) {
        this.username = _username;
    }

    public void set_password(String _password) {
        this.password = _password;
    }

    public void set_notes(String _notes) {
        this.notes = _notes;
    }

    public void set_danish(String _danish) {
        this.danish = _danish;
    }

    public void set_english(String _english) {
        this.english = _english;
    }

    public int get_id() {
        return id;
    }

    public String get_username() {
        return username;
    }

    public String get_password () {
        return password;
    }
    public String get_notes () {
        return notes;
    }

    public String get_danish () {
        return danish;
    }

    public String get_english () {
        return english;
    }

}