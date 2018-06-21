package com.example.ritu.project404.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;


import com.example.ritu.project404.R;
import com.example.ritu.project404.adapter.CustomAdapter;
import com.example.ritu.project404.adapter.CustomAdapterGrid;
import com.example.ritu.project404.apis.ApiService;
import com.example.ritu.project404.apis.RetroClient;
import com.example.ritu.project404.model.Contact;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ritu on 1/11/2018.
 */

public class GridFragment extends Fragment {
    private CustomAdapterGrid adapter;
    private List<Contact> ContactActivityList;
    private View parentView;


    public GridFragment(){

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_gridview,container,false);
        final GridView gridView = (GridView) rootView.findViewById(R.id.gridView);
        ContactActivityList = new ArrayList<>();

        Toast toast = Toast.makeText(getActivity().getApplicationContext(), "hello", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(cm.getActiveNetworkInfo()!= null){
            ApiService api = RetroClient.getApi();
            Call<List<Contact>> call = api.getMyJSON();


            call.enqueue(new Callback<List<Contact>>() {
                @Override
                public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                    List<Contact> list = response.body();
                    if(list!= null){
                        ContactActivityList.addAll(list);
                    }

                    adapter = new CustomAdapterGrid(getContext(),ContactActivityList);
                    gridView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<List<Contact>> call, Throwable t) {
                    Log.e("GridFragment","onFailure");
                }
            });
        }
        else
        {
            Snackbar.make(parentView, "Internet Connection not available", Snackbar.LENGTH_LONG).show();
        }

        parentView = rootView.findViewById(R.id.parentLayout);
        //returning our layout file
        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("GridView");

    }

}
