package com.example.mainPackage.customRequests;


import com.example.mainPackage.cryptography.asymmericCipers.RSA;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class SharedItemRequest extends Request<SharedItemRequest> {


    String userSharedName;


    Long itemID;

    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public SharedItemRequest(Long userId, String userSharedName, Long itemID, String message) {
        this.userId = userId;
        this.userSharedName = userSharedName;
        this.itemID = itemID;
        this.message = message;
    }

    public SharedItemRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public SharedItemRequest encrypt(PublicKey key) {
        return null;
    }

    public String getUserSharedName() {
        return userSharedName;
    }

    public void setUserSharedName(String userSharedName) {
        this.userSharedName = userSharedName;
    }

    public Long getItemID() {
        return itemID;
    }

    public void setItemID(Long itemID) {
        this.itemID = itemID;
    }


    public SharedItemRequest decrypt(PrivateKey key) {
        SharedItemRequest result = new SharedItemRequest();

        result.setUserId(this.userId);
        result.setItemID(this.getItemID());
        result.setUserSharedName(RSA.decrypt(this.userSharedName, key));
        result.setMessage(RSA.decrypt(this.message, key));
        return result;
    }

    @Override
    public boolean verifySign(PublicKey publicKey) {
        try {

            Signature theirSig = Signature.getInstance("SHA1withRSA");
            theirSig.initVerify(publicKey);
            theirSig.update(this.message.getBytes());
            theirSig.update(this.userSharedName.getBytes());

            if (theirSig.verify(this.userSign.getBytes())) {
                System.out.println("Signature checks out.");
            } else {
                System.out.println("Signature failed. Possible imposter found.");
            }

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "SharedItemRequest{" +
                "userId=" + userId +
                ", userSharedName='" + userSharedName + '\'' +
                ", itemID=" + itemID +
                ", message='" + message + '\'' +
                '}';
    }
}
