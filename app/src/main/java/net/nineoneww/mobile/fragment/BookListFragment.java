package net.nineoneww.mobile.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import net.nineoneww.mobile.HomeActivity;
import net.nineoneww.mobile.R;
import net.nineoneww.mobile.adapter.BookAdapter;
import net.nineoneww.mobile.api.res.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lilian on 2017/8/9.
 */

public class BookListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,View.OnClickListener {

    private static final String TAG = "BookListFragment";

    private SwipeRefreshLayout surveyRefreshLayout;

    /**
     * book
     */
    public static final String URL = "https://api.douban.com/v2/book/1220562";

    private ListView listView;

    private List<Book> datas;

    private BookAdapter surveyListAdapter;
    private SwipeRefreshLayout refreshLayout;
    private boolean isWebViewOpened = false;

    public BookListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_book_list, container, false);
        listView = (ListView) rootView.findViewById(R.id.surveyListView);
        datas = new ArrayList<Book>();
        refreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.survey_refresh_layout);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeResources(R.color.colorPrimary);
        refreshLayout.setVisibility(View.VISIBLE);
//        loadInfo(URL);
        surveyListAdapter = new BookAdapter(this.getContext(), datas);
        listView.setAdapter(surveyListAdapter);

        //show refresh
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                loadInfo(URL);
            }
        });

        return rootView;
    }

    @Override
    public void onRefresh() {
        loadInfo(URL);
    }

    @Override
    public void onStart() {
        super.onStart();
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.title_survey));
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
     * 通过接口获取列表的方法
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
                            JSONArray jsonArray = jsonObject.getJSONArray("tags");

                            for (int i = 0; i <jsonArray.length() ; i++) {
                                JSONObject item = jsonArray.getJSONObject(i);
                                Book data = new Book();
                                data.setBookTitle(item.getString("name"));
                                data.setBookDate(item.getString("count"));
                                data.setBookImgUrl(jsonObject.getString("image"));
                                data.setBookUrl(jsonObject.getString("url"));
                                datas.add(data);
                            }
                            refreshLayout.setRefreshing(false);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        /**
                         * 请求成功后为ListView设置Adapter
                         */

                        listView.setAdapter(surveyListAdapter);
                        surveyListAdapter.notifyDataSetChanged();

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

    @Override
    public void onClick(View view) {

    }

    public interface OnFragmentInteractionListener {
        void onFragmentSurveyListInteraction(Uri uri);
    }
}
