package lws.com.liwushuo.adapter.bangdan;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by YaYun on 2016/10/12.
 */

public class BangdanAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    private String[] titles;

    public BangdanAdapter(FragmentManager fm,List<Fragment> list,String[] titles) {
        super(fm);
        this.list = list;
        this.titles = titles;
    }


    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
