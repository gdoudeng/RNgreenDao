package com.testsqlite3.database;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.testsqlite3.MainApplication;
import com.testsqlite3.database.Entity.User;
import com.testsqlite3.database.greendao.DaoSession;
import com.testsqlite3.database.greendao.UserDao;

import java.util.List;

public class DBManagerModule extends ReactContextBaseJavaModule {

    private UserDao mUserDao;
    private static final String TAG = "DBManagerModule";

    public DBManagerModule(@NonNull ReactApplicationContext reactContext) {
        super(reactContext);
        DaoSession daoSession = ((MainApplication) reactContext.getApplicationContext()).getDaoSession();
        //获取的UserDao供我们操作数据
        mUserDao = daoSession.getUserDao();
    }

    private WritableArray wrapUserList(List<User> users) {
        WritableArray userList = Arguments.createArray();
        for (int i = 0; i < users.size(); i++) {
            WritableMap map = Arguments.createMap();
            map.putInt("id", users.get(i).getId().intValue());
            map.putString("username", users.get(i).getUsername());
            map.putInt("age", users.get(i).getAge());
            userList.pushMap(map);
        }
        return userList;
    }

    @ReactMethod
    public void addUser(int userKey, String userName, int userAge, Callback callback) {
        User user = new User();
        user.setId((long) userKey);
        user.setUsername(userName);
        user.setAge(userAge);
        mUserDao.insertOrReplace(user);
        callback.invoke(true);
    }

    /**
     * 根据用户名查询
     *
     * @param username 用户名
     * @param callback 回调函数
     */
    @ReactMethod
    public void queryUserByUserName(String username, Callback callback) {
        List<User> users = mUserDao.queryBuilder().where(UserDao.Properties.Username.eq(username)).list();
        WritableArray array = wrapUserList(users);
        callback.invoke(array);
    }

    /**
     * 查询年龄大于等于18的用户，并按年龄降序排列
     *
     * @param callback
     */
    @ReactMethod
    public void queryUserOlderThan18OrderByAge(Callback callback) {
        List<User> users = mUserDao.queryBuilder().where(UserDao.Properties.Age.ge(18)).orderDesc(UserDao.Properties.Age).list();
        WritableArray array = wrapUserList(users);
        callback.invoke(array);
    }

    @ReactMethod
    public void getAllUser(Callback callback) {
        List<User> users = mUserDao.queryBuilder().list();
        WritableArray array = wrapUserList(users);
        callback.invoke(array);
    }

    // 获取用户数量
    @ReactMethod
    public void getUserSize(Callback callback) {
        long count = mUserDao.queryBuilder().count();
        callback.invoke(count);
    }

    @ReactMethod
    public void deleteUserById(int id, Callback callback) {
        mUserDao.deleteByKey((long) id);
        callback.invoke(true);
    }

    @ReactMethod
    public void deleteAllUser(Callback callback) {
        mUserDao.deleteAll();
        callback.invoke(true);
    }

    @NonNull
    @Override
    public String getName() {
        return TAG;
    }
}
