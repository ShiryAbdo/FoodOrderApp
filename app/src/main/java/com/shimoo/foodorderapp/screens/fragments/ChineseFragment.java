package com.shimoo.foodorderapp.screens.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shimoo.foodorderapp.R;
 import com.shimoo.foodorderapp.components.MainHomeActivitycomponents;
import com.shimoo.foodorderapp.controls.MyApplicationClass;
import com.shimoo.foodorderapp.modules.MainHomeActivityModule;
import com.shimoo.foodorderapp.screens.activities.MainHomeActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChineseFragment extends Fragment {


    public ChineseFragment() {
        // Required empty public constructor
    }
    MainHomeActivity mainHomeActivity ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this
        View root =inflater.inflate(R.layout.fragment_chinese, container, false);
         MainHomeActivitycomponents component= DaggerMainHomeActivitycomponents.builder()
                .MainHomeActivityModule(new MainHomeActivityModule(getActivity()))
                .componentInterFace(MyApplicationClass.get(getActivity()).getComponent())
                .build();
        return root ;

    }


}
