package com.oguzcan.paketlerdemo.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "admins")
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long adminId;

    private String name;
    private String email;
    private String password;
}
