import {NativeModules} from 'react-native';

const IdCardDBManagerModule = NativeModules.IdCardDBManagerModule;

export default class IdCardDao {
  static getAllIdCard() {
    return new Promise((resolve, reject) => {
      try {
        IdCardDBManagerModule.getAllIdCard(result => {
          resolve(result);
        });
      } catch (e) {
        reject(e);
      }
    });
  }
}
