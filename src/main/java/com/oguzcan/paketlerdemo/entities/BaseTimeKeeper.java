package com.oguzcan.paketlerdemo.entities;



import javax.persistence.MappedSuperclass;
import java.time.ZonedDateTime;

@MappedSuperclass
public abstract class BaseTimeKeeper {

    private ZonedDateTime createAt;
    private ZonedDateTime updateAt;

    public ZonedDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(ZonedDateTime createAt) {
        this.createAt = createAt;
    }

    public ZonedDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(ZonedDateTime updateAt) {
        this.updateAt = updateAt;
    }
}
