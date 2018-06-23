package com.bway.day0622_demo.mvp.view.activity;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bway.day0622_demo.R;
import com.bway.day0622_demo.base.BaseActivity;
import com.bway.day0622_demo.mvp.adapter.MyRecylerViewAdapter;
import com.bway.day0622_demo.mvp.model.bean.NewsBean;
import com.bway.day0622_demo.mvp.presenter.NewsPresenter;
import com.bway.day0622_demo.mvp.view.NewsView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<NewsPresenter> implements View.OnClickListener, NewsView {


    private List<NewsBean.DataBeanX.DataBean> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private MyRecylerViewAdapter adapter;

    @Override
    protected void initData() {
        presenter.doNews();
    }

    @Override
    protected NewsPresenter prode() {
        return new NewsPresenter((NewsView) this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initViews() {

        //初始化
        recyclerView = findViewById(R.id.main_recy);

    }

    @Override
    protected int provideId() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onSuccess(NewsBean newsBean) {

        List<NewsBean.DataBeanX.DataBean> data = newsBean.getData().getData();
        list.addAll(data);
        //创建适配器
        adapter = new MyRecylerViewAdapter(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);

//长按条目删除
        adapter.setOnItemLongClickListener(new MyRecylerViewAdapter.OnItemLongClickListener() {
            @Override
            public void OnItemLongClick(View view, final int position) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("删除条目");
                builder.setMessage("确定要删除本宝宝吗");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton("取消", null);
                builder.show();
            }
        });

        //点击图片改变图片透明度
        adapter.setOnItemClickListener(new MyRecylerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {

                ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f, 1f);
                alpha.setDuration(5000);
                alpha.start();
            }
        });


    }

    @Override
    public void onFaild(String error) {

    }
}
