package com.example.mainPackage.customRequests;

import com.example.mainPackage.cryptography.asymmericCipers.RSA;

import java.security.PrivateKey;
import java.security.PublicKey;

public class GetAllItemRequest extends Request<GetAllItemRequest> {


    @Override
    public GetAllItemRequest encrypt(PublicKey request) {
        return null;
    }

    @Override
    public GetAllItemRequest decrypt(PrivateKey request) {
        GetAllItemRequest result = new GetAllItemRequest();


        result.setUserId(Long.valueOf(RSA.decrypt(String.valueOf(this.userId), request)));

        return result;
    }

    @Override
    public boolean verifySign(PublicKey publicKey) {
        return false;
    }
}
