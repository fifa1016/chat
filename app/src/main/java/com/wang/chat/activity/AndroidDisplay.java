package com.wang.chat.activity;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by wang on 16-6-21.
 */
public class AndroidDisplay implements Display {

    private AppCompatActivity activity;

    public AndroidDisplay(AppCompatActivity appCompatActivity) {
        this.activity = appCompatActivity;
    }

    @Override
    public void showAccount() {

    }
}
