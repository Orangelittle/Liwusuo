package lws.com.liwushuo.view.fragment;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lws.com.liwushuo.R;
import lws.com.liwushuo.adapter.shouye.PopwindowAdapter;
import lws.com.liwushuo.adapter.shouye.ShouyeViewPagerAdapter;
import lws.com.liwushuo.bean.shouye.TabTitleBean;
import lws.com.liwushuo.server.shouye.ShouyeServer;
import lws.com.liwushuo.view.fragment.shouye.MyItemDecoration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * OurApp simple {@link Fragment} subclass.
 */
public class ShouyeFragment extends Fragment implements View.OnClickListener, Callback<TabTitleBean>,PopwindowAdapter.OnItemClickListener, ViewPager.OnPageChangeListener {


    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TextView changeTabText;
    private TextView topSearch;
    private View view;
    private ImageView changeTabImg;
    private boolean isOpen;
    private List<Fragment> fragmentList = new ArrayList<>();//frgament的集合
    private List<String> titles = new ArrayList<>();        //tablayout的title
    private String gender = "1";
    private String generation = "2";
    private View popwindowView;
    private RecyclerView popWindwonRecyclerView;
    private PopwindowAdapter popwindowAdapter;
    private PopupWindow popupWindow;
    private int currentPosition = 0;
    private LinearLayout tabLinearLayout;
    private boolean isCloseByListener; //记录是否是通过监听关闭的弹窗
    private View lastView;

    public ShouyeFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shouye_main_fragment, container, false);
        //popwindow的view
        popwindowView = LayoutInflater.from(getContext()).inflate(R.layout.shouye_main_popwindow_fragment, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        //设置viewpager ontouch事件
//        viewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        if (popupWindow.isShowing()){
//                            popupWindow.dismiss();
//                        }
//                        return true;
//                }
//                return false;
//            }
//        });
        //tabtitle的网络请求title
        // TODO: 2016/10/12 这里要先判断数据库有没有把title请求到，请求到了 就不需要去网络请求title了 、
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.liwushuo.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ShouyeServer server = retrofit.create(ShouyeServer.class);
        server.getTitleBean(gender, generation).enqueue(this);
        //设置popwindow,这里是空数据
//        popwindowAdapter = new PopwindowAdapter(getContext());
//        popWindwonRecyclerView.setAdapter(popwindowAdapter);
        //切换频道点击事件
        changeTabImg.setOnClickListener(this);
    }

    //初始化控件
    private void initView() {
        viewPager = ((ViewPager) view.findViewById(R.id.shouye_main_viewpager));
        tabLayout = ((TabLayout) view.findViewById(R.id.shouye_main_tablayout));
        changeTabImg = ((ImageView) view.findViewById(R.id.shouye_main_changeTabImg));
        changeTabText = ((TextView) view.findViewById(R.id.shouye_main_changeTabText));
        topSearch = ((TextView) view.findViewById(R.id.shouye_main_Search));
        //tabTitle的布局
        tabLinearLayout = ((LinearLayout) view.findViewById(R.id.shouye_main_tabLinearLayout));
        //初始化popwindow
        initPopupWindow();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shouye_main_changeTabImg:
                // TODO: 2016/10/12 弹出popwindon 频道列表
                // 这里判断需要注意，popwindow关闭时的监听也要处理这里状态的改变
                //改变三角状态没隐藏tablayout
//                if (isOpen) {//如果是打开状态
//                    changeTabImg.setImageResource(R.mipmap.arrow_index_down);
//                    tabLayout.setVisibility(View.VISIBLE);
//                    changeTabText.setVisibility(View.GONE);
//                    popupWindow.dismiss();
//                } else {//如果是关闭状态
//                    changeTabImg.setImageResource(R.mipmap.arrow_index_up);
//                    tabLayout.setVisibility(View.GONE);
//                    changeTabText.setVisibility(View.VISIBLE);
//                    //打开popupWindow
//                    popupWindow.showAsDropDown(tabLinearLayout);
//                }
//                isOpen = !isOpen;

                if (popupWindow != null) {
                    if (popupWindow.isShowing()) {
                        changeTabImg.setImageResource(R.mipmap.arrow_index_down);
                        tabLayout.setVisibility(View.VISIBLE);
                        changeTabText.setVisibility(View.GONE);
                        //关闭popupWindow
                        popupWindow.dismiss();
                    } else {
                        changeTabImg.setImageResource(R.mipmap.arrow_index_up);
                        tabLayout.setVisibility(View.GONE);
                        changeTabText.setVisibility(View.VISIBLE);
                        //打开popupWindow
                        popupWindow.showAsDropDown(tabLinearLayout);
                    }
                }
                break;
        }
    }

    //对popwindow的设置
    private void initPopupWindow() {
        //popwindow内部RecyclerView
        popWindwonRecyclerView = ((RecyclerView) popwindowView.findViewById(R.id.shouye_main_popwindow_recyclerView));
        popWindwonRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));//设置为gridlayout
        popWindwonRecyclerView.addItemDecoration(new MyItemDecoration());

        //创建popupwindow
        popupWindow = new PopupWindow(popwindowView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);//抢占焦点，可以防止点击事件冲突
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // TODO: 2016/10/13 更改flag打开状态 这里逻辑没判断好
                isOpen = false;
                changeTabImg.setImageResource(R.mipmap.arrow_index_down);
                tabLayout.setVisibility(View.VISIBLE);
                changeTabText.setVisibility(View.GONE);
            }
        });
      //  popupWindow.setBackgroundDrawable(getResources().getDrawable(R.color.WhiteBackgroud));
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void onResponse(Call<TabTitleBean> call, Response<TabTitleBean> response) {
        TabTitleBean titleBean = response.body();
        //设置tabtitle，以及viewpager
        ShouyeViewPagerAdapter adapter = new ShouyeViewPagerAdapter(getChildFragmentManager(), getContext(), titleBean.getData().getChannels());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);//页面被选择的时候要修改popwindow里面的item
        tabLayout.setupWithViewPager(viewPager);
        //设置popwindow
        popwindowAdapter = new PopwindowAdapter(getContext(), titleBean.getData().getChannels());
        popWindwonRecyclerView.setAdapter(popwindowAdapter);
        //popupwindow里面的item点击事件
        // TODO: 2016/10/13 popwindow点击事件
        popwindowAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onFailure(Call<TabTitleBean> call, Throwable t) {

    }

    /**
     * popupwindow里面item的点击事件
     * 在这里点击了要切换viewpager，
     * 并且设置popupwindow里面设置每个item的文字颜色
     *
     * @param v  点击的item
     * @param recyclerView  当前的recyclerView
     * @param position      点击的item在recyclerView中list的位置
     *
     */
    @Override
    public void onItemClick(View v, RecyclerView recyclerView, int position) {
        //设置页面关闭popupwindow
        viewPager.setCurrentItem(position);
        popupWindow.dismiss();
        //传递点击的position
        popwindowAdapter.setCurrentPosition(position);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 页面被选择的时候把传递position给适配器。更改view的颜色
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
        popwindowAdapter.setCurrentPosition(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
