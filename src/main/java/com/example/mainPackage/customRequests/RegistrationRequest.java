package com.example.mainPackage.customRequests;

import com.example.mainPackage.cryptography.asymmericCipers.RSA;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class RegistrationRequest extends Request<RegistrationRequest> {


    private String email;


    private String password;


    private String publicKey;


    private String userName;


    public RegistrationRequest(String email, String password, String publicKey, String userName) {
        this.email = email;
        this.password = password;
        this.publicKey = publicKey;
        this.userName = userName;
    }

    public RegistrationRequest() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }


    @Override
    public String toString() {
        return "RegistrationRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", publicKey='" + publicKey + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    @Override
    public RegistrationRequest encrypt(PublicKey key) {
        return null;
    }

    @Override
    public RegistrationRequest decrypt(PrivateKey key) {
        RegistrationRequest result = new RegistrationRequest();
        result.setPublicKey(this.getPublicKey());
        result.setEmail(RSA.decrypt(this.email, key));
        result.setPassword(RSA.decrypt(this.password, key));
        result.setUserName(RSA.decrypt(this.userName, key));
        return result;
    }

    @Override
    public boolean verifySign(PublicKey publicKey) {
        try {

            Signature theirSig = Signature.getInstance("SHA1withRSA");
            theirSig.initVerify(publicKey);
            theirSig.update(this.email.getBytes());
            theirSig.update(this.password.getBytes());
            theirSig.update(this.userName.getBytes());

            if (theirSig.verify(userSign.getBytes())) {
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
}
