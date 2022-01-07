package com.example.mainPackage.customRequests;

import com.example.mainPackage.cryptography.asymmericCipers.RSA;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class AddItemRequest extends Request<AddItemRequest> {


    private String title;

    private String email;

    private String password;

    private String description;


    public AddItemRequest(Long userId, String title, String email, String password, String description) {
        this.userId = userId;
        this.title = title;
        this.email = email;
        this.password = password;
        this.description = description;
    }

    public AddItemRequest() {
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public AddItemRequest encrypt(PublicKey key) {
        return null;
    }

    @Override
    public AddItemRequest decrypt(PrivateKey key) {
        AddItemRequest result = new AddItemRequest();

        result.setUserId(this.userId);
        result.setDescription(RSA.decrypt(this.description, key));
        result.setTitle(RSA.decrypt(this.title, key));
        result.setEmail(RSA.decrypt(this.email, key));
        result.setPassword(RSA.decrypt(this.password, key));
        return result;
    }

    @Override
    public boolean verifySign(PublicKey publicKey) {
        try {

            Signature theirSig = Signature.getInstance("SHA1withRSA");
            theirSig.initVerify(publicKey);
            theirSig.update(this.description.getBytes());
            theirSig.update(this.title.getBytes());
            theirSig.update(this.email.getBytes());
            theirSig.update(this.password.getBytes());

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
        return "AddItemRequest{" +
                "title='" + title + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", description='" + description + '\'' +
                ", userId=" + userId +
                '}';
    }
}
