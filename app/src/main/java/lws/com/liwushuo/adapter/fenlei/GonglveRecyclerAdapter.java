package lws.com.liwushuo.adapter.fenlei;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import lws.com.liwushuo.R;
import lws.com.liwushuo.bean.fenlei.LanmuBean;

/**
 * Created by zhangziyang on 2016/10/12.
 */

public class GonglveRecyclerAdapter extends RecyclerView.Adapter<GonglveRecyclerAdapter.ViewHolder> implements View.OnClickListener {
    private List<LanmuBean.DataBean.ColumnsBean> lanmuBeanList;
    private Context context;

    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    public GonglveRecyclerAdapter(Context context ,List<LanmuBean.DataBean.ColumnsBean> lanmuBeanList) {
        this.context = context;
        this.lanmuBeanList = lanmuBeanList;
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    @Override
    public void onClick(View view) {
        if (onRecyclerViewItemClickListener != null) {
            onRecyclerViewItemClickListener.onRecyclerViewItemClick(view, ((LanmuBean.DataBean.ColumnsBean) view.getTag()));
        }
    }

    public interface OnRecyclerViewItemClickListener{
        void onRecyclerViewItemClick(View view,LanmuBean.DataBean.ColumnsBean columnsBean);
    }

    public void addAll(List<LanmuBean.DataBean.ColumnsBean> aLanmuBeanList){
        lanmuBeanList.addAll(aLanmuBeanList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.fenlei_recyclerview_item,null);
        ViewHolder viewHolder = new ViewHolder(itemView);
        viewHolder.ll_gonglve_1.setOnClickListener(this);
        viewHolder.ll_gonglve_2.setOnClickListener(this);
        viewHolder.ll_gonglve_3.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.author1.setText(lanmuBeanList.get(position*3).getAuthor());
        holder.title1.setText(lanmuBeanList.get(position*3).getTitle());
        holder.bannerImage1.setImageURI(lanmuBeanList.get(position*3).getBanner_image_url());
        holder.ll_gonglve_1.setTag(lanmuBeanList.get(position*3)); //设置tag，为了点击时获取参数
        
        holder.author2.setText(lanmuBeanList.get(position*3+1).getAuthor());
        holder.title2.setText(lanmuBeanList.get(position*3+1).getTitle());
        holder.bannerImage2.setImageURI(lanmuBeanList.get(position*3+1).getBanner_image_url());
        holder.ll_gonglve_2.setTag(lanmuBeanList.get(position*3+1)); //设置tag，为了点击时获取参数
        
        if(lanmuBeanList.size()>=(position+1)*3){
            holder.author3.setText(lanmuBeanList.get(position*3+2).getAuthor());
            holder.title3.setText(lanmuBeanList.get(position*3+2).getTitle());
            holder.bannerImage3.setImageURI(lanmuBeanList.get(position*3+2).getBanner_image_url());
            holder.ll_gonglve_3.setTag(lanmuBeanList.get(position*3+2)); //设置tag，为了点击时获取参数
        }else{
            holder.ll_gonglve_3.setVisibility(View.GONE);
            holder.chakanquanbu.setVisibility(View.VISIBLE);
            holder.chakanquanbu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return lanmuBeanList==null?0:(lanmuBeanList.size()+2)/3;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title1;
        private TextView author1;
        private SimpleDraweeView bannerImage1;
        private TextView title2;
        private TextView author2;
        private SimpleDraweeView bannerImage2;
        private TextView title3;
        private TextView author3;
        private SimpleDraweeView bannerImage3;
        private LinearLayout ll_gonglve_1;
        private LinearLayout ll_gonglve_2;
        private LinearLayout ll_gonglve_3;
        private TextView chakanquanbu;

        public ViewHolder(View itemView) {
            super(itemView);
            bannerImage1 = ((SimpleDraweeView) itemView.findViewById(R.id.iv_gonglve_banner_image_url1));
            title1 = ((TextView) itemView.findViewById(R.id.tv_recyclerView_title1));
            author1 = ((TextView) itemView.findViewById(R.id.tv_recyclerView_author1));
            bannerImage2 = ((SimpleDraweeView) itemView.findViewById(R.id.iv_gonglve_banner_image_url2));
            title2 = ((TextView) itemView.findViewById(R.id.tv_recyclerView_title2));
            author2 = ((TextView) itemView.findViewById(R.id.tv_recyclerView_author2));
            bannerImage3 = ((SimpleDraweeView) itemView.findViewById(R.id.iv_gonglve_banner_image_url3));
            title3 = ((TextView) itemView.findViewById(R.id.tv_recyclerView_title3));
            author3 = ((TextView) itemView.findViewById(R.id.tv_recyclerView_author3));

            ll_gonglve_1 = ((LinearLayout) itemView.findViewById(R.id.ll_gonglve_1));
            ll_gonglve_2 = ((LinearLayout) itemView.findViewById(R.id.ll_gonglve_2));
            ll_gonglve_3 = ((LinearLayout) itemView.findViewById(R.id.ll_gonglve_3));

            chakanquanbu = ((TextView) itemView.findViewById(R.id.tv_lanmu_chakanquanbu));
        }
    }
}
