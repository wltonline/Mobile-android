package net.nineoneww.mobile.ui.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.nineoneww.mobile.adapter.SurveyListAdapter;
import net.nineoneww.mobile.api.res.SopSurveysJson;
import net.nineoneww.mobile.api.res.Survey;
import net.nineoneww.mobile.ui.view.SurveyListView;

import java.util.ArrayList;

/**
 * Created by lilian on 2017/8/30.
 */

public class SurveyPresenter extends BasePresenter<SurveyListView> {
    private SurveyListView surveyListView;
    private ArrayList<Survey> homeItems;

    public SurveyPresenter(SurveyListView surveyListView, Context context){
        this.surveyListView = surveyListView;
    }

}
