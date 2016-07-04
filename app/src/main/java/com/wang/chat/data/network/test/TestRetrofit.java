package com.wang.chat.data.network.test;

import android.util.Log;

import com.wang.chat.data.RetrofitGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wang on 16-7-4.
 */
public class TestRetrofit {
    private static final String TAG = "TestRetrofit";

    public static void syncTest() {
        Log.d(TAG, "testRetrofit: ");
        GitHubClient client = RetrofitGenerator.createService(GitHubClient.class);
        Call<List<Contributor>> call =
                client.contributors("square", "retrofit");

        //sync: in new thread
        try {
            Response<List<Contributor>> response = call.execute();

            if (response != null && !response.isSuccessful() && response.errorBody() != null) {
                //handle error here
            }

            List<Contributor> contributorList = response.body();
            Log.d(TAG, "testRetrofit: size=" + contributorList);

            for (Contributor ct : contributorList) {
                Log.d(TAG, "-- " + ct.login + ": " + ct.contributions);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //async, can use in Main Thread
    public static void asyncTest(){
        GitHubClient client = RetrofitGenerator.createService(GitHubClient.class);
        Call<List<Contributor>> call =
                client.contributors("square", "retrofit");

        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                if( response != null && !response.isSuccessful() && response.errorBody() != null ){
                    //handle error here
                }else{
                    List<Contributor> list = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable t) {

            }
        });
    }
}
