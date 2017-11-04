package com.example.meena.disastersupporttwo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by meena on 11/4/2017.
 */

public class AdviceTab extends Fragment {
    CommunicationChannelTwo mCallback;

    public interface CommunicationChannelTwo{
        // void sendInfo(Double speed, Double distance, Date date);
        //void graphIt();
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (CommunicationChannelTwo) activity;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()+ " must implement OnHeadlineSelectedListener");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.advice_layout, container, false);

        return rootView;
    }

}
