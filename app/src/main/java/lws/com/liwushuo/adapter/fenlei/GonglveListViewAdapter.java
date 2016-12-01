package lws.com.liwushuo.adapter.fenlei;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import lws.com.liwushuo.R;
import lws.com.liwushuo.bean.fenlei.GonglveListViewItemBean;

/**
 * Created by zhangziyang on 2016/10/12.
 */

public class GonglveListViewAdapter extends BaseAdapter{
    private Context context;
    private List<GonglveListViewItemBean.DataBean.ChannelGroupsBean> list;

    public GonglveListViewAdapter(Context context, List<GonglveListViewItemBean.DataBean.ChannelGroupsBean> list) {
        this.context = context;
        this.list = list;
    }

    public void addAll(List<GonglveListViewItemBean.DataBean.ChannelGroupsBean> aList){
        list.addAll(aList);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public Object getItem(int i) {
        return list==null?null:list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.fenlei_gonglve_item,null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        viewHolder = ((ViewHolder) view.getTag());
        viewHolder.name.setText(list.get(i).getName());
        viewHolder.image1.setImageURI(list.get(i).getChannels().get(0).getCover_image_url());
        viewHolder.image2.setImageURI(list.get(i).getChannels().get(1).getCover_image_url());
        viewHolder.image3.setImageURI(list.get(i).getChannels().get(2).getCover_image_url());
        viewHolder.image4.setImageURI(list.get(i).getChannels().get(3).getCover_image_url());
        viewHolder.image5.setImageURI(list.get(i).getChannels().get(4).getCover_image_url());
        viewHolder.image6.setImageURI(list.get(i).getChannels().get(5).getCover_image_url());
        if (list.size()<=6) {
            viewHolder.chakan.setVisibility(View.GONE);
        }


        return view;
    }

    public static class ViewHolder{
        private TextView name;
        private TextView chakan;
        private SimpleDraweeView image1;
        private SimpleDraweeView image2;
        private SimpleDraweeView image3;
        private SimpleDraweeView image4;
        private SimpleDraweeView image5;
        private SimpleDraweeView image6;

        public ViewHolder(View itemView){
            name = ((TextView) itemView.findViewById(R.id.tv_gonglve_name));
            chakan = ((TextView) itemView.findViewById(R.id.tv_gonglve_chakan));
            image1 = ((SimpleDraweeView) itemView.findViewById(R.id.iv_gonglve_item_1));
            image2 = ((SimpleDraweeView) itemView.findViewById(R.id.iv_gonglve_item_2));
            image3 = ((SimpleDraweeView) itemView.findViewById(R.id.iv_gonglve_item_3));
            image4 = ((SimpleDraweeView) itemView.findViewById(R.id.iv_gonglve_item_4));
            image5 = ((SimpleDraweeView) itemView.findViewById(R.id.iv_gonglve_item_5));
            image6 = ((SimpleDraweeView) itemView.findViewById(R.id.iv_gonglve_item_6));
            image1.setAspectRatio(2.122f);
            image2.setAspectRatio(2.122f);
            image3.setAspectRatio(2.122f);
            image4.setAspectRatio(2.122f);
            image5.setAspectRatio(2.122f);
            image6.setAspectRatio(2.122f);
        }
    }
}
