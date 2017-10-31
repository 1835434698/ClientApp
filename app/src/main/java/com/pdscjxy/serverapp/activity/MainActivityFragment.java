package com.pdscjxy.serverapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pdscjxy.serverapp.R;
import com.pdscjxy.serverapp.activity.base.BaseActivity;
import com.pdscjxy.serverapp.fragment.FirstFragment;
import com.pdscjxy.serverapp.fragment.MineFragment;

/**
 * Created by Administrator on 2017/10/26.
 */

public class MainActivityFragment extends BaseActivity{
    private RelativeLayout tab1, tab2;
    private ImageView tab1_image, tab2_image;
    private TextView tab1_text, tab2_text;
    private int currentIndex = -1;
    private LinearLayout bottom_layout;
//    2.）设置绑定生命周期
//    Glide.with(Context context);// 绑定Context
//    Glide.with(Activity activity);// 绑定Activity
//    Glide.with(FragmentActivity activity);// 绑定FragmentActivity
//    Glide.with(Fragment fragment);// 绑定Fragment
//    3. ）简单的加载图片实例
//    Glide.with(this).load(imageUrl).into(imageView);
//    4.）设置加载中以及加载失败图片
//    api里面对placeholder()、error()函数中有多态实现 用的时候可以具体的熟悉一下
//    Glide.with(this).load(imageUrl).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(imageView);
//    5.）设置跳过内存缓存
//    Glide.with(this).load(imageUrl).skipMemoryCache(true).into(imageView);
//    6.）设置下载优先级
//    Glide.with(this).load(imageUrl).priority(Priority.NORMAL).into(imageView);
//    7.）设置缓存策略
//    Glide.with(this).load(imageUrl).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
//    策略解说：
//    all:缓存源资源和转换后的资源
//    none:不作任何磁盘缓存
//    source:缓存源资源
//    result：缓存转换后的资源
//    8.）设置加载动画
//    api也提供了几个常用的动画：比如crossFade()
//    Glide.with(this).load(imageUrl).animate(R.anim.item_alpha_in).into(imageView);
//<?xml version="1.0" encoding="utf-8"?>
//    <set xmlns:android="http://schemas.android.com/apk/res/android">
//    <alpha
//    android:duration="500"
//    android:fromAlpha="0.0"
//    android:toAlpha="1.0"/>
//    </set>
//    9.）设置缩略图支持
//    这样会先加载缩略图 然后在加载全图
//    Glide.with(this).load(imageUrl).thumbnail(0.1f).into(imageView);
//    10.）设置加载尺寸
//    Glide.with(this).load(imageUrl).override(800, 800).into(imageView);
//    11.）设置动态转换
//    Glide.with(this).load(imageUrl).centerCrop().into(imageView);
//    11.）设置动态转换
//    Glide.with(this).load(imageUrl).centerCrop().into(imageView);
//    api提供了比如：centerCrop()、fitCenter()等函数也可以通过自定义Transformation，举例说明：比如一个人圆角转化器
//    public class GlideRoundTransform extends BitmapTransformation {
//        private float radius = 0f;
//        public GlideRoundTransform(Context context) {
//            this(context, 4);
//        }
//        public GlideRoundTransform(Context context, int dp) {
//            super(context);
//            this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
//        }
//        @Override
//        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
//            return roundCrop(pool, toTransform);
//        }
//        private Bitmap roundCrop(BitmapPool pool, Bitmap source) {
//            if (source == null) return null;
//            Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
//            if (result == null) {
//                result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
//            }
//            Canvas canvas = new Canvas(result);
//            Paint paint = new Paint();
//            paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
//            paint.setAntiAlias(true);
//            RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
//            canvas.drawRoundRect(rectF, radius, radius, paint);
//            return result;
//        }
//        @Override
//        public String getId() {
//            return getClass().getName() + Math.round(radius);
//        }
//    }
//    Glide.with(this).load(imageUrl).transform(new GlideRoundTransform(this)).into(imageView);
//    12.）设置要加载的内容
//    项目中有很多需要先下载图片然后再做一些合成的功能，比如项目中出现的图文混排，该如何实现目标下
//    Glide.with(this).load(imageUrl).centerCrop().into(new SimpleTarget<GlideDrawable>() {
//        @Override
//        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
//            imageView.setImageDrawable(resource);
//        }
//    });
//    13 .）设置监听请求接口
//    Glide.with(this).load(imageUrl).listener(new RequestListener<String, GlideDrawable>() {
//        @Override
//        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//            return false;
//        }
//
//        @Override
//        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//            //imageView.setImageDrawable(resource);
//            return false;
//        }
//    }).into(imageView);
//    设置监听的用处 可以用于监控请求发生错误来源，以及图片来源 是内存还是磁盘
//    15.)设置动态GIF加载方式
//    Glide.with(this).load(imageUrl).asBitmap().into(imageView);//显示gif静态图片
//    Glide.with(this).load(imageUrl).asGif().into(imageView);//显示gif动态图片
//    16.）缓存的动态清理
//    Glide.get(this).clearDiskCache();//清理磁盘缓存 需要在子线程中执行
//    Glide.get(this).clearMemory();//清理内存缓存  可以在UI主线程中进行

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
//        initTitleBar();
        hideTitle();
        initView();
        initTab();
        initListener();
        setTab(0);
    }

    private void initView() {
        // TODO Auto-generated method stub
        tab1 = (RelativeLayout) findViewById(R.id.tab1);
        tab2 = (RelativeLayout) findViewById(R.id.tab2);
        tab1_image = (ImageView) findViewById(R.id.tab1_image);
        tab2_image = (ImageView) findViewById(R.id.tab2_image);
        tab1_text = (TextView) findViewById(R.id.tab1_text);
        tab2_text = (TextView) findViewById(R.id.tab2_text);

        bottom_layout = (LinearLayout) findViewById(R.id.bottom_layout);
    }

    private void initTab() {//根据用户权限选择下导航显示的内容
//        switch (UserManager.getInstance().getUserRights()) {//0:客户经理；1：客服；2：团队;3:门店经理/副理；4：分部总；5：区域总
//            case 0:
//            case 2:
//                tab1_text.setText(getResources().getString(R.string.home_page));
//                tab2.setVisibility(View.VISIBLE);
//                tab2_text.setText(getResources().getString(R.string.client_page));
//                tab3_text.setText(getResources().getString(R.string.lent_page));
//                tab4_text.setText(getResources().getString(R.string.my_page));
//                break;
//
//            case 3:
//                tab1_text.setText(getResources().getString(R.string.home_page));
//                tab2.setVisibility(View.GONE);
//                tab3_text.setText(getResources().getString(R.string.lent_page));
//                tab4_text.setText(getResources().getString(R.string.my_page));
//                break;
//        }
    }

    private void initListener() {
        // TODO Auto-generated method stub
        tab1.setOnClickListener(new TabClickListener());
        tab2.setOnClickListener(new TabClickListener());
    }

    private class TabClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch (v.getId()) {
                case R.id.tab1:
                    setTab(0);
                    break;

                case R.id.tab2:
                    setTab(1);
                    break;
            }
        }
    }
    public void setTab(int tab) {//选择当前处于显示状态的tab
        // TODO Auto-generated method stub
        if (currentIndex == tab) {
            return;
        }
        switch (tab) {
            case 0:
                setTabView(0);
                break;

            case 1:
                setTabView(1);
                break;
        }
    }
    public void setTabView(int tab) {
        switch (tab) {
            case 0://
                setClientTabView(tab);
                setClientFragment(tab, 0);
                break;

            case 1://
                setClientTabView(tab);
                setClientFragment(tab, 1);
                break;
        }
    }
    public void setClientFragment(int tab, int tag) {
        if (currentIndex == tab) {
            return;
        }
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment tab0 = (FirstFragment) fragmentManager.findFragmentByTag("tab0");
        Fragment tab1 = (MineFragment) fragmentManager.findFragmentByTag("tab1");
        switch (tab) {
            case 0:
                if (tab1 != null) {
                    fragmentTransaction.hide(tab1);
                }
                if (tab0 == null) {
                    //0:客户经理；1：门店；2：总部
                    tab0 = new FirstFragment();
                    fragmentTransaction.add(R.id.fragment_content, tab0, "tab0");
                } else {
                    fragmentTransaction.show(tab0);
                }
                break;
            case 1:
                if (tab0 != null) {
                    fragmentTransaction.hide(tab0);
                }
                if (tab1 == null) {
                    tab1 = new MineFragment();
                    fragmentTransaction.add(R.id.fragment_content, tab1, "tab1");
                } else {
                    fragmentTransaction.show(tab1);
                }
                break;
        }
        fragmentTransaction.commitAllowingStateLoss();
        currentIndex = tab;
    }

    //公有方法，客户经理首页通过我的业务横向列表调用改变下导航的显示样式和切换Fragment页面
    public void setClientHomeBottomBar(int tag) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment tab0 = fragmentManager.findFragmentByTag("tab0");
        Fragment tab1 = fragmentManager.findFragmentByTag("tab1");
        if (tab0 != null) {
            fragmentTransaction.hide(tab0);
        }
        if (tab1 == null) {
            tab1 = new FirstFragment();

            Bundle bun = new Bundle();
            bun.putInt("tag", tag);
            tab1.setArguments(bun);
            fragmentTransaction.add(R.id.fragment_content, tab1, "tab1");
        } else {

//            tab1.obtainTag(tag);
            fragmentTransaction.show(tab1);
        }

        currentIndex = 1;
        fragmentTransaction.commitAllowingStateLoss();
        setClientTabView(1);
    }

    public void setClientTabView(int tab) {//处理客户经理角色时下导航字体和图标状态
        switch (tab) {
            case 0:
//                tab1_image.setImageResource(R.drawable.iv_a);
//                tab2_image.setImageResource(R.drawable.client_home_client);
//                tab1_text.setTextColor(getResources().getColor(R.color.color_2772FF));
//                tab2_text.setTextColor(getResources().getColor(R.color.color_515151));
                break;
            case 1:
//                tab1_image.setImageResource(R.drawable.client_home_home);
//                tab2_image.setImageResource(R.drawable.iv_b);
//                tab1_text.setTextColor(getResources().getColor(R.color.color_515151));
//                tab2_text.setTextColor(getResources().getColor(R.color.color_2772FF));
                break;
        }
    }

}
