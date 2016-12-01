package lws.com.liwushuo.view.fragment.shouye;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.percent.PercentFrameLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import lws.com.liwushuo.R;
import lws.com.liwushuo.adapter.shouye.JinxuanListViewAdapter;
import lws.com.liwushuo.adapter.shouye.JinxuanLunboAdapter;
import lws.com.liwushuo.bean.shouye.JinxuanLiwuBean;
import lws.com.liwushuo.bean.shouye.JinxuanLunboBean;
import lws.com.liwushuo.server.shouye.ShouyeServer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ZQ on 2016/10/12.
 */

public class JinxuanFragment extends Fragment implements Callback<JinxuanLunboBean>, ViewPager.OnPageChangeListener, Handler.Callback, AbsListView.OnScrollListener, AdapterView.OnItemClickListener {

    private int channelsId;
    private String pageName;
    private View view;
    private ListView listView;
    private PercentFrameLayout headerView;
    private ViewPager viewPager;
    private ImageView lunboPoint;
    private LinearLayout lunboPoints;
    private ShouyeServer server;
    private JinxuanLunboAdapter lunboAdapter;
    private Handler handler = new Handler(this);
    private JinxuanListViewAdapter listViewAdapter;
    private boolean hasLunbo;
    // http://api.liwushuo.com/v2/channels/101/items_v2?ad=2&gender=1&generation=2&limit=20&offset=0
    //listview请求参数
    private int ad = 2;
    private int gender = 1;
    private int generation = 2;
    private int limit = 20;
    private int offset = 0;
    private boolean hasMore = true;
    private boolean isLoading;
    private static Context context;
    private int currentPage;

    public JinxuanFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        channelsId = bundle.getInt("channelsId", -1);
        pageName = bundle.getString("pageName");
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (pageName.equals("精选")) {
            hasLunbo = true;
        } else {
            hasLunbo = false;
        }
        if (view != null) {
            return view;
        }
        // TODO: 2016/10/13 这里记得做 状态保存
        view = inflater.inflate(R.layout.shouye_jinxuan_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // TODO: 2016/10/13 这里不知道做不做判断 判断控件
        initView();

        //请求网络
        server = new Retrofit.Builder()
                .baseUrl("http://api.liwushuo.com/")
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(ShouyeServer.class);
        //轮播的数据
        if (hasLunbo) {
            server.getLunboBean().enqueue(this);
        }
        //listView的数据
        server.getLiwuBean(channelsId, ad, gender, generation, limit, offset)
                .enqueue(new Callback<JinxuanLiwuBean>() {
                    @Override
                    public void onResponse(Call<JinxuanLiwuBean> call, Response<JinxuanLiwuBean> response) {
                        List<JinxuanLiwuBean.DataBean.ItemsBean> itemsBeen = response.body().getData().getItems();
                        if (itemsBeen != null) {
                            listViewAdapter.addAll(itemsBeen);
                        }
                    }

                    @Override
                    public void onFailure(Call<JinxuanLiwuBean> call, Throwable t) {

                    }
                });
        //上拉加载更多
        listView.setOnScrollListener(this);
    }

    //控件初始化
    private void initView() {

        if (listView == null) {
            //详情礼物列表
            listView = ((ListView) view.findViewById(R.id.shouye_jinxuan_listView));
            //listView的适配器
            listViewAdapter = new JinxuanListViewAdapter(getContext());
            listView.setAdapter(listViewAdapter);
            //item点击事件
            listView.setOnItemClickListener(this);
        }
        if (hasLunbo) {//有轮播才执行这些操作

            if (headerView == null) {
                //listView头部轮播View
                headerView = ((PercentFrameLayout) LayoutInflater.from(getContext()).inflate(R.layout.shouye_jinxuan_lunbo_layout, null));
                //头部的viewpager
                viewPager = ((ViewPager) headerView.findViewById(R.id.shouye_jinxuan_lunboViewpager));
                viewPager.addOnPageChangeListener(this);//轮播监听
                //listView添加头布局
                listView.addHeaderView(headerView);
                //头部小白点和小白点集合
                lunboPoint = ((ImageView) headerView.findViewById(R.id.shouye_jinxuan_lunboPoint));
                lunboPoints = ((LinearLayout) headerView.findViewById(R.id.shouye_jinxuan_lunboPoints));
            }
        }
    }

    @Override
    public void onResponse(Call<JinxuanLunboBean> call, Response<JinxuanLunboBean> response) {
        List<JinxuanLunboBean.DataBean.BannersBean> banners = response.body().getData().getBanners();

        if (lunboAdapter == null) {
            //设置轮播的适配器
            lunboAdapter = new JinxuanLunboAdapter(getContext(), banners);
            viewPager.setAdapter(lunboAdapter);
            viewPager.setCurrentItem(1);//第一次的页面要设置为真正的第一张
            //创建小白点
            createPoint(banners);
            //无限轮播
            handler.obtainMessage(0, 1, 0).sendToTarget();
        }
    }

    @Override
    public void onFailure(Call<JinxuanLunboBean> call, Throwable t) {
        Toast.makeText(getContext(), "渣渣网络！", Toast.LENGTH_SHORT).show();
    }

