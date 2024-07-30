package org.example.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name="items")
public class Item {

    @Id
    @Column(name="id")
    int id;
    @Column(name="Ean")
    String ean;
    @Column(name="Name")
    String name;
    @Column(name="Location")
    String location;
    @Column(name="Qty")
    int qty;
    @Column(name="items_comment")
    String comment;
    @Column(name="Info_date")
    String date;
    @Column(name="AppId")
    String appId;
    @Column(name="UserId")
    String user;

    public Item(int id, String ean, String name, String location, int qty, String comment, String date, String appId, String user) {
        this.id = id;
        this.ean = ean;
        this.name = name;
        this.location = location;
        this.qty = qty;
        this.comment = comment;
        this.date = date;
        this.appId = appId;
        this.user = user;
    }

    public Item() {
    }
}
