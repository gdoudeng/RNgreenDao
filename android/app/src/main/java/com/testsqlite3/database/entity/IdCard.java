package com.testsqlite3.database.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class IdCard {
    @Id
    private String userName; // 用户名
    @Unique
    private String idNo; // 身份证号

    @Generated(hash = 1028827110)
    public IdCard(String userName, String idNo) {
        this.userName = userName;
        this.idNo = idNo;
    }

    @Generated(hash = 1500073048)
    public IdCard() {
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
