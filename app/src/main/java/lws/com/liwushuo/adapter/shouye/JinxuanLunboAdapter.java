package lws.com.liwushuo.adapter.shouye;

import android.content.Context;
import android.support.v4.util.Pools;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import lws.com.liwushuo.bean.shouye.JinxuanLunboBean;

/**
 * Created by ZQ on 2016/10/13.
 */

public class JinxuanLunboAdapter extends PagerAdapter {
    private Context context;
    private Pools.Pool<SimpleDraweeView> pool = new Pools.SimplePool<>(4);//轮播页面的缓冲池，用来复用的
    private List<JinxuanLunboBean.DataBean.BannersBean> list;
    private LinearLayout view;

    @Override
    public int getCount() {
        return list.size();
    }

    public JinxuanLunboAdapter(Context context, List<JinxuanLunboBean.DataBean.BannersBean> list) {
        this.context = context;
        this.list = new ArrayList<>(list);
        JinxuanLunboBean.DataBean.BannersBean first = list.get(0);
        JinxuanLunboBean.DataBean.BannersBean last = list.get(list.size() - 1);
        this.list.add(0, last);//在集合第一个加上最后一张图
        this.list.add(first);//在集合最后一张图 加上第一张图
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        SimpleDraweeView draweeView=pool.acquire();
        if (draweeView==null){
            draweeView=new SimpleDraweeView(context);
        }
        draweeView.setMinimumWidth(container.getWidth());
        draweeView.setAspectRatio(2.13f);
        draweeView.setImageURI(list.get(position).getImage_url());
        container.addView(draweeView);
        return draweeView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        pool.release((SimpleDraweeView) object);
    }

}
