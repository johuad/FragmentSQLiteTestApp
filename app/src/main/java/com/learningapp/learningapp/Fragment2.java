package com.learningapp.learningapp;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Fragment2 extends Fragment {

    protected View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_2, container, false);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());
        ArrayList<Person> people = dataBaseHelper.getAll();

        // Initialize RecyclerView.
        MyFragmentViewAdapter fragmentViewAdapter = new MyFragmentViewAdapter(people);
        RecyclerView fragmentRecyclerView = view.findViewById(R.id.infoDisplay);
        fragmentRecyclerView.setAdapter(fragmentViewAdapter);
        fragmentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

}