package lws.com.liwushuo.view.fragment.bangdan;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import lws.com.liwushuo.R;
import lws.com.liwushuo.adapter.bangdan.BanddanerAdapter;

public class BangdanerActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private BanddanerAdapter adapter;
    private List<Fragment> fragmentList;
    private String[] titles = new String[]{"单品", "详情", "评论"};
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView back;
    private ImageView share;
    private ImageView shoucang;
    private ImageView fenxiang;
    private Button goumai;
    private String purchase_url;
    private RelativeLayout toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bangdaner);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        tabLayout = ((TabLayout) findViewById(R.id.bangdaner_tabLayout));
        viewPager = ((ViewPager) findViewById(R.id.bangdaner_viewPager));
        back = ((ImageView) findViewById(R.id.bangdaner_back));
        share = ((ImageView) findViewById(R.id.bangdaner_share));
        shoucang = ((ImageView) findViewById(R.id.bangdaner_shoucang));
        fenxiang = ((ImageView) findViewById(R.id.bangdaner_fenxiang));
        goumai = ((Button) findViewById(R.id.bangdaner_goumai));
        toolbar = ((RelativeLayout) findViewById(R.id.toolbar));

        back.setOnClickListener(this);
        share.setOnClickListener(this);
        shoucang.setOnClickListener(this);
        fenxiang.setOnClickListener(this);
        goumai.setOnClickListener(this);

        fragmentList = new ArrayList<>();
        Bundle bundle = this.getIntent().getExtras();
        int id = bundle.getInt("id");
        purchase_url = bundle.getString("purchase_url");

        fragmentList.add(new DanpinFragment(id));
        fragmentList.add(new BangdanDetailFragment(id));
        fragmentList.add(new BangdanPinglunFragment(id));
        adapter = new BanddanerAdapter(getSupportFragmentManager(), titles, fragmentList);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bangdaner_back:
                finish();
                break;
            case R.id.bangdaner_share:
            case R.id.bangdaner_shoucang:
                Intent intent = new Intent(this, DengliActivity.class);
                startActivity(intent);
                break;
            case R.id.bangdaner_goumai:
                Intent intent1 = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("purchase_url", purchase_url);
                intent1.putExtras(bundle);
                intent1.setClass(this, GoumaiActivity.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //第一、第二页面切换渐变
        float percent = Math.abs(1f - positionOffset) / 1f;
        if (position == 0) {
            int  alpha=(int) (255 * (1-percent));
            toolbar.getBackground().setAlpha(alpha);
        } else {
            toolbar.getBackground().setAlpha(255);
        }
    }

    @Override
    public void onPageSelected(int position) {
        //改变状态栏颜色
        switch (viewPager.getCurrentItem()) {
            case 0:
                toolbar.getBackground().setAlpha(0);
                share.setVisibility(View.INVISIBLE);
                break;
            case 1:
                toolbar.getBackground().setAlpha(255);
                share.setVisibility(View.INVISIBLE);

                break;
            case 2:
                toolbar.getBackground().setAlpha(255);
                share.setVisibility(View.VISIBLE);
                break;
        }
    }
        @Override
        public void onPageScrollStateChanged ( int state){

        }
    }

