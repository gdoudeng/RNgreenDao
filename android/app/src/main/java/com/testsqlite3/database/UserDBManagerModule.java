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
import com.testsqlite3.database.entity.User;
import com.testsqlite3.database.greendao.DaoSession;
import com.testsqlite3.database.greendao.UserDao;

import java.util.List;

public class UserDBManagerModule extends ReactContextBaseJavaModule {

    private UserDao mUserDao;
    private static final String TAG = "UserDBManagerModule";

    public UserDBManagerModule(@NonNull ReactApplicationContext reactContext) {
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
            map.putString("email", users.get(i).getEmail());
            userList.pushMap(map);
        }
        return userList;
    }

    //    ========================= 增 =========================
    @ReactMethod
    public void addUser(int userKey, String userName, int userAge, String email, Callback callback) {
        User user = new User();
        user.setId((long) userKey);
        user.setUsername(userName);
        user.setAge(userAge);
        user.setEmail(email);
        mUserDao.insertOrReplace(user);
        callback.invoke(true);
    }

    //    ========================= 查 =========================

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
        List<User> users = mUserDao.loadAll();
        WritableArray array = wrapUserList(users);
        callback.invoke(array);
    }

    // 获取用户数量
    @ReactMethod
    public void getUserSize(Callback callback) {
        long count = mUserDao.queryBuilder().count();
        WritableMap map = Arguments.createMap();
        map.putInt("size", (int) count);
        callback.invoke(map);
    }

    //    ========================= 删 =========================

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

    // 删除年龄小于18的用户
    @ReactMethod
    public void deleteUserWhoUnder18(Callback callback) {
        mUserDao.queryBuilder().where(UserDao.Properties.Age.lt(18)).buildDelete().executeDeleteWithoutDetachingEntities();
        callback.invoke(true);
    }


    @ReactMethod
    public void deleteUserByUserName(String username, Callback callback) {
        mUserDao.queryBuilder().where(UserDao.Properties.Username.eq(username)).buildDelete().executeDeleteWithoutDetachingEntities();
        callback.invoke(true);
    }

    //    ========================= 改 =========================
    // 将所有年龄大于等于18岁的用户改名为成年人
    @ReactMethod
    public void updateUserNameWhoOlder18(Callback callback) {
        List<User> users = mUserDao.queryBuilder().where(UserDao.Properties.Age.ge(18)).list();
        for (int i = 0; i < users.size(); i++) {
            users.get(i).setUsername("成年人");
        }
        mUserDao.updateInTx(users);
        callback.invoke(true);
    }

    @ReactMethod
    public void updateUserNameById(int id, String username, Callback callback) {
        User user = mUserDao.queryBuilder().where(UserDao.Properties.Id.eq(id)).unique();
        user.setUsername(username);
        mUserDao.update(user);
        callback.invoke(true);
    }

    @NonNull
    @Override
    public String getName() {
        return TAG;
    }
}
