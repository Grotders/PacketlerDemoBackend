package com.oguzcan.paketlerdemo.dto;

import lombok.Data;

@Data
public class PacketDto {
    private long packetId;
    private String title;
    private double price;

    private int data;
    private int minutes;
    private int sms;
}
