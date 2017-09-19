package net.nineoneww.mobile.presenter.impl;

import android.content.Context;

import net.nineoneww.mobile.model.entity.Survey;
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
