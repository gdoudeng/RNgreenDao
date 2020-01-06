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
import com.testsqlite3.database.greendao.CreditCardDao;
import com.testsqlite3.database.greendao.DaoSession;

import java.util.List;

public class CreditCardDBManagerModule extends ReactContextBaseJavaModule {
    private static final String TAG = "CreditCardDBManagerModule";
    private CreditCardDao mCreditCardDao;

    public CreditCardDBManagerModule(@NonNull ReactApplicationContext reactContext) {
        super(reactContext);
        DaoSession daoSession = ((MainApplication) reactContext.getApplicationContext()).getDaoSession();
        mCreditCardDao = daoSession.getCreditCardDao();
    }

    @NonNull
    @Override
    public String getName() {
        return TAG;
    }

    static WritableArray wrapCreditCardList(List<CreditCard> creditCards) {
        WritableArray idCardList = Arguments.createArray();
        for (int i = 0; i < creditCards.size(); i++) {
            WritableMap map = wrapCreditCard(creditCards.get(i));
            idCardList.pushMap(map);
        }
        return idCardList;
    }

    private static WritableMap wrapCreditCard(CreditCard creditCard) {
        WritableMap map = Arguments.createMap();
        map.putInt("id", creditCard.getId().intValue());
        map.putInt("studentId", creditCard.getStudentId().intValue());
        map.putString("userName", creditCard.getUserName());
        map.putString("cardNum", creditCard.getCardNum());
        map.putString("whichBank", creditCard.getWhichBank());
        map.putInt("cardType", creditCard.getCardType());
        return map;
    }

    //    ========================= 查 =========================
    @ReactMethod
    public void getAllCreditCard(Callback callback) {
        List<CreditCard> creditCards = mCreditCardDao.loadAll();
        WritableArray array = wrapCreditCardList(creditCards);
        callback.invoke(array);
    }
}
