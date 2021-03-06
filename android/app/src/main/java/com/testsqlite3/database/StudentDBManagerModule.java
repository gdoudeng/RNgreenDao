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
import com.testsqlite3.database.entity.Student;
import com.testsqlite3.database.entity.StudentAndTeacherBean;
import com.testsqlite3.database.entity.Teacher;
import com.testsqlite3.database.greendao.CreditCardDao;
import com.testsqlite3.database.greendao.DaoSession;
import com.testsqlite3.database.greendao.IdCardDao;
import com.testsqlite3.database.greendao.StudentAndTeacherBeanDao;
import com.testsqlite3.database.greendao.StudentDao;
import com.testsqlite3.database.greendao.TeacherDao;
import com.testsqlite3.utils.RandomValue;

import java.util.Collections;
import java.util.List;
import java.util.Random;


public class StudentDBManagerModule extends ReactContextBaseJavaModule {
    private static final String TAG = "StudentDBManagerModule";
    private StudentDao mStudentDao;
    private IdCardDao mIdCardDao;
    private CreditCardDao mCreditCardDao;
    private TeacherDao mTeacherDao;
    private StudentAndTeacherBeanDao mStudentAndTeacherBeanDao;
    private Random mRandom = new Random();

    public StudentDBManagerModule(@NonNull ReactApplicationContext reactContext) {
        super(reactContext);
        DaoSession daoSession = ((MainApplication) reactContext.getApplicationContext()).getDaoSession();
        mStudentDao = daoSession.getStudentDao();
        mIdCardDao = daoSession.getIdCardDao();
        mCreditCardDao = daoSession.getCreditCardDao();
        mTeacherDao = daoSession.getTeacherDao();
        mStudentAndTeacherBeanDao = daoSession.getStudentAndTeacherBeanDao();
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
        student.setGrade((age % 10) + "年级");
        student.setSchoolName(RandomValue.getSchoolName());
        mStudentDao.insert(student);
        // 为一对一关系的idcard添加数据
        addOtherData(chineseName, student.getId());
        addTeachers(student.getId());
        callback.invoke(true);
    }

    private void addTeachers(Long id) {
        List<Teacher> teacherList = mTeacherDao.loadAll();
        if (teacherList != null && teacherList.size() > 3) {
            Collections.shuffle(teacherList);
            for (int j = 0; j < mRandom.nextInt(5) + 1; j++) {
                if (j < teacherList.size()) {
                    Teacher teacher = teacherList.get(j);
                    StudentAndTeacherBean teacherBean = new StudentAndTeacherBean(null, id, teacher.getId());
                    mStudentAndTeacherBeanDao.insert(teacherBean);
                }
            }
        }
    }

    private void addOtherData(String userName, Long id) {
        IdCard idCard = new IdCard();
        idCard.setUserName(userName);
        idCard.setIdNo(RandomValue.getRandomID());
        mIdCardDao.insert(idCard);
        for (int j = 0; j < mRandom.nextInt(5) + 1; j++) {
            CreditCard creditCard = new CreditCard();
            creditCard.setStudentId(id);
            creditCard.setUserName(userName);
            creditCard.setCardNum(String.valueOf(mRandom.nextInt(899999999) + 100000000) + (mRandom.nextInt(899999999) + 100000000));
            creditCard.setWhichBank(RandomValue.getBankName());
            creditCard.setCardType(mRandom.nextInt(10));
            mCreditCardDao.insert(creditCard);
        }
    }

    //    ========================= 查 =========================
    @ReactMethod
    public void getAllStudent(Callback callback) {
        List<Student> students = mStudentDao.loadAll();
        WritableArray array = wrapStudentList(students);
        callback.invoke(array);
    }

    // 根据studentId（根据学号 姓名查也是可以的）获取其IDCard信息 这就是一对一关系的好处
    @ReactMethod
    public void getIdCardByStudentId(int studentId, Callback callback) {
        Student student = mStudentDao.queryBuilder().where(StudentDao.Properties.Id.eq(studentId)).unique();
        WritableMap map = Arguments.createMap();
        if (student != null) {
            IdCard idCard = student.getMIdCard();
            map = IdCardDBManagerModule.wrapIdCard(idCard);
        }
        callback.invoke(map);
    }

    @ReactMethod
    public void getIdCardByStudentNo(int studentNo, Callback callback) {
        Student student = mStudentDao.queryBuilder().where(StudentDao.Properties.StudentNo.eq(studentNo)).unique();
        WritableMap map = Arguments.createMap();
        if (student != null) {
            IdCard idCard = student.getMIdCard();
            map = IdCardDBManagerModule.wrapIdCard(idCard);
        }
        callback.invoke(map);
    }

    // 利用一对多关系 根据学号查询该生所拥有的信用卡
    @ReactMethod
    public void getCreditCardListByStudentNo(int studentNo, Callback callback) {
        Student student = mStudentDao.queryBuilder().where(StudentDao.Properties.StudentNo.eq(studentNo)).unique();
        WritableArray array = Arguments.createArray();
        if (student != null) {
            List<CreditCard> creditCardList = student.getMCreditCardList();
            array = CreditCardDBManagerModule.wrapCreditCardList(creditCardList);
        }
        callback.invoke(array);
    }

    // 利用多对多关系 根据学号查询该生所对应的老师列表
    @ReactMethod
    public void getTeacherListByStudentNo(int studentNo, Callback callback) {
        Student student = mStudentDao.queryBuilder().where(StudentDao.Properties.StudentNo.eq(studentNo)).unique();
        WritableArray array = Arguments.createArray();
        if (student != null) {
            List<Teacher> teacherList = student.getMTeacherList();
            array = TeacherDBManagerModule.wrapTeacherList(teacherList);
        }
        callback.invoke(array);
    }
}
