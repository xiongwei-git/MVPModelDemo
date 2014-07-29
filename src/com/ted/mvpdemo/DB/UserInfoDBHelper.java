package com.ted.mvpdemo.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Ted on 14-7-29.
 */
public class UserInfoDBHelper extends OrmLiteSqliteOpenHelper {
    private static final String TAG = "UserIndfoDBHelper";
    // 数据库名称
    private static final String DATABASE_NAME = "MyUserInfo.db";
    // 数据库version
    private static final int DATABASE_VERSION = 1;

    private Dao<UserInfoDBItem, Integer> houseInfoDao = null;
    private RuntimeExceptionDao<UserInfoDBItem, Integer> houseInfoRuntimeDao = null;

    public UserInfoDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public UserInfoDBHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource) {
        try {
            // 建立User表
            TableUtils.createTable(connectionSource, UserInfoDBItem.class);
            // 初始化DAO
            houseInfoDao = getHouseInfoDao();
            houseInfoRuntimeDao = getHouseInfoDataDao();
        } catch (SQLException e) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource, int oldVer, int newVer) {
        try {
            TableUtils.dropTable(connectionSource, UserInfoDBItem.class, true);// 建立search表
            onCreate(sqliteDatabase, connectionSource);
        } catch (SQLException e) {
            Log.e(UserInfoDBHelper.class.getName(), "Unable to upgrade database from version " + oldVer + " to new " + newVer, e);
        }
    }

    /**
     * @return
     * @throws SQLException
     * @throws java.sql.SQLException
     */
    private Dao<UserInfoDBItem, Integer> getHouseInfoDao() throws SQLException {
        if (houseInfoDao == null)
            houseInfoDao = getDao(UserInfoDBItem.class);
        return houseInfoDao;
    }

    public RuntimeExceptionDao<UserInfoDBItem, Integer> getHouseInfoDataDao() {
        if (houseInfoRuntimeDao == null) {
            houseInfoRuntimeDao = getRuntimeExceptionDao(UserInfoDBItem.class);
        }
        return houseInfoRuntimeDao;
    }
    /**
     * 释放 DAO
     */
    @Override
    public void close() {
        super.close();
        houseInfoRuntimeDao = null;
    }
}
