package com.example.pbl;

public class Users {
    String name;
    String uid;
    String password;
    String email;

    public Users() {
    }

    public Users(String name, String uid, String password, String email) {
        this.name = name;
        this.uid = uid;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
