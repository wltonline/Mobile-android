package net.nineoneww.mobile.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import net.nineoneww.mobile.R;

/**
 * Created by lilian on 2017/8/10.
 */

public class SettingFragment extends Fragment {

    private static final String TAG = "SettingFragment";

    private ProgressBar progressBar;

    public SettingFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //initiate
        View rootView = inflater.inflate(R.layout.fragment_setting, container, false);

        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        return rootView;
    }

    public interface OnFragmentInteractionListener {
        void onSettingFragmentInteraction(Uri uri);
    }
}
