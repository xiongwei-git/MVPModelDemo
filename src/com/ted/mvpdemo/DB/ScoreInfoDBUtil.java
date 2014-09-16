package com.ted.mvpdemo.DB;

import android.content.Context;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.DeleteBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ted on 14-7-29.
 */
public class ScoreInfoDBUtil {
    private Context mContext;
    DBHelper mDBHelper;
    RuntimeExceptionDao<ScoreInfoItem, Integer> mScoreInfoRunDao;

    public ScoreInfoDBUtil(Context context) {
        this.mContext = context;
        mDBHelper = OpenHelperManager.getHelper(mContext, DBHelper.class);
        mScoreInfoRunDao = mDBHelper.getScoreInfoRunDao();
    }

    /**
     * 插入
     */
    public void insert(ScoreInfoItem scoreInfoItem) {
        mScoreInfoRunDao.createOrUpdate(scoreInfoItem);
    }

    /**
     * 按照指定的id 删除一项
     *
     * @param scoreInfoItem
     * @return 删除成功返回1 ，失败返回0
     */
    public int delete(ScoreInfoItem scoreInfoItem) {
        try {
            DeleteBuilder<ScoreInfoItem, Integer> deleteBuilder = mScoreInfoRunDao.deleteBuilder();
            deleteBuilder.where().eq("id", scoreInfoItem.getId());
            return deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 按照指定的id 删除一项
     *
     * @param infoID
     * @return 删除成功返回1 ，失败返回0
     */
    public int delete(long infoID) {
        try {
            DeleteBuilder<ScoreInfoItem, Integer> deleteBuilder = mScoreInfoRunDao.deleteBuilder();
            deleteBuilder.where().eq("mUserInfoID", infoID);
            return deleteBuilder.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 删除全部
     */
    public void deleteAll() {
        mScoreInfoRunDao.delete(queryAll());
    }

    /**
     * 查询所有的
     */
    public List<ScoreInfoItem> queryAll() {
        List<ScoreInfoItem> userInfos = mScoreInfoRunDao.queryForAll();
        return userInfos;
    }

    public void closeDB(){
        mDBHelper.close();
    }
}
