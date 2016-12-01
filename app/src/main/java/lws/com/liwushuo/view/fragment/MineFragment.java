package lws.com.liwushuo.view.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import lws.com.liwushuo.R;
import lws.com.liwushuo.adapter.mine.MineViewPagerAdapter;
import lws.com.liwushuo.view.fragment.mine.Blank2Fragment;
import lws.com.liwushuo.view.fragment.mine.BlankFragment;

/**
 * OurApp simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment {

    private TabLayout tab;
    private AppBarLayout mine_appbar;
    private Toolbar mine_toolbar;
    private ImageView saoma;
    private ImageView setting;
    private ImageView message;
    private ImageView qiandao;
    private View view_line;

    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_mine, container, false);
        tab = (TabLayout) view.findViewById(R.id.mine_tab);
        List<Fragment> list = new ArrayList<>();
        list.add(new BlankFragment());
        list.add(new Blank2Fragment());
        String [] titles={"单品","攻略"};
        MineViewPagerAdapter adapter = new MineViewPagerAdapter( getChildFragmentManager(),list,titles);
        ViewPager pager = (ViewPager) view.findViewById(R.id.mine_viewpager);
        pager.setAdapter(adapter);
        tab.setupWithViewPager(pager);

        mine_toolbar = ((Toolbar) view.findViewById(R.id.mine_toolbar));
        saoma = ((ImageView) view.findViewById(R.id.saoma));
        setting = ((ImageView) view.findViewById(R.id.setting));
        message = ((ImageView) view.findViewById(R.id.message));
        qiandao = ((ImageView) view.findViewById(R.id.qiandao));
        view_line = ((View) view.findViewById(R.id.view_line));

        mine_appbar = ((AppBarLayout) view.findViewById(R.id.mine_appbar));
        mine_appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int appbarheight= mine_appbar.getTotalScrollRange();
                float percent=Math.abs((float)verticalOffset/appbarheight);
                int alpha = Math.round(percent * 255);
                mine_toolbar.getBackground().setAlpha(alpha);

                if (Math.abs(verticalOffset)==appbarheight){
                    view_line.setVisibility(View.VISIBLE);
                    message.setImageResource(R.mipmap.mine_message2);
                    saoma.setImageResource(R.mipmap.mine_scanner2);
                    setting.setImageResource(R.mipmap.mine_settings2);
                    qiandao.setImageResource(R.mipmap.mine_sign2);
                }else{
                    view_line.setVisibility(View.INVISIBLE);
                    message.setImageResource(R.mipmap.mine_message);
                    saoma.setImageResource(R.mipmap.mine_scanner);
                    setting.setImageResource(R.mipmap.mine_settings);
                    qiandao.setImageResource(R.mipmap.mine_sign);
                }
            }
        });


        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==1) {
                    mine_appbar.setExpanded(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }

}
