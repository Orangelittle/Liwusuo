package lws.com.liwushuo.adapter.bangdan;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by YaYun on 2016/10/14.
 */

public class BanddanerAdapter extends FragmentStatePagerAdapter {
    private String[] titles;
    private List<Fragment> fragmentList;
    public BanddanerAdapter(FragmentManager fm,String[] titles,List<Fragment> fragmentList) {
        super(fm);
        this.titles = titles;
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
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
