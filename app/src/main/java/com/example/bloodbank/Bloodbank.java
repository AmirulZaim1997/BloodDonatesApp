package com.example.bloodbank;

public class Bloodbank {

    private String bankname;
    private String address;
    private String phone;
    private String state;
    private String latitud;
    private String longtitud;

    public Bloodbank() {
    }

    public Bloodbank(String bankname,String address,String phone,String state,String latitud,String longtitud){

        this.bankname = bankname;
        this.address = address;
        this.phone = phone;
        this.state = state;
        this.latitud = latitud;
        this.longtitud = longtitud;

    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongtitud() {
        return longtitud;
    }

    public void setLongtitud(String longtitud) {
        this.longtitud = longtitud;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
