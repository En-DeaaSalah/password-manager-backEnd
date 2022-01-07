package com.example.mainPackage.customResponses;

import com.example.mainPackage.entites.Users;

import java.io.Serializable;

public class RegistrationResponse implements Serializable {



    public Users user;

    public  String message;



    public RegistrationResponse(Users user, String message) {
        this.user = user;
        this.message = message;
    }



    public RegistrationResponse() {
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
