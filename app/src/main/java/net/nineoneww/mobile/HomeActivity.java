package net.nineoneww.mobile;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HomeActivity extends AppCompatActivity implements FragmentSurveyList.OnFragmentInteractionListener{

    private static final String TAG = "HomeActivity";
    private FragmentSurveyList fragmentSurveyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //create fragment
        fragmentSurveyList = new FragmentSurveyList();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragmentSurveyList).commit();

    }

    @Override
    public void onFragmentSurveyListInteraction(Uri uri) {

    }

}
