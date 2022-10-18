package com.oguzcan.paketlerdemo.entities;

import javax.persistence.*;

@Entity
@Table(name = "admins")
public class Admin extends User{
    private String fullName;
}
