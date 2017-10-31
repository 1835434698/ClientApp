package com.pdscjxy.serverapp.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.pdscjxy.serverapp.R;
import com.pdscjxy.serverapp.adapter.CommonAdapter;
import com.pdscjxy.serverapp.adapter.ViewHolder;
import com.pdscjxy.serverapp.bean.HomeAdBean;
import com.pdscjxy.serverapp.bean.HomeBean;
import com.pdscjxy.serverapp.fragment.base.BaseFragment;
import com.pdscjxy.serverapp.util.Logger;
import com.pdscjxy.serverapp.view.CanForbidViewPager;
import com.pdscjxy.serverapp.view.ZdyListView;
import com.pdscjxy.serverapp.view.ZdyScrollView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;


/**
 * Created by Administrator on 2017/10/19.
 */

public class FirstFragment extends BaseFragment {

    private static final String TAG = "FirstFragment";

    @BindView(R.id.main_home_viewpager)
    CanForbidViewPager main_home_viewpager;
    @BindView(R.id.rl_viewpager_text)
    TextView rl_viewpager_text;
    @BindView(R.id.lv_home)
    ZdyListView lv_home;

    @BindView(R.id.scrollView)
    ZdyScrollView scrollView;

    private CommonAdapter<HomeBean> homeAdapter;
    private List<HomeBean> lists = new ArrayList<>();


    private List<HomeAdBean> adBeans = new ArrayList<HomeAdBean>();//广告列表
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Logger.d(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        setView(R.layout.fragment_first);

        init();
        HomeAdBean item = new HomeAdBean();
        item.setImageUrl("");
        adBeans.add(item);
        item = new HomeAdBean();
        item.setImageUrl("");
        adBeans.add(item);
        viewPagerAdapter.notifyDataSetChanged();
        HomeBean home;
        for (int i = 0; i<5; i++){
            home = new HomeBean();
            lists.add(home);
        }
        homeAdapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logger.d(TAG, "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    private void init() {
        main_home_viewpager.setAdapter(viewPagerAdapter);
        homeAdapter = new CommonAdapter<HomeBean>(getActivity(), lists, R.layout.item_home) {
            @Override
            public void convert(ViewHolder helper, HomeBean item) {
//                Logger.d(TAG, "lists = "+lists.size());

            }
        };
        lv_home.setAdapter(homeAdapter);
        scrollView.setOnZdyScrollViewListener(new ZdyScrollView.OnZdyScrollViewListener() {
            @Override
            public void ZdyScrollViewListener() {
                Logger.d(TAG, "More");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                HomeBean home;
                                for (int i = 0; i<5; i++){
                                    home = new HomeBean();
                                    lists.add(home);
                                }
                                homeAdapter.notifyDataSetChanged();
                                lv_home.LoadingComplete();
                                scrollView.loadingComponent();

                            }
                        });

                    }
                }).start();

            }
        });
    }

    private int max = 20000;
    private PagerAdapter viewPagerAdapter = new PagerAdapter() {

        private int resourceNo;

        @Override
        public int getCount() {
            return adBeans.size() == 0 ? 0 : max;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(getActivity(), R.layout.item_viewpager, null);
            ImageView image = (ImageView) view.findViewById(R.id.viewpager_imageview);
            if (adBeans.size() == 0) {
                return view;
            }
//            final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.viewpager_item_progressbar);
            resourceNo = position % adBeans.size();
            rl_viewpager_text.setText((resourceNo+1)+"/"+adBeans.size());
            HomeAdBean bean = adBeans.get(resourceNo);
            view.setTag(resourceNo);
            view.setOnClickListener(adViewClickListener);
            String imageUrl = bean.getImageUrl();
            String targetUrl = bean.getTargetUrl();

            Glide.with(getActivity()).load(R.mipmap.ico_vp_1).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(image);
//            Glide.with(getActivity()).load(imageUrl).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(image);

//            DisplayImageOptions imageVpOptions = ImageLoaderFactory.getImageVpOptions();
//            ImageLoader.getInstance().displayImage(imageUrl, image, ImageLoaderFactory.getOptionsBuilder(R.drawable.main_home_ad).build(), new ImageLoadingListener() {
//
//                @Override
//                public void onLoadingStarted(String s, View view) {
//                    progressBar.setVisibility(View.VISIBLE);
//                }
//
//                @Override
//                public void onLoadingFailed(String s, View view, FailReason failReason) {
//                    progressBar.setVisibility(View.GONE);
//                }
//
//                @Override
//                public void onLoadingComplete(String s, View view, Bitmap bitmap) {
//                    progressBar.setVisibility(View.GONE);
//                }
//
//                @Override
//                public void onLoadingCancelled(String s, View view) {
//                    progressBar.setVisibility(View.GONE);
//                }
//            });


            container.addView(view);
            return view;
        }

    };

    private View.OnClickListener adViewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Logger.i(TAG, "adViewClickListener onClick");
            int position = (Integer) v.getTag();
            HomeAdBean bean = adBeans.get(position);
            String targetUrl = bean.getTargetUrl();
            String title = bean.getTitle();
            if (!TextUtils.isEmpty(targetUrl)) {
                Logger.e("xsw", "targetUrl:" + targetUrl + "   title:" + title);
                // TODO: 2016/6/18 服务器所返回的url不包含http://
                HashMap<String, Object> params = new HashMap<>();
//                params.put(PreferentialBean.TAG, bean);
//                ui.startPreferentialWebViewActivity(getString(R.string.intercommerce_preferential_detail), params);
            }
        }
    };

}
