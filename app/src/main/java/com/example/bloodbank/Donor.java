package com.example.bloodbank;

public class Donor {
    private String Name;
    private Integer PhoneNo;
    private Integer Age;
    private String BloodType;
    private String Status;
    private String Date;
    private String City;
    private String State;
    private String Email;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(Integer phoneNo) {
        PhoneNo = phoneNo;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    public String getBloodType() {
        return BloodType;
    }

    public void setBloodType(String bloodType) {
        BloodType = bloodType;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }


    public Donor(String name, Integer phoneNo, Integer age, String bloodType, String status, String date, String city, String state, String email) {
        Name = name;
        PhoneNo = phoneNo;
        Age = age;
        BloodType = bloodType;
        Status = status;
        Date = date;
        City = city;
        State = state;
        Email = email;
    }

    public Donor(){


        }


}
