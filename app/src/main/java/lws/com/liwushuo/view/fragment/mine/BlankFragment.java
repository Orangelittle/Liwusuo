package lws.com.liwushuo.view.fragment.mine;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

import lws.com.liwushuo.R;
import lws.com.liwushuo.adapter.mine.MineRecyclerAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    private RecyclerView recyclerView;
    private MineRecyclerAdapter adapter;

    public BlankFragment() {
        // Required empty public constructor
    }
    private List<String> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_mine_sub, container, false);
        recyclerView = ((RecyclerView) view.findViewById(R.id.listview));
        list=new ArrayList<>();
        for (int i = 0; i <30 ; i++) {
            list.add("数据===>"+i);
        }
        adapter = new MineRecyclerAdapter(getContext(),list);
        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }

}
