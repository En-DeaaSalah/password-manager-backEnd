package com.example.mainPackage.customRequests;

import java.security.PrivateKey;
import java.security.PublicKey;

public class LoginRequest extends  Request<LoginRequest> {



    public  String  email;

    public String password;


    @Override
    public LoginRequest encrypt(PublicKey request) {
        return null;
    }

    @Override
    public LoginRequest decrypt(PrivateKey request) {
        return null;
    }

    @Override
    public boolean verifySign(PublicKey publicKey) {
        return false;
    }
}
