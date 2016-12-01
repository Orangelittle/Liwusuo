package lws.com.liwushuo.adapter.fenlei;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import lws.com.liwushuo.R;
import lws.com.liwushuo.view.fragment.fenlei.DanpinFragment;

/**
 * Created by zhangziyang on 2016/10/13.
 */

public class DanpinListviewAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private DanpinFragment fragment;

    private int item_height;

    public DanpinListviewAdapter(Context context, List<String> list, DanpinFragment fragment) {
        this.context = context;
        this.list = list;
        this.fragment = fragment;

        View view = LayoutInflater.from(context).inflate(R.layout.danpin_listview_textview,null);
        item_height = view.getHeight();
//        Log.e("自定义标签", "类DanpinListviewAdapter中的方法DanpinListviewAdapter:  "+item_height);
    }

    public void add(String string) {
        if (list != null) {
            list.add(string);
        }
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int i) {
        return list == null ? null : list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        view = LayoutInflater.from(context).inflate(R.layout.danpin_listview_textview, viewGroup, false);

        item_height = view.getMeasuredHeight();
        viewHolder = new ViewHolder(view);
        viewHolder.textView.setText(list.get(i));
        if (i == fragment.getSelectedPosition()) {  //新点击的
            viewHolder.frameLayout.setBackgroundResource(R.drawable.danpin_listview_item_left);
            viewHolder.textView.setTextColor(context.getResources().getColor(R.color.rb_text_color));
            fragment.setLastSelectedPosition(i);
        } else {
            viewHolder.frameLayout.setBackgroundColor(context.getResources().getColor(R.color.listviewitem));
        }

        return view;
    }

    public static class ViewHolder {
        private TextView textView;
        private FrameLayout frameLayout;

        public ViewHolder(View itemView) {
            textView = ((TextView) itemView.findViewById(R.id.tv_danpin_listView_textView));
            frameLayout = ((FrameLayout) itemView.findViewById(R.id.fl_danpin_listView));

        }
    }
}
