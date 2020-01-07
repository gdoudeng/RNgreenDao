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
import com.testsqlite3.database.entity.CreditCard;
import com.testsqlite3.database.entity.IdCard;
import com.testsqlite3.database.entity.Teacher;
import com.testsqlite3.database.greendao.CreditCardDao;
import com.testsqlite3.database.greendao.DaoSession;
import com.testsqlite3.database.greendao.IdCardDao;
import com.testsqlite3.database.greendao.TeacherDao;
import com.testsqlite3.utils.RandomValue;

import java.util.List;
import java.util.Random;

public class TeacherDBManagerModule extends ReactContextBaseJavaModule {
    private static final String TAG = "TeacherDBManagerModule";
    private TeacherDao mTeacherDao;
    private IdCardDao mIdCardDao;
    private CreditCardDao mCreditCardDao;
    private Random mRandom = new Random();

    public TeacherDBManagerModule(@NonNull ReactApplicationContext reactContext) {
        super(reactContext);
        DaoSession daoSession = ((MainApplication) reactContext.getApplicationContext()).getDaoSession();
        mTeacherDao = daoSession.getTeacherDao();
        mIdCardDao = daoSession.getIdCardDao();
        mCreditCardDao = daoSession.getCreditCardDao();
    }

    @NonNull
    @Override
    public String getName() {
        return TAG;
    }

    static WritableArray wrapTeacherList(List<Teacher> teachers) {
        WritableArray writableArray = Arguments.createArray();
        for (int i = 0; i < teachers.size(); i++) {
            WritableMap map = wrapTeacher(teachers.get(i));
            writableArray.pushMap(map);
        }
        return writableArray;
    }

    private static WritableMap wrapTeacher(Teacher teacher) {
        WritableMap map = Arguments.createMap();
        map.putInt("id", teacher.getId().intValue());
        map.putInt("teacherNo", teacher.getTeacherNo());
        map.putString("name", teacher.getName());
        map.putString("sex", teacher.getSex());
        map.putString("schoolName", teacher.getSchoolName());
        map.putString("subject", teacher.getSubject());
        map.putInt("age", teacher.getAge());
        return map;
    }

    //    ========================= 查 =========================
    @ReactMethod
    public void getAllTeacher(Callback callback) {
        List<Teacher> teachers = mTeacherDao.loadAll();
        WritableArray array = wrapTeacherList(teachers);
        callback.invoke(array);
    }

    //    ========================= 增 =========================
    @ReactMethod
    public void randomAddTeacher(Callback callback) {
        Teacher teacher = new Teacher();
        teacher.setTeacherNo((int) System.currentTimeMillis());
        int age = mRandom.nextInt(20) + 18;
        teacher.setAge(age);
        String chineseName = RandomValue.getChineseName();
        teacher.setName(chineseName);
        teacher.setSex("男");
        teacher.setSchoolName(RandomValue.getSchoolName());
        teacher.setSubject(RandomValue.getRandomSubject());
        mTeacherDao.insert(teacher);
        addOtherData(chineseName, teacher.getId());
        callback.invoke(true);
    }

    private void addOtherData(String userName, Long id) {
        IdCard idCard = new IdCard();
        idCard.setUserName(userName);
        idCard.setIdNo(RandomValue.getRandomID());
        mIdCardDao.insert(idCard);
        for (int j = 0; j < mRandom.nextInt(5) + 1; j++) {
            CreditCard creditCard = new CreditCard();
            creditCard.setTeacherId(id);
            creditCard.setUserName(userName);
            creditCard.setCardNum(String.valueOf(mRandom.nextInt(899999999) + 100000000) + (mRandom.nextInt(899999999) + 100000000));
            creditCard.setWhichBank(RandomValue.getBankName());
            creditCard.setCardType(mRandom.nextInt(10));
            mCreditCardDao.insert(creditCard);
        }
    }

}
