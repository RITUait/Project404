package com.example.ritu.project404.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ritu.project404.R;
import com.example.ritu.project404.adapter.CustomAdapterRecycler;
import com.example.ritu.project404.model.Contact;

import java.util.List;

/**
 * Created by ritu on 1/11/2018.
 */

public class RecyclerFragment extends Fragment {

    private RecyclerView mrecyclerView;
    List<Contact> data = Contact.listAll(Contact.class);


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        Log.e("recyclerFragment","ritu"+data);
        View rootView = inflater.inflate(R.layout.fragment_recyclerview,container,false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        CustomAdapterRecycler customAdapterRecycler = new CustomAdapterRecycler(getContext(), data);
        recyclerView.setAdapter(customAdapterRecycler); // set the Adapter to RecyclerView
        //return inflater.inflate(R.layout.fragment_recyclerview, container, false);
        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("RecycleView");
    }
}
