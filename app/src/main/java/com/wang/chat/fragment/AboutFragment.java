package com.wang.chat.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wang.chat.ChatApplication;
import com.wang.chat.R;
import com.wang.chat.presenter.AboutPresenter;

/**
 * Created by shawn on 9/9/15.
 */
public class AboutFragment extends BaseFragment<AboutPresenter.AboutUiCallbacks>{

    private AboutPresenter.AboutUiCallbacks mCallbacks;

    public void setCallbacks(AboutPresenter.AboutUiCallbacks callbacks) {
        mCallbacks = callbacks;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }




}
