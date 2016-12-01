package lws.com.liwushuo.adapter.shouye;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import lws.com.liwushuo.bean.shouye.TabTitleBean;
import lws.com.liwushuo.view.fragment.shouye.JinxuanFragment;

/**
 * Created by ZQ on 2016/10/12.
 */

public class ShouyeViewPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private List<TabTitleBean.DataBean.ChannelsBean> titles;
    private List<Fragment> list = new ArrayList<>();

    public ShouyeViewPagerAdapter(FragmentManager fm, Context context, List<TabTitleBean.DataBean.ChannelsBean> titles) {
        super(fm);
        this.context = context;
        this.titles = titles;
        //这里new出所有的fragment
        for (int i = 0; i < titles.size(); i++) {
            JinxuanFragment fragment = new JinxuanFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("channelsId", titles.get(i).getId());
            bundle.putString("pageName", titles.get(i).getName());
            fragment.setArguments(bundle);
            list.add(fragment);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return titles.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position).getName();
    }
}
