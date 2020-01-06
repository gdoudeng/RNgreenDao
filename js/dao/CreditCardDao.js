import {NativeModules} from 'react-native';

const CreditCardDBManagerModule = NativeModules.CreditCardDBManagerModule;

export default class CreditCardDao {
  static getAllCreditCard() {
    return new Promise((resolve, reject) => {
      try {
        CreditCardDBManagerModule.getAllCreditCard(result => {
          resolve(result);
        });
      } catch (e) {
        reject(e);
      }
    });
  }
}
