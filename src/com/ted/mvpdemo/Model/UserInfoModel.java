package com.ted.mvpdemo.Model;

import com.ted.mvpdemo.DB.UserInfoDBItem;

/**
 * Created by Ted on 14-7-29.
 */
public interface UserInfoModel {
    public UserInfoDBItem LoadUserInfo(int id);
    public void SaveUserInfo(UserInfoDBItem userInfoDBItem);
}
