package com.example.mainPackage.entites;


import com.example.mainPackage.cryptography.asymmericCipers.RSA;
import com.example.mainPackage.customResponses.AddItemResponse;

import javax.persistence.*;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Users {


    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"

    )
    @Column(
            updatable = false
    )
    private Long id;
    @Column(columnDefinition = "text")
    private String email;
    @Column(columnDefinition = "text")
    private String password;
    @Column(columnDefinition = "text")
    private String name;


    private Boolean isLogin = false;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_profile")
    private Profile profile;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    private List<Record> accounts;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    private List<SecureKey> secureKeys;


    public List<Record> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Record> accounts) {
        this.accounts = accounts;
    }

    public Users() {

        secureKeys = new ArrayList<>();
        accounts = new ArrayList<>();
        secureKeys = new ArrayList<>();
        profile = new Profile();

    }

    public Users(String email, String password, String name, Profile profile, List<Record> accounts, List<SecureKey> secureKeys) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.profile = profile;
        this.accounts = accounts;
        this.secureKeys = secureKeys;
        secureKeys = new ArrayList<>();
        accounts = new ArrayList<>();
        secureKeys = new ArrayList<>();
        profile = new Profile();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SecureKey> getSecureKeys() {
        return secureKeys;
    }

    public void setSecureKeys(List<SecureKey> secureKeys) {
        this.secureKeys = secureKeys;
    }

    public Boolean getLogin() {
        return isLogin;
    }

    public void setLogin(Boolean login) {
        isLogin = login;
    }

    public Long getId() {
        return id;
    }

    public void addKey(SecureKey key) {

        secureKeys.add(key);

    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", isLogin=" + isLogin +
                ", profile=" + profile +
                '}';
    }


    public Users encrypt(PublicKey key) {

        Users user = new Users();
        user.setName(RSA.encrypt(this.name, key));
        user.setId(this.id);
        user.setProfile(this.profile);
        user.setAccounts(this.accounts);
        user.setSecureKeys(this.secureKeys);
        user.setPassword(RSA.encrypt(this.password, key));
        user.setEmail(RSA.encrypt(this.email, key));

        return user;
    }


    public Users decrypt(PrivateKey key) {

        Users user = new Users();
        user.setName(RSA.decrypt(this.name, key));
        user.setId(this.id);
        user.setProfile(this.profile);
        user.setAccounts(this.accounts);
        user.setSecureKeys(this.secureKeys);
        user.setPassword(RSA.decrypt(this.password, key));
        user.setEmail(RSA.decrypt(this.email, key));

        return user;
    }


}
