package com.pdscjxy.serverapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.pdscjxy.serverapp.R;
import com.pdscjxy.serverapp.activity.base.BaseActivity;
import com.pdscjxy.serverapp.net.OkHttpManager;
import com.pdscjxy.serverapp.util.Logger;

import org.json.JSONObject;


public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    private TextView tv_look;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideTitle();
        init();
    }

    private void init() {
        tv_look = (TextView) findViewById(R.id.tv_look);
        tv_look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCallbackActivity(new Intent(MainActivity.this, OrderActivity.class));

            }
        });
    }


    private OkHttpManager.ResponseListener listener = new OkHttpManager.ResponseListener() {
        @Override
        public void onResp(JSONObject respons, String uri) {
            Logger.d(TAG, "onResp = "+respons);
//            Logger.d(TAG, "respons = "+respons.optString("userid"));
//            stopProgressDialog();
//            JSONObject json = null;
//            try {
//                json = new JSONObject(respons);
//                Toasts.showToast(json.optString("retMessage"), Toast.LENGTH_SHORT);
//                if (json.optString("retCode").equals("000000")){
////                    MiddleView.getInstance().startCleanActivity(FirstPageAc.class, null);
//
//                }
//            } catch (JSONException e) {
//                Toasts.showToast("返回数据格式不正确", Toast.LENGTH_SHORT);
//                e.printStackTrace();
//            }
        }

        @Override
        public void onErr(String respons, String uri) {
//            stopProgressDialog();
//            Logger.d(TAG, "onErr = "+respons);
//            Toasts.showToast("网络地址错误", Toast.LENGTH_SHORT);

        }
    };
}
