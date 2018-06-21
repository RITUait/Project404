package com.example.ritu.project404.apis;

import com.example.ritu.project404.model.Contact;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ritu on 1/14/2018.
 */

public interface ApiService {
    /*
   Retrofit get annotation with our URL
   And our method that will return us the List of ContactList
   */
    @GET("/bins/cj2br")
    Call<List<Contact>> getMyJSON();

//    @GET("/bins/cj2br")
//    Call<List<Add>> getMyJSON();
}
