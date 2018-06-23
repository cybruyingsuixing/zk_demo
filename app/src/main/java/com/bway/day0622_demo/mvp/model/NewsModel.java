package com.bway.day0622_demo.mvp.model;

import com.bway.day0622_demo.mvp.model.bean.NewsBean;
import com.bway.day0622_demo.utils.HttpConfig;
import com.bway.day0622_demo.utils.OkHttpUtils;
import com.google.gson.Gson;

public class NewsModel {

    public void doNews(final onCallBack onCallBack){

        String url= HttpConfig.URL_ONE;
        OkHttpUtils.getInstance().doGet(url, new OkHttpUtils.onCallBack() {
            @Override
            public void onFaild(Exception e) {

            }

            @Override
            public void onResponse(String json) {
                Gson gson = new Gson();
                NewsBean newsBean = gson.fromJson(json, NewsBean.class);
                int code = newsBean.getCode();
                if(0==code){
                     if(onCallBack!=null){
                         onCallBack.onSuccess(newsBean);
                     }
                }else{
                    if(onCallBack!=null){
                        onCallBack.onFaild("失败");
                    }
                }
            }
        });

    }


    //设置接口
    public interface onCallBack{
        void onSuccess(NewsBean newsBean);
        void onFaild(String error);
    }
}
