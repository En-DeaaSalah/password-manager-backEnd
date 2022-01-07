package com.example.mainPackage.customRequests;

import com.example.mainPackage.cryptography.asymmericCipers.RSA;

import java.security.PrivateKey;
import java.security.PublicKey;

public class DeleteItemRequest extends Request<DeleteItemRequest> {


    public Long ItemId;

    public DeleteItemRequest() {
    }

    public DeleteItemRequest(Long userId, Long itemId) {
        this.userId = userId;
        ItemId = itemId;
    }


    public Long getItemId() {
        return ItemId;
    }

    public void setItemId(Long itemId) {
        ItemId = itemId;
    }

    @Override
    public String toString() {
        return "DeleteItemRequest{" +
                "ItemId=" + ItemId +
                ", userId=" + userId +
                '}';
    }


    @Override
    public DeleteItemRequest encrypt(PublicKey key) {

        return null;
    }

    @Override
    public DeleteItemRequest decrypt(PrivateKey key) {

        DeleteItemRequest result = new DeleteItemRequest();

        result.setItemId(Long.valueOf(RSA.decrypt(String.valueOf(this.ItemId), key)));

        result.setUserId(Long.valueOf(RSA.decrypt(String.valueOf(this.userId), key)));

        return result;

    }

    @Override
    public boolean verifySign(PublicKey publicKey) {
        return false;
    }
}
