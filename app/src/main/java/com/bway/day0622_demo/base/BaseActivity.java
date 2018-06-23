package com.bway.day0622_demo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity <P extends BasePresenter> extends AppCompatActivity{

    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideId());
        initViews();
        initListener();
        //创建presenter
        presenter=prode();
        initData();
    }

    protected abstract void initData();

    protected abstract P prode();

    protected abstract void initListener();

    protected abstract void initViews();

    protected abstract int provideId();

    //防止内存泄漏

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestory();
    }
}
