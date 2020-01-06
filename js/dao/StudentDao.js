import {NativeModules} from 'react-native';

const StudentDBManagerModule = NativeModules.StudentDBManagerModule;

export default class StudentDao {
  // 查询所有学生
  static getAllStudent() {
    return new Promise((resolve, reject) => {
      try {
        StudentDBManagerModule.getAllStudent(result => {
          resolve(result);
        });
      } catch (e) {
        reject(e);
      }
    });
  }

  static randomAddStudent(callback) {
    StudentDBManagerModule.randomAddStudent(callback);
  }

  static getIdCardByStudentId(id, callback) {
    StudentDBManagerModule.getIdCardByStudentId(Number(id), callback);
  }

  static getIdCardByStudentNo(studentNo, callback) {
    StudentDBManagerModule.getIdCardByStudentNo(Number(studentNo), callback);
  }

  static getCreditCardListByStudentNo(studentNo, callback) {
    StudentDBManagerModule.getCreditCardListByStudentNo(Number(studentNo), callback);
  }
}
