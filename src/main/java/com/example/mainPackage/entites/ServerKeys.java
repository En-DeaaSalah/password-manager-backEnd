package com.example.mainPackage.entites;


import javax.persistence.*;

@Entity
public class ServerKeys {

    @Id
    @SequenceGenerator(
            name = "serverKeys_sequence",
            sequenceName = "serverKeys_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator ="serverKeys_sequence"

    )
    @Column(
            updatable = false
    )

    private Long id;

    @Column(columnDefinition = "text")

    private String publicKey;

    @Column(columnDefinition = "text")
    private String privateKey;


    public ServerKeys(String publicKey, String privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public ServerKeys() {
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}
