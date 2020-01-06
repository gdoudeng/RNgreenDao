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
import com.testsqlite3.database.greendao.DaoSession;
import com.testsqlite3.database.greendao.IdCardDao;

import java.util.List;

public class IdCardDBManagerModule extends ReactContextBaseJavaModule {
    private static final String TAG = "IdCardDBManagerModule";
    private IdCardDao mIdCardDao;

    public IdCardDBManagerModule(@NonNull ReactApplicationContext reactContext) {
        super(reactContext);
        DaoSession daoSession = ((MainApplication) reactContext.getApplicationContext()).getDaoSession();
        mIdCardDao = daoSession.getIdCardDao();
    }

    @NonNull
    @Override
    public String getName() {
        return TAG;
    }

    private WritableArray wrapIdCardList(List<IdCard> idCards) {
        WritableArray idCardList = Arguments.createArray();
        for (int i = 0; i < idCards.size(); i++) {
            WritableMap map = wrapIdCard(idCards.get(i));
            idCardList.pushMap(map);
        }
        return idCardList;
    }

    static WritableMap wrapIdCard(IdCard idCard) {
        WritableMap map = Arguments.createMap();
        map.putInt("id", idCard.getId().intValue());
        map.putString("userName", idCard.getUserName());
        map.putString("idNo", idCard.getIdNo());
        return map;
    }

    //    ========================= 查 =========================
    @ReactMethod
    public void getAllIdCard(Callback callback) {
        List<IdCard> idCards = mIdCardDao.loadAll();
        WritableArray array = wrapIdCardList(idCards);
        callback.invoke(array);
    }
}
