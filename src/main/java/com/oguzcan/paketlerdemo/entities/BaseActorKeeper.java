package com.oguzcan.paketlerdemo.entities;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseActorKeeper extends BaseTimeKeeper {
    private String createBy;
    private String updateBy;

    public String getCreateBy() {
        return createBy;
    }
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    public String getUpdateBy() {
        return updateBy;
    }
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}
