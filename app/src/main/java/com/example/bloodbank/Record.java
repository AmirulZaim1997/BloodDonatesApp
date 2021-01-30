package com.example.bloodbank;

public class Record {

    String name;
    String location;
    String state;
    String date;
    String status;

    public Record() {
    }

    public Record(String name, String location,String state, String date, String status) {
        this.name = name;
        this.location = location;
        this.state = state;
        this.date = date;
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
