package com.testsqlite3.database.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class StudentAndTeacherBean {
    @Id(autoincrement = true)
    private Long id;
    //学生ID
    private Long studentId;
    //老师ID
    private Long teacherId;

    @Generated(hash = 2146410221)
    public StudentAndTeacherBean(Long id, Long studentId, Long teacherId) {
        this.id = id;
        this.studentId = studentId;
        this.teacherId = teacherId;
    }

    @Generated(hash = 207804266)
    public StudentAndTeacherBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getTeacherId() {
        return this.teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

}
