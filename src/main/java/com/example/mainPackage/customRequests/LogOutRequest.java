package com.example.mainPackage.customRequests;

import java.security.PrivateKey;
import java.security.PublicKey;

public class LogOutRequest extends  Request<LogOutRequest> {

    @Override
    public LogOutRequest encrypt(PublicKey request) {
        return null;
    }

    @Override
    public LogOutRequest decrypt(PrivateKey request) {
        return null;
    }

    @Override
    public boolean verifySign(PublicKey publicKey) {
        return false;
    }
}
