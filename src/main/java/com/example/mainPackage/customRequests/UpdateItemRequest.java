package com.example.mainPackage.customRequests;

import com.example.mainPackage.cryptography.asymmericCipers.RSA;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class UpdateItemRequest extends Request<UpdateItemRequest> {


    private Long itemId;

    private String title;

    private String email;

    private String password;

    private String description;


    public UpdateItemRequest(Long userId, Long itemId, String title, String email, String password, String description) {
        this.userId = userId;
        this.itemId = itemId;
        this.title = title;
        this.email = email;
        this.password = password;
        this.description = description;
    }


    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public UpdateItemRequest() {
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
    public String toString() {
        return "UpdateItemRequest{" +
                "userId=" + userId +
                ", itemId=" + itemId +
                ", title='" + title + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public UpdateItemRequest encrypt(PublicKey key) {
        return null;
    }

    @Override
    public UpdateItemRequest decrypt(PrivateKey key) {
        UpdateItemRequest result = new UpdateItemRequest();

        result.setUserId(this.userId);
        result.setItemId(this.getItemId());
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
            userSign = new String(theirSig.sign());

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
}
