package lws.com.liwushuo.adapter.bangdan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lws.com.liwushuo.R;
import lws.com.liwushuo.bean.bangdan.BangdanBean;

/**
 * Created by YaYun on 2016/10/12.
 */

public class MeirituijiamAdapter extends RecyclerView.Adapter<MeirituijiamAdapter.ViewHolder> implements View.OnClickListener {

    private final Context context;
    private List<BangdanBean.DataBean.ItemsBean> list;
    private LayoutInflater inflater;
    private RecyclerView mRecyclerView;//用来计算child 的位置
    private OnItemClickListener clickLietener;
    private String firthPath;

    public void setClickLietener(OnItemClickListener clickLietener){
        this.clickLietener=clickLietener;
    }


    public MeirituijiamAdapter(Context context,List<BangdanBean.DataBean.ItemsBean> list) {
        this.list = list;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    //创建ViewHolder,导入布局，实例化itemView
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        switch(viewType){
            case 1:
                itemView = inflater.inflate(R.layout.bangdan_item1,null);
                break;
            case 2:
                itemView = inflater.inflate(R.layout.bangdan_item2,null);
                break;
        }
        itemView.setOnClickListener(this);
        return new ViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position) {
            if(position == 0){
                return 1;
            }else {
                return 2;
            }
    }

    //绑定ViewHolder，加载数据
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case 1:
                addFirst(firthPath);
                SimpleDraweeView bangdan_item1_img = (SimpleDraweeView) holder.getView(R.id.bangdan_item1_img);
                bangdan_item1_img.setImageURI(firthPath);
                break;
            case 2:
                ((TextView) holder.getView(R.id.bandan_price)).setText(list.get(position-1).getPrice());
                ((TextView) holder.getView(R.id.bangdan_name)).setText(list.get(position-1).getName());
                ((TextView) holder.getView(R.id.bangdan_short_description)).setText(list.get(position-1).getShort_description());
                SimpleDraweeView bangdan_cover_image_url = (SimpleDraweeView) holder.getView(R.id.bangdan_cover_image_url);
                String picPaht1 = list.get(position-1).getCover_image_url();
                bangdan_cover_image_url.setImageURI(picPaht1);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }
    



    //适配器绑定到RecyclerView的时候，会将绑定适配器的RecyclerView传递过来
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    public void addRes(List<BangdanBean.DataBean.ItemsBean> aList){
        if(list != null){
            list.addAll(aList);
            notifyDataSetChanged();
        }
    }

    public void addFirst(String firthPath){
        if(firthPath != null){
            this.firthPath = firthPath;
        }
    }
    @Override
    public void onClick(View v) {
        int childAdapterPosition = mRecyclerView.getChildAdapterPosition(v);
        if(clickLietener != null){
            clickLietener.setItemClickListener(childAdapterPosition);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private Map<Integer,View> mCacheView;

        public ViewHolder(View itemView) {
            super(itemView);
            mCacheView = new HashMap<>();
        }

        public View getView(int resId){
            View view;
            if(mCacheView.containsKey(resId)){
                view = mCacheView.get(resId);
            }else{
                view = itemView.findViewById(resId);
                mCacheView.put(resId,view);
            }
            return view;
        }
    }


    //接口回调1.定义接口，定义接口中的方法，2.在数据产生的地方持有接口，并提供初始化方法，在数据产生的时候调用借口的方法
    //3.在需要处理数据的地方实现接口，实现接口中的方法，并将接口传递到数据产生的地方
    public interface OnItemClickListener{
        void setItemClickListener(int position);
    }
}
