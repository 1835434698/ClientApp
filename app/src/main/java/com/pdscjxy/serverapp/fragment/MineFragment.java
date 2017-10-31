package com.pdscjxy.serverapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.pdscjxy.serverapp.R;
import com.pdscjxy.serverapp.activity.SetActivity;
import com.pdscjxy.serverapp.activity.base.BaseActivity;
import com.pdscjxy.serverapp.fragment.base.BaseFragment;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/10/19.
 */

public class MineFragment  extends BaseFragment {

    @BindView(R.id.iv_head)
    ImageView iv_head;
    @BindView(R.id.tv_yqno)
    TextView tv_yqno;
    @BindView(R.id.tv_order_all)
    TextView tv_order_all;
    @BindView(R.id.tv_wallt_all)
    TextView tv_wallt_all;
    @BindView(R.id.tv_wallt_1)
    TextView tv_wallt_1;
    @BindView(R.id.tv_wallt_2)
    TextView tv_wallt_2;
    @BindView(R.id.tv_wallt_3)
    TextView tv_wallt_3;
    @BindView(R.id.tv_wallt_4)
    TextView tv_wallt_4;
    @BindView(R.id.ll_order_1)
    LinearLayout ll_order_1;
    @BindView(R.id.ll_order_2)
    LinearLayout ll_order_2;
    @BindView(R.id.ll_order_3)
    LinearLayout ll_order_3;
    @BindView(R.id.ll_order_4)
    LinearLayout ll_order_4;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setView(R.layout.fragment_mine);

        listener();
    }

    private void listener() {
        RxView.clicks(iv_head).throttleFirst(1, TimeUnit.SECONDS).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                Intent intent = new Intent(getActivity(), SetActivity.class);
                ((BaseActivity)getActivity()).startCallbackActivity(intent);
            }
        });
    }
}
