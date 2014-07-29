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
public class UserInfoDBUtil {
    private Context mContext;
    UserInfoDBHelper mHouseInfoDBHelper;
    RuntimeExceptionDao<UserInfoDBItem, Integer> mHouseInfoDAO;

    public UserInfoDBUtil(Context context) {
        this.mContext = context;
        mHouseInfoDBHelper = OpenHelperManager.getHelper(mContext, UserInfoDBHelper.class);
        mHouseInfoDAO = mHouseInfoDBHelper.getHouseInfoDataDao();
    }

    /**
     * 插入
     */
    public void insert(UserInfoDBItem houseInfo) {
        mHouseInfoDAO.createOrUpdate(houseInfo);
    }

    /**
     * 按照指定的id 删除一项
     *
     * @param houseInfo
     * @return 删除成功返回1 ，失败返回0
     */
    public int delete(UserInfoDBItem houseInfo) {
        try {
            DeleteBuilder<UserInfoDBItem, Integer> deleteBuilder = mHouseInfoDAO.deleteBuilder();
            deleteBuilder.where().eq("mUserInfoID", houseInfo.getmUserInfoID());
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
            DeleteBuilder<UserInfoDBItem, Integer> deleteBuilder = mHouseInfoDAO.deleteBuilder();
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
        mHouseInfoDAO.delete(queryAll());
    }

    /**
     * 查询所有的
     */
    public List<UserInfoDBItem> queryAll() {
        List<UserInfoDBItem> userInfos = mHouseInfoDAO.queryForAll();
        return userInfos;
    }

    public void closeDB(){
        mHouseInfoDBHelper.close();
    }
}
