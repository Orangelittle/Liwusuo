package lws.com.liwushuo.adapter.shouye;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import lws.com.liwushuo.R;
import lws.com.liwushuo.bean.shouye.JinxuanLiwuBean;

/**
 * Created by ZQ on 2016/10/13.
 */

public class JinxuanListViewAdapter extends BaseAdapter {

    private Context context;
    private List<JinxuanLiwuBean.DataBean.ItemsBean> list;

    public JinxuanListViewAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHolder myHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.shouye_jinxuan_listview_item, null);
            myHolder = new MyHolder(convertView);
            convertView.setTag(myHolder);
        }
        myHolder = (MyHolder) convertView.getTag();
        //绑定数据
        onBinder(myHolder, list.get(position));
        return convertView;
    }


    public void addAll(List<JinxuanLiwuBean.DataBean.ItemsBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void clear() {
        this.list.clear();
        notifyDataSetChanged();
    }

    public void add(JinxuanLiwuBean.DataBean.ItemsBean bean) {
        this.list.add(bean);
        notifyDataSetChanged();
    }

    /**
     * 数据绑定
     */
    private void onBinder(MyHolder myHolder, JinxuanLiwuBean.DataBean.ItemsBean itemsBean) {
        myHolder.itemImg.setImageURI(itemsBean.getCover_image_url());
        myHolder.itemTextTitle.setText(itemsBean.getTitle());
        myHolder.itemIntroduction.setText(itemsBean.getIntroduction());
        if (itemsBean.getColumn() == null) {
            //坑比数据！！！有时候没有column数据！！！！
            myHolder.itemLanmu.setVisibility(View.GONE);
            myHolder.itemColumnTitle.setVisibility(View.GONE);
        }else{
            if (itemsBean.getColumn().getTitle().equals("")) {
                myHolder.itemLanmu.setVisibility(View.GONE);
            } else {
                myHolder.itemColumnTitle.setText(itemsBean.getColumn().getTitle());
            }
        }
        myHolder.itemLikeNum.setText(itemsBean.getLikes_count() + "");
    }

    public static class MyHolder {
        private SimpleDraweeView itemImg;
        private TextView itemTextTitle;
        private TextView itemIntroduction;
        private TextView itemLanmu;
        private TextView itemColumnTitle;
        private TextView itemLikeNum;

        public MyHolder(View itemView) {
            itemImg = ((SimpleDraweeView) itemView.findViewById(R.id.jinxuan_listView_itemImg));
            itemTextTitle = ((TextView) itemView.findViewById(R.id.jinxuan_listview_itemTextTitle));
            itemIntroduction = ((TextView) itemView.findViewById(R.id.jinxuan_listView_itemIntroduction));
            itemLanmu = (TextView) itemView.findViewById(R.id.jinxuan_listView_itemLanmu);
            itemColumnTitle = (TextView) itemView.findViewById(R.id.jinxuan_listView_itemColumnTitle);
            itemLikeNum = (TextView) itemView.findViewById(R.id.jinxuan_listView_itemLikeNum);
        }
    }
}
