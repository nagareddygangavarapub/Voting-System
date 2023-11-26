package com.example.votingsystem;

public class Users {
    public String Name,Phone,Email,Adhar;
    public int vote;

    public Users() {
    }

    public Users(String firstname, String phone, String email, String adhar,int vote) {
        this.Name = firstname;
        this.Phone = phone;
        this.Email = email;
        this.Adhar = adhar;
        this.vote=vote;
    }
}
