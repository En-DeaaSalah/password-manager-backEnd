package com.example.mainPackage.entites;



import javax.persistence.*;

@Entity
public class Profile {

    @Id
    @SequenceGenerator(
            name = "profile_sequence",
            sequenceName = "profile_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator ="profile_sequence"

    )
    @Column(
            updatable = false
    )
    private Long id;

    private String phone;

    private String photoUrl;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user")

    private Users owner;

    public Profile(Users owner) {
        this.owner = owner;
    }

    public Profile() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Users getOwner() {
        return owner;
    }

    public void setOwner(Users owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", owner=" + owner +
                '}';
    }
}
