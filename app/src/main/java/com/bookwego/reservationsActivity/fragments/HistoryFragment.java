package com.bookwego.reservationsActivity.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bookwego.R;
import com.bookwego.reservationsActivity.adapters.HistoryAdapter;
import com.bookwego.reservationsActivity.adapters.UpcomingAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    View view;
    @BindView(R.id.history_recyclerview)
    RecyclerView history_recyclerview;
    HistoryAdapter historyAdapter;


    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this, view);


        Initialization();
        return view;
    }

    private void Initialization() {
        historyAdapter = new HistoryAdapter(getActivity());
        history_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        history_recyclerview.setAdapter(historyAdapter);


    }

}
