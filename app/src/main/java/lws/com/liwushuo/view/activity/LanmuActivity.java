package lws.com.liwushuo.view.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import lws.com.liwushuo.R;
import lws.com.liwushuo.adapter.fenlei.LanmuDetailsAdapter;
import lws.com.liwushuo.bean.fenlei.LanmuDetailsBean;
import lws.com.liwushuo.server.fenlei.FenleiService;
import lws.com.liwushuo.utils.MyBitmapTask;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LanmuActivity extends AppCompatActivity implements Callback<LanmuDetailsBean>, View.OnClickListener, AbsListView.OnScrollListener {

    private ListView listView;
    private RelativeLayout rl_top_background;
    private ImageView back;
    private ImageView fenxiang;
    private TextView topName;
    private View header;

    //头部的view
    private ImageView bannerImage;
    private TextView headerName;
    private TextView guanzhu;
    private TextView likeCount;
    private TextView jianjie;
    private TextView tv_chakanquanbu;
    private ImageView zhedie;
    private RelativeLayout rl_chakanquanbu;
    private Intent intent;
    private int id;
    private String cover_image_url;
    private String title;
    private String description;
    private LanmuDetailsAdapter lanmuDetailsAdapter;

    //滑动相关
    private boolean titleTopTextColorFlag;
    private int bigImageHeight;
    private PopupWindow sharePopupWindow;
    private WindowManager.LayoutParams params;
    private FrameLayout rootView;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lanmu);
        rootView = ((FrameLayout) findViewById(R.id.lanmu_rootView));
        intent = getIntent();
        id = intent.getIntExtra("id",-1);
        cover_image_url = intent.getStringExtra("cover_image_url");
        title = intent.getStringExtra("title");
        description = intent.getStringExtra("description");
        //窗体的参数
        params = getWindow().getAttributes();
        initPopupWindow();//初始化popupwindow
        listView = ((ListView) findViewById(R.id.lv_lanmu));
        rl_top_background = ((RelativeLayout) findViewById(R.id.rl_lanmu_details_top_background));
        back = ((ImageView) findViewById(R.id.iv_lanmu_back));
        back.setOnClickListener(this);
        fenxiang = ((ImageView) findViewById(R.id.iv_lanmu_fenxiang));
        fenxiang.setOnClickListener(this);//分享设置监听，弹出分享弹窗
        topName = ((TextView) findViewById(R.id.tv_lanmu_bar_name));
        topName.setText(title);  //给顶部的topName赋值
        //添加头部
        header = LayoutInflater.from(this).inflate(R.layout.lanmu_header,null);
        bannerImage = ((ImageView) header.findViewById(R.id.iv_lanmu_banner));
        headerName = ((TextView) header.findViewById(R.id.tv_lanmu_header_name));
        guanzhu = ((TextView) header.findViewById(R.id.tv_lanmu_guanzhu));
        likeCount = ((TextView) header.findViewById(R.id.tv_lanmu_top_likes_count));
        jianjie = ((TextView) header.findViewById(R.id.tv_lanmu_jianjie));
        tv_chakanquanbu = ((TextView) header.findViewById(R.id.tv_lanmu_chakanquanbu));
        zhedie = ((ImageView) header.findViewById(R.id.iv_lanmu_zhedie));
        rl_chakanquanbu = ((RelativeLayout) header.findViewById(R.id.rl_lanmu_chakanquanbu));
        //给头部赋值
        headerName.setText(title);
        jianjie.setText(description);

        new MyBitmapTask(this,bannerImage).execute(cover_image_url);

        //listView添加头部
        listView.addHeaderView(header);
        //listView添加滑动监听
        listView.setOnScrollListener(this);
        //生成一个listView的adapter
        lanmuDetailsAdapter = new LanmuDetailsAdapter(this,new ArrayList<LanmuDetailsBean.DataBean.PostsBean>());

        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl("http://api.liwushuo.com").addConverterFactory(GsonConverterFactory.create()).build();
        FenleiService service = retrofit.create(FenleiService.class);
        service.getLanmuDetailsBean(id,20,0).enqueue(this);

        bigImageHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, this.getResources().getDisplayMetrics());

    }

    @Override
    public void onResponse(Call<LanmuDetailsBean> call, Response<LanmuDetailsBean> response) {
        LanmuDetailsBean body = response.body();
        likeCount.setText(body.getData().getLikes_count()+"人喜欢");
        lanmuDetailsAdapter.addAll(body.getData().getPosts());
        listView.setAdapter(lanmuDetailsAdapter);
    }

    @Override
    public void onFailure(Call<LanmuDetailsBean> call, Throwable t) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_lanmu_back:
                finish();
                break;
            case R.id.iv_lanmu_fenxiang:
                if (sharePopupWindow != null) {
                    params.alpha = 0.7f; //0.0-1.0
                    getWindow().setAttributes(params);
                    sharePopupWindow.showAtLocation(rootView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                }
                break;
            //分享弹窗取消
            case R.id.shouye_gonglue_share_cancle:
                sharePopupWindow.dismiss();
                break;
        }
    }

    /**
     * 初始化分享的弹窗控件
     */
    public void initPopupWindow() {
        sharePopupWindow = new PopupWindow();
        View popupWindowView = LayoutInflater.from(this).inflate(R.layout.shouye_gonglue_share_layout, null);
        popupWindowView.findViewById(R.id.shouye_gonglue_share_cancle).setOnClickListener(this);
        sharePopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        sharePopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        sharePopupWindow.setContentView(popupWindowView);
        //点击外部时，关闭popupwindon
        sharePopupWindow.setFocusable(true);
        sharePopupWindow.setOutsideTouchable(true);
        sharePopupWindow.setBackgroundDrawable(getResources().getDrawable(R.color.WhiteBackgroud));
        //给弹窗设置动画
        sharePopupWindow.setAnimationStyle(R.style.gonglue_share_anim);
        //设置关闭时的监听
        sharePopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //关闭的时候将外部透明度改回来
                params.alpha = 1.0f;
                getWindow().setAttributes(params);
            }
        });

    }



    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
        float top = (float)(-header.getTop()+0.01);
        if (i == 0 && top < bigImageHeight) {
            ViewCompat.setAlpha(rl_top_background,top/bigImageHeight);
            if (titleTopTextColorFlag) {
                titleTopTextColorFlag = false;
                topName.setTextColor(0x00000000);
            }
        }else{
            titleTopTextColorFlag = true;
            ViewCompat.setAlpha(rl_top_background,1);
            topName.setTextColor(0xffffffff);
        }
    }
}
