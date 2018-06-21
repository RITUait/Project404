package com.example.ritu.project404.fragment;


import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ritu.project404.apis.ApiService;
import com.example.ritu.project404.model.Contact;
import com.example.ritu.project404.adapter.CustomAdapter;
import com.example.ritu.project404.apis.RetroClient;
import com.example.ritu.project404.R;
import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ritu on 1/11/2018.
 */

public class ListFragment extends Fragment {
    private ListView listView;
    private View parentView;
    private List<Contact> contactActivityList;
    private CustomAdapter adapter;


    //when you - for instance, fill some lists from the adapter, then you should do it in the onActivityCreated method
    // as well as restoring the view state when setRetainInstance used to do so.
    /**@Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    }
     **/

    /**
     * public void onCreate(Bundle savedInstanceState) {
     * super.onCreate(savedInstanceState);
     * // ...
     * if (savedInstanceState == null) {
     * FragmentManager fragmentManager = getFragmentManager();
     * // Or: FragmentManager fragmentManager = getSupportFragmentManager()
     * FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
     * ListFragment fragment = new ListFragment();
     * fragmentTransaction.add(R.id.nav_listView, fragment);
     * fragmentTransaction.commit();
     * <p>
     * }
     * }
     **/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.fragment_listview);
        View view = inflater.inflate(R.layout.fragment_listview, container, false);
        listView = (ListView) view.findViewById(R.id.listView);
        contactActivityList = new ArrayList<>();
        SugarContext.init(getContext());
        // Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        // AppCompatActivity activity = (AppCompatActivity) getActivity();
        // activity.setSupportActionBar(toolbar);
        //  (nhi chahiye )setSupportActionBar(toolbar);

        /**
         * Array List for Binding Data from JSON to this List
         */
        //contactActivityList = new ArrayList<>();
        //parentView = view.findViewById(R.id.parentLayout);


        /**
         * Getting List and Setting List Adapter
         */
        //listView = (ListView) view.findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Snackbar.make(parentView, contactActivityList.get(position).getName() + " => " + contactActivityList.get(position).getMobile(), Snackbar.LENGTH_LONG).show();
            }
        });

        /**
         * Just to know onClick and Printing Hello Toast in Center.
         */
        Toast toast = Toast.makeText(getActivity().getApplicationContext(), "hello", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        FloatingActionButton fab1 = (FloatingActionButton) view.findViewById(R.id.fab1);
        assert fab1 != null;
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(@NonNull final View view) {

                /**
                 * Checking Internet Connection
                 */
                ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                if (cm.getActiveNetworkInfo() != null) {
                    //if (InternetConnection.checkConnection(getActivity().getApplicationContext())) {
                    // final ProgressDialog dialog;
                    /**
                     * Progress Dialog for User Interaction
                     */
                    // dialog = new ProgressDialog(ListFragment.this);
                    // dialog.setTitle(getString(R.string.string_getting_json_title));
                    // dialog.setMessage(getString(R.string.string_getting_json_message));
                    // dialog.show();

                    //Creating an object of our api interface
                    ApiService api = RetroClient.getApi();
                    Log.e("RetroClient", "hii" + api);

                    /**
                     * Calling JSON
                     */
                    Call<List<Contact>> call = api.getMyJSON();
                    Log.e("api.getMyJSON", "hii" + call);

                    /**
                     * Enqueue Callback will be call when get response...
                     */
                    call.enqueue(new Callback<List<Contact>>() {
                        @Override
                        public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                            //Dismiss Dialog
                            // dialog.dismiss();
                            Log.e("inside onResponse", "response" + response);
                            Log.e("inside onResponse", "call" + call);
//                                if (response.isSuccessful()) {
                            /**
                             * Got Successfully
                             */
                            Log.e("response ", "successful");
                            List<Contact> d = response.body();
                            Log.e("printing d","ritu"+d);
                            if (d != null) {
                                contactActivityList.addAll(d);
                            }
                            Log.e("listview_fragment", "hii" + contactActivityList);
                            //
                            Contact.saveInTx(contactActivityList);
                            List<Contact> data = Contact.listAll(Contact.class);
                            Log.e("SugarORM","ritu"+data);

                            /**
                             * Binding that List to Adapter
                             */
                            adapter = new CustomAdapter(getContext(), contactActivityList);
                            listView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

//                                } else {
//                                    Snackbar.make(parentView, "Oops ! something went wrong", Snackbar.LENGTH_LONG).show();
//                                }
                        }

                        @Override
                        public void onFailure(Call<List<Contact>> call, Throwable t) {
                            // dialog.dismiss();
                            Log.e("ListFragment", "onFailure");
                        }
                    });

                    // }
                } else {
                    Snackbar.make(parentView, "Internet connection not available", Snackbar.LENGTH_LONG).show();
                }
            }
        });
        return view;
        // return inflater.inflate(R.layout.fragment_listview, container, false);
    }


    /**
     * @Nullable
     * @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     * //returning our layout file
     * return inflater.inflate(R.layout.fragment_listview, container, false);
     * }
     **/

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("ListView");
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        contactActivityList = new ArrayList<>();
        parentView = view.findViewById(R.id.parentLayout);
        //listView = (ListView) view.findViewById(R.id.listView);

    }

}
