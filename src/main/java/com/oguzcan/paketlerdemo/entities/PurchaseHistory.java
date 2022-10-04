package com.oguzcan.paketlerdemo.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "purchase_histories")
public class PurchaseHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseId;
    private Long userID;
    private Long packetId;
    private Date purchaseDate;
}
