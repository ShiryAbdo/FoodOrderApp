package com.shimoo.foodorderapp.screens.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shimoo.foodorderapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class InfoFragment extends Fragment {
    @BindView(R.id.Contact) TextView Contact;
    @BindView(R.id.linkedin_button) ImageView linkedin_button;
    @BindView(R.id.github_button) ImageView github_button;
    @BindView(R.id.gmail_button) ImageView gmail_button;





    View rooteView ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //  the layout for this fragment
        rooteView = inflater.inflate(R.layout.profile_main, container, false);
        ButterKnife.bind(this,rooteView);
        Contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        linkedin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        gmail_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        github_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return  rooteView ;
    }


}
