package com.oguzcan.paketlerdemo.dto;

import java.util.Date;

public class CustomerRemainingDataDto extends CustomerDto {

    private Date expireDate;
    private double remainingData;
    private double remainingMinutes;
    private double remainingSMS;

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

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

    public double getRemainingSMS() {
        return remainingSMS;
    }

    public void setRemainingSMS(double remainingSMS) {
        this.remainingSMS = remainingSMS;
    }

    @Override
    public String toString() {
        return "UserRemainingDataDto{" +
                "expireDate=" + expireDate +
                ", remainingData=" + remainingData +
                ", remainingMinutes=" + remainingMinutes +
                ", remainingSMS=" + remainingSMS +
                '}';
    }
}
