package com.oguzcan.paketlerdemo.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "packets")
public class Packet extends BaseActorKeeper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "packet_id")
    private long packetId;
    private String title;
    private BigDecimal price;
    private double data;
    private double minutes;
    private int sms;

    @OneToMany(mappedBy = "packet")
    private Set<Customer> customers;

    public long getPacketId() {
        return packetId;
    }

    public void setPacketId(long packetId) {
        this.packetId = packetId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public double getData() {
        return data;
    }

    public void setData(double data) {
        this.data = data;
    }

    public double getMinutes() {
        return minutes;
    }

    public void setMinutes(double minutes) {
        this.minutes = minutes;
    }

    public int getSms() {
        return sms;
    }

    public void setSms(int sms) {
        this.sms = sms;
    }
}
