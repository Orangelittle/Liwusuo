package lws.com.liwushuo.adapter.fenlei;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import lws.com.liwushuo.R;
import lws.com.liwushuo.app.OurApp;
import lws.com.liwushuo.bean.fenlei.DanpinBean;

/**
 * Created by zhangziyang on 2016/10/13.
 */

public class DanpinRecyclerviewAdapter1 extends RecyclerView.Adapter<DanpinRecyclerviewAdapter1.ViewHolder> implements View.OnClickListener {
    public Context context;
    //DanpinBean.DataBean.CategoriesBean.SubcategoriesBean
    private List<List<Object>> subcategoriesBeanList;
    private RecyclerView recyclerView;

    public static final int TITLE = 0;
    public static final int THREE_IMAGES = 1;

    private ArrayList<Integer> type = new ArrayList<>();  //用以记录每一行应该返回的布局的代号，不是layout的id

    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    public DanpinRecyclerviewAdapter1(Context context) {
        this.context = context;
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    @Override
    public void onClick(View view) {
        if (onRecyclerViewItemClickListener != null) {
            onRecyclerViewItemClickListener.onRecyclerViewItemClick(view, ((DanpinBean.DataBean.CategoriesBean.SubcategoriesBean) view.getTag()));
        }
    }

    public interface OnRecyclerViewItemClickListener{
        void onRecyclerViewItemClick(View view,DanpinBean.DataBean.CategoriesBean.SubcategoriesBean subcategoriesBean);
    }

    public List<List<Object>> getSubcategoriesBeanList() {
        return subcategoriesBeanList;
    }

    public void setSubcategoriesBeanList(List<List<Object>> subcategoriesBeanList) {
        this.subcategoriesBeanList = subcategoriesBeanList;
    }



    public ArrayList<Integer> getType() {
        return type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TITLE:
                view = LayoutInflater.from(context).inflate(R.layout.danpin_textview_top, null);
                return new ViewHolder(view);
            case THREE_IMAGES:
                view = LayoutInflater.from(context).inflate(R.layout.danpin_threeimage_item, null);
                return new ViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (type.get(position)) {
            case TITLE:
                holder.name.setText(((String) subcategoriesBeanList.get(position).get(0)));
                break;
            case THREE_IMAGES:
                holder.item_name_1.setText(((DanpinBean.DataBean.CategoriesBean.SubcategoriesBean) subcategoriesBeanList.get(position).get(0)).getName());
                holder.sdv_1.setImageURI(((DanpinBean.DataBean.CategoriesBean.SubcategoriesBean) subcategoriesBeanList.get(position).get(0)).getIcon_url());
                holder.ll_1.setOnClickListener(this);
                holder.ll_1.setTag(subcategoriesBeanList.get(position).get(0));
                if (subcategoriesBeanList.get(position).size() >= 2) {
                    holder.ll_2.setVisibility(View.VISIBLE);
                    holder.item_name_2.setText(((DanpinBean.DataBean.CategoriesBean.SubcategoriesBean) subcategoriesBeanList.get(position).get(1)).getName());
                    holder.sdv_2.setImageURI(((DanpinBean.DataBean.CategoriesBean.SubcategoriesBean) subcategoriesBeanList.get(position).get(1)).getIcon_url());
                    holder.ll_2.setOnClickListener(this);
                    holder.ll_2.setTag(subcategoriesBeanList.get(position).get(1));
                } else {
                    holder.ll_2.setVisibility(View.GONE);
                    holder.ll_3.setVisibility(View.GONE);
                }
                if (subcategoriesBeanList.get(position).size() == 3) {
                    holder.ll_3.setVisibility(View.VISIBLE);
                    holder.sdv_3.setImageURI(((DanpinBean.DataBean.CategoriesBean.SubcategoriesBean) subcategoriesBeanList.get(position).get(2)).getIcon_url());
                    holder.item_name_3.setText(((DanpinBean.DataBean.CategoriesBean.SubcategoriesBean) subcategoriesBeanList.get(position).get(2)).getName());
                    holder.ll_3.setOnClickListener(this);
                    holder.ll_3.setTag(subcategoriesBeanList.get(position).get(2));
                }else {
                    holder.ll_3.setVisibility(View.GONE);
                }
                break;
        }

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
        return type.size();
    }

    @Override
    public int getItemViewType(int position) {
        return type.get(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private LinearLayout ll_1;
        private LinearLayout ll_2;
        private LinearLayout ll_3;

        private SimpleDraweeView sdv_1;
        private SimpleDraweeView sdv_2;
        private SimpleDraweeView sdv_3;

        private TextView item_name_1;
        private TextView item_name_2;
        private TextView item_name_3;

        public ViewHolder(View itemView) {
            super(itemView);
            name = ((TextView) itemView.findViewById(R.id.tv_danpin_listView_textView));
            ll_1 = ((LinearLayout) itemView.findViewById(R.id.ll_danpin_threeimage_item1));
            ll_2 = ((LinearLayout) itemView.findViewById(R.id.ll_danpin_threeimage_item2));
            ll_3 = ((LinearLayout) itemView.findViewById(R.id.ll_danpin_threeimage_item3));
            sdv_1 = ((SimpleDraweeView) itemView.findViewById(R.id.sdv_danpin_item_item1));
            sdv_2 = ((SimpleDraweeView) itemView.findViewById(R.id.sdv_danpin_item_item2));
            sdv_3 = ((SimpleDraweeView) itemView.findViewById(R.id.sdv_danpin_item_item3));
            item_name_1 = ((TextView) itemView.findViewById(R.id.tv_danpin_item_item_name1));
            item_name_2 = ((TextView) itemView.findViewById(R.id.tv_danpin_item_item_name2));
            item_name_3 = ((TextView) itemView.findViewById(R.id.tv_danpin_item_item_name3));

            if (sdv_1 != null) {
//                WindowManager windowManager = ((WindowManager) OurApp.context.getSystemService(Context.WINDOW_SERVICE));
//                int width = windowManager.getDefaultDisplay().getWidth();
//                ViewGroup.LayoutParams layoutParams1 = sdv_1.getLayoutParams();
//                layoutParams1.width = width/3;
//                ViewGroup.LayoutParams layoutParams2 = sdv_2.getLayoutParams();
//                layoutParams2.width = width/3;
//                ViewGroup.LayoutParams layoutParams3 = sdv_3.getLayoutParams();
//                layoutParams3.width = width/3;
                sdv_1.setAspectRatio(0.8f);
                sdv_2.setAspectRatio(0.8f);
                sdv_3.setAspectRatio(0.8f);
            }
        }
    }

}
