package com.testsqlite3;

import android.app.Application;
import android.content.Context;

import com.facebook.react.PackageList;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.soloader.SoLoader;
import com.github.yuweiguocn.library.greendao.MigrationHelper;
import com.testsqlite3.database.DBManagerPackage;
import com.testsqlite3.database.greendao.DaoMaster;
import com.testsqlite3.database.greendao.DaoSession;
import com.testsqlite3.database.support.MySQLiteOpenHelper;

import org.greenrobot.greendao.query.QueryBuilder;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class MainApplication extends Application implements ReactApplication {
    private DaoSession mDaoSession;

    private final ReactNativeHost mReactNativeHost =
            new ReactNativeHost(this) {
                @Override
                public boolean getUseDeveloperSupport() {
                    return BuildConfig.DEBUG;
                }

                @Override
                protected List<ReactPackage> getPackages() {
                    @SuppressWarnings("UnnecessaryLocalVariable")
                    List<ReactPackage> packages = new PackageList(this).getPackages();
                    // Packages that cannot be autolinked yet can be added manually here, for example:
                    packages.add(new DBManagerPackage());
                    return packages;
                }

                @Override
                protected String getJSMainModuleName() {
                    return "index";
                }
            };

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, /* native exopackage */ false);
        initializeFlipper(this); // Remove this line if you don't want Flipper enabled

        // 将DEBUG设置为true查看日志信息
        MigrationHelper.DEBUG = BuildConfig.DEBUG;
        // 这里使用自定义的Helper对DaoMaster进行初始化，这样就可以实现数据库升级时的数据迁移
        // 默认的DaoMaster.OpenHelper不具备数据迁移功能，它会在数据库升级时将数据删除。
        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(this, "RNGreenDAOStorage.db", null);
        mDaoSession = new DaoMaster(helper.getWritableDatabase()).newSession();
        // 数据库增删改查时的log
        QueryBuilder.LOG_SQL = BuildConfig.DEBUG;
        QueryBuilder.LOG_VALUES = BuildConfig.DEBUG;
        // 清空缓存
        mDaoSession.clear();
    }

    /**
     * Loads Flipper in React Native templates.
     *
     * @param context
     */
    private static void initializeFlipper(Context context) {
        if (BuildConfig.DEBUG) {
            try {
        /*
         We use reflection here to pick up the class that initializes Flipper,
        since Flipper library is not available in release mode
        */
                Class<?> aClass = Class.forName("com.facebook.flipper.ReactNativeFlipper");
                aClass.getMethod("initializeFlipper", Context.class).invoke(null, context);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
}
