package com.example.ritu.project404.apis;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ritu on 1/14/2018.
 */

public class RetroClient {
    /********
     * URLS
     *******/
    /***

    private static final String ROOT_URL = "https://api.myjson.com/bins/";
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
loggin(HttpLoggingInterceptor.Level.BODY);

    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
// add your other interceptors …

// add logging as last interceptor
httpClient.(logging);  // <-- this is the important line!


    /**
     * Get Retrofit Instance
     */
    /**
    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    /**
     * Get API Service
     *
     * @return API Service
     */
    /**
    public static ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);

    }

    **/


    private static RetroClient retrofitAPI;
    private static Retrofit retrofit;

    private RetroClient() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .registerTypeAdapterFactory(new ItemTypeAdapterFactory())
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
// httpClient.addInterceptor(new Interceptor() {
// @Override
// public Response intercept(Interceptor.Chain chain) throws IOException {
// Request original = chain.request();
//
// // Request customization: add request headers
// Request.Builder requestBuilder = original.newBuilder()
// .header("Authorization", "auth-value");
//
// Request request = requestBuilder.build();
// return chain.proceed(request);
// }
// });

// HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
// interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
// httpClient.addInterceptor(interceptor);

        httpClient.connectTimeout(60, TimeUnit.SECONDS);
        httpClient.readTimeout(60, TimeUnit.SECONDS);
        httpClient.addInterceptor(interceptor);

        final OkHttpClient okHttpClient = httpClient.build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.myjson.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }


    public static ApiService getApi() {
        if (retrofitAPI == null) {
            getInstance();
        }
        return retrofit.create(ApiService.class);
    }

    public static RetroClient getInstance() {
        if (retrofitAPI == null) {
            if (retrofitAPI == null) {
                retrofitAPI = new RetroClient();
            }
        }
        return retrofitAPI;
    }
}
