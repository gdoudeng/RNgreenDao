package com.testsqlite3.database.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Unique;

import java.util.List;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

import com.testsqlite3.database.greendao.DaoSession;
import com.testsqlite3.database.greendao.StudentDao;
import com.testsqlite3.database.greendao.CreditCardDao;
import com.testsqlite3.database.greendao.IdCardDao;
import com.testsqlite3.database.greendao.TeacherDao;

@Entity
public class Teacher {
    @Id(autoincrement = true)
    private Long id;

    // 职工号
    @Unique
    private Integer teacherNo;
    private Integer age;
    private String sex;
    private String name;
    //学校名字
    private String schoolName;
    //科目
    private String subject;

    // 一对一关系,注解中的id为Teacher中定义的id，在insert时，将Teacher的id同一对一关联的IdCard的主键id绑定
    @ToOne(joinProperty = "id")
    private IdCard mIdCard;

    // 此处的teacherId是在CreditCard中定义的一个变量
    @ToMany(referencedJoinProperty = "teacherId")
    private List<CreditCard> mCreditCardList;

    // 们需要创建一个学生老师管理器(StudentAndTeacherBean)，用来对应学生和老师的ID;
    @ToMany
    @JoinEntity(entity = StudentAndTeacherBean.class, sourceProperty = "teacherId", targetProperty = "studentId")
    private List<Student> mStudentList;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 648119699)
    private transient TeacherDao myDao;

    @Generated(hash = 430342201)
    public Teacher(Long id, Integer teacherNo, Integer age, String sex, String name, String schoolName,
                   String subject) {
        this.id = id;
        this.teacherNo = teacherNo;
        this.age = age;
        this.sex = sex;
        this.name = name;
        this.schoolName = schoolName;
        this.subject = subject;
    }

    @Generated(hash = 1630413260)
    public Teacher() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTeacherNo() {
        return this.teacherNo;
    }

    public void setTeacherNo(Integer teacherNo) {
        this.teacherNo = teacherNo;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public String getSchoolName() {
        return this.schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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
    @Generated(hash = 1952383315)
    public List<CreditCard> getMCreditCardList() {
        if (mCreditCardList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CreditCardDao targetDao = daoSession.getCreditCardDao();
            List<CreditCard> mCreditCardListNew = targetDao._queryTeacher_MCreditCardList(id);
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
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1964508019)
    public List<Student> getMStudentList() {
        if (mStudentList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            StudentDao targetDao = daoSession.getStudentDao();
            List<Student> mStudentListNew = targetDao._queryTeacher_MStudentList(id);
            synchronized (this) {
                if (mStudentList == null) {
                    mStudentList = mStudentListNew;
                }
            }
        }
        return mStudentList;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 1611095639)
    public synchronized void resetMStudentList() {
        mStudentList = null;
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
    @Generated(hash = 1349174479)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTeacherDao() : null;
    }


}
