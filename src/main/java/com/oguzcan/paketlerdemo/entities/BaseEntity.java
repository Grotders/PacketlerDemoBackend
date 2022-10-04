package com.oguzcan.paketlerdemo.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@ToString
public class BaseEntity {

    private Date createAt;
    private Date updateAt;

}
