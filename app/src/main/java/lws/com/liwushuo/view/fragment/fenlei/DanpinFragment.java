package lws.com.liwushuo.view.fragment.fenlei;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lws.com.liwushuo.R;
import lws.com.liwushuo.adapter.fenlei.DanpinListviewAdapter;
import lws.com.liwushuo.adapter.fenlei.DanpinRecyclerviewAdapter;
import lws.com.liwushuo.adapter.fenlei.DanpinRecyclerviewAdapter1;
import lws.com.liwushuo.bean.fenlei.DanpinBean;
import lws.com.liwushuo.server.fenlei.FenleiService;
import lws.com.liwushuo.view.activity.DetailsDanpinActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * OurApp simple {@link Fragment} subclass.
 */
public class DanpinFragment extends Fragment implements Callback<DanpinBean>, AdapterView.OnItemClickListener, DanpinRecyclerviewAdapter1.OnRecyclerViewItemClickListener {

    private View view;
    private FenleiService server;
    private int lastSelectedPosition;
    private int selectedPosition;
    private ListView listView;
    private RecyclerView recyclerView;
    private DanpinListviewAdapter danpinListviewAdapter;
    //    private DanpinRecyclerviewAdapter danpinRecyclerviewAdapter;
    private DanpinRecyclerviewAdapter1 danpinRecyclerviewAdapter1;
    private List<List<Object>> subcategoriesBeanList;

    private boolean isFirst = true;
    private int listviewItemHeight;

    private boolean isFirstItemHeight = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_danpin, container, false);
        listView = ((ListView) view.findViewById(R.id.lv_danpin));
        recyclerView = ((RecyclerView) view.findViewById(R.id.rv_danpin));

        danpinListviewAdapter = new DanpinListviewAdapter(getContext(), new ArrayList<String>(), this);
        danpinRecyclerviewAdapter1 = new DanpinRecyclerviewAdapter1(getContext());
        subcategoriesBeanList = new ArrayList<>();

        listView.setOnItemClickListener(this);

        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl("http://api.liwushuo.com").addConverterFactory(GsonConverterFactory.create()).build();
        server = retrofit.create(FenleiService.class);
        server.getDanpinBean().enqueue(this);

        danpinRecyclerviewAdapter1.setOnRecyclerViewItemClickListener(this);

        return view;
    }

    @Override
    public void onResponse(Call<DanpinBean> call, Response<DanpinBean> response) {
        DanpinBean body = response.body();

//        for (DanpinBean.DataBean.CategoriesBean categoriesBean : body.getData().getCategories()) {
//            danpinListviewAdapter.add(categoriesBean.getName());
//            danpinRecyclerviewAdapter.add(categoriesBean);
//        }
//        listView.setAdapter(danpinListviewAdapter);
//        recyclerView.setAdapter(danpinRecyclerviewAdapter);

        for (DanpinBean.DataBean.CategoriesBean categoriesBean : body.getData().getCategories()) {
            danpinListviewAdapter.add(categoriesBean.getName());

            ArrayList<Object> nameString = new ArrayList<>();
            //先添加名字
            if (!isFirst) {
                nameString.add(categoriesBean.getName());
                subcategoriesBeanList.add(nameString);
                //添加布局类型的id
                danpinRecyclerviewAdapter1.getType().add(DanpinRecyclerviewAdapter1.TITLE);
            } else {
                isFirst = false;
            }

            int size = categoriesBean.getSubcategories().size();
            int rowCount = (size + 2) / 3;

            for (int i = 0; i < rowCount; i++) {
                ArrayList<Object> subcategoriesBeen = new ArrayList<>(); //再添加三个图片的那一行的数据

                danpinRecyclerviewAdapter1.getType().add(DanpinRecyclerviewAdapter1.THREE_IMAGES);
                subcategoriesBeen.add(categoriesBean.getSubcategories().get(i * 3));
                if (i * 3 + 1 <= size - 1) {
                    subcategoriesBeen.add(categoriesBean.getSubcategories().get(i * 3 + 1));
                }
                if (i * 3 + 2 <= size - 1) {
                    subcategoriesBeen.add(categoriesBean.getSubcategories().get(i * 3 + 2));
                }
                subcategoriesBeanList.add(subcategoriesBeen);  //再添加三个图片的那一行的数据
            }
        }
        danpinRecyclerviewAdapter1.setSubcategoriesBeanList(subcategoriesBeanList);
        listView.setAdapter(danpinListviewAdapter);
        recyclerView.setAdapter(danpinRecyclerviewAdapter1);

    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setLastSelectedPosition(int lastSelectedPosition) {
        this.lastSelectedPosition = lastSelectedPosition;
    }

    @Override
    public void onFailure(Call<DanpinBean> call, Throwable t) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (lastSelectedPosition == i) {
            return;
        }
        if (isFirstItemHeight) {
            listviewItemHeight = view.getMeasuredHeight();
            isFirstItemHeight = false;
        }
//        int topTo = -(i-1) * listviewItemHeight;
//        int topNow = adapterView.getTop();
//        Log.e("自定义标签", "类DanpinFragment中的方法onItemClick: topTo: "+topTo+"  topNow: "+topNow);
//        ViewCompat.setTranslationY(adapterView,topTo- topNow);
//        listView.smoothScrollToPosition(5);
//        listView.setSelection(5);
//        listView.smoothScrollToPositionFromTop(i,listviewItemHeight);
        selectedPosition = i;
        danpinListviewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRecyclerViewItemClick(View view, DanpinBean.DataBean.CategoriesBean.SubcategoriesBean subcategoriesBean) {
        Intent intent = new Intent(getContext(), DetailsDanpinActivity.class);
        intent.putExtra("id",subcategoriesBean.getId());
        startActivity(intent);
    }
}
