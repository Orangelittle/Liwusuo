package lws.com.liwushuo.adapter.fenlei;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import lws.com.liwushuo.R;
import lws.com.liwushuo.bean.fenlei.DanpinBean;

/**
 * Created by zhangziyang on 2016/10/13.
 */

public class DanpinRecyclerviewItemAdapter extends RecyclerView.Adapter<DanpinRecyclerviewItemAdapter.ViewHolder>{
    private Context context;
    private List<DanpinBean.DataBean.CategoriesBean.SubcategoriesBean> list;
    private RecyclerView recyclerView;

    public DanpinRecyclerviewItemAdapter(Context context, List<DanpinBean.DataBean.CategoriesBean.SubcategoriesBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.danpin_item_item,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.icon.setImageURI(list.get(position).getIcon_url());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,3);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView name;
        private final SimpleDraweeView icon;

        public ViewHolder(View itemView) {
            super(itemView);
            name = ((TextView) itemView.findViewById(R.id.tv_danpin_item_item_name));
            icon = ((SimpleDraweeView) itemView.findViewById(R.id.sdv_danpin_item_item));
            icon.setAspectRatio(1.0f);
        }
    }
}
