package com.example.mainPackage.customRequests;


import com.example.mainPackage.cryptography.asymmericCipers.RSA;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class GetUserKeyRequest extends Request<GetUserKeyRequest> {


    String userName;


    public GetUserKeyRequest(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public GetUserKeyRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public GetUserKeyRequest encrypt(PublicKey key) {
        return null;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public GetUserKeyRequest decrypt(PrivateKey key) {

        GetUserKeyRequest result = new GetUserKeyRequest();

        result.setUserId(this.userId);
        result.setUserName(RSA.decrypt(this.getUserName(), key));

        return result;
    }

    @Override
    public boolean verifySign(PublicKey publicKey) {
        try {

            Signature theirSig = Signature.getInstance("SHA1withRSA");
            theirSig.initVerify(publicKey);
            theirSig.update(this.getUserName().getBytes());


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
        return "GetUserKeyRequest{" +
                "UserName='" + userName + '\'' +
                ", userId=" + userId +
                '}';
    }
}
