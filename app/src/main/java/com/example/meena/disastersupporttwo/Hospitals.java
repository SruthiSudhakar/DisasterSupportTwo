package com.example.meena.disastersupporttwo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by meena on 11/4/2017.
 */

public class Hospitals extends Fragment {
    CommunicationChannel mCallback;
    public interface CommunicationChannel{
       // void sendInfo(Double speed, Double distance, Date date);
        //void graphIt();
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (CommunicationChannel) activity;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()+ " must implement OnHeadlineSelectedListener");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1, container, false);
        /*distance_id= (EditText) rootView.findViewById(R.id.distance_id);
        time_id= (EditText) rootView.findViewById(R.id.time_id);
        send= (Button) rootView.findViewById(R.id.button);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((!distance_id.getText().toString().isEmpty())||(!time_id.getText().toString().isEmpty()))
                    mCallback.sendInfo(Double.parseDouble(String.valueOf(distance_id.getText()))/Double.parseDouble(String.valueOf(time_id.getText())), Double.parseDouble(String.valueOf(distance_id.getText())), Calendar.getInstance().getTime());
            }
        });*/
        return rootView;
    }
}
