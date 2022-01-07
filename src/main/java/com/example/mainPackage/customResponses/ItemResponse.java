package com.example.mainPackage.customResponses;

import com.example.mainPackage.cryptography.asymmericCipers.RSA;
import com.example.mainPackage.entites.Record;

import java.io.Serializable;
import java.security.PublicKey;

public class ItemResponse implements Serializable {

    private Long id;

    private String title;

    private String email;

    private String password;

    private String description;


    public ItemResponse(Long id, String title, String email, String password, String description) {
        this.id = id;
        this.title = title;
        this.email = email;
        this.password = password;
        this.description = description;
    }


    public ItemResponse() {
    }
    public ItemResponse(Record record) {

        this.id = record.getId();
        this.title = record.getTitle();
        this.email = record.getEmail();
        this.password = record.getPassword();
        this.description = record.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "ItemResponse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


    public ItemResponse encrypt(PublicKey key) {
        ItemResponse result = new ItemResponse();


        result.setDescription(RSA.encrypt(this.description, key));
        result.setTitle(RSA.encrypt(this.title, key));
        result.setEmail(RSA.encrypt(this.email, key));
        result.setPassword(RSA.encrypt(this.password, key));
        return result;
    }
}
