package com.example.mainPackage.entites;

import javax.persistence.*;

@Entity
public class ShareOrder {

    @Id
    @SequenceGenerator(
            name = "shareOrder_sequence",
            sequenceName = "shareOrder_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "shareOrder_sequence"

    )
    @Column(
            updatable = false
    )
    private Long id;


    @ManyToOne(targetEntity = Record.class)
    @JoinColumn(name = "item_id")
    Record item;
    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "sours_user_id")
    Users soursUser;
    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "target_user_id")
    Users targetUser;

    @Column(columnDefinition = "text")

    String message;

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ShareOrder(Record item, Users soursUser, Users targetUser, String message) {
        this.id = id;
        this.item = item;
        this.soursUser = soursUser;
        this.targetUser = targetUser;
        this.message = message;
    }

    public Long getId() {
        return id;
    }


    public Record getItem() {
        return item;
    }

    public void setItem(Record item) {
        this.item = item;
    }

    public Users getSoursUser() {
        return soursUser;
    }

    public void setSoursUser(Users soursUser) {
        this.soursUser = soursUser;
    }

    public Users getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(Users targetUser) {
        this.targetUser = targetUser;
    }

    public ShareOrder() {
    }

    @Override
    public String toString() {
        return "ShareOrder{" +
                "id=" + id +
                ", item=" + item +
                ", soursUser=" + soursUser +
                ", targetUser=" + targetUser +
                ", message='" + message + '\'' +
                '}';
    }
}
