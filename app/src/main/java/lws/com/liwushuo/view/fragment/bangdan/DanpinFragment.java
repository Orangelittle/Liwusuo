package lws.com.liwushuo.view.fragment.bangdan;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import lws.com.liwushuo.R;
import lws.com.liwushuo.adapter.bangdan.ImageViewpager;
import lws.com.liwushuo.server.bangdan.Bangdan_danpin_Server;

/**
 * A simple {@link Fragment} subclass.
 */
public class DanpinFragment extends Fragment {
    private View view;
    private Bangdan_danpin_Server server;
    private int id;
//    private String webPath;


    public DanpinFragment(int id) {
        // Required empty public constructor
        this.id = id;
    }
    //测试相关数据
    private List<ImageView> list=new ArrayList<>();
    private ViewPager detail_viewPager;
    private int[] res={R.mipmap.p6,R.mipmap.p4,R.mipmap.p5};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(view != null){
            return view;
        }
        view = inflater.inflate(R.layout.fragment_danpin2, container, false);
        //测试：/////////////////////////////////////////
        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(view.getContext());
            imageView.setImageResource(res[i]);
            list.add(imageView);
        }

        detail_viewPager = ((ViewPager) view.findViewById(R.id.detail_viewpager));
        detail_viewPager.setAdapter(new ImageViewpager(list));
        ///////////////////////////////////////////

//        initView();
        return view;
    }
//        private void initView(){
//            Retrofit.Builder builder = new Retrofit.Builder();
//            Retrofit retrofit = builder.baseUrl("http://api.liwushuo.com").addConverterFactory(GsonConverterFactory.create()).build();
//            server = retrofit.create(Bangdan_danpin_Server.class);
//            server.getBangdan_danpinBean(id).enqueue(new Callback<Bangdan_danpinBean>() {
//                @Override
//                public void onResponse(Call<Bangdan_danpinBean> call, Response<Bangdan_danpinBean> response) {
//
//                }
//                @Override
//                public void onFailure(Call<Bangdan_danpinBean> call, Throwable t) {
//
//                }
//            });
//        }

}
