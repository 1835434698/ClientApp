package com.pdscjxy.serverapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pdscjxy.serverapp.R;
import com.pdscjxy.serverapp.activity.base.BaseActivity;

/**
 * Created by tangzy on 17/10/31.
 */

public class SetActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        setTitleLayout(ONE_LAYOUT);
        setTitleText("");
        setLeftTitle("设置");

    }
}
