package net.nineoneww.mobile.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import net.nineoneww.mobile.R;
import net.nineoneww.mobile.adapter.PromotionAdapter;
import net.nineoneww.mobile.view.DividerItemDecoration;

import java.util.ArrayList;

/**
 * Created by lilian on 2017/8/21.
 */

public class PromotionFragment extends Fragment {
    private static final String TAG = "PromotionFragment";

    private ProgressBar progressBar;
    private RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;

    public PromotionFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //initiate
        View rootView = inflater.inflate(R.layout.fragment_promotion, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        mAdapter = new PromotionAdapter(getData());
        // 设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        // 设置adapter
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置Item之间间隔样式
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(), LinearLayoutManager.VERTICAL));

        return rootView;
    }

    private ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        String temp = " item";
        for(int i = 0; i < 20; i++) {
            data.add(i + temp);
            Log.i(TAG, "initdata: item" + i);
        }

        return data;
    }

    public interface OnFragmentInteractionListener {
        void onPromotionFragmentInteraction(Uri uri);
    }
}
