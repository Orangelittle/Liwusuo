package lws.com.liwushuo.view.fragment.bangdan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import lws.com.liwushuo.R;
import lws.com.liwushuo.bean.bangdan.Bangdan_danpinBean;
import lws.com.liwushuo.server.bangdan.Bangdan_danpin_Server;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class BangdanDetailFragment extends Fragment {
    private View view;
    private WebView webView;
    private Bangdan_danpin_Server server;
    private String webPath;
    private int id;
    public BangdanDetailFragment(int id) {
        // Required empty public constructor
        this.id = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_bangdan_detail, container, false);
        webView = ((WebView) view.findViewById(R.id.bangdan_detail_webView));
        initView();
        return view;
    }
    private void initView(){
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl("http://api.liwushuo.com").addConverterFactory(GsonConverterFactory.create()).build();
        server = retrofit.create(Bangdan_danpin_Server.class);
        server.getBangdan_danpinBean(id).enqueue(new Callback<Bangdan_danpinBean>() {
            @Override
            public void onResponse(Call<Bangdan_danpinBean> call, Response<Bangdan_danpinBean> response) {
                webPath = response.body().getData().getDetail_html();
                webView.loadData(webPath, "text/html;charset=utf-8", null);
                webView.setWebViewClient(new WebViewClient(){
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        if (url.startsWith("http:") || url.startsWith("https:")) {
                            view.loadUrl(url);
                            return false;
                        } else {
                            return true;
                        }
                    }
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                        view.loadUrl(request.toString());
                        return true;
                    }
                });
            }
            @Override
            public void onFailure(Call<Bangdan_danpinBean> call, Throwable t) {

            }
        });
    }

}
