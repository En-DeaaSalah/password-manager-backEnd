package com.example.mainPackage.entites;


import javax.persistence.*;
import java.util.Arrays;

@Entity
public class Record {

    @Id
    @SequenceGenerator(
            name = "record_sequence",
            sequenceName = "record_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator ="record_sequence"

    )
    @Column(
            updatable = false
    )
    private Long id;
    @Column(columnDefinition = "text")
    private String title;
    @Column(columnDefinition = "text")
    private String email;
    @Column(columnDefinition = "text")
    private String password;
    @Column(columnDefinition = "text")
    private String description;



    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "user_id")
    private Users user;

    public Record() {
    }

    public void updateRecord(Record item) {

        this.description=item.getDescription();
        this.id=item.getId();
        this.email=item.getEmail();
        this.password=item.getPassword();
        this.title=item.getTitle();

    }

    public Record(Long id, String title, String email, String password, String description, Users user) {
        this.id = id;
        this.title = title;
        this.email = email;
        this.password = password;
        this.description = description;
        this.user = user;
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

    public Long getId() {
        return id;
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


    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                '}';
    }
}
