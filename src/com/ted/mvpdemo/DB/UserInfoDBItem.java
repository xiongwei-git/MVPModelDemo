package com.ted.mvpdemo.DB;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Ted on 14-7-29.
 */
@DatabaseTable(tableName = "userinfo")
public class UserInfoDBItem {
    /**用户信息编号，作为主键*/
    @DatabaseField(id=true)
    private int mUserInfoID;
    /**用户姓名*/
    @DatabaseField()
    private String mName;
    /**用户手机号码*/
    @DatabaseField
    private String mMobile;
    public UserInfoDBItem(){}

    public int getmUserInfoID() {
        return mUserInfoID;
    }

    public void setmUserInfoID(int mUserInfoID) {
        this.mUserInfoID = mUserInfoID;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmMobile() {
        return mMobile;
    }

    public void setmMobile(String mMobile) {
        this.mMobile = mMobile;
    }
}
