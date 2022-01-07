package com.example.mainPackage.entites;


import javax.persistence.*;

@Entity
public class SecureKey {

    @Id
    @SequenceGenerator(
            name = "secureKey_sequence",
            sequenceName = "secureKey_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "secureKey_sequence"

    )
    @Column(
            updatable = false
    )
    private Long id;

    @Column(columnDefinition = "text")
    private String Key;





    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "user_id")
    private Users user;


    public SecureKey(String key, Users user) {
        Key = key;

        this.user = user;
    }

    public SecureKey() {
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }


    @Override
    public String toString() {
        return "SecureKey{" +
                "id=" + id +
                ", Key='" + Key + '\'' +
                ", user=" + user +
                '}';
    }
}
