package com.wang.chat.fragment;

import android.app.Fragment;
import com.wang.chat.activity.BaseActivity;
import com.wang.chatlib.presenter.BaseUiPresenter;

/**
 *
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
