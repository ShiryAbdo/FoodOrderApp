package com.shimoo.foodorderapp.screens.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shimoo.foodorderapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BurgerFragment extends Fragment {


    public BurgerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_burger, container, false);
    }

}
