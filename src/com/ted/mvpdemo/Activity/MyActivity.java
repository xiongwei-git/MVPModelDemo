package com.ted.mvpdemo.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.ted.mvpdemo.DB.ScoreInfoDBUtil;
import com.ted.mvpdemo.Presenter.UserInfoPresenterImpl;
import com.ted.mvpdemo.R;
import com.ted.mvpdemo.View.UserInfoView;

public class MyActivity extends Activity implements UserInfoView, View.OnClickListener{
    private EditText mInfoId;
    private EditText mInfoName;
    private EditText mInfoMobile;
    private Button mReadBtn;
    private Button mSaveBtn;
    private UserInfoPresenterImpl userInfoPresenter;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScoreInfoDBUtil scoreInfoDBUtil = new ScoreInfoDBUtil(this);
        scoreInfoDBUtil.queryAll();

        setContentView(R.layout.main);
        mInfoId = (EditText)findViewById(R.id.info_id);
        mInfoName = (EditText)findViewById(R.id.info_name);
        mInfoMobile = (EditText)findViewById(R.id.info_mobile);
        mReadBtn = (Button)findViewById(R.id.info_read);
        mSaveBtn = (Button)findViewById(R.id.info_save);
        userInfoPresenter = new UserInfoPresenterImpl(this,this);
        mSaveBtn.setOnClickListener(this);
        mReadBtn.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        userInfoPresenter.CloseDB();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.info_read:
                if(userInfoPresenter.LoadUserInfo() < 0){
                    Toast.makeText(MyActivity.this,"请输入正确的ID",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.info_save:
                if(userInfoPresenter.SaveUserInfo() < 0){
                    Toast.makeText(MyActivity.this,"请输入正确的ID",Toast.LENGTH_SHORT).show();
                }else{
                    clearData();
                    Toast.makeText(MyActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
    @Override
    public void setUserInfoID(int infoID) {
        mInfoId.setText(infoID+"");
    }

    @Override
    public void setUserInfoName(String name) {
        mInfoName.setText(name);
    }

    @Override
    public void setUserInfoMobile(String mobile) {
        mInfoMobile.setText(mobile);
    }


    @Override
    public int getUserInfoID() {
        String idStr = mInfoId.getText().toString();
        if("".equals(idStr)||null == idStr){
            return -1;
        }else{
            int id = Integer.valueOf(idStr).intValue();
            return id>0?id:-1;
        }
    }

    @Override
    public String getUserInfoName() {
        return mInfoName.getText().toString();
    }

    @Override
    public String getUserInfoMobile() {
        return mInfoMobile.getText().toString();
    }

    private void clearData(){
        mInfoId.setText("");
        mInfoName.setText("");
        mInfoMobile.setText("");
    }
}
