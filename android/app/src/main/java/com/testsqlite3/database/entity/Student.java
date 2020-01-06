package com.testsqlite3.database.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

import com.testsqlite3.database.greendao.DaoSession;
import com.testsqlite3.database.greendao.IdCardDao;
import com.testsqlite3.database.greendao.StudentDao;

import java.util.List;

import com.testsqlite3.database.greendao.CreditCardDao;

@Entity
public class Student {
    @Id(autoincrement = true)
    private Long id;

    @Unique
    private Integer studentNo;
    //年龄
    private int age;
    //手机号
    private String telPhone;
    //性别
    private String sex;
    //姓名
    private String name;
    //家庭住址
    private String address;
    //学校名字
    private String schoolName;
    //几年级
    private String grade;

    // 一对一关系,注解中的id为Student中定义的id，在insert时，将Student的id同一对一关联的IdCard的主键id绑定
    @ToOne(joinProperty = "id")
    private IdCard mIdCard;

    // 此处的studentId是在CreditCard中定义的一个变量
    @ToMany(referencedJoinProperty = "studentId")
    private List<CreditCard> mCreditCardList;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1943931642)
    private transient StudentDao myDao;

    @Generated(hash = 457849939)
    public Student(Long id, Integer studentNo, int age, String telPhone, String sex,
                   String name, String address, String schoolName, String grade) {
        this.id = id;
        this.studentNo = studentNo;
        this.age = age;
        this.telPhone = telPhone;
        this.sex = sex;
        this.name = name;
        this.address = address;
        this.schoolName = schoolName;
        this.grade = grade;
    }

    @Generated(hash = 1556870573)
    public Student() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStudentNo() {
        return this.studentNo;
    }

    public void setStudentNo(Integer studentNo) {
        this.studentNo = studentNo;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTelPhone() {
        return this.telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSchoolName() {
        return this.schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getGrade() {
        return this.grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Generated(hash = 709993798)
    private transient Long mIdCard__resolvedKey;

    /**
     * To-one relationship, resolved on first access.
     */
    @Generated(hash = 1076843322)
    public IdCard getMIdCard() {
        Long __key = this.id;
        if (mIdCard__resolvedKey == null || !mIdCard__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            IdCardDao targetDao = daoSession.getIdCardDao();
            IdCard mIdCardNew = targetDao.load(__key);
            synchronized (this) {
                mIdCard = mIdCardNew;
                mIdCard__resolvedKey = __key;
            }
        }
        return mIdCard;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 309105486)
    public void setMIdCard(IdCard mIdCard) {
        synchronized (this) {
            this.mIdCard = mIdCard;
            id = mIdCard == null ? null : mIdCard.getId();
            mIdCard__resolvedKey = id;
        }
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 712525154)
    public List<CreditCard> getMCreditCardList() {
        if (mCreditCardList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CreditCardDao targetDao = daoSession.getCreditCardDao();
            List<CreditCard> mCreditCardListNew = targetDao
                    ._queryStudent_MCreditCardList(id);
            synchronized (this) {
                if (mCreditCardList == null) {
                    mCreditCardList = mCreditCardListNew;
                }
            }
        }
        return mCreditCardList;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 395421686)
    public synchronized void resetMCreditCardList() {
        mCreditCardList = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 1701634981)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getStudentDao() : null;
    }


}
