package lws.com.liwushuo.view.fragment.shouye;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import lws.com.liwushuo.R;
import lws.com.liwushuo.adapter.shouye.GonglueDetialBottomAdapter;
import lws.com.liwushuo.bean.shouye.GonglueDetaiBottomRecommendBean;
import lws.com.liwushuo.bean.shouye.GonglueDetialBean;
import lws.com.liwushuo.server.shouye.ShouyeServer;
import lws.com.liwushuo.view.activity.LanmuActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.os.Build.VERSION_CODES.M;
import static lws.com.liwushuo.R.id.gonglue_detial_top_backIco;

public class GonglueActivity extends AppCompatActivity implements Callback<GonglueDetialBean>, View.OnClickListener, GonglueDetialBottomAdapter.ItemOnClickListener {


    private SimpleDraweeView topImg;
    private TextView topTitle;
    private ImageView backIco;
    private TextView topChakanQuanji;
    private WebView mWebView;
    private TextView likeNum;
    private TextView sharesNum;
    private TextView commentsNum;
    private ShouyeServer server;
    private PopupWindow sharePopupWindow;
    private RelativeLayout rootView;
    private WindowManager.LayoutParams lp;
    private RecyclerView bottomRecyclerView;
    private ShouyeServer bottomServer;
    private GonglueDetialBottomAdapter bottomAdapter;
    private LinearLayout bottomGuessyoulikeLinearLayout;
    private String urlId;
    private int columenId;
    private String cover_image_url;
    private String title;
    private String description;
    private AppBarLayout appbar;
    private TextView gonglue_title;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shouye_gonglue_activity);
        //获取二级页面的id,然后跳转
        Intent intent =  getIntent();
        urlId = intent.getStringExtra("itemsBeanId");
        //跳转 查看全集 的参数
        columenId = intent.getIntExtra("columenId",-1);
        //这三个鸡肋参数 是张子阳坑比害我的
        cover_image_url = intent.getStringExtra("cover_image_url");
        title = intent.getStringExtra("title");
        description = intent.getStringExtra("description");
        //状态栏设置为黑色
        setStatusBar();
        //初始化控件
        initView();
        //请求网络,获取数据
        loadMaiData(); //主体部分数据
        //  loadBottomData(); //底部的猜你喜欢数据，放到webView加载完成的回调里面去处理
    }

    /**
     * 设置状态栏颜色
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setStatusBar() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.black));//最低版本要21也就时5.0以上
    }

    /**
     * 加载主体的数据
     */
    private void loadMaiData() {
        if (server == null) {
            server = new Retrofit.Builder()
                    .baseUrl("http://api.liwushuo.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ShouyeServer.class);
        }
        server.getGonglueBean(urlId).enqueue(this);
    }

    /**
     * 加载底部的数据
     */
    private void loadBottomData() {
        if (bottomServer == null) {
            bottomServer = new Retrofit.Builder().baseUrl("http://hawaii.liwushuo.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ShouyeServer.class);
        }
        bottomServer.getBottomRecommendBean(urlId).enqueue(new Callback<GonglueDetaiBottomRecommendBean>() {
            @Override
            public void onResponse(Call<GonglueDetaiBottomRecommendBean> call, Response<GonglueDetaiBottomRecommendBean> response) {
                List<GonglueDetaiBottomRecommendBean.DataBean.RecommendPostsBean> recommendPostsBeen = response.body().getData().getRecommend_posts();
                if (recommendPostsBeen == null || recommendPostsBeen.size() == 0) {//有时候底部没有推荐
                    bottomGuessyoulikeLinearLayout.setVisibility(View.GONE);
                    bottomRecyclerView.setVisibility(View.GONE);
                } else {
                    bottomGuessyoulikeLinearLayout.setVisibility(View.VISIBLE);
                    bottomRecyclerView.setVisibility(View.VISIBLE);
                    bottomAdapter.addAll(recommendPostsBeen);
                }
            }

            @Override
            public void onFailure(Call<GonglueDetaiBottomRecommendBean> call, Throwable t) {

            }
        });
    }

    /**
     * 控件初始化
     */
    private void initView() {
        //root
        rootView = (RelativeLayout) findViewById(R.id.gonglue_rootView);
        lp = getWindow().getAttributes();//窗体的参数
        //主要控件
        topImg = ((SimpleDraweeView) findViewById(R.id.gonglue_detial_topImg));//顶部图片
        topTitle = ((TextView) findViewById(R.id.gonglue_detial_topTitle));//图片下的title
        backIco = ((ImageView) findViewById(gonglue_detial_top_backIco));//左上角返回键
        backIco.setOnClickListener(this); //设置返回的监听
        topChakanQuanji = (TextView) findViewById(R.id.gonglue_detial_topChakanQuanji);//右上角查看全集
        topChakanQuanji.setOnClickListener(this);//设置查看全集的点击事件
        mWebView = ((WebView) findViewById(R.id.gonglue_detial_webView));//webview详情页
        //对webview的一些设置
        initWebChromeClient();
        initWebSettings();
        initWebViewClient();
        //底部的猜你喜欢
        bottomGuessyoulikeLinearLayout = (LinearLayout) findViewById(R.id.gonglue_bottom_guessyoulikelayout);
        bottomRecyclerView = (RecyclerView) findViewById(R.id.gonglue_cainixihuan);
        //设置横向滑动的recyclerview
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        bottomRecyclerView.setLayoutManager(layoutManager);
        //设置item之间的间隔
        bottomRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                if (parent.getChildPosition(view) != 0) {
                    outRect.left = getResources().getDimensionPixelSize(R.dimen.space);
                }
            }
        });

        //设置顶部title的渐变：
        appbar = ((AppBarLayout) findViewById(R.id.appbar));
        gonglue_title = ((TextView) findViewById(R.id.gonglue_title));
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int appbarheight= appbar.getTotalScrollRange();
                float percent=Math.abs((float)verticalOffset/appbarheight);
                int alpha = Math.round(percent * 255);
                gonglue_title.setTextColor(Color.argb(alpha, 255, 255, 255));
            }
        });
        bottomAdapter = new GonglueDetialBottomAdapter(this);
        bottomRecyclerView.setAdapter(bottomAdapter);
        //bootomRecyclerView设置监听
        bottomAdapter.setItemOnClickListener(this);
        //底部按钮
        likeNum = ((TextView) findViewById(R.id.gonglue_detial_bottom_likeNum));//底部左边的 喜欢
        sharesNum = ((TextView) findViewById(R.id.gonglue_detial_bottom_sharesCount));//底部中间的分享
        commentsNum = ((TextView) findViewById(R.id.gonglue_detial_bottom_commentsCount));//底部右边的评论
        //设置底部的三个点击事件
        likeNum.setOnClickListener(this);
        sharesNum.setOnClickListener(this);
        commentsNum.setOnClickListener(this);

        //初始化popupwindow
        initPopupWindow();
    }

    //初始化webview的客户端
    private void initWebChromeClient() {

        mWebView.setWebChromeClient(new WebChromeClient() {

            private Bitmap mDefaultVideoPoster;//默认的视频展示图

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }

            @Override
            public Bitmap getDefaultVideoPoster() {
                if (mDefaultVideoPoster == null) {
                    mDefaultVideoPoster = BitmapFactory.decodeResource(
                            getResources(), R.mipmap.ic_launcher
                    );
                    return mDefaultVideoPoster;
                }
                return super.getDefaultVideoPoster();
            }
        });
    }

    private void initWebSettings() {
        WebSettings settings = mWebView.getSettings();
        //支持获取手势焦点
        mWebView.requestFocusFromTouch();
        //支持JS
        settings.setJavaScriptEnabled(true);
        //支持插件
        settings.setPluginState(WebSettings.PluginState.ON);
        //设置适应屏幕
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        //支持缩放
        settings.setSupportZoom(false);
        //隐藏原生的缩放控件
        settings.setDisplayZoomControls(false);
        //支持内容重新布局
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.supportMultipleWindows();
        settings.setSupportMultipleWindows(true);
        //设置缓存模式
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setAppCacheEnabled(true);
        settings.setAppCachePath(mWebView.getContext().getCacheDir().getAbsolutePath());

        //设置可访问文件
        settings.setAllowFileAccess(true);
        //当webview调用requestFocus时为webview设置节点
        settings.setNeedInitialFocus(true);
        //支持自动加载图片
        if (Build.VERSION.SDK_INT >= 19) {
            settings.setLoadsImagesAutomatically(true);
        } else {
            settings.setLoadsImagesAutomatically(false);
        }
        settings.setNeedInitialFocus(true);
        //设置编码格式
        settings.setDefaultTextEncodingName("UTF-8");
    }

    private void initWebViewClient() {
        mWebView.setWebViewClient(new WebViewClient() {
            //页面开始加载时
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            //至关重要的一段代码。处理了头部不是http和https的情况
            //点击了网页中的链接会触发这个方法，所以可以用来拦截一些url，然后与activity交互
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("http:") || url.startsWith("https:")) {
                    view.loadUrl(url);
                    return false;
                } else if (url.startsWith("liwushuo:///page?type=item&page_action=navigation&item_id=")) {
                    //监听点击了图片就需要跳转到单品、详情、评论的activity
                    int indexOf = url.lastIndexOf("item_id=");
                    int end = url.lastIndexOf("&");
                    // TODO: 2016/10/15 先去看一下那个页面的传值命名是什么
                    String id = url.substring(indexOf + 8, end);
//                    Intent intent = new Intent(this, null);
//                    intent.putExtra("urlId",id);
//                    startActivity(intent);
                    Toast.makeText(GonglueActivity.this,"点击了图片，可以跳转，但是hyy还没有把那个activity写好！！id="+id,Toast.LENGTH_SHORT).show();
                    return true;
                } else {

                    return true;
                }
            }

            //页面完成加载时
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                loadBottomData();//底部的猜你喜欢数据,这里必须在回调里面处理，才能避免底部先于webview加载出来
            }

            //是否在WebView内加载新页面
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.toString());
                return true;
            }

            //网络错误时回调的方法
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                /**
                 * 在这里写网络错误时的逻辑,比如显示一个错误页面
                 *
                 */
            }

            @TargetApi(M)
            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
            }
        });
    }

    /**
     * 网络请求，回调
     *
     * @param call
     * @param response
     */
    @Override
    public void onResponse(Call<GonglueDetialBean> call, Response<GonglueDetialBean> response) {
        GonglueDetialBean.DataBean dataBean = response.body().getData();
        onBinderData(dataBean);
    }

    /**
     * 给控件绑定数据
     *
     * @param dataBean
     */
    private void onBinderData(GonglueDetialBean.DataBean dataBean) {
        String topImageUrl = dataBean.getCover_image_url();//顶部图片地址
        String topTitleText = dataBean.getTitle();//顶部标题
        String contentHtmlUrl = dataBean.getContent_url();//webview网址
        int likesCount = dataBean.getLikes_count();//喜欢的数量
        int sharesCount = dataBean.getShares_count();//分享的数量
        int commentsCount = dataBean.getComments_count();//回复的数量
        //绑定数据
        topImg.setImageURI(topImageUrl);
        topTitle.setText(topTitleText + "");
        mWebView.loadUrl(contentHtmlUrl);
        likeNum.setText(likesCount + "");
        sharesNum.setText(sharesCount + "");
        commentsNum.setText(commentsCount + "");
    }

    @Override
    public void onFailure(Call<GonglueDetialBean> call, Throwable t) {
        Toast.makeText(this, "网络错误！", Toast.LENGTH_SHORT).show();
    }

    /**
     * 返回点击事件
     * 查看全集点击事件
     * 底部的三个按钮点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //返回按钮
            case gonglue_detial_top_backIco:
                finish();//直接关闭当前activity
                break;
            //查看全集
            case R.id.gonglue_detial_topChakanQuanji:
                // TODO: 2016/10/14
                Intent intent = new Intent(this, LanmuActivity.class);
                intent.putExtra("id",columenId);
                intent.putExtra("title",title);
                intent.putExtra("cover_image_url",cover_image_url);
                intent.putExtra("description",description);
                startActivity(intent);
                break;
            //喜欢
            case R.id.gonglue_detial_bottom_likeNum:
                // TODO: 2016/10/14
                break;
            //分享
            case R.id.gonglue_detial_bottom_sharesCount:
                // TODO: 2016/10/14
                if (sharePopupWindow != null) {
                    lp.alpha = 0.7f; //0.0-1.0
                    getWindow().setAttributes(lp);
                    sharePopupWindow.showAtLocation(rootView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                }
                break;
            //分享弹窗取消
            case R.id.shouye_gonglue_share_cancle:
                sharePopupWindow.dismiss();
                break;
            //回复
            case R.id.gonglue_detial_bottom_commentsCount:
                // TODO: 2016/10/14
                break;
        }
    }

    /**
     * 初始化控件
     */
    public void initPopupWindow() {
        sharePopupWindow = new PopupWindow();
        View popupWindowView = LayoutInflater.from(this).inflate(R.layout.shouye_gonglue_share_layout, null);
        popupWindowView.findViewById(R.id.shouye_gonglue_share_cancle).setOnClickListener(this);
        sharePopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        sharePopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        sharePopupWindow.setContentView(popupWindowView);
        //点击外部时，关闭popupwindon
        sharePopupWindow.setOutsideTouchable(true);
        sharePopupWindow.setBackgroundDrawable(getResources().getDrawable(R.color.WhiteBackgroud));
        //给弹窗设置动画
        sharePopupWindow.setAnimationStyle(R.style.gonglue_share_anim);
        //设置关闭时的监听
        sharePopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //关闭的时候将外部透明度改回来
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
            }
        });

    }


    /**
     * 底部猜你喜欢点击事件
     *
     * @param v
     * @param position
     */
    @Override
    public void itemOnClick(View v, int position, GonglueDetaiBottomRecommendBean.DataBean.RecommendPostsBean data) {
        Intent intent = new Intent(this, GonglueActivity.class);
        //继续跳转当前的activity
        int id = data.getId();
        intent.putExtra("itemsBeanId", id + "");
        startActivity(intent);
    }


}
