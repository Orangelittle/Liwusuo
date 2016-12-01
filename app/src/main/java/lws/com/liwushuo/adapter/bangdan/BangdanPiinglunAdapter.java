package lws.com.liwushuo.adapter.bangdan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import lws.com.liwushuo.R;
import lws.com.liwushuo.bean.bangdan.BangdanPinglunBean;

/**
 * Created by YaYun on 2016/10/14.
 */

public class BangdanPiinglunAdapter extends RecyclerView.Adapter<BangdanPiinglunAdapter.ViewHolder> implements View.OnClickListener {

    private List<BangdanPinglunBean.DataBean.CommentsBean> list;
    private LayoutInflater inflater;
    private RecyclerView mRecyclerView;
    private Context context;

    private OnItemClickListener clickLietener;
    //对外提供接口初始化方法
    public void setClickLietener(BangdanPiinglunAdapter.OnItemClickListener clickLietener){
        this.clickLietener=clickLietener;
    }


    public BangdanPiinglunAdapter(Context context,List<BangdanPinglunBean.DataBean.CommentsBean> list) {
        this.list = list;
//        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.pinglun,null);
        itemView.setOnClickListener(this);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
         BangdanPinglunBean.DataBean.CommentsBean commentBean = list.get(position);
         holder.avatar_url.setImageURI(commentBean.getUser().getAvatar_url());

         holder.content.setText(commentBean.getContent());
        if(commentBean.getReplied_user()!=null){
            holder.content.setText("回复"+commentBean.getReplied_user().getNickname()+":"+commentBean.getContent());
        }else {
            holder.nickname.setText(commentBean.getUser().getNickname());
            holder.content.setText(commentBean.getContent());
        }

         Date date = new Date(commentBean.getCreated_at());
         String strs = "";
         SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 HH:mm:ss");
         strs = sdf.format(date);
         holder.time.setText(strs);
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    @Override
    public void onClick(View v) {
        int childAdapterPosition = mRecyclerView.getChildAdapterPosition(v);
        if(clickLietener != null){
            clickLietener.setItemClickListener(childAdapterPosition);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private SimpleDraweeView avatar_url;
        private TextView content;
        private TextView nickname;
        private TextView time;

        public ViewHolder(View itemView) {
            super(itemView);
            avatar_url = ((SimpleDraweeView) itemView.findViewById(R.id.pinglun_avatar_url));
            content = ((TextView) itemView.findViewById(R.id.pinglun_content));
            nickname = ((TextView) itemView.findViewById(R.id.pinglun_nickname0));
            time = ((TextView) itemView.findViewById(R.id.pinglun_time));
        }
    }

    public interface OnItemClickListener{
        void setItemClickListener(int position);
    }
}
