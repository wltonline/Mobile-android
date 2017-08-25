package net.nineoneww.mobile;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.nineoneww.mobile.fragment.FragmentPromotion;
import net.nineoneww.mobile.fragment.FragmentSetting;
import net.nineoneww.mobile.fragment.FragmentSurveyList;
import net.nineoneww.mobile.fragment.FragmentVoteList;
import net.nineoneww.mobile.view.IconFontTextView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements
        FragmentSurveyList.OnFragmentInteractionListener,
        FragmentVoteList.OnFragmentInteractionListener,
        FragmentPromotion.OnFragmentInteractionListener,
        FragmentSetting.OnFragmentInteractionListener,
        View.OnClickListener{

    private static final String TAG = "HomeActivity";
    private FragmentSurveyList fragmentSurveyList;
    private FragmentVoteList fragmentVoteList;
    private FragmentSetting fragmentSetting;
    private FragmentPromotion fragmentPromotion;
    private LinearLayout ly_one,ly_two,ly_three,ly_four,ly_five;
    private TextView mTextView1,mTextView2,mTextView3,mTextView4,mTextView5;
    private IconFontTextView mIconFontTextView1,mIconFontTextView2,mIconFontTextView3,mIconFontTextView4,mIconFontTextView5;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();
        ly_one.performClick();
        setSelectFragment(0);
    }

    private Toolbar initToolbar(int id, int titleId, int titleString) {
        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(id);
//        toolbar.setTitle("");
        TextView textView = (TextView) findViewById(titleId);
        textView.setText(titleString);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null){
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setDisplayShowTitleEnabled(false);
//        }
        return toolbar;
    }

    private void setSelectFragment(int i) {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);
        switch (i) {
            case 0:
                if(fragmentSurveyList == null){
                    fragmentSurveyList = new FragmentSurveyList();
                    transaction.add(R.id.fragment_container,fragmentSurveyList);
                }else {
                    transaction.show(fragmentSurveyList);
                }
                break;
            case 1:
                if(fragmentVoteList == null){
                    fragmentVoteList = new FragmentVoteList();
                    transaction.add(R.id.fragment_container,fragmentVoteList);
                }else {
                    transaction.show(fragmentVoteList);
                }
                break;
            case 2:
                if(fragmentPromotion == null){
                    fragmentPromotion = new FragmentPromotion();
                    transaction.add(R.id.fragment_container,fragmentPromotion);
                }else {
                    transaction.show(fragmentPromotion);
                }
                break;
            case 3:
                if(fragmentSurveyList == null){
                    fragmentSurveyList = new FragmentSurveyList();
                    transaction.add(R.id.fragment_container,fragmentSurveyList);
                }else {
                    transaction.show(fragmentSurveyList);
                }
                break;
            case 4:
                if(fragmentSetting == null){
                    fragmentSetting = new FragmentSetting();
                    transaction.add(R.id.fragment_container,fragmentSetting);
                }else {
                    transaction.show(fragmentSetting);
                }
                break;

            default:
                break;
        }
        transaction.commit();

    }

    private void hideFragment(FragmentTransaction transaction2) {
        // TODO Auto-generated method stub
        if(fragmentSurveyList != null){
            transaction2.hide(fragmentSurveyList);
        }

        if(fragmentVoteList != null){
            transaction2.hide(fragmentVoteList);
        }

        if(fragmentPromotion != null){
            transaction2.hide(fragmentPromotion);
        }

        if(fragmentSetting != null){
            transaction2.hide(fragmentSetting);
        }

    }

    private void initView() {
        ly_one = (LinearLayout)findViewById(R.id.ly_tab_menu_survey);
        ly_two = (LinearLayout)findViewById(R.id.ly_tab_menu_vote);
        ly_three = (LinearLayout)findViewById(R.id.ly_tab_menu_promotion);
        ly_four = (LinearLayout)findViewById(R.id.ly_tab_menu_exchange);
        ly_five = (LinearLayout)findViewById(R.id.ly_tab_menu_user);

        mTextView1 = (TextView)findViewById(R.id.tab_menu_survey);
        mTextView2 = (TextView)findViewById(R.id.tab_menu_vote);
        mTextView3 = (TextView)findViewById(R.id.tab_menu_promotion);
        mTextView4 = (TextView)findViewById(R.id.tab_menu_exchange);
        mTextView5 = (TextView)findViewById(R.id.tab_menu_user);

        mIconFontTextView1 = (IconFontTextView)findViewById(R.id.tab_menu_survey_fontIcon);
        mIconFontTextView2 = (IconFontTextView)findViewById(R.id.tab_menu_vote_fontIcon);
        mIconFontTextView3 = (IconFontTextView)findViewById(R.id.tab_menu_promotion_fontIcon);
        mIconFontTextView4 = (IconFontTextView)findViewById(R.id.tab_menu_exchange_fontIcon);
        mIconFontTextView5 = (IconFontTextView)findViewById(R.id.tab_menu_user_fontIcon);

        ly_one.setOnClickListener(this);
        ly_two.setOnClickListener(this);
        ly_three.setOnClickListener(this);
        ly_four.setOnClickListener(this);
        ly_five.setOnClickListener(this);


    }

    //重置所有文本的选中状态
    private void setSelected() {
        mTextView1.setSelected(false);
        mTextView2.setSelected(false);
        mTextView3.setSelected(false);
        mTextView4.setSelected(false);
        mTextView5.setSelected(false);

        mIconFontTextView1.setSelected(false);
        mIconFontTextView2.setSelected(false);
        mIconFontTextView3.setSelected(false);
        mIconFontTextView4.setSelected(false);
        mIconFontTextView5.setSelected(false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ly_tab_menu_survey:
                initToolbar(R.id.toolbar, R.id.toolbar_title,R.string.title_survey);
                setSelected();
                setSelectFragment(0);
                mTextView1.setSelected(true);
                mIconFontTextView1.setSelected(true);
                break;
            case R.id.ly_tab_menu_vote:
                initToolbar(R.id.toolbar, R.id.toolbar_title,R.string.title_vote);
                setSelected();
                setSelectFragment(1);
                mTextView2.setSelected(true);
                mIconFontTextView2.setSelected(true);
                break;
            case R.id.ly_tab_menu_promotion:
                initToolbar(R.id.toolbar, R.id.toolbar_title,R.string.title_promotion);
                setSelected();
//                Intent intent = new Intent(this, PromotionActivity.class);
//                startActivity(intent);
                setSelectFragment(2);
                mTextView3.setSelected(true);
                mIconFontTextView3.setSelected(true);
                break;
            case R.id.ly_tab_menu_exchange:
                initToolbar(R.id.toolbar, R.id.toolbar_title,R.string.title_exchange);
                setSelected();
                setSelectFragment(3);
                mTextView4.setSelected(true);
                mIconFontTextView4.setSelected(true);
                break;
            case R.id.ly_tab_menu_user:
                initToolbar(R.id.toolbar, R.id.toolbar_title,R.string.title_user);
                setSelected();
                setSelectFragment(4);
                mTextView5.setSelected(true);
                mIconFontTextView5.setSelected(true);
                break;
        }
    }

    @Override
    public void onFragmentSurveyListInteraction(Uri uri) {

    }

    @Override
    public void onVoteListFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPromotionFragmentInteraction(Uri uri) {

    }

    @Override
    public void onSettingFragmentInteraction(Uri uri) {

    }
}
