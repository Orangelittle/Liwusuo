package lws.com.liwushuo.adapter.fenlei;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import lws.com.liwushuo.R;
import lws.com.liwushuo.bean.fenlei.DanpinDetailsBean;

/**
 * Created by zhangziyang on 2016/10/15.
 */

public class DanpinDetailsRecyclerviewAdapter extends RecyclerView.Adapter<DanpinDetailsRecyclerviewAdapter.ViewHolder>{
    private Context context;
    private List<DanpinDetailsBean.DataBean.ItemsBean> list;

    public DanpinDetailsRecyclerviewAdapter(Context context, List<DanpinDetailsBean.DataBean.ItemsBean> list) {
        this.context = context;
        this.list = list;
    }

    public void addAll(List<DanpinDetailsBean.DataBean.ItemsBean> aList){
        list.addAll(aList);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.danpin_details_item,null);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.price.setText("￥"+list.get(position).getPrice());
        holder.sdv.setImageURI(list.get(position).getCover_image_url());
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
//        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
//            @Override
//            public int getSpanSize(int position) {
//
//                return 0;
//            }
//        });
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private SimpleDraweeView sdv;
        private TextView name;
        private TextView price;

        public ViewHolder(View itemView) {
            super(itemView);
            sdv = ((SimpleDraweeView) itemView.findViewById(R.id.sdv_danpin_deails_item));
            name = ((TextView) itemView.findViewById(R.id.tv_danpin_details_name));
            price = ((TextView) itemView.findViewById(R.id.tv_danpin_details_price));
            sdv.setAspectRatio(1.2f);
        }
    }
}
