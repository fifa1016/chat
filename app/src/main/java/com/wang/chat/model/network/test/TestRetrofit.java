package com.wang.chat.model.network.test;

import android.util.Log;

import com.wang.chat.model.RetrofitGenerator;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

/**
 * Created by wang on 16-7-4.
 */
public class TestRetrofit {
    private static final String TAG = "TestRetrofit";

    public static void syncTest() {
        Log.d(TAG, "testRetrofit: ");
//        GitHubService client = RetrofitGenerator.createService(GitHubService.class);
//        Call<List<Contributor>> call =
//                client.contributors("square", "retrofit");
//
//        //sync: in new thread
//        try {
//            Response<List<Contributor>> response = call.execute();
//
//            if (response != null && !response.isSuccessful() && response.errorBody() != null) {
//                //handle error here
//            }
//
//            List<Contributor> contributorList = response.body();
//            Log.d(TAG, "testRetrofit: size=" + contributorList);
//
//            for (Contributor ct : contributorList) {
//                Log.d(TAG, "-- " + ct.login + ": " + ct.contributions);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    //async, can use in Main Thread
    public static void asyncTest() {
        GitHubService gitHubService = RetrofitGenerator.createService(GitHubService.class);
        Subscription subscription = gitHubService
                .contributors("square", "retrofit")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Contributor>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onNext(List<Contributor> list) {
                        Log.d(TAG, "onNext: ");
                        for (Contributor ct : list) {
                            Log.d(TAG, "-- " + ct.login + ": " + ct.contributions);
                        }
                    }
                });

//        call.enqueue(new Callback<List<Contributor>>() {
//            @Override
//            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
//                if (response != null && !response.isSuccessful() && response.errorBody() != null) {
//                    //handle error here
//                } else {
//                    List<Contributor> list = response.body();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Contributor>> call, Throwable t) {
//
//            }
//        });
    }

    public static void test(){
        Observable.defer(() -> Observable.just("test"));
    }
}
