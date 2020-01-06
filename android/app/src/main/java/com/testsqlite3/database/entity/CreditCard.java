package com.testsqlite3.database.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class CreditCard {
    @Id
    private Long id;

    //此处自定义studentId，用于和Student对应
    @NotNull
    private Long studentId;
    //持有者名字
    @NotNull
    private String userName;
    //卡号
    private String cardNum;
    //哪个银行的
    private String whichBank;
    //卡等级，分类 0 ~ 5
    private Integer cardType;

    @Generated(hash = 195173630)
    public CreditCard(Long id, @NotNull Long studentId, @NotNull String userName,
                      String cardNum, String whichBank, Integer cardType) {
        this.id = id;
        this.studentId = studentId;
        this.userName = userName;
        this.cardNum = cardNum;
        this.whichBank = whichBank;
        this.cardType = cardType;
    }

    @Generated(hash = 1860989810)
    public CreditCard() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCardNum() {
        return this.cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getWhichBank() {
        return this.whichBank;
    }

    public void setWhichBank(String whichBank) {
        this.whichBank = whichBank;
    }

    public Integer getCardType() {
        return this.cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }
}
