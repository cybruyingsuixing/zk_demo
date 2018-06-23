package com.bway.day0622_demo.utils;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtils {

    private final Handler handler;
    private final OkHttpClient okHttpClient;
    private static OkHttpUtils okHttpUtils;


    private OkHttpUtils() {
        handler = new Handler(Looper.getMainLooper());
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .writeTimeout(5000, TimeUnit.MILLISECONDS)
                .build();
    }

    //设置外部访问的类
    public static OkHttpUtils getInstance() {

        synchronized (OkHttpUtils.class) {
            if (okHttpUtils == null) {
                return okHttpUtils = new OkHttpUtils();
            }
        }
        return okHttpUtils;
    }

//设置get请求
    public void doGet(String url,final onCallBack onCallBack){

        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (onCallBack != null) {
                            onCallBack.onFaild(e);
                        }
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                try{
                    if(response!=null&&response.isSuccessful()){
                        final String json = response.body().string();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if(onCallBack!=null){
                                    onCallBack.onResponse(json);
                                }
                            }
                        });
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }if(onCallBack!=null){
                    onCallBack.onFaild(new Exception("有异常"));
                }
            }
        });

    }
    //设置post请求
    public void doPost(String url, final onCallBack onCallBack) {
        FormBody.Builder builder = new FormBody.Builder();
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
                .post(formBody)
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (onCallBack != null) {
                            onCallBack.onFaild(e);
                        }
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                try{
                    if(response!=null&&response.isSuccessful()){
                        final String json = response.body().string();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if(onCallBack!=null){
                                    onCallBack.onResponse(json);
                                }
                            }
                        });
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }if(onCallBack!=null){
                    onCallBack.onFaild(new Exception("有异常"));
                }
            }

        });

    }

    //设置接口

    public interface onCallBack {
        void onFaild(Exception e);

        void onResponse(String json);
    }

}
