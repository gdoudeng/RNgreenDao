package com.testsqlite3.database.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.testsqlite3.database.entity.User;
import com.testsqlite3.database.entity.IdCard;
import com.testsqlite3.database.entity.Student;
import com.testsqlite3.database.entity.CreditCard;
import com.testsqlite3.database.entity.StudentAndTeacherBean;
import com.testsqlite3.database.entity.Teacher;

import com.testsqlite3.database.greendao.UserDao;
import com.testsqlite3.database.greendao.IdCardDao;
import com.testsqlite3.database.greendao.StudentDao;
import com.testsqlite3.database.greendao.CreditCardDao;
import com.testsqlite3.database.greendao.StudentAndTeacherBeanDao;
import com.testsqlite3.database.greendao.TeacherDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig userDaoConfig;
    private final DaoConfig idCardDaoConfig;
    private final DaoConfig studentDaoConfig;
    private final DaoConfig creditCardDaoConfig;
    private final DaoConfig studentAndTeacherBeanDaoConfig;
    private final DaoConfig teacherDaoConfig;

    private final UserDao userDao;
    private final IdCardDao idCardDao;
    private final StudentDao studentDao;
    private final CreditCardDao creditCardDao;
    private final StudentAndTeacherBeanDao studentAndTeacherBeanDao;
    private final TeacherDao teacherDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        idCardDaoConfig = daoConfigMap.get(IdCardDao.class).clone();
        idCardDaoConfig.initIdentityScope(type);

        studentDaoConfig = daoConfigMap.get(StudentDao.class).clone();
        studentDaoConfig.initIdentityScope(type);

        creditCardDaoConfig = daoConfigMap.get(CreditCardDao.class).clone();
        creditCardDaoConfig.initIdentityScope(type);

        studentAndTeacherBeanDaoConfig = daoConfigMap.get(StudentAndTeacherBeanDao.class).clone();
        studentAndTeacherBeanDaoConfig.initIdentityScope(type);

        teacherDaoConfig = daoConfigMap.get(TeacherDao.class).clone();
        teacherDaoConfig.initIdentityScope(type);

        userDao = new UserDao(userDaoConfig, this);
        idCardDao = new IdCardDao(idCardDaoConfig, this);
        studentDao = new StudentDao(studentDaoConfig, this);
        creditCardDao = new CreditCardDao(creditCardDaoConfig, this);
        studentAndTeacherBeanDao = new StudentAndTeacherBeanDao(studentAndTeacherBeanDaoConfig, this);
        teacherDao = new TeacherDao(teacherDaoConfig, this);

        registerDao(User.class, userDao);
        registerDao(IdCard.class, idCardDao);
        registerDao(Student.class, studentDao);
        registerDao(CreditCard.class, creditCardDao);
        registerDao(StudentAndTeacherBean.class, studentAndTeacherBeanDao);
        registerDao(Teacher.class, teacherDao);
    }
    
    public void clear() {
        userDaoConfig.clearIdentityScope();
        idCardDaoConfig.clearIdentityScope();
        studentDaoConfig.clearIdentityScope();
        creditCardDaoConfig.clearIdentityScope();
        studentAndTeacherBeanDaoConfig.clearIdentityScope();
        teacherDaoConfig.clearIdentityScope();
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public IdCardDao getIdCardDao() {
        return idCardDao;
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public CreditCardDao getCreditCardDao() {
        return creditCardDao;
    }

    public StudentAndTeacherBeanDao getStudentAndTeacherBeanDao() {
        return studentAndTeacherBeanDao;
    }

    public TeacherDao getTeacherDao() {
        return teacherDao;
    }

}
