package com.hao.e_nsurance.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.hao.e_nsurance.R;

import java.util.ArrayList;
import java.util.List;

public class PointChocFragment extends Fragment implements RadioButton.OnCheckedChangeListener {

    private RadioButton r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12;
    RelativeLayout schema;
    List<RadioButton> radioButtonList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_point_choc, container, false);

        r1 = (RadioButton) rootView.findViewById(R.id.radio1);
        r2 = (RadioButton) rootView.findViewById(R.id.radio2);
        r3 = (RadioButton) rootView.findViewById(R.id.radio3);
        r4 = (RadioButton) rootView.findViewById(R.id.radio4);
        r5 = (RadioButton) rootView.findViewById(R.id.radio5);
        r6 = (RadioButton) rootView.findViewById(R.id.radio6);
        r7 = (RadioButton) rootView.findViewById(R.id.radio7);
        r8 = (RadioButton) rootView.findViewById(R.id.radio8);
        r9 = (RadioButton) rootView.findViewById(R.id.radio9);
        r10 = (RadioButton) rootView.findViewById(R.id.radio10);
        r11 = (RadioButton) rootView.findViewById(R.id.radio11);
        r12 = (RadioButton) rootView.findViewById(R.id.radio12);

        schema = (RelativeLayout) rootView.findViewById(R.id.schema);

        radioButtonList = new ArrayList<>();
        radioButtonList.add(0, r1);
        radioButtonList.add(1, r2);
        radioButtonList.add(2, r3);
        radioButtonList.add(3, r4);
        radioButtonList.add(4, r5);
        radioButtonList.add(5, r6);
        radioButtonList.add(6, r7);
        radioButtonList.add(7, r8);
        radioButtonList.add(8, r9);
        radioButtonList.add(9, r10);
        radioButtonList.add(10, r11);
        radioButtonList.add(11, r12);

        r1.setOnCheckedChangeListener(this);
        r2.setOnCheckedChangeListener(this);
        r3.setOnCheckedChangeListener(this);
        r4.setOnCheckedChangeListener(this);
        r5.setOnCheckedChangeListener(this);
        r6.setOnCheckedChangeListener(this);
        r7.setOnCheckedChangeListener(this);
        r8.setOnCheckedChangeListener(this);
        r9.setOnCheckedChangeListener(this);
        r10.setOnCheckedChangeListener(this);
        r11.setOnCheckedChangeListener(this);
        r12.setOnCheckedChangeListener(this);
        return rootView;
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

        if(compoundButton.isChecked() /*&& !allUnchecked && firstTime*/){
            for(RadioButton radioButton : radioButtonList){
                if(radioButton == compoundButton){
                    continue;
                }
                radioButton.setChecked(false);
            }
        }
    }
}
