package com.hao.e_nsurance.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hao.e_nsurance.R;
import com.rm.freedrawview.FreeDrawView;

import de.hdodenhof.circleimageview.CircleImageView;

public class CroquisFragment extends Fragment {

    FreeDrawView draw;
    Button undo, redo, clear;
    CircleImageView black, red, blue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_croquis, container, false);

        draw = (FreeDrawView) rootView.findViewById(R.id.draw);

        undo = (Button) rootView.findViewById(R.id.undo);
        redo = (Button) rootView.findViewById(R.id.redo);
        clear = (Button) rootView.findViewById(R.id.clear);

        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                draw.undoLast();
            }
        });
        redo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                draw.redoLast();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                draw.undoAll();
            }
        });

        black = (CircleImageView) rootView.findViewById(R.id.black);
        red = (CircleImageView) rootView.findViewById(R.id.red);
        blue = (CircleImageView) rootView.findViewById(R.id.blue);

        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                draw.setPaintColor(Color.BLACK);
            }
        });

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                draw.setPaintColor(getResources().getColor(R.color.red, null));
            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                draw.setPaintColor(getResources().getColor(R.color.blue, null));
            }
        });
        return rootView;
    }
}
