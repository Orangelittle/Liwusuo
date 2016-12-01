package lws.com.liwushuo.view.fragment.bangdan;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import lws.com.liwushuo.R;
import lws.com.liwushuo.adapter.bangdan.BangdanPiinglunAdapter;
import lws.com.liwushuo.bean.bangdan.BangdanPinglunBean;
import lws.com.liwushuo.server.bangdan.BangdanpinglunServer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class BangdanPinglunFragment extends Fragment implements View.OnClickListener {
    private int id;
    private View view;
    private RecyclerView mRecyclerView;
    private BangdanpinglunServer server;
    private BangdanPiinglunAdapter adapter;
    private List<BangdanPinglunBean.DataBean.CommentsBean> list;

    private AlertDialog alerDialog;
    public BangdanPinglunFragment(int id) {
        // Required empty public constructor
        this.id = id;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(view != null){
            return view;
        }
        view = inflater.inflate(R.layout.fragment_bangdan_pinglun, container, false);
        initView();
        return view;
    }

    private void initView(){
        mRecyclerView = ((RecyclerView) view.findViewById(R.id.bangdanpinglun_recyclerView));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
//        adapter = new BangdanPiinglunAdapter(getContext(),setList());
        setList();
//        mRecyclerView.setAdapter(adapter);

    }

        //设置自定义对话框
    private void createMyDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog,null);
        Button huifu = (Button) view.findViewById(R.id.huifu);
        Button jubao = (Button) view.findViewById(R.id.jubao);
        Button quxiao = (Button) view.findViewById(R.id.quxiao);
        huifu.setOnClickListener(this);
        jubao.setOnClickListener(this);
        quxiao.setOnClickListener(this);

        builder.setView(view);
        alerDialog = builder.create();
        Window window = alerDialog.getWindow();
        window.setGravity(Gravity.BOTTOM);//使控件显示在屏幕下方
        alerDialog.show();
    }
        public List<BangdanPinglunBean.DataBean.CommentsBean> setList(){
            list = new ArrayList<>();
            Retrofit.Builder builder = new Retrofit.Builder();
            Retrofit retrofit = builder.baseUrl("http://api.liwushuo.com").addConverterFactory(GsonConverterFactory.create()).build();
            server = retrofit.create(BangdanpinglunServer.class);
            server.getBangdanPinglunBean(id,20,0).enqueue(new Callback<BangdanPinglunBean>() {
                @Override
                public void onResponse(Call<BangdanPinglunBean> call, Response<BangdanPinglunBean> response) {
                    Log.e("自定义标签", "size:"+response.body().getData());
//                    adapter.getList(response.body().getData().getComments());
                    list = response.body().getData().getComments();
                    adapter = new BangdanPiinglunAdapter(getContext(),list);
                    mRecyclerView.setAdapter(adapter);
                    //设置分隔线
                    mRecyclerView.addItemDecoration(new MyDecoration(getContext(),MyDecoration.VERTICAL_LIST));

                    adapter.setClickLietener(new BangdanPiinglunAdapter.OnItemClickListener() {
                        @Override
                        public void setItemClickListener(int position) {
                            createMyDialog();
                        }
                    });
                }

                @Override
                public void onFailure(Call<BangdanPinglunBean> call, Throwable t) {
                    Log.e("自定义标签", "请求失败...");
                }
            });
            return list;
        }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.quxiao:
                if(alerDialog != null){
                    alerDialog.dismiss();//关闭对话框
                }
                break;
            case R.id.huifu:
            case R.id.jubao:
                Intent intent = new Intent(getContext(),DengliActivity.class);
                startActivity(intent);
                break;
        }
    }
}
