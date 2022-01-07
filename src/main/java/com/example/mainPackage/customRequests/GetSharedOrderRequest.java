package com.example.mainPackage.customRequests;

import java.security.PrivateKey;
import java.security.PublicKey;

public class GetSharedOrderRequest extends Request<GetSharedOrderRequest> {


    public GetSharedOrderRequest() {
    }


    @Override
    public GetSharedOrderRequest encrypt(PublicKey key) {
        return null;
    }




    @Override
    public GetSharedOrderRequest decrypt(PrivateKey key) {
        return null;
    }

    @Override
    public boolean verifySign(PublicKey publicKey) {
        return false;
    }
}
