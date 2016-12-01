package lws.com.liwushuo.view.fragment.bangdan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import lws.com.liwushuo.R;

public class GoumaiActivity extends AppCompatActivity {
    private String purchase_url;
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goumai);
        Bundle bundle = getIntent().getExtras();
        purchase_url = bundle.getString("purchase_url");
        webView = ((WebView)findViewById(R.id.goumai_webView));
        webView.loadUrl(purchase_url);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.toString());
                return true;
            }
        });
    }
}
