package com.bway.day0622_demo.mvp.view;

import com.bway.day0622_demo.base.IView;
import com.bway.day0622_demo.mvp.model.bean.NewsBean;

public interface NewsView extends IView{

    void onSuccess(NewsBean newsBean);
    void onFaild(String error);

}
