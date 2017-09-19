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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.nineoneww.mobile.ui.activity.HomeActivity;
import net.nineoneww.mobile.R;
import net.nineoneww.mobile.ui.activity.VoteDetailActivity;
import net.nineoneww.mobile.ui.adapter.SurveyListAdapter;
import net.nineoneww.mobile.api.res.SopSurveysJson;
import net.nineoneww.mobile.api.res.Survey;

import java.util.ArrayList;

/**
 * Created by lilian on 2017/8/25.
 */

public class SurveyListFragment  extends Fragment implements SwipeRefreshLayout.OnRefreshListener,View.OnClickListener {
    private static final String TAG = "SurveyListFragment";

    private RecyclerView surveyRecyclerView;
    private SurveyListAdapter surveyListAdapter;
    private SwipeRefreshLayout surveyRefreshLayout;
    private LinearLayoutManager surverLayoutManager;
    private ArrayList<Survey> homeItems;
    private SwipeRefreshLayout refreshLayout;
    private boolean isWebViewOpened = false;
    private boolean isLoading;
    int page = 0;
    int totalPage = 2;//模拟请求的一共的页数
    int lastVisibleItemPosition;
    private Handler handler = new Handler();

    public SurveyListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_survey_list, container, false);

        //swipe
        surveyRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.survey_refresh_layout);
        surveyRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        surveyRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadInfo();
                    }
                }, 2000);
            }
        });
        surveyRefreshLayout.setVisibility(View.VISIBLE);
        //show refresh
//        surveyRefreshLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                surveyRefreshLayout.setRefreshing(true);
//                loadInfo();
//            }
//        });

        surveyRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_survey);
        homeItems = new ArrayList<Survey>();
        surveyListAdapter = new SurveyListAdapter(this.getContext(),homeItems);
        surverLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        surveyRecyclerView.setLayoutManager(surverLayoutManager);
        surveyRecyclerView.setAdapter(surveyListAdapter);
        surveyRecyclerView.setItemAnimator(new DefaultItemAnimator());

        surveyRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d("test", "StateChanged = " + newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d("test", "onScrolled");

                lastVisibleItemPosition = surverLayoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition + 1 == surveyListAdapter.getItemCount()) {
                    Log.d("test", "loading executed");

                    boolean isRefreshing = surveyRefreshLayout.isRefreshing();
                    if (isRefreshing) {
                        surveyListAdapter.notifyItemRemoved(surveyListAdapter.getItemCount());
                        return;
                    }
                    if (!isLoading) {
                        if (page < totalPage) {
                            Log.e("duanlian", "onScrollStateChanged: " + "进来了");
                            isLoading = true;
                            surveyListAdapter.changeState(1);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    loadInfo();
                                    page++;
                                    isLoading = false;
                                }
                            }, 2000);
                        } else {
                            surveyListAdapter.changeState(2);
                        }
                    }
                }
            }
        });

        //event
        surveyListAdapter.setOnItemClickListener(new SurveyListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Vote VoteItem = voteItems.get(position - 1);
                Intent intent = new Intent(SurveyListFragment.this.getActivity(), VoteDetailActivity.class);
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
        loadInfo();
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
        surveyRecyclerView.setAdapter(surveyListAdapter);
        surveyListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {

    }

    public interface OnFragmentInteractionListener {
        void onFragmentSurveyListInteraction(Uri uri);
    }

    public void loadInfo(){
        surveyRefreshLayout.setRefreshing(true);
        String string = "{\"data\":{\"research\":[{\"surveyId\":\"0\",\"loi\":\"15\",\"isAnswered\":\"1\",\"title\":\"关于日常生活的问卷\",\"date\":\"2017-06-07\",\"extraInfo\":{\"date\":{\"startAt\":\"2015-1-12\",\"endAt\":\"2017-11-22\"},\"point\":{\"complete\":\"500\",\"screenout\":\"50\",\"quotafull\":\"10\"},\"content\":\"这是什么用\"}},{\"surveyId\":\"1\",\"loi\":\"15\",\"isAnswered\":\"1\",\"title\":\"关于日常生活的问卷\",\"date\":\"2017-06-07\",\"extraInfo\":{\"date\":{\"startAt\":\"2015-1-12\",\"endAt\":\"2017-11-22\"},\"point\":{\"complete\":\"500\",\"screenout\":\"50\",\"quotafull\":\"10\"},\"content\":\"这是什么用\"}},{\"surveyId\":\"2\",\"loi\":\"15\",\"isAnswered\":\"1\",\"title\":\"关于日常生活的问卷\",\"date\":\"2017-06-07\",\"extraInfo\":{\"date\":{\"startAt\":\"2015-1-12\",\"endAt\":\"2017-11-22\"},\"point\":{\"complete\":\"500\",\"screenout\":\"50\",\"quotafull\":\"10\"},\"content\":\"这是什么用\"}},{\"surveyId\":\"3\",\"loi\":\"15\",\"isAnswered\":\"1\",\"title\":\"关于日常生活的问卷\",\"date\":\"2017-06-07\",\"extraInfo\":{\"date\":{\"startAt\":\"2015-1-12\",\"endAt\":\"2017-11-22\"},\"point\":{\"complete\":\"500\",\"screenout\":\"50\",\"quotafull\":\"10\"},\"content\":\"这是什么用\"}},{\"surveyId\":\"4\",\"loi\":\"15\",\"isAnswered\":\"1\",\"title\":\"关于日常生活的问卷\",\"date\":\"2017-06-07\",\"extraInfo\":{\"date\":{\"startAt\":\"2015-1-12\",\"endAt\":\"2017-11-22\"},\"point\":{\"complete\":\"500\",\"screenout\":\"50\",\"quotafull\":\"10\"},\"content\":\"这是什么用\"}}]}}";
//        Reader reader = new InputStreamReader(SurveyListFragment.class.getResourceAsStream("/book.json"), "UTF-8");
        Gson gson = new GsonBuilder().create();
        final SopSurveysJson sopSurveysJson = gson.fromJson(string, SopSurveysJson.class);
//        final List<Demo> ps = gson.fromJson(string, new TypeToken<List<Demo>>(){}.getType());

        homeItems.addAll(sopSurveysJson.getData().getResearch());

        //surveyListAdapter = new SurveyListAdapter(this.getContext(), homeItems);
        surveyRecyclerView.setAdapter(surveyListAdapter);
        surveyListAdapter.notifyDataSetChanged();
        surveyRefreshLayout.setRefreshing(false);

    }
}
