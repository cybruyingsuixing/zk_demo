package com.bway.day0622_demo.mvp.presenter;

import com.bway.day0622_demo.base.BasePresenter;
import com.bway.day0622_demo.mvp.model.NewsModel;
import com.bway.day0622_demo.mvp.model.bean.NewsBean;
import com.bway.day0622_demo.mvp.view.NewsView;

public class NewsPresenter extends BasePresenter<NewsView> {


    private NewsModel newsModel;

    public NewsPresenter(NewsView view) {
        super(view);
    }

    @Override
    protected void initModel() {
        newsModel = new NewsModel();
    }

    public void doNews(){
        newsModel.doNews(new NewsModel.onCallBack() {
            @Override
            public void onSuccess(NewsBean newsBean) {
                if(view!=null){
                    view.onSuccess(newsBean);
                }
            }

            @Override
            public void onFaild(String error) {

                if(view!=null){
                    view.onFaild(error);
                }
            }
        });

    }
}
