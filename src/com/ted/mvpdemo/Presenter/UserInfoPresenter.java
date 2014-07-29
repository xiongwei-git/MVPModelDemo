package com.ted.mvpdemo.Presenter;

import com.ted.mvpdemo.DB.UserInfoDBItem;

/**
 * Created by Ted on 14-7-15.
 */
public interface UserInfoPresenter {
    public int SaveUserInfo();
    public int LoadUserInfo();
    public void CloseDB();
}
