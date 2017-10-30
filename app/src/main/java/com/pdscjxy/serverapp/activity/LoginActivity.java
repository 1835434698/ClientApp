package com.pdscjxy.serverapp.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.pdscjxy.serverapp.R;
import com.pdscjxy.serverapp.activity.base.BaseActivity;
import com.pdscjxy.serverapp.manager.Constant;
import com.pdscjxy.serverapp.net.OkHttpManager;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * Created by tangzy on 17/10/29.
 */

public class LoginActivity extends BaseActivity {

    private static final String TAG = "LoginActivity";

    @BindView(R.id.tv_login)
    TextView tv_login;
    @BindView(R.id.tv_register)
    TextView tv_register;
    @BindView(R.id.tv_forget_psw)
    TextView tv_forget_psw;

    @BindView(R.id.chbox_remember)
    CheckBox chbox_remember;

    @BindView(R.id.cb_show_pw_img)
    CheckedTextView cb_show_pw_img;

    @BindView(R.id.et_phone_number)
    EditText et_phone_number;
    @BindView(R.id.et_password)
    EditText et_password;

    @BindView(R.id.iv_login_mobile_delete)
    ImageView iv_login_mobile_delete;
    @BindView(R.id.iv_password_delete)
    ImageView iv_password_delete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Constant.context = this;
        setContentView(R.layout.activity_login);
        hideTitle();
        init();
        startCallbackActivity(new Intent(LoginActivity.this, MainActivityFragment.class));
    }

    private void init() {
        et_phone_number.setFilters(Constant.FILTER_LIMIT_PHONE_INPUT);
    }


    @OnTextChanged(value = R.id.et_phone_number)
    void phonewNumberChange(CharSequence text){
        if (TextUtils.isEmpty(text)){
            iv_login_mobile_delete.setVisibility(View.INVISIBLE);
        }else {
            iv_login_mobile_delete.setVisibility(View.VISIBLE);
        }
    }

    @OnTextChanged(value = R.id.et_password)
    void passwordChange(CharSequence text){
        if (TextUtils.isEmpty(text)){
            iv_password_delete.setVisibility(View.INVISIBLE);
        }else {
            iv_password_delete.setVisibility(View.VISIBLE);
        }
    }


    @OnClick(R.id.cb_show_pw_img)
    void showPsw() {
        cb_show_pw_img.toggle();
        if (cb_show_pw_img.isChecked()) {
//                    //如果选中，显示密码
            et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            et_password.setInputType((InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD));
            et_password.setTypeface(Typeface.SANS_SERIF);
        } else {
//                    //否则隐藏密码
            et_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            et_password.setTypeface(Typeface.SANS_SERIF);
        }
    }
    @OnClick(R.id.iv_login_mobile_delete)
    void mobileDele() {
        et_phone_number.setText("");
    }
    @OnClick(R.id.iv_password_delete)
    void passwordDele() {
        et_password.setText("");
    }
    @OnClick(R.id.tv_forget_psw)
    void forgetPsw() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        intent.putExtra(RegisterActivity.MOBILE, "18501942558");
        startCallbackActivity(intent);
    }
    @OnClick(R.id.tv_register)
    void register() {
        startCallbackActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    @OnClick(R.id.tv_login)
    void login() {
        startProgressDialog();
        final Map<String, String> httpParams =  new HashMap<>();
        httpParams.put("userName","1234567890");
        httpParams.put("userPassword","123456");//
        OkHttpManager.asyncRequest("test0.php", httpParams, new OkHttpManager.ResponseListener() {
            @Override
            public void onResp(JSONObject respons, String uri) {
                stopProgressDialog();
                startCallbackActivity(new Intent(LoginActivity.this, MainActivityFragment.class));
            }

            @Override
            public void onErr(String respons, String uri) {
                stopProgressDialog();
                startCallbackActivity(new Intent(LoginActivity.this, MainActivityFragment.class));
            }
        }, true);
    }
}
