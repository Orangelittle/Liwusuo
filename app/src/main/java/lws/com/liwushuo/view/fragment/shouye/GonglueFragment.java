package lws.com.liwushuo.view.fragment.shouye;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import lws.com.liwushuo.R;
import lws.com.liwushuo.bean.shouye.GonglueDetialBean;
import lws.com.liwushuo.server.shouye.ShouyeServer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GonglueFragment extends Fragment implements Callback<GonglueDetialBean> {

    private String urlId;
    private View view;
    private SimpleDraweeView topImg;
    private TextView topTitle;
    private ImageView backIco;
    private TextView topChakanQuanji;
    private WebView mWebView;
    private TextView likeNum;
    private TextView sharesNum;
    private TextView commentsNum;
    private ShouyeServer server;

    public GonglueFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        //获取urlid
        urlId = arguments.getString("itemsBeanId");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("onCreateView: ", "攻略fragment");
        if (view == null) {
            view = inflater.inflate(R.layout.shouye_gonglue_activity, container, false);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        //请求网络,获取数据
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
     * 控件初始化
     *
     * @param view
     */
    private void initView(View view) {
        topImg = ((SimpleDraweeView) view.findViewById(R.id.gonglue_detial_topImg));//顶部图片
        topTitle = ((TextView) view.findViewById(R.id.gonglue_detial_topTitle));//图片下的title
        backIco = ((ImageView) view.findViewById(R.id.gonglue_detial_top_backIco));//左上角返回键

        topChakanQuanji = (TextView) view.findViewById(R.id.gonglue_detial_topChakanQuanji);//右上角查看全集
        mWebView = ((WebView) view.findViewById(R.id.gonglue_detial_webView));//webview详情页
        //初始化webview一些设置
        initWebChromeClient();
        initWebSettings();
        initWebViewClient();
        likeNum = ((TextView) view.findViewById(R.id.gonglue_detial_bottom_likeNum));//底部左边的 喜欢
        sharesNum = ((TextView) view.findViewById(R.id.gonglue_detial_bottom_sharesCount));//底部中间的分享
        commentsNum = ((TextView) view.findViewById(R.id.gonglue_detial_bottom_commentsCount));//底部右边的评论
    }

    @Override
    public void onDetach() {
        super.onDetach();
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
        Toast.makeText(getContext(), "网络错误！", Toast.LENGTH_SHORT).show();
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
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(url.startsWith("http:") || url.startsWith("https:") ) {
                    view.loadUrl(url);
                    return false;
                }else{
  //                  Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                    startActivity(intent);
                    return true;
                }
            }


            //页面完成加载时
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
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
                 * 这里我偷个懒不写了
                 * */
            }

            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
            }
        });
    }
}
