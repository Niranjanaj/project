package com.example.niranjan.app;

/**
 * Created by Niranjan on 31-12-2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by Belal on 18/09/16.
 */


public class Menu1 extends Fragment implements View.OnClickListener {
    ImageButton b1,b2;
    Button mButton;
    Intent i;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View v = inflater.inflate(R.layout.fragment, container, false);
        mButton = (Button) v.findViewById(R.id.button);
        mButton.setOnClickListener(this);
        b1 = (ImageButton)v.findViewById(R.id.b1);
        b1.setOnClickListener(this);
        b2 = (ImageButton)v.findViewById(R.id.b2);
        b2.setOnClickListener(this);
        return v;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b1:
                i = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(i);
                break;
            case R.id.b2:
                i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i, "Select Picture"), 1);
                break;
            case R.id.button:
                break;
        }
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Make complaints");
    }
}
