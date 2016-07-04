package com.wang.chat.data;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wang on 16-7-4.
 */
public class RetrofitGenerator {
    public static final String API_BASE_URL = "http://api.github.com";

    private static OkHttpClient httpClient = new OkHttpClient.Builder().build();
    private static Retrofit.Builder retrofitBuilder
            = new Retrofit.Builder().baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = retrofitBuilder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }
}
