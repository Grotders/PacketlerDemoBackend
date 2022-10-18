package com.oguzcan.paketlerdemo.entities;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Embeddable
public class PacketBalance {


    private double remainingData;
    private double remainingMinutes;
    private int remainingSMS;
    private ZonedDateTime expireDate;



    public double getRemainingData() {
        return remainingData;
    }

    public void setRemainingData(double remainingData) {
        this.remainingData = remainingData;
    }

    public double getRemainingMinutes() {
        return remainingMinutes;
    }

    public void setRemainingMinutes(double remainingMinutes) {
        this.remainingMinutes = remainingMinutes;
    }

    public int getRemainingSMS() {
        return remainingSMS;
    }

    public void setRemainingSMS(int remainingSMS) {
        this.remainingSMS = remainingSMS;
    }

    public ZonedDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(ZonedDateTime expireDate) {
        this.expireDate = expireDate;
    }
}
