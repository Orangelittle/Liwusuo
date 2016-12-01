package lws.com.liwushuo.adapter.fenlei;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by zhangziyang on 2016/10/12.
 */

public class FenleiViewPagerAdapter extends FragmentPagerAdapter{
    private List<Fragment> fragmentList;
    private String[] titles = {"攻略","单品"};

    public FenleiViewPagerAdapter(FragmentManager fm ,List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList==null?null:fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList==null?0:fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
