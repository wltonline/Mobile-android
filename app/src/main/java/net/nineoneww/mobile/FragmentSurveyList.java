package net.nineoneww.mobile;

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

import net.nineoneww.mobile.adapter.MyAdapter;
import net.nineoneww.mobile.api.res.NewsData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lilian on 2017/8/9.
 */

public class FragmentSurveyList extends Fragment implements SwipeRefreshLayout.OnRefreshListener,View.OnClickListener {

    private static final String TAG = "FragmentSurveyList";

    private SwipeRefreshLayout surveyRefreshLayout;

    /**
     * 新闻列表请求接口
     */
    public static final String URL = "https://api.douban.com/v2/book/1220562";

    /**
     * ListView对象
     */
    private ListView listView;

    /**
     * 新闻集合对象
     */
    private List<NewsData> datas;

    /**
     * 自定义的Adapter对象
     */
    private MyAdapter surveyListAdapter;

    public FragmentSurveyList() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_survey_list, container, false);
        listView = (ListView) rootView.findViewById(R.id.surveyListView);
        datas = new ArrayList<NewsData>();
        loadInfo(URL);
        surveyListAdapter = new MyAdapter(this.getContext(), datas);


        /**
         * 实例化Adapter对象(注意:必须要写在在getDatas() 方法后面,不然datas中没有数据)
         */
//        surveyListAdapter = new MyAdapter(this.getActivity(), datas);
//        listView.setAdapter(surveyListAdapter);

        //show refresh
//        surveyRefreshLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                loadInfo(URL);
//            }
//        });

        return rootView;
    }

    @Override
    public void onRefresh() {
        loadInfo(URL);
    }

    /**
     * 通过接口获取新闻列表的方法
     * @param url
     */
    public void loadInfo(String url){

//        surveyRefreshLayout.setRefreshing(true);

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
                                NewsData data = new NewsData();
                                data.setNewsTitle(item.getString("name"));
                                data.setNewsDate(item.getString("count"));
                                data.setNewsImgUrl(jsonObject.getString("image"));
                                data.setNewsUrl(jsonObject.getString("url"));
                                datas.add(data);
                            }


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
