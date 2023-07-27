package com.example.pbl;

public class aman_msg {
    String message;
    String uid;

    public aman_msg() {
    }

    public aman_msg(String message, String uid) {
        this.message = message;
        this.uid = uid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
