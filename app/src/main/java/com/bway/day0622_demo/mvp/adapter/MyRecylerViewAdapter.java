package com.bway.day0622_demo.mvp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bway.day0622_demo.R;
import com.bway.day0622_demo.app.MyApp;
import com.bway.day0622_demo.mvp.model.bean.NewsBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MyRecylerViewAdapter extends RecyclerView.Adapter {

    //定义三种常量  表示三种条目类型
    public static final int viewtype01 = 0;
    public static final int viewtype02 = 1;


    private List<NewsBean.DataBeanX.DataBean> list;

    public MyRecylerViewAdapter(List<NewsBean.DataBeanX.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.base_item01, parent, false);
        View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.base_item02, parent, false);

          if(viewType==viewtype01){
              return new MyViewHolder01(view1);
          }
        return new MyViewHolder02(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

       if(holder instanceof MyViewHolder01){
           ((MyViewHolder01) holder).textView01.setText(list.get(position).getTitle());
           ((MyViewHolder01) holder).textView02.setText(list.get(position).getComment_amount()+"评论");
           ((MyViewHolder01) holder).textView03.setText(list.get(position).getViews()+"点赞");
           String url = "http://365jia.cn/uploads/"+list.get(position).getPics().get(0);
           ImageLoader.getInstance().displayImage(url,((MyViewHolder01) holder).imageView, MyApp.getOptions());

           holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
               @Override
               public boolean onLongClick(View v) {
                   onItemLongClickListener.OnItemLongClick(v,position);
                   return false;
               }
           });

           ((MyViewHolder01) holder).imageView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   onItemClickListener.OnItemClick(v,position);
               }
           });


       }else{
           ((MyViewHolder02) holder).textView01.setText(list.get(position).getTitle());
           ((MyViewHolder02) holder).textView02.setText(list.get(position).getComment_amount()+"评论");
           ((MyViewHolder02) holder).textView03.setText(list.get(position).getViews()+"点赞");
           String url0 = "http://365jia.cn/uploads/"+list.get(position).getPics().get(0);
           String url1 = "http://365jia.cn/uploads/"+list.get(position).getPics().get(1);
           String url2 = "http://365jia.cn/uploads/"+list.get(position).getPics().get(2);
           ImageLoader.getInstance().displayImage(url0,((MyViewHolder02) holder).img01,MyApp.getOptions());
           ImageLoader.getInstance().displayImage(url1,((MyViewHolder02) holder).img02,MyApp.getOptions());
           ImageLoader.getInstance().displayImage(url2,((MyViewHolder02) holder).img03,MyApp.getOptions());
       }

    }

    @Override
    public int getItemViewType(int position) {

        List<String> pics = list.get(position).getPics();

        if(pics.size()<3){
            return viewtype01;
        }
        return viewtype02;
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }


    public class MyViewHolder01 extends RecyclerView.ViewHolder {

        private final TextView textView01;
        private final TextView textView02;
        private final TextView textView03;
        private final ImageView imageView;

        public MyViewHolder01(View itemView) {
            super(itemView);
            textView01 = itemView.findViewById(R.id.tv_title_item01);
            textView02 = itemView.findViewById(R.id.tv_comment_item01);
            textView03 = itemView.findViewById(R.id.tv_views_item01);
            imageView = itemView.findViewById(R.id.iv_img_item01);


        }
    }


    public class MyViewHolder02 extends RecyclerView.ViewHolder {

        private TextView textView01;
        private final TextView textView02;
        private final TextView textView03;
        private final ImageView img01;
        private final ImageView img02;
        private final ImageView img03;

        public MyViewHolder02(View itemView) {
            super(itemView);
            //获取资源id
            textView01 = itemView.findViewById(R.id.tv_title_item02);
            textView02 = itemView.findViewById(R.id.tv_comment_item02);
            textView03 = itemView.findViewById(R.id.tv_views_item02);
            img01 = itemView.findViewById(R.id.iv_img01_item02);
            img02 = itemView.findViewById(R.id.iv_img02_item02);
            img03 = itemView.findViewById(R.id.iv_img03_item02);
        }

    }
    public interface OnItemClickListener{
        void OnItemClick(View view,int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    private OnItemLongClickListener onItemLongClickListener;

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemLongClickListener{
        void OnItemLongClick(View view,int position);
    }

}
