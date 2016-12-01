package lws.com.liwushuo.view.fragment.fenlei;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import lws.com.liwushuo.R;
import lws.com.liwushuo.adapter.fenlei.GonglveListViewAdapter;
import lws.com.liwushuo.adapter.fenlei.GonglveRecyclerAdapter;
import lws.com.liwushuo.bean.fenlei.GonglveListViewItemBean;
import lws.com.liwushuo.bean.fenlei.LanmuBean;
import lws.com.liwushuo.server.fenlei.FenleiService;
import lws.com.liwushuo.view.activity.LanmuActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * OurApp simple {@link Fragment} subclass.
 */
public class GonglveFragment extends Fragment implements Callback<LanmuBean>,GonglveRecyclerAdapter.OnRecyclerViewItemClickListener {
    private View view;
    private ListView listView;
    private View header;
    private RecyclerView recyclerView;
    private GonglveRecyclerAdapter recyclerViewAdapter;
    private List<LanmuBean.DataBean.ColumnsBean> recyclerViewList = new ArrayList<>();
    private List<GonglveListViewItemBean.DataBean.ChannelGroupsBean> listViewList = new ArrayList<>();
    private GonglveListViewAdapter gonglveListViewAdapter;

    private FenleiService server;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gonglve, container, false);
        listView = ((ListView) view.findViewById(R.id.lv_gonglve));
        header = LayoutInflater.from(getContext()).inflate(R.layout.fenlei_gonglve_header,null);
        recyclerView = ((RecyclerView) header.findViewById(R.id.rv_gonglve_lanmu));
        recyclerViewAdapter = new GonglveRecyclerAdapter(getContext(),recyclerViewList);
        recyclerView.setAdapter(recyclerViewAdapter);
//        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayout.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        gonglveListViewAdapter = new GonglveListViewAdapter(getContext(),listViewList);
        listView.setAdapter(gonglveListViewAdapter);
        listView.addHeaderView(header);

        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl("http://api.liwushuo.com").addConverterFactory(GsonConverterFactory.create()).build();
        server = retrofit.create(FenleiService.class);
        server.getLanmuBean().enqueue(this);

        Retrofit retrofit1 = builder.baseUrl("http://api.liwushuo.com").addConverterFactory(GsonConverterFactory.create()).build();
        server = retrofit1.create(FenleiService.class);
        server.getGonglveListViewItemBean().enqueue(new Callback<GonglveListViewItemBean>() {
            @Override
            public void onResponse(Call<GonglveListViewItemBean> call, Response<GonglveListViewItemBean> response) {
                gonglveListViewAdapter.addAll(response.body().getData().getChannel_groups());

            }

            @Override
            public void onFailure(Call<GonglveListViewItemBean> call, Throwable t) {

            }
        });

        return view;
    }

    @Override
    public void onResponse(Call<LanmuBean> call, Response<LanmuBean> response) {
        LanmuBean lanmuBean = response.body();
        recyclerViewAdapter.addAll(lanmuBean.getData().getColumns());
        recyclerViewAdapter.setOnRecyclerViewItemClickListener(this);  //给recyclerView设置点击监听
    }

    @Override
    public void onFailure(Call<LanmuBean> call, Throwable t) {

    }

    @Override
    public void onRecyclerViewItemClick(View view, LanmuBean.DataBean.ColumnsBean columnsBean) {
        Intent intent = new Intent(getContext(), LanmuActivity.class);
        intent.putExtra("id",columnsBean.getId());
        intent.putExtra("cover_image_url",columnsBean.getCover_image_url());
        intent.putExtra("title",columnsBean.getTitle());
        intent.putExtra("description",columnsBean.getDescription());
        startActivity(intent);
    }
}
