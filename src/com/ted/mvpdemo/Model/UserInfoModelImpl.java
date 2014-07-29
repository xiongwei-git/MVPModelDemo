package com.ted.mvpdemo.Model;

import android.content.Context;
import com.ted.mvpdemo.DB.UserInfoDBItem;
import com.ted.mvpdemo.DB.UserInfoDBUtil;

import java.util.List;

/**
 * Created by Ted on 14-7-29.
 */
public class UserInfoModelImpl implements UserInfoModel{
    UserInfoDBUtil userInfoDBUtil;

    public UserInfoModelImpl(Context context){
        userInfoDBUtil = new UserInfoDBUtil(context);
    }

    @Override
    public UserInfoDBItem LoadUserInfo(int id) {
        List<UserInfoDBItem> userInfoDBItems = userInfoDBUtil.queryAll();
        UserInfoDBItem userInfoDBItem = null;
        for(UserInfoDBItem userInfo:userInfoDBItems){
            if(id==userInfo.getmUserInfoID()){
                userInfoDBItem = userInfo;
            }
        }
        return userInfoDBItem;
    }

    @Override
    public void SaveUserInfo(UserInfoDBItem userInfoDBItem) {
        userInfoDBUtil.insert(userInfoDBItem);
    }
    public void closeDB(){
        userInfoDBUtil.closeDB();
    }
}
