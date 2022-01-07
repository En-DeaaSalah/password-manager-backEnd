package com.example.mainPackage.customRequests;

import com.example.mainPackage.cryptography.asymmericCipers.RSA;

import java.security.PrivateKey;
import java.security.PublicKey;

public class GetItemRequest extends Request<GetItemRequest> {



    private Long itemId;

    public GetItemRequest() {
    }

    public GetItemRequest(Long userId, Long itemId) {
        this.userId = userId;
        this.itemId = itemId;
    }


    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "GetItemRequest{" +
                "ItemId=" + itemId +
                ", userId=" + userId +
                '}';
    }


    @Override
    public GetItemRequest encrypt(PublicKey request) {
        return null;
    }

    @Override
    public GetItemRequest decrypt(PrivateKey key) {

        GetItemRequest result = new GetItemRequest();

        result.setItemId(Long.valueOf(RSA.decrypt(String.valueOf(this.itemId), key)));

        result.setUserId(Long.valueOf(RSA.decrypt(String.valueOf(this.userId), key)));

        return result;
    }

    @Override
    public boolean verifySign(PublicKey publicKey) {
        return false;
    }
}
