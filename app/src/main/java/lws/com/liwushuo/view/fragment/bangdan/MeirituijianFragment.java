package lws.com.liwushuo.view.fragment.bangdan;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import lws.com.liwushuo.R;
import lws.com.liwushuo.adapter.bangdan.MeirituijiamAdapter;
import lws.com.liwushuo.bean.bangdan.BangdanBean;
import lws.com.liwushuo.server.bangdan.MeitituijianServer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeirituijianFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private MeirituijiamAdapter adapter;
    private List<BangdanBean.DataBean.ItemsBean> list;
    private View view;
    private BangdanBean.DataBean data;
    private MeitituijianServer server;
    private int id,limit,offset;
    private SwipeRefreshLayout swipeRefreshLayout;


    public MeirituijianFragment(int id, int limit, int offset) {
        this.id = id;
        this.limit = limit;
        this.offset = offset;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(view != null){
            return view;
        }
        view = inflater.inflate(R.layout.fragment_meirituijian, container, false);
        initView();
        return view;
    }

    private void initView(){
        mRecyclerView = (RecyclerView) view.findViewById(R.id.meirituijian_recyclerView);
        swipeRefreshLayout = ((SwipeRefreshLayout) view.findViewById(R.id.meirituijian_refresh_layout));
        swipeRefreshLayout.setColorSchemeColors(Color.BLACK,Color.BLUE,Color.RED,Color.BLACK);

        //这句话是为了第一次进入页面的时候显示加载进度条
        swipeRefreshLayout.setProgressViewOffset(false,0, (int) TypedValue
        .applyDimension(TypedValue.COMPLEX_UNIT_DIP,24,getResources()
        .getDisplayMetrics()));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {

            }
        });
        //设置布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
            @Override
            public int getSpanSize(int position) {
                int spansize = 1;
                switch(adapter.getItemViewType(position)){
                    case 1:
                        spansize = 2;
                        break;
                    case 2:
                        spansize = 1;
                        break;
                }
                return spansize;
            }
        });

        mRecyclerView.setLayoutManager(gridLayoutManager);
        adapter = new MeirituijiamAdapter(getContext(),new ArrayList<BangdanBean.DataBean.ItemsBean>());
        initData();
    }

    public void initData(){
        //网络请求
        final Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl("http://api.liwushuo.com").addConverterFactory(GsonConverterFactory.create()).build();
        server = retrofit.create(MeitituijianServer.class);
        server.getBandanBen(id,limit,offset).enqueue(new Callback<BangdanBean>() {
            @Override
            public void onResponse(Call<BangdanBean> call, Response<BangdanBean> response) {
                data = response.body().getData();
                adapter.addFirst(data.getCover_image());
                adapter.addRes(data.getItems());
                mRecyclerView.setAdapter(adapter);

                   adapter.setClickLietener(new MeirituijiamAdapter.OnItemClickListener() {
                       @Override
                       public void setItemClickListener(int position) {
                           if(position > 0){
                               Intent intent = new Intent();
                               Bundle bundle = new Bundle();
                               bundle.putInt("id",data.getItems().get(position-1).getId());
                               bundle.putString("purchase_url",data.getItems().get(position-1).getPurchase_url());
                               intent.putExtras(bundle);
                               intent.setClass(getContext(),BangdanerActivity.class);
                               startActivity(intent);
                           }
                       }
                   });
            }

            @Override
            public void onFailure(Call<BangdanBean> call, Throwable t) {
                Log.e("自定义标签", "请求出现错误...");
            }
        });
    }
}
