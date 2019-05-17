package com.bookwego.reservationsActivity.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bookwego.R;
import com.bookwego.reservationsActivity.adapters.UpcomingAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpComingFragment extends Fragment {

    View view;

    @BindView(R.id.upcoming_recyclerview)
    RecyclerView upcoming_recyclerview;
    UpcomingAdapter upcomingAdapter;


    public UpComingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_up_coming, container, false);
        ButterKnife.bind(this, view);


        Initialization();
        EventListner();
        return view;
    }


    private void Initialization() {

        upcomingAdapter = new UpcomingAdapter(getActivity());
        upcoming_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        upcoming_recyclerview.setAdapter(upcomingAdapter);

    }

    private void EventListner() {
    }

}
