package lws.com.liwushuo.view.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import lws.com.liwushuo.R;
import lws.com.liwushuo.adapter.bangdan.BangdanAdapter;
import lws.com.liwushuo.view.fragment.bangdan.MeirituijianFragment;

/**
 * OurApp simple {@link Fragment} subclass.
 */
public class BangdanFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private BangdanAdapter adapter;
    private List<Fragment> list;
    private String[] titles = {"每日推荐","TOP100","独立原创榜","新星榜"};

    public BangdanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bangdan, container, false);
        tabLayout = ((TabLayout) view.findViewById(R.id.bangdan_tab));
        viewPager = ((ViewPager) view.findViewById(R.id.bangdan_viewPager));
        list = new ArrayList<>();
        list.add(new MeirituijianFragment(1,21,0));
        list.add(new MeirituijianFragment(2,21,0));
        list.add(new MeirituijianFragment(3,21,0));
        list.add(new MeirituijianFragment(4,21,0));
        adapter = new BangdanAdapter(getChildFragmentManager(),list,titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}