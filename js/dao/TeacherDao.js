import {NativeModules} from 'react-native';

const TeacherDBManagerModule = NativeModules.TeacherDBManagerModule;

export default class TeacherDao {
  static getAllTeacher() {
    return new Promise((resolve, reject) => {
      try {
        TeacherDBManagerModule.getAllTeacher(result => {
          resolve(result);
        });
      } catch (e) {
        reject(e);
      }
    });
  }

  static randomAddTeacher(callback) {
    TeacherDBManagerModule.randomAddTeacher(callback);
  }
}
