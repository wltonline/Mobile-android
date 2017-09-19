package net.nineoneww.mobile.ui.activity;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.nineoneww.mobile.api.res.SopSurveysJson;
import net.nineoneww.mobile.api.res.Survey;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by lilian on 2017/8/25.
 */

public class Test {
    public static void main(String args[]){
//        String string = "{\"data\":{\"research\":[{\"surveyId\":\"0\",\"loi\":\"15\",\"title\":\"关于日常生活的问卷\",\"date\":\"2017-06-07\",\"extraInfo\":{\"date\":{\"startAt\":\"2015-1-12\",\"endAt\":\"2017-11-22\"},\"point\":{\"complete\":\"500\",\"screenout\":\"50\",\"quotafull\":\"10\"}}},{\"surveyId\":\"1\",\"loi\":\"15\",\"title\":\"关于日常生活的问卷\",\"date\":\"2017-06-07\",\"extraInfo\":{\"date\":{\"startAt\":\"2015-1-12\",\"endAt\":\"2017-11-22\"},\"point\":{\"complete\":\"500\",\"screenout\":\"50\",\"quotafull\":\"10\"}}},{\"surveyId\":\"2\",\"loi\":\"15\",\"title\":\"关于日常生活的问卷\",\"date\":\"2017-06-07\",\"extraInfo\":{\"date\":{\"startAt\":\"2015-1-12\",\"endAt\":\"2017-11-22\"},\"point\":{\"complete\":\"500\",\"screenout\":\"50\",\"quotafull\":\"10\"}}},{\"surveyId\":\"3\",\"loi\":\"15\",\"title\":\"关于日常生活的问卷\",\"date\":\"2017-06-07\",\"extraInfo\":{\"date\":{\"startAt\":\"2015-1-12\",\"endAt\":\"2017-11-22\"},\"point\":{\"complete\":\"500\",\"screenout\":\"50\",\"quotafull\":\"10\"}}},{\"surveyId\":\"4\",\"loi\":\"15\",\"title\":\"关于日常生活的问卷\",\"date\":\"2017-06-07\",\"extraInfo\":{\"date\":{\"startAt\":\"2015-1-12\",\"endAt\":\"2017-11-22\"},\"point\":{\"complete\":\"500\",\"screenout\":\"50\",\"quotafull\":\"10\"}}}]}}";//此处造你的json
//        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
//        final SopSurveysJson sopSurveysJson = gson.fromJson(string, SopSurveysJson.class);
//        System.out.println(sopSurveysJson.getData().getResearch().size());

        //还有个更快的方式 你反过来弄不就好了吗
        Gson gson = new GsonBuilder().create();
        SopSurveysJson sopSurveysJson = new SopSurveysJson();
        SopSurveysJson.Data data = new SopSurveysJson.Data();
        List<Survey> list = new ArrayList<Survey>();
        Survey survey = null;
        Survey.ExtraInfo extraInfo = null;
        Survey.Date date = null;
        Survey.Point point = null;
        for(int i=0;i<5;i++){
            extraInfo = new Survey.ExtraInfo();
            date = new Survey.Date();
            point = new Survey.Point();
            date.setStartAt("2015-1-12");
            date.setEndAt("2017-11-22");
            point.setComplete("500");
            point.setQuotafull("10");
            point.setScreenout("50");
            extraInfo.setContent("这是什么用");
            extraInfo.setDate(date);
            extraInfo.setPoint(point);
            survey = new Survey();
            survey.setDate("2017-06-07");
            survey.setLoi("15");
            survey.setTitle("关于日常生活的问卷");
            survey.setSurveyId(""+i);
            survey.setExtraInfo(extraInfo);
            survey.setIsAnswered("1");
            list.add(survey);
        }
        data.setResearch(list);
        sopSurveysJson.setData(data);
        System.out.print(gson.toJson(sopSurveysJson));

    }
}
