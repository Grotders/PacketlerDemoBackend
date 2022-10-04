package com.oguzcan.paketlerdemo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserRemainingDataDto extends UserDto{

    private Date expireDate;
    private double remainingData;
    private double remainingMinutes;
    private double remainingSMS;
}
