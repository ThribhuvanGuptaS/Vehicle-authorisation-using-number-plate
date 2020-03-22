package com.example.friendchat;

public class Users {

    private String Name;
    private String ContactNumber;
    private String CarNumber;

    public Users () {
    }

    public Users ( String name , String contactNumber , String carNumber ) {
        Name = name;
        ContactNumber = contactNumber;
        CarNumber = carNumber;
    }

    public String getName () {
        return Name;
    }

    public void setName ( String name ) {
        Name = name;
    }

    public String getContactNumber () {
        return ContactNumber;
    }

    public void setContactNumber ( String contactNumber ) {
        ContactNumber = contactNumber;
    }

    public String getCarNumber () {
        return CarNumber;
    }

    public void setCarNumber ( String carNumber ) {
        CarNumber = carNumber;
    }
}
