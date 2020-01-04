import {NativeModules} from 'react-native';

const UserDBManagerModule = NativeModules.UserDBManagerModule;

export default class UserDao {

  // 查询所有用户
  static getAllUser() {
    return new Promise((resolve, reject) => {
      try {
        UserDBManagerModule.getAllUser(result => {
          resolve(result);
        });
      } catch (e) {
        reject(e);
      }
    });
  }

  /**
   * 保存用户信息
   * @param id 主键id Long
   * @param username 用户名 String
   * @param age 年龄 int
   * @param callback
   */
  static addUser(id, username, age, callback) {
    UserDBManagerModule.addUser(Number(id), username, Number(age), callback);
  }

  static queryUserByUserName(username, callback) {
    UserDBManagerModule.queryUserByUserName(username, callback);
  }

  static queryUserOlderThan18OrderByAge(callback) {
    UserDBManagerModule.queryUserOlderThan18OrderByAge(callback);
  }

  static getUserSize(callback) {
    UserDBManagerModule.getUserSize(callback);
  }

  static deleteUserById(id, callback) {
    UserDBManagerModule.deleteUserById(id, callback);
  }

  static deleteAllUser(callback) {
    UserDBManagerModule.deleteAllUser(callback);
  }

  static deleteUserByUserName(username, callback) {
    UserDBManagerModule.deleteUserByUserName(username, callback);
  }

  static deleteUserWhoUnder18(callback) {
    UserDBManagerModule.deleteUserWhoUnder18(callback);
  }

  /**
   * 根据id修改用户名
   * @param id number
   * @param username string
   * @param callback
   */
  static updateUserNameById(id, username, callback) {
    UserDBManagerModule.updateUserNameById(id, username, callback);
  }

  // 将所有年龄大于等于18岁的用户改名为成年人
  static updateUserNameWhoOlder18(callback) {
    UserDBManagerModule.updateUserNameWhoOlder18(callback);
  }
}
