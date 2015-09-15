package com.wang.chat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.wang.chat.R;
import com.wang.chatlib.presenter.AboutPresenter;
import com.wang.chatlib.util.AppUtil;

/**
 * Created by shawn on 9/9/15.
 */
public class AboutFragment extends BaseFragment<AboutPresenter.AboutUiCallbacks> {

    private AboutPresenter.AboutUiCallbacks mCallbacks;

    public void setCallbacks(AboutPresenter.AboutUiCallbacks callbacks) {
        mCallbacks = callbacks;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Bind(R.id.about_version)
    TextView mTxtVersion;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mTxtVersion.setText(AppUtil.getVersionName(getActivity()));
    }
}
