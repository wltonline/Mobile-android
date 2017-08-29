package net.nineoneww.mobile.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import net.nineoneww.mobile.R;
import net.nineoneww.mobile.api.res.HomeItem;
import net.nineoneww.mobile.api.res.Survey;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kotaro.arimura on 2016/04/25.
 */
public class SurveyListAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private static final int TYPE_ITEM = 0;

    private static final int TYPE_FOOTER = 1;
    //上拉加载更多
    static final int PULL_LOAD_MORE = 0;
    //正在加载更多
    static final int LOADING_MORE = 1;
    //没有更多
    static final int NO_MORE = 2;
    //脚布局当前的状态,默认为没有更多
    int footer_state = 1;
    private ArrayList<Survey> surveyData;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    private SurveyListAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public SurveyListAdapter(Context context, ArrayList<Survey> surveys) {
        this.surveyData = surveys;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.content_survey_item,  parent, false);
            return new SurveyListAdapter.ItemViewHolder(view);
        }
        else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.content_footer,  parent, false);
            return new SurveyListAdapter.FooterViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
// 绑定数据
        if(holder instanceof ItemViewHolder){
            ItemViewHolder itemViewHolder = (ItemViewHolder)holder;
//            itemViewHolder.ivImg.setTag(voteData.get(position).getItemTitle());//设置Tag
            itemViewHolder.tv_survey_title.setText(surveyData.get(position).getItemTitle());
            itemViewHolder.tv_survey_number.setText(surveyData.get(position).getItemNumber());
            itemViewHolder.tv_survey_type.setText("商业问卷");
            itemViewHolder.tv_survey_duration.setText(surveyData.get(position).getItemLoi());
            itemViewHolder.tv_survey_points.setText(surveyData.get(position).getItemPoint());

            //通过集合中的图片地址获得图片并且设置到view上
//            getImage(this.context, voteData.get(position).getVoteImgUrl(), itemViewHolder.ivImg);
            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onItemClick(holder.itemView, position);
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onItemLongClick(holder.itemView, position);
                        return false;
                    }
                });
            }
        }else if(holder instanceof FooterViewHolder){
            FooterViewHolder footerViewHolder = (FooterViewHolder)holder;
            if(position == 0){
                footerViewHolder.mProgressBar.setVisibility(View.GONE);
                footerViewHolder.tv_line1.setVisibility(View.GONE);
                footerViewHolder.tv_line2.setVisibility(View.GONE);
                footerViewHolder.tv_state.setText("");
            }
            switch (footer_state) {//根据状态来让脚布局发生改变
                case PULL_LOAD_MORE://上拉加载
                    footerViewHolder.mProgressBar.setVisibility(View.GONE);
                    footerViewHolder.tv_state.setText(R.string.footer_pull_loading);
                    break;
                case LOADING_MORE:
                    footerViewHolder.mProgressBar.setVisibility(View.VISIBLE);
                    footerViewHolder.tv_line1.setVisibility(View.GONE);
                    footerViewHolder.tv_line2.setVisibility(View.GONE);
                    footerViewHolder.tv_state.setText(R.string.footer_loading);
                    break;
                case NO_MORE:
                    footerViewHolder.mProgressBar.setVisibility(View.GONE);
                    footerViewHolder.tv_line1.setVisibility(View.VISIBLE);
                    footerViewHolder.tv_line2.setVisibility(View.VISIBLE);
                    footerViewHolder.tv_state.setText(R.string.footer_baseline);
                    break;
            }
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
//        private ImageView ivImg;
        private TextView tv_survey_title;
        private TextView tv_survey_number;
        private TextView tv_survey_type;
        private TextView tv_survey_duration;
        private TextView tv_survey_points;

        public ItemViewHolder(View itemView) {
            super(itemView);
//            ivImg = (ImageView) itemView.findViewById(R.id.survey_url);
            tv_survey_title = (TextView) itemView.findViewById(R.id.survey_title);
            tv_survey_number = (TextView) itemView.findViewById(R.id.survey_number);
            tv_survey_type = (TextView) itemView.findViewById(R.id.survey_type);
            tv_survey_duration = (TextView) itemView.findViewById(R.id.survey_duration);
            tv_survey_points = (TextView) itemView.findViewById(R.id.survey_points);
        }
    }

    public static class FooterViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar mProgressBar;
        private TextView tv_state;
        private TextView tv_line1;
        private TextView tv_line2;

        public FooterViewHolder(View itemView) {
            super(itemView);
            mProgressBar = (ProgressBar) itemView.findViewById(R.id.progressbar);
            tv_state = (TextView) itemView.findViewById(R.id.foot_view_item_tv);
            tv_line1 = (TextView) itemView.findViewById(R.id.tv_line1);
            tv_line2 = (TextView) itemView.findViewById(R.id.tv_line2);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return surveyData == null ? 0 : surveyData.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public void changeState(int state) {
        this.footer_state = state;
        notifyDataSetChanged();
    }

//    @Override
//    public int getItemViewType(int position) {
//        HomeItem surveyItem = getItem(position);
//        if (surveyItem.isProfileQuestionnaire()) {
//            return tv_survey_type.PROFILE_QUESTIONNAIRE.val;
//        } else if (surveyItem.isSOPQuestionnaire()) {
//            Survey research = (Survey) surveyItem;
//            if (research.getBlockedDevices().isMobileBlocked()) {
//                return tv_survey_type.MOBILE_BLOCKED_SURVEY.val;
//            } else {
//                return tv_survey_type.SURVEY.val;
//            }
//        } else {
//            return tv_survey_type.SSI_SURVEY.val;
//        }
//    }
//
//    private enum tv_survey_type {
//        PROFILE_QUESTIONNAIRE(0),
//        MOBILE_BLOCKED_SURVEY(1),
//        SURVEY(2),
//        SSI_SURVEY(3);
//
//        private final int val;
//
//        tv_survey_type(int val) {
//            this.val = val;
//        }
//
//        static tv_survey_type valueOf(int val) {
//            switch (val) {
//                case 0:
//                    return PROFILE_QUESTIONNAIRE;
//                case 1:
//                    return MOBILE_BLOCKED_SURVEY;
//                case 2:
//                    return SURVEY;
//                default :
//                    return SSI_SURVEY;
//            }
//        }
//    }
}
