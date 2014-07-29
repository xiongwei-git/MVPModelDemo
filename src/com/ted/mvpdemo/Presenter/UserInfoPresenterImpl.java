package com.ted.mvpdemo.Presenter;

import android.content.Context;
import com.ted.mvpdemo.DB.UserInfoDBItem;
import com.ted.mvpdemo.Model.UserInfoModelImpl;
import com.ted.mvpdemo.View.UserInfoView;

/**
 * Created by Ted on 14-7-29.
 */
public class UserInfoPresenterImpl implements UserInfoPresenter{
    UserInfoView mUserInfoView;
    UserInfoModelImpl mUserInfoModel;

    public UserInfoPresenterImpl(UserInfoView userInfoView,Context context) {
        mUserInfoView = userInfoView;
        mUserInfoModel = new UserInfoModelImpl(context);
    }

    @Override
    public int SaveUserInfo() {
        if(mUserInfoView.getUserInfoID() == -1){
            return -1;
        }
        UserInfoDBItem userInfoDBItem = new UserInfoDBItem();
        userInfoDBItem.setmUserInfoID(mUserInfoView.getUserInfoID());
        userInfoDBItem.setmName(mUserInfoView.getUserInfoName());
        userInfoDBItem.setmMobile(mUserInfoView.getUserInfoMobile());
        mUserInfoModel.SaveUserInfo(userInfoDBItem);
        return 0;
    }

    @Override
    public int LoadUserInfo() {
        if(mUserInfoView.getUserInfoID() == -1){
            return -1;
        }
        UserInfoDBItem userInfoDBItem = mUserInfoModel.LoadUserInfo(mUserInfoView.getUserInfoID());
        if(userInfoDBItem == null){
            return -1;
        }
        mUserInfoView.setUserInfoID(userInfoDBItem.getmUserInfoID());
        mUserInfoView.setUserInfoName(userInfoDBItem.getmName());
        mUserInfoView.setUserInfoMobile(userInfoDBItem.getmMobile());
        return 0;
    }

    @Override
    public void CloseDB() {
        mUserInfoModel.closeDB();
    }
}
