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
import lws.com.liwushuo.bean.fenlei.LanmuDetailsBean;

/**
 * Created by zhangziyang on 2016/10/14.
 */

public class LanmuDetailsAdapter extends BaseAdapter {
    private Context context;
    private List<LanmuDetailsBean.DataBean.PostsBean> list;

    public LanmuDetailsAdapter(Context context, List<LanmuDetailsBean.DataBean.PostsBean> list) {
        this.context = context;
        this.list = list;
    }

    public void addAll(List<LanmuDetailsBean.DataBean.PostsBean> aList){
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
            view = LayoutInflater.from(context).inflate(R.layout.lanmu_details_listview_item,null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        viewHolder = ((ViewHolder) view.getTag());
        viewHolder.message.setText(list.get(i).getTitle());
        viewHolder.time.setText(list.get(i).getUpdated_at()+"");
        viewHolder.authorName.setText(list.get(i).getAuthor().getNickname());
        viewHolder.likeCount.setText(list.get(i).getLikes_count()+"");
        viewHolder.sdv.setImageURI(list.get(i).getCover_image_url());
        return view;
    }

    private static class ViewHolder{
        private View itemView;
        private SimpleDraweeView sdv;
        private TextView message;
        private TextView time;
        private TextView authorName;
        private TextView likeCount;

        public ViewHolder(View itemView) {
            this.itemView = itemView;
            sdv = ((SimpleDraweeView) itemView.findViewById(R.id.sdv_lanmu_item));
            message = ((TextView) itemView.findViewById(R.id.tv_lanmu_item_message));
            time = ((TextView) itemView.findViewById(R.id.tv_lanmu_item_time));
            authorName = ((TextView) itemView.findViewById(R.id.tv_lanmu_item_authorName));
            likeCount = ((TextView) itemView.findViewById(R.id.tv_lanmu_item_likeCount));
        }
    }
}
