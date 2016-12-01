package lws.com.liwushuo.adapter.shouye;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lws.com.liwushuo.R;
import lws.com.liwushuo.bean.shouye.TabTitleBean;

/**
 * Created by ZQ on 2016/10/13.
 */

public class PopwindowAdapter extends RecyclerView.Adapter<PopwindowAdapter.MyHolder> implements View.OnClickListener {

    private Context context;
    private List<TabTitleBean.DataBean.ChannelsBean> titlesBean;
    private int currentPosition = 0;//将当前的tabtitle位置传入间来,默认为0
    private OnItemClickListener onItemClickListener;
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

    public PopwindowAdapter(Context context) {
        this.context = context;
        this.titlesBean = new ArrayList<>();
    }

    public PopwindowAdapter(Context context,List<TabTitleBean.DataBean.ChannelsBean> titlesBean) {
        this.context = context;
        this.titlesBean = new ArrayList<>();
        this.titlesBean=titlesBean;
    }
    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
        notifyDataSetChanged();
    }

    public void addAll(List<TabTitleBean.DataBean.ChannelsBean> titlesBean) {
        titlesBean.addAll(titlesBean);
        notifyDataSetChanged();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shouye_main_popwindow_item, null);
       view.setOnClickListener(this);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        //如果当前选中tablayout页面是当前页面就改变字体颜色
        if (currentPosition == position) {
            holder.itemText.setTextColor(context.getResources().getColor(R.color.rb_text_color));
            holder.itemBottomLine.setVisibility(View.VISIBLE);
        }else{//这里必须要写，因为有复用，每次都要重新设置
            holder.itemText.setTextColor(context.getResources().getColor(R.color.ShouyeBigWord));
            holder.itemBottomLine.setVisibility(View.GONE);
        }
        holder.itemText.setText(titlesBean.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return titlesBean.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {

        private TextView itemText;//文字内容
        private View itemBottomLine;//底部红线
        public MyHolder(View itemView) {
            super(itemView);
            itemText = (TextView) itemView.findViewById(R.id.shouye_main_popwindow_itemText);
            itemBottomLine = itemView.findViewById(R.id.shouye_main_popwindow_itemBottomLine);
        }
    }

    //自定义点击事件
    public interface OnItemClickListener{
        /**
         * 自定义点击方法
         * @param v  点击的item
         * @param recyclerView  当前的recyclerView
         * @param position      点击的item在recyclerView中list的位置
         */
        void onItemClick(View v,RecyclerView recyclerView,int position);
    }

    //注册点击事件
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * View的点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (onItemClickListener!=null){
            int position = recyclerView.getChildAdapterPosition(v);
            onItemClickListener.onItemClick(v,recyclerView,position);
        }
    }

}
