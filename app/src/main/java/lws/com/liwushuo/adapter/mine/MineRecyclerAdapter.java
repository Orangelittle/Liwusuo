package lws.com.liwushuo.adapter.mine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import lws.com.liwushuo.R;

/**
 * Created by King on 2016/10/15.
 */

public class MineRecyclerAdapter extends RecyclerView.Adapter<MineRecyclerAdapter.ViewHolder>
{
    private Context context;
    private List<String> list;

    public MineRecyclerAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mine_sub_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(list.get(position));
            holder.image.setImageResource(R.mipmap.bg_profile);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView textView;
        private final ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = ((TextView) itemView.findViewById(R.id.tv));
            image = ((ImageView) itemView.findViewById(R.id.iv));

        }
    }
}
