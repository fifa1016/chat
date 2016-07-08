package com.wang.chat.view.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wang.chat.activity.Display;

/**
 *
 */
public abstract class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";
    private Display display;

    public void setDisplay(Display display) {
        this.display = display;
    }

    public Display getDisplay() {
        return display;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getViewLayout(), container, false);
    }

    public abstract int getViewLayout();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    public abstract void initViews(View view);
}
