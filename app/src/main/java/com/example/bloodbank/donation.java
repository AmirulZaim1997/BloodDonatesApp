package com.example.bloodbank;

public class donation {


    String location;
    String event ;
    String date;
    String time ;
    String state;

    public donation() {
    }

    public donation(String location, String event, String date, String time, String state) {
        this.location = location;
        this.event = event;
        this.date = date;
        this.time = time;
        this.state = state;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
