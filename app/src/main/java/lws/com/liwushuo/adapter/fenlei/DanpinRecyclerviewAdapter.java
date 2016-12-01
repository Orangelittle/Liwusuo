package lws.com.liwushuo.adapter.fenlei;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import lws.com.liwushuo.R;
import lws.com.liwushuo.bean.fenlei.DanpinBean;

/**
 * Created by zhangziyang on 2016/10/13.
 */

public class DanpinRecyclerviewAdapter extends RecyclerView.Adapter<DanpinRecyclerviewAdapter.ViewHolder> {
    private Context context;
    private List<DanpinBean.DataBean.CategoriesBean> categoriesBeanList;
    private RecyclerView recyclerView;

    public DanpinRecyclerviewAdapter(Context context, List<DanpinBean.DataBean.CategoriesBean> categoriesBeanList) {
        this.context = context;
        this.categoriesBeanList = categoriesBeanList;
    }

    public void add(DanpinBean.DataBean.CategoriesBean categoriesBean) {
        if (categoriesBeanList != null) {
            categoriesBeanList.add(categoriesBean);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.danpin_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.item_name.setText(categoriesBeanList.get(position).getName());
        holder.item_recyclerView.setAdapter(new DanpinRecyclerviewItemAdapter(context, categoriesBeanList.get(position).getSubcategories()));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);

        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public int getItemCount() {
        return categoriesBeanList == null ? 0 : categoriesBeanList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView item_name;
        private RecyclerView item_recyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            item_name = ((TextView) itemView.findViewById(R.id.tv_danpin_item_name));
            item_recyclerView = ((RecyclerView) itemView.findViewById(R.id.rv_danpin_item));
        }
    }

}
