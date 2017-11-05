package com.example.meena.disastersupporttwo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by meena on 11/4/2017.
 */

public class AdviceTab extends Fragment {
    CommunicationChannelTwo mCallback;
    Button searchButton;
    EditText editText;
    TextView faq_tv,advice_tv;
    String editString;

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
        searchButton = (Button)rootView.findViewById(R.id.searchButton_id);
        editText = (EditText)rootView.findViewById(R.id.editText_id);
        faq_tv = (TextView)rootView.findViewById(R.id.med_faq_tv);
        advice_tv = (TextView)rootView.findViewById(R.id.our_advice_tv);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayAdvice();
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editString = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        faq_tv.setText("");
        advice_tv.setText("");
        return rootView;


    }

    public void DisplayAdvice(){

        advice_tv.setText(editString);

    }

}
