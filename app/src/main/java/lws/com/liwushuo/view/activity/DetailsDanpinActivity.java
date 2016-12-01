package lws.com.liwushuo.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;
import lws.com.liwushuo.R;
import lws.com.liwushuo.adapter.fenlei.DanpinDetailsRecyclerviewAdapter;
import lws.com.liwushuo.bean.fenlei.DanpinDetailsBean;
import lws.com.liwushuo.server.fenlei.FenleiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsDanpinActivity extends AppCompatActivity implements View.OnClickListener, Callback<DanpinDetailsBean> {

    private PtrClassicFrameLayout ptr;
    private RecyclerView recyclerView;
    private ImageView back;
    private ImageView sort;
    private DanpinDetailsRecyclerviewAdapter danpinDetailsRecyclerviewAdapter;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details__danpin_);

        Intent intent = getIntent();
        id = intent.getIntExtra("id",-1);

        ptr = ((PtrClassicFrameLayout) findViewById(R.id.ptr_danpin_details));
        recyclerView = ((RecyclerView) findViewById(R.id.rv_danpin_details));

        setPtr();//设置与ptr相关的内容

        danpinDetailsRecyclerviewAdapter = new DanpinDetailsRecyclerviewAdapter(this,new ArrayList<DanpinDetailsBean.DataBean.ItemsBean>());

        back = ((ImageView) findViewById(R.id.iv_danpin_details_back));
        sort = ((ImageView) findViewById(R.id.iv_danpin_details_sort));
        back.setOnClickListener(this);

        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl("http://api.liwushuo.com").addConverterFactory(GsonConverterFactory.create()).build();
        FenleiService service = retrofit.create(FenleiService.class);
        service.getDanpinDetailsBean(id,20,0).enqueue(this);

    }

    public void setPtr(){
        ptr.setResistance(1.5f);
        ptr.setRatioOfHeaderHeightToRefresh(1.5f);
        ptr.setDurationToClose(2000);
        ptr.setDurationToCloseHeader(5000);
        ptr.setPullToRefresh(false);
        ptr.addPtrUIHandler(new PtrUIHandler() {
            @Override
            public void onUIReset(PtrFrameLayout frame) {

            }

            @Override
            public void onUIRefreshPrepare(PtrFrameLayout frame) {

            }

            @Override
            public void onUIRefreshBegin(PtrFrameLayout frame) {

            }

            @Override
            public void onUIRefreshComplete(PtrFrameLayout frame) {

            }

            @Override
            public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_danpin_details_back:
                finish();
                break;
            case R.id.iv_danpin_details_sort:

                break;
        }
    }

    @Override
    public void onResponse(Call<DanpinDetailsBean> call, Response<DanpinDetailsBean> response) {
        DanpinDetailsBean body = response.body();

        danpinDetailsRecyclerviewAdapter.addAll(body.getData().getItems());
        recyclerView.setAdapter(danpinDetailsRecyclerviewAdapter);
    }

    @Override
    public void onFailure(Call<DanpinDetailsBean> call, Throwable t) {

    }
}
