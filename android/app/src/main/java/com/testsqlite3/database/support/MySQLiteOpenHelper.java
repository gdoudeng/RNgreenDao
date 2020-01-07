package com.testsqlite3.database.support;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.github.yuweiguocn.library.greendao.MigrationHelper;
import com.testsqlite3.database.greendao.CreditCardDao;
import com.testsqlite3.database.greendao.DaoMaster;
import com.testsqlite3.database.greendao.IdCardDao;
import com.testsqlite3.database.greendao.StudentAndTeacherBeanDao;
import com.testsqlite3.database.greendao.StudentDao;
import com.testsqlite3.database.greendao.TeacherDao;
import com.testsqlite3.database.greendao.UserDao;

import org.greenrobot.greendao.database.Database;

public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {

            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                DaoMaster.createAllTables(db, ifNotExists);
            }

            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                DaoMaster.dropAllTables(db, ifExists);
            }
        }, UserDao.class, StudentDao.class, IdCardDao.class, CreditCardDao.class, TeacherDao.class, StudentAndTeacherBeanDao.class);
    }
}
