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
import com.testsqlite3.database.entity.IdCard;
import com.testsqlite3.database.entity.Student;
import com.testsqlite3.database.greendao.DaoSession;
import com.testsqlite3.database.greendao.IdCardDao;
import com.testsqlite3.database.greendao.StudentDao;
import com.testsqlite3.utils.RandomValue;

import java.util.List;
import java.util.Random;


public class StudentDBManagerModule extends ReactContextBaseJavaModule {
    private static final String TAG = "StudentDBManagerModule";
    private StudentDao mStudentDao;
    private IdCardDao mIdCardDao;
    private Random mRandom = new Random();

    public StudentDBManagerModule(@NonNull ReactApplicationContext reactContext) {
        super(reactContext);
        DaoSession daoSession = ((MainApplication) reactContext.getApplicationContext()).getDaoSession();
        mStudentDao = daoSession.getStudentDao();
        mIdCardDao = daoSession.getIdCardDao();
    }

    @NonNull
    @Override
    public String getName() {
        return TAG;
    }

    private WritableArray wrapStudentList(List<Student> students) {
        WritableArray studentList = Arguments.createArray();
        for (int i = 0; i < students.size(); i++) {
            WritableMap map = Arguments.createMap();
            map.putInt("id", students.get(i).getId().intValue());
            map.putInt("studentNo", students.get(i).getStudentNo());
            map.putString("telPhone", students.get(i).getTelPhone());
            map.putString("sex", students.get(i).getSex());
            map.putString("name", students.get(i).getName());
            map.putString("address", students.get(i).getAddress());
            map.putString("schoolName", students.get(i).getSchoolName());
            map.putString("grade", students.get(i).getGrade());
            studentList.pushMap(map);
        }
        return studentList;
    }

    //    ========================= 增 =========================

    @ReactMethod
    public void randomAddStudent(Callback callback) {
        Student student = new Student();
        student.setStudentNo((int) System.currentTimeMillis());
        int age = mRandom.nextInt(10) + 10;
        student.setAge(age);
        student.setTelPhone(RandomValue.getTel());
        String chineseName = RandomValue.getChineseName();
        student.setName(chineseName);
        student.setSex("男");
        student.setAddress(RandomValue.getRoad());
        student.setGrade((age % 10) + "年纪");
        student.setSchoolName(RandomValue.getSchoolName());
        mStudentDao.insert(student);
//        为一对一关系的idcard添加数据
        addOtherData(chineseName, student.getId(), true);
        callback.invoke(true);
    }

    public void addOtherData(String userName, Long id, boolean isStudent) {
        IdCard idCard = new IdCard();
        idCard.setUserName(userName);
        idCard.setIdNo(RandomValue.getRandomID());
        mIdCardDao.insert(idCard);
    }

    //    ========================= 查 =========================
    @ReactMethod
    public void getAllStudent(Callback callback) {
        List<Student> students = mStudentDao.loadAll();
        WritableArray array = wrapStudentList(students);
        callback.invoke(array);
    }

    // 根据studentId获取其IDCard信息 这就是一对一关系的好处
    @ReactMethod
    public void getIdCardByStudentId(int studentId, Callback callback) {
        Student student = mStudentDao.queryBuilder().where(StudentDao.Properties.Id.eq(studentId)).unique();
        IdCard idCard = student.getMIdCard();
        WritableMap map = IdCardDBManagerModule.wrapIdCard(idCard);
        callback.invoke(map);
    }
}
