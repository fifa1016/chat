package com.wang.chat.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import com.wang.chat.R;
import com.wang.chat.activity.BaseActivity;
import com.wang.chat.presenter.BasePresenter;
import com.wang.chat.presenter.BaseUiPresenter;

/**
 * Set user's name and image.
 */
public abstract class BaseFragment<UC> extends Fragment implements BaseUiPresenter.Ui<UC>{
    private static final String TAG = "AccountFragment";

    private BaseUiPresenter mPresenter;

    public BaseUiPresenter getPresenter() {
        if (mPresenter == null) {
            mPresenter = ((BaseActivity) getActivity()).getPresenter();
        }
        return mPresenter;
    }

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onButtonPressed(int resId) {
        Log.d(TAG, "button pressed: account set ");
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().attachUi(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPresenter().detachUi(this);
    }

}
