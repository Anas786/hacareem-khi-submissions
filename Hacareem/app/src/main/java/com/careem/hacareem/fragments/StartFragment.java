package com.careem.hacareem.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.careem.hacareem.R;
import com.careem.hacareem.utils.Helper;

/**
 * Created by anas__000 on 30-Apr-17.
 */
public class StartFragment extends Fragment {

    Button btnRideNow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_start, container, false);
        btnRideNow = (Button) rootView.findViewById(R.id.btn_rideNow);
        btnRideNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.showSpinner("Finding nearby Captain",getContext());
                FragmentManager fm;
                fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                DetailFragment myFragment = new DetailFragment();
                ft.replace(R.id.mainLayout, myFragment);
                ft.commit();
            }
        });
        return rootView;
    }
}