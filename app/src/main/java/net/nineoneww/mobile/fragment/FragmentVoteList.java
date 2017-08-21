package net.nineoneww.mobile.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import net.nineoneww.mobile.BookDetailActivity;
import net.nineoneww.mobile.HomeActivity;
import net.nineoneww.mobile.R;
import net.nineoneww.mobile.adapter.BookListAdapter;
import net.nineoneww.mobile.api.res.Book;
import net.nineoneww.mobile.util.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lilian on 2017/8/10.
 */

public class FragmentVoteList extends Fragment implements SwipeRefreshLayout.OnRefreshListener,View.OnClickListener {

    private static final String TAG = "FragmentVoteList";

    //book list
    public static final String URL = "https://api.douban.com/v2/book/search?q=python&fields=id,title,author,image,price,url,pubdate";

    private ListView listView;
    private OnFragmentInteractionListener mListener;
    private List<Book> datas;
    private LinearLayout linerlayoutPoint;
    private TextView textViewPoint;
    private TextView textViewPointEmpty;

    private BookListAdapter bookListAdapter;
    private SwipeRefreshLayout refreshLayout;
    private List<Book> bookItems;
    private boolean isWebViewOpened = false;

    public FragmentVoteList() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_vote_list, container, false);
        listView = (ListView) rootView.findViewById(R.id.voteListView);
        datas = new ArrayList<Book>();

        //swipe
        refreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.survey_refresh_layout);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeResources(R.color.colorPrimary);
        refreshLayout.setVisibility(View.VISIBLE);
        bookListAdapter = new BookListAdapter(this.getContext(), datas);
        listView.setAdapter(bookListAdapter);

        //event
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //header
//                if (position == 0) {
//                    return;
//                }
//
//                Book bookItem = bookItems.get(position - 1);
                Intent intent = new Intent(FragmentVoteList.this.getActivity(), BookDetailActivity.class);
//                intent.putExtra(Constant.KEY_HOME_ITEM, bookItem);
//                intent.putExtra(Constant.KEY_PROFILE_QUESTIONNAIRE_POINT, profileQuestionnaire);
                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.enter_from_right, R.anim.zoom_out);
            }
        });

        //set lister for show history
//        View.OnClickListener listener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mListener.showPointHistory();
//            }
//        };
//        linerlayoutPoint.setOnClickListener(listener);
//        textViewPoint.setOnClickListener(listener);
//        textViewPointEmpty.setOnClickListener(listener);
//        rootView.findViewById(R.id.frameLayout).setOnClickListener(listener);



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
    public void onClick(View view) {

    }

    @Override
    public void onStart() {
        super.onStart();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.app_name));

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
                                Book data = new Book();
                                data.setBookTitle(item.getString("title"));
                                data.setBookPubDate(item.getString("pubdate"));
//                                int authorLen = item.getString("author").length();
//                                String authorArray=item.getString("author");
//                                ArrayList<String> authorList=new ArrayList<String>();
//                                for(int j=0;j<authorLen;j++){
//                                    authorList.add(authorArray);
//                                }
                                data.setBookAuthor(item.getString("author"));
                                data.setBookPrice(item.getString("price"));
                                data.setBookImgUrl(item.getString("image"));
//                                data.setBookUrl(item.getString("url"));
                                datas.add(data);
                            }
                            refreshLayout.setRefreshing(false);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        /**
                         * 请求成功后为ListView设置Adapter
                         */

                        listView.setAdapter(bookListAdapter);
                        bookListAdapter.notifyDataSetChanged();

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

    public interface OnFragmentInteractionListener {
        void onVoteListFragmentInteraction(Uri uri);
    }
}
