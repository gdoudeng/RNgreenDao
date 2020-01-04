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

import com.testsqlite3.database.greendao.UserDao;
import com.testsqlite3.database.greendao.IdCardDao;
import com.testsqlite3.database.greendao.StudentDao;

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

    private final UserDao userDao;
    private final IdCardDao idCardDao;
    private final StudentDao studentDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        idCardDaoConfig = daoConfigMap.get(IdCardDao.class).clone();
        idCardDaoConfig.initIdentityScope(type);

        studentDaoConfig = daoConfigMap.get(StudentDao.class).clone();
        studentDaoConfig.initIdentityScope(type);

        userDao = new UserDao(userDaoConfig, this);
        idCardDao = new IdCardDao(idCardDaoConfig, this);
        studentDao = new StudentDao(studentDaoConfig, this);

        registerDao(User.class, userDao);
        registerDao(IdCard.class, idCardDao);
        registerDao(Student.class, studentDao);
    }
    
    public void clear() {
        userDaoConfig.clearIdentityScope();
        idCardDaoConfig.clearIdentityScope();
        studentDaoConfig.clearIdentityScope();
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

}
