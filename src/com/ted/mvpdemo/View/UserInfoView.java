package com.ted.mvpdemo.View;

/**
 * Created by Ted on 14-7-29.
 */
public interface UserInfoView {
    void setUserInfoID(int infoID);
    void setUserInfoName(String name);
    void setUserInfoMobile(String mobile);

    int getUserInfoID();
    String getUserInfoName();
    String getUserInfoMobile();
}
