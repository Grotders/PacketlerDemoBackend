package com.oguzcan.paketlerdemo.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
@ToString
public class BaseAll extends BaseEntity{

    private String createBy;
    private String updateBy;
}
