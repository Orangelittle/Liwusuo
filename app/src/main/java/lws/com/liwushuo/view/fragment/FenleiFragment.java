package lws.com.liwushuo.view.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import lws.com.liwushuo.R;
import lws.com.liwushuo.adapter.fenlei.FenleiViewPagerAdapter;
import lws.com.liwushuo.view.fragment.fenlei.DanpinFragment;
import lws.com.liwushuo.view.fragment.fenlei.GonglveFragment;

/**
 * OurApp simple {@link Fragment} subclass.
 */
public class FenleiFragment extends Fragment {
    private View view;
    private GonglveFragment gonglveFragment = new GonglveFragment();
    private DanpinFragment danpinFragment = new DanpinFragment();
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> listFragment = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view != null) {
            return view;
        }
        view = inflater.inflate(R.layout.fragment_fenlei, container, false);

        tabLayout = ((TabLayout) view.findViewById(R.id.tl_fenlei));
        viewPager = ((ViewPager) view.findViewById(R.id.fl_fenlei));
        listFragment.add(gonglveFragment);
        listFragment.add(danpinFragment);
        viewPager.setAdapter(new FenleiViewPagerAdapter(getChildFragmentManager(),listFragment));
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

}
