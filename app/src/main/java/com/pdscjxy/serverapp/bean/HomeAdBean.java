package com.pdscjxy.serverapp.bean;

import android.text.TextUtils;

import com.pdscjxy.serverapp.util.JsonUtils;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/31.
 */

public class HomeAdBean implements BaseBean {

        private String title;
        private String message;
        private String imageUrl;
        private String targetUrl;
        private String imageUrl4Share;

    @Override
    public void parse(JSONObject jo) {
        imageUrl = JsonUtils.getString(jo, "imageUrl");
//        state = JsonUtils.getString(jo, "state");


    }
//        private String getCorrectUrlString(String str) {
//            String url = json.getString(str);
//            if (!TextUtils.isEmpty(url))
//                url = url.replace("\\","");
//            return url;
//        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }


        public String getTargetUrl() {
            return targetUrl;
        }

        public void setTargetUrl(String targetUrl) {
            this.targetUrl = targetUrl;
        }

        public void setImageUrl4Share(String imageUrl4Share) {
            this.imageUrl4Share = imageUrl4Share;
        }

        public String getImageUrl4Share() {

            return imageUrl4Share;
        }

        @Override
        public String toString() {
            return "HomeAdBean{" +
                    "title='" + title + '\'' +
                    ", message='" + message + '\'' +
                    ", imageUrl='" + imageUrl + '\'' +
                    ", targetUrl='" + targetUrl + '\'' +
                    '}';

        }
}
