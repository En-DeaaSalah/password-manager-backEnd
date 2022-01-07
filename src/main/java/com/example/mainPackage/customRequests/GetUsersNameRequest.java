package com.example.mainPackage.customRequests;

import java.security.PrivateKey;
import java.security.PublicKey;

public class GetUsersNameRequest extends Request<GetUsersNameRequest> {
    @Override
    public GetUsersNameRequest encrypt(PublicKey key) {
        return null;
    }

    @Override
    public GetUsersNameRequest decrypt(PrivateKey key) {
        return null;
    }

    @Override
    public boolean verifySign(PublicKey publicKey) {
        return false;
    }
}
