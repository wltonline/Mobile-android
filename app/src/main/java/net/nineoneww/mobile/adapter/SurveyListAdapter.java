package net.nineoneww.mobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import net.nineoneww.mobile.R;
import net.nineoneww.mobile.api.res.HomeItem;
import net.nineoneww.mobile.api.res.Survey;
import net.nineoneww.mobile.util.StringUtil;

import java.util.List;

/**
 * Created by lilian on 2017/8/8.
 */

public class SurveyListAdapter extends ArrayAdapter<HomeItem> {

    private String profileQuestionnairePoint;

    public SurveyListAdapter(Context context, List<HomeItem> surveys) {
        super(context, 0, surveys);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final HomeItem survey = getItem(position);

        SurveyType surveyType = SurveyType.valueOf(getItemViewType(position));

//        switch (surveyType) {
//            case PROFILE_QUESTIONNAIRE:
//                if (convertView == null) {
//                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.survey_list_item_profile_questionnaire, parent, false);
//                }
//
//                ((TextView) convertView.findViewById(R.id.textViewSurveyTitle)).setText(survey.getItemTitle());
//                ((TextView) convertView.findViewById(R.id.textViewSurveyId)).setText(survey.getItemNumber());
//                String format = getContext().getResources().getString(R.string.label_unit_profile_questionnaire);
//                ((TextView) convertView.findViewById(R.id.textViewPointLabel)).setText(StringUtil.format(format, profileQuestionnairePoint));
//                break;
//            case SURVEY:
//                if (convertView == null) {
//                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.survey_list_item, parent, false);
//                }
//
//                ((TextView) convertView.findViewById(R.id.textViewSurveyTitle)).setText(survey.getItemTitle());
//                ((TextView) convertView.findViewById(R.id.textViewSurveyId)).setText(survey.getItemNumber());
//                ((TextView) convertView.findViewById(R.id.textViewMoney)).setText(survey.getItemPoint());
//                ((TextView) convertView.findViewById(R.id.textViewPointLabel)).setText(getContext().getResources().getString(R.string.label_unit_survey));
//                ((TextView) convertView.findViewById(R.id.textViewLoi)).setText(survey.getItemLoi());
//                break;
//            case MOBILE_BLOCKED_SURVEY:
//                if (convertView == null) {
//                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.survey_list_item_blocked, parent, false);
//                }
//
//                //init for recycling
//                convertView.findViewById(R.id.imageViewPc).setVisibility(View.VISIBLE);
//                convertView.findViewById(R.id.imageViewTablet).setVisibility(View.VISIBLE);
//
//                ((TextView) convertView.findViewById(R.id.textViewSurveyTitle)).setText(survey.getItemTitle());
//                ((TextView) convertView.findViewById(R.id.textViewSurveyId)).setText(survey.getItemNumber());
//                ((TextView) convertView.findViewById(R.id.textViewMoney)).setText(survey.getItemPoint());
//                ((TextView) convertView.findViewById(R.id.textViewPointLabel)).setText(getContext().getResources().getString(R.string.label_unit_survey));
//                ((TextView) convertView.findViewById(R.id.textViewLoi)).setText(survey.getItemLoi());
//                Survey researchSurvey = (Survey) survey;
//                if(researchSurvey.getBlockedDevices().isPcBlocked()){
//                    convertView.findViewById(R.id.imageViewPc).setVisibility(View.GONE);
//                }
//                if(researchSurvey.getBlockedDevices().isTabletBlocked()){
//                    convertView.findViewById(R.id.imageViewTablet).setVisibility(View.GONE);
//                }
//                break;
//
//            case SSI_SURVEY:
//                if (convertView == null) {
//                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.survey_list_item_profile_questionnaire, parent, false);
//                }
//
//                ((TextView) convertView.findViewById(R.id.textViewSurveyTitle)).setText(survey.getItemTitle());
//                if (survey.getItemNumber().equals("--")) {
//                    convertView.findViewById(R.id.textView2).setVisibility(View.INVISIBLE);
//                    convertView.findViewById(R.id.textViewSurveyId).setVisibility(View.INVISIBLE);
//                } else {
//                    ((TextView) convertView.findViewById(R.id.textViewSurveyId)).setText(survey.getItemNumber());
//                }
//                String pointFormat = getContext().getResources().getString(R.string.label_unit_profile_questionnaire_single);
//                ((TextView) convertView.findViewById(R.id.textViewPointLabel)).setText(StringUtil.format(pointFormat, survey.getItemPoint()));
//                ((TextView) convertView.findViewById(R.id.textViewProfilingSurvey)).setText(getContext().getResources().getString(R.string.label_ssi_Lucky_message));
//                break;
//        }

        // Return the completed view to render on screen
        return convertView;
    }

    public void setProfileQuestionnairePoint(String profileQuestionnairePoint) {
        this.profileQuestionnairePoint = profileQuestionnairePoint;
    }

    @Override
    public int getViewTypeCount() {
        return SurveyType.values().length;
    }

    @Override
    public int getItemViewType(int position) {
        HomeItem surveyItem = getItem(position);
        if (surveyItem.isProfileQuestionnaire()) {
            return SurveyType.PROFILE_QUESTIONNAIRE.val;
        } else if (surveyItem.isSOPQuestionnaire()) {
            Survey research = (Survey) surveyItem;
            if (research.getBlockedDevices().isMobileBlocked()) {
                return SurveyType.MOBILE_BLOCKED_SURVEY.val;
            } else {
                return SurveyType.SURVEY.val;
            }
        } else {
            return SurveyType.SSI_SURVEY.val;
        }
    }

    private enum SurveyType {
        PROFILE_QUESTIONNAIRE(0),
        MOBILE_BLOCKED_SURVEY(1),
        SURVEY(2),
        SSI_SURVEY(3);

        private final int val;

        SurveyType(int val) {
            this.val = val;
        }

        static SurveyType valueOf(int val) {
            switch (val) {
                case 0:
                    return PROFILE_QUESTIONNAIRE;
                case 1:
                    return MOBILE_BLOCKED_SURVEY;
                case 2:
                    return SURVEY;
                default :
                    return SSI_SURVEY;
            }
        }
    }
}

