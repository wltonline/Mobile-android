package net.nineoneww.mobile.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import net.nineoneww.mobile.ui.activity.VoteDetailActivity;
import net.nineoneww.mobile.ui.activity.HomeActivity;
import net.nineoneww.mobile.R;
import net.nineoneww.mobile.ui.adapter.VoteListAdapter;
import net.nineoneww.mobile.api.res.Vote;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by lilian on 2017/8/10.
 */

public class VoteListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,View.OnClickListener {

    private static final String TAG = "VoteListFragment";

    //Vote list
    public static final String URL = "https://api.douban.com/v2/book/search?q=python&fields=id,title,author,image,price,url,pubdate";

    private RecyclerView mRecyclerView;
    private VoteListAdapter mAdapter;

    private LinearLayoutManager mLayoutManager;
    private ArrayList<Vote> datas;

    private SwipeRefreshLayout refreshLayout;
    private boolean isWebViewOpened = false;
    private boolean isLoading;
    int page = 0;
    int totalPage = 2;//模拟请求的一共的页数
    int lastVisibleItemPosition;
    private Handler handler = new Handler();

    public VoteListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_vote_list, container, false);

        //swipe
        refreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.vote_refresh_layout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadInfo(URL);
                    }
                }, 2000);
            }
        });
        refreshLayout.setColorSchemeResources(R.color.colorPrimary);
        refreshLayout.setVisibility(View.VISIBLE);
        //show refresh
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
                loadInfo(URL);
            }
        });

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_vote);
        datas = new ArrayList<Vote>();
        mAdapter = new VoteListAdapter(this.getContext(), datas);
        mLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d("test", "StateChanged = " + newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d("test", "onScrolled");

                lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition + 1 == mAdapter.getItemCount()) {
                    Log.d("test", "loading executed");

                    boolean isRefreshing = refreshLayout.isRefreshing();
                    if (isRefreshing) {
                        mAdapter.notifyItemRemoved(mAdapter.getItemCount());
                        return;
                    }
                    if (!isLoading) {
                        if (page < totalPage) {
                            Log.e("duanlian", "onScrollStateChanged: " + "进来了");
                            isLoading = true;
                            mAdapter.changeState(1);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    loadMore();
                                    page++;
                                    isLoading = false;
                                }
                            }, 2000);
                        } else {
                            mAdapter.changeState(2);
                        }
                    }
                }
            }
        });

        //event
        mAdapter.setOnItemClickListener(new VoteListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Vote VoteItem = voteItems.get(position - 1);
                Intent intent = new Intent(VoteListFragment.this.getActivity(), VoteDetailActivity.class);
//                intent.putExtra(Constant.KEY_HOME_ITEM, VoteItem);
//                intent.putExtra(Constant.KEY_PROFILE_QUESTIONNAIRE_POINT, profileQuestionnaire);
                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.enter_from_right, R.anim.zoom_out);
                Log.d("test", "item position = " + position);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

        return rootView;
    }

    @Override
    public void onRefresh() {
        loadInfo(URL);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onStart() {
        super.onStart();
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.title_vote));
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isWebViewOpened) {
            isWebViewOpened = false;
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    /**
     * 通过接口获取书籍列表的方法
     * @param url
     */
    public void loadInfo(String url){
        refreshLayout.setRefreshing(true);

        final RequestQueue mQueue = Volley.newRequestQueue(this.getContext());

        JsonObjectRequest stringRequest = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        try {

                            /**
                             * 对返回的json数据进行解析,然后装入datas集合中
                             */
                            JSONArray jsonArray = jsonObject.getJSONArray("books");

                            for (int i = 0; i <jsonArray.length() ; i++) {

                                JSONObject item = jsonArray.getJSONObject(i);
                                Vote data = new Vote();
                                data.setVoteTitle(item.getString("title"));
                                data.setVotePubDate(item.getString("pubdate"));
//                                int authorLen = item.getString("author").length();
//                                String authorArray=item.getString("author");
//                                ArrayList<String> authorList=new ArrayList<String>();
//                                for(int j=0;j<authorLen;j++){
//                                    authorList.add(authorArray);
//                                }
                                data.setVoteAuthor(item.getString("author"));
                                data.setVotePrice(item.getString("price"));
                                data.setVoteImgUrl(item.getString("image"));
//                                data.setVoteUrl(item.getString("url"));
                                datas.add(data);
                            }
                            refreshLayout.setRefreshing(false);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        /**
                         * 请求成功后为ListView设置Adapter
                         */

                        mRecyclerView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                refreshLayout.setRefreshing(false);
            }
        }
        );

        mQueue.add(stringRequest);

    }

    private void loadMore() {
        mAdapter.notifyDataSetChanged();
        refreshLayout.setRefreshing(false);
        mAdapter.notifyItemRemoved(mAdapter.getItemCount());
    }

    public interface OnFragmentInteractionListener {
        void onVoteListFragmentInteraction(Uri uri);
    }
}