    /**
     * 创建小白点，并且把第一个设置为选中
     *
     * @param banners
     */
    private void createPoint(List<JinxuanLunboBean.DataBean.BannersBean> banners) {
        for (int i = 0; i < banners.size(); i++) {
            ImageView point = new ImageView(context);
            point.setImageResource(R.drawable.point);
            int p = (int) context.getResources().getDimension(R.dimen.point_padding);
            point.setPadding(p, p, p, p);
            lunboPoints.addView(point);
        }
        //第一次把圆点移动到第一位 不过这里为什么要post // TODO: 2016/10/13 为什么
        lunboPoint.post(new Runnable() {
            @Override
            public void run() {
                ViewCompat.setTranslationX(lunboPoint, lunboPoints.getLeft());
            }
        });
    }


    /**
     * viewpager滑动时，就小白点也要滑动
     *
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //value表示小白点要位移的距离，由当前的viewpager的位置+上viewpager的当前偏移量，
        // 乘上每个小白点的宽度，再加上装小白点的这个linearlayout的最左边位置
        float value = lunboPoints.getLeft() + (position - 1 + positionOffset) * lunboPoint.getWidth();
            /*
            *小白点位移的距离不能比装小白点的linearlayout最左边还小,因为我们前后加入两个图，
            * 所以当我们看到第一张图的时候，他的前面还有最后一张图，所以position的值有1变为0，
            * position - 1 + positionOffset 这个值算出来就是负数，然后value的值就会小于fPoints的最左边，
            * 然后移动就会移动超出了最左边，所以位移取比和getLeft的最大值，或getRight的最小值
            * 右边同理
            *
             */
        value = Math.max(value, lunboPoints.getLeft());//
        value = Math.min(value, lunboPoints.getRight() - lunboPoint.getWidth());//也不能大于最右边的距离
        ViewCompat.setTranslationX(lunboPoint, value);
    }

    /**
     * 页面选中的时候，需要注意头尾加了两个页面，
     * 这里要设置一下
     *
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
        if (position == viewPager.getAdapter().getCount() - 1) {
            //滑到了最后一张，我们需要跳到第二张也就是真正的第一张图片
            viewPager.setCurrentItem(1, false);
        } else if (position == 0) {
            //滑到了第一张，我们需要跳到倒数第二张，也就是真正的最后一张图
            viewPager.setCurrentItem(viewPager.getAdapter().getCount() - 2, false);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }


    /**
     * 无限轮播
     *
     * @param msg
     * @return
     */
    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 0:
                if (viewPager != null) {
                    int arg1 = msg.arg1;//arg1是要跳转的页面
                    if (arg1 > viewPager.getAdapter().getCount() - 2) {
                        //这里也要判断跳转的是不是我们添加的假的数据
                        //这里跳转的是真正的最后一页，我们应该跳到第0页，也就是我们自己添加的页面
                        arg1 = 1;
                    }
                    viewPager.setCurrentItem(arg1, arg1 != 1);//如果要跳转到第一页，就必须设置跳转方式为false
                    Message message = handler.obtainMessage(0, ++arg1, 0);
                    currentPage = arg1;
                    handler.sendMessageDelayed(message, 5000);
                }
                break;
        }
        return true;
    }

    @Override
    public void onStop() {
        super.onStop();
        handler.removeMessages(0);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (viewPager != null && viewPager.getAdapter() != null) {
            handler.obtainMessage(0, currentPage, 0).sendToTarget();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeMessages(0);
    }


    /**
     * 上拉加载更多
     *
     * @param view
     * @param firstVisibleItem
     * @param visibleItemCount
     * @param totalItemCount
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (!isLoading && hasMore && totalItemCount - visibleItemCount - firstVisibleItem < 3) {
            isLoading = true;
            Toast.makeText(getContext(), "正在拼命加载.....", Toast.LENGTH_SHORT).show();
            server.getLiwuBean(channelsId, ad, gender, generation, limit, offset = offset + 20)
                    .enqueue(new Callback<JinxuanLiwuBean>() {
                        @Override
                        public void onResponse(Call<JinxuanLiwuBean> call, Response<JinxuanLiwuBean> response) {
                            List<JinxuanLiwuBean.DataBean.ItemsBean> itemsBeen = response.body().getData().getItems();
                            if (itemsBeen == null && response.body().getData().getPaging().getNext_url() == null) {
                                hasMore = false;
                            } else {
                                listViewAdapter.addAll(itemsBeen);
                            }
                            isLoading = false;
                        }

                        @Override
                        public void onFailure(Call<JinxuanLiwuBean> call, Throwable t) {
                            isLoading = false;
                        }
                    });
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }


    /**
     * 礼物列表的点击事件，
     * 除了轮播
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (hasLunbo) {//有轮播的时候需要-1，因为第0位是轮播，
            position=position - 1;
        }
       JinxuanLiwuBean.DataBean.ItemsBean itemsBean = (JinxuanLiwuBean.DataBean.ItemsBean) listViewAdapter.getItem(position);
        //获取二级页面的数据请求id，以及查看全集的请求id，然后跳转过去
        int columnId = itemsBean.getColumn().getId();
        int itemsBeanId = itemsBean.getId();
        Intent intent = new Intent(getContext(), GonglueActivity.class);
        intent.putExtra("itemsBeanId", itemsBeanId + "");
        intent.putExtra("columenId",columnId);
        //下面的参数 是被张子阳这坑比 害的 必须再传三个参数过去，说为了减少网络请求
        String title = itemsBean.getColumn().getTitle();
        String cover_image_url = itemsBean.getColumn().getCover_image_url();
        String description = itemsBean.getColumn().getDescription();
        intent.putExtra("title",title);
        intent.putExtra("cover_image_url",cover_image_url);
        intent.putExtra("description",description);

        startActivity(intent);
    }
}