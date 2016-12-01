package lws.com.liwushuo.adapter.shouye;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import lws.com.liwushuo.R;
import lws.com.liwushuo.bean.shouye.GonglueDetaiBottomRecommendBean;

/**
 * Created by ZQ on 2016/10/15.
 */

public class GonglueDetialBottomAdapter extends RecyclerView.Adapter<GonglueDetialBottomAdapter.MyHolder> implements View.OnClickListener {

    private Context context;
    private List<GonglueDetaiBottomRecommendBean.DataBean.RecommendPostsBean> list;
    private ItemOnClickListener itemOnClickListener;
    private RecyclerView recyclerView;


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }
    //注册监听
    public void setItemOnClickListener(ItemOnClickListener itemOnClickListener) {
        this.itemOnClickListener = itemOnClickListener;
    }

    @Override
    public void onClick(View v) {
        if (itemOnClickListener!=null){
            int position = recyclerView.getChildAdapterPosition(v);
            itemOnClickListener.itemOnClick(v,position,list.get(position));
        }
    }

    public interface ItemOnClickListener{
        void itemOnClick(View v,int position,GonglueDetaiBottomRecommendBean.DataBean.RecommendPostsBean data);
    }

    public GonglueDetialBottomAdapter(Context context) {
        this.context = context;
        this.list=new ArrayList<>();
    }

    public void addAll(List<GonglueDetaiBottomRecommendBean.DataBean.RecommendPostsBean> beans){
        list.addAll(beans);
        notifyDataSetChanged();
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shouye_gonglue_bottom_guessyoulike_item, null);
        view.setOnClickListener(this);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        GonglueDetaiBottomRecommendBean.DataBean.RecommendPostsBean recommendPostsBean = list.get(position);
        holder.bottomImg.setImageURI(recommendPostsBean.getCover_image_url());
        holder.bottomTitle.setText(recommendPostsBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder{

        private SimpleDraweeView bottomImg;
        private TextView bottomTitle;
        public MyHolder(View itemView) {
            super(itemView);
            bottomImg=(SimpleDraweeView)itemView.findViewById(R.id.gonglue_bottom_guessyoulike_img);
            bottomTitle=(TextView)itemView.findViewById(R.id.gonglue_bottom_guessyoulike_title);
        }
    }
}
