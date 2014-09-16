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
public class DBHelper extends OrmLiteSqliteOpenHelper {
    private static final String TAG = "UserInfoDBHelper";
    private static final String DATABASE_NAME = "MyUserInfo.db";
    private static final int DATABASE_VERSION = 2;

    private Dao<UserInfoDBItem, Integer> userInfoDao = null;
    private RuntimeExceptionDao<UserInfoDBItem, Integer> userInfoRuntimeExceptionDao = null;

    private Dao<ScoreInfoItem, Integer> scoreInfoDao = null;
    private RuntimeExceptionDao<ScoreInfoItem, Integer> scoreInfoRuntimeExceptionDao = null;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DBHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, UserInfoDBItem.class);
            TableUtils.createTable(connectionSource, ScoreInfoItem.class);
            userInfoDao = getUserInfoDao();
            scoreInfoDao = getScoreInfoDao();
            userInfoRuntimeExceptionDao = getUserInfoRunDao();
            scoreInfoRuntimeExceptionDao = getScoreInfoRunDao();
        } catch (SQLException e) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource, int oldVer, int newVer) {
        try {
            //TableUtils.dropTable(connectionSource, UserInfoDBItem.class, true);
            //TableUtils.dropTable(connectionSource, ScoreInfoItem.class, true);
            TableUtils.createTable(connectionSource, ScoreInfoItem.class);
            onCreate(sqliteDatabase, connectionSource);
        } catch (SQLException e) {
            Log.e(DBHelper.class.getName(), "Unable to upgrade database from version " + oldVer + " to new " + newVer, e);
        }
    }
    private Dao<UserInfoDBItem, Integer> getUserInfoDao() throws SQLException {
        if (userInfoDao == null)
            userInfoDao = getDao(UserInfoDBItem.class);
        return userInfoDao;
    }

    public RuntimeExceptionDao<UserInfoDBItem, Integer> getUserInfoRunDao() {
        if (userInfoRuntimeExceptionDao == null) {
            userInfoRuntimeExceptionDao = getRuntimeExceptionDao(UserInfoDBItem.class);
        }
        return userInfoRuntimeExceptionDao;
    }
    private Dao<ScoreInfoItem, Integer> getScoreInfoDao() throws SQLException {
        if (scoreInfoDao == null)
            scoreInfoDao = getDao(ScoreInfoItem.class);
        return scoreInfoDao;
    }

    public RuntimeExceptionDao<ScoreInfoItem, Integer> getScoreInfoRunDao() {
        if (scoreInfoRuntimeExceptionDao == null) {
            scoreInfoRuntimeExceptionDao = getRuntimeExceptionDao(ScoreInfoItem.class);
        }
        return scoreInfoRuntimeExceptionDao;
    }
    /**
     * 释放 DAO
     */
    @Override
    public void close() {
        super.close();
        userInfoRuntimeExceptionDao = null;
        scoreInfoRuntimeExceptionDao = null;
    }
}
