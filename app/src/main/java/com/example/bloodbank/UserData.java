package com.example.bloodbank;

public class UserData {

    private String name;
    private String btype;
    private String email;

    public UserData() {
    }

    public UserData(String name, String btype, String email) {
        this.name = name;
        this.btype = btype;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBtype() {
        return btype;
    }

    public void setBtype(String btype) {
        this.btype = btype;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
