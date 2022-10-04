package com.oguzcan.paketlerdemo.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "packets")
public class Packet extends BaseAll{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long packetId;
    private String title;
    private double price;

    private int data;
    private int minutes;
    private int sms;

}
