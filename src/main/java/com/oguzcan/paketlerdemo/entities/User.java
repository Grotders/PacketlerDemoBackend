package com.oguzcan.paketlerdemo.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
@Data
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    private String firstname;
    private String lastname;
    private Date birthdate;
    private String email;
    private String password;
}
