package com.oguzcan.paketlerdemo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {

    private Long userId;
    private String firstname;
    private String lastname;
    private Date birthdate;

    private String email;
    private String password;

    private Long packetId;
    private String packetName;
}
