package com.testsqlite3.database.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class IdCard {
    @Id(autoincrement = true)
    private Long id;
    @NotNull
    private String userName; // 用户名
    @Unique
    private String idNo; // 身份证号

    @Generated(hash = 459221886)
    public IdCard(Long id, @NotNull String userName, String idNo) {
        this.id = id;
        this.userName = userName;
        this.idNo = idNo;
    }

    @Generated(hash = 1500073048)
    public IdCard() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdNo() {
        return this.idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }


}
